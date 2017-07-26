package controller;

import hsenid.com.domain.StudentData;
import hsenid.com.repository.StudentDataRepositoryImpl;
import models.SuccessMessage;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * Created by hsenid on 7/6/17.
 */

@RestController
@RequestMapping(value = "/student")
public class StudentDataController {


    @Autowired
    private  StudentDataRepositoryImpl studentRepository;

    //-------------------Retrieve All Users--------------------------------------------------------

//    @RequestMapping(value = "/viewAll", method = RequestMethod.GET, produces = {"application/json"})
    @GetMapping("/viewAll")
    public ResponseEntity<List<StudentData>> listAllUsers() {
        List<StudentData> students = studentRepository.retrieveAllDetails();

        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setStatus("success");
        successMessage.setCode(HttpStatus.OK.value());

        if (students.isEmpty()) {
            successMessage.setMessage("No records found.");
        } else {
            successMessage.setMessage(students.size() + " records found.");
            for (int i = 0; i < students.size(); i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("student_id", students.get(i).getStudent_id());
                jsonObject.put("name", students.get(i).getName());
                jsonObject.put("age", students.get(i).getAge());
                jsonObject.put("marksSubjectA", students.get(i).getMarksSubjectA());
                jsonObject.put("marksSubjectB", students.get(i).getMarksSubjectB());

                successMessage.addData(jsonObject);
            }
            return new ResponseEntity<List<StudentData>>((List<StudentData>) successMessage, HttpStatus.OK);

        }
        return new ResponseEntity<List<StudentData>>(HttpStatus.NO_CONTENT);
    }


    //-------------------Retrieve Single User--------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<StudentData> getUser(@PathVariable("id") int id){

            System.out.println("Fetching Student with id " + id);
            StudentData student = studentRepository.retrieveStudentDetail(id);
            SuccessMessage successMessage = new SuccessMessage();
            successMessage.setStatus("success");
            successMessage.setCode(HttpStatus.OK.value());

            if (student == null) {
                System.out.println("Student with id " + id + " not found");
                successMessage.setMessage("no matching records found to delete.");
                return new ResponseEntity<StudentData>(HttpStatus.NOT_FOUND);

            }else {
                successMessage.setMessage("student record is retrieved.");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("student", student);
                successMessage.addData(jsonObject);


            }
            return new ResponseEntity<StudentData>(student, HttpStatus.OK);

        }



    //-------------------Create a Student--------------------------------------------------------

  // @RequestMapping(value = "/insert/", method = RequestMethod.POST, produces = {"application/json"}, consumes = {"application/json"})
    @PostMapping("/insert")
    public ResponseEntity<Object> insertStudent( @RequestBody StudentData student) {
        System.out.println("Creating User " + student.getName());
        
         boolean status = studentRepository.addStudent(student);
         SuccessMessage successMessage = new SuccessMessage();
        
          if (status) {
            successMessage.setStatus("success");
            successMessage.setCode(HttpStatus.CREATED.value());
            successMessage.setMessage("new student added.");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("student_id", student.getStudent_id());
            jsonObject.put("name", student.getName());
            jsonObject.put("age", student.getAge());
            jsonObject.put("marksSubjectA", student.getMarksSubjectA());
            jsonObject.put("marksSubjectB", student.getMarksSubjectB());
            successMessage.addData(jsonObject);
          }
            return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }
        
        
    //-------------------Delete A Student--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
    public ResponseEntity<StudentData> deleteStudent(@PathVariable("id") int studentId) {

        int affectedRows = studentRepository.deleteStudent(studentId);
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setStatus("success");
        successMessage.setCode(HttpStatus.OK.value());
        if (affectedRows > 0) {
            successMessage.setMessage("student record deleted.");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("student_id", studentId);
            successMessage.addData(jsonObject);
        } else {
            successMessage.setMessage("no matching records found to delete.");
            return new ResponseEntity<StudentData>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<StudentData>((MultiValueMap<String, String>) successMessage, HttpStatus.OK);
    }
        
  //------------------Update A Student--------------------------------------------------------
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<StudentData> updateStudent(@RequestBody StudentData student) {

        int affecteRows = studentRepository.updateStudent(student);
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setStatus("success");
        successMessage.setCode(HttpStatus.OK.value());
        if(affecteRows>0){
            successMessage.setMessage("student record updated.");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Student id", student.getStudent_id());
            successMessage.addData(jsonObject);
            return new ResponseEntity<StudentData>(HttpStatus.NOT_FOUND);
        }else{
            successMessage.setMessage("no matching records found to update.");
        }
        return new ResponseEntity<StudentData>((MultiValueMap<String, String>) successMessage, HttpStatus.OK);
}


}
