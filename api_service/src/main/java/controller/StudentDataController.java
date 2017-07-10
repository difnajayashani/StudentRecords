package controller;

import hsenid.com.domain.StudentData;
import hsenid.com.repository.StudentDataRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    StudentDataRepositoryImpl studentRepository;

    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value = "/viewAll/", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<List<StudentData>> listAllUsers() {
        List<StudentData> students = studentRepository.retrieveAllDetails();
        
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setStatus("success");
        successMessage.setCode(HttpStatus.OK.value());
            
        if(students.isEmpty()){
             successMessage.setMessage("No records found.");
        }else{
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
        return new ResponseEntity<List<StudentData>>( successMessage, HttpStatus.OK);
    }


    //-------------------Retrieve Single User--------------------------------------------------------

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<StudentData>> getUser(@PathVariable("id") int id) {
        System.out.println("Fetching Student with id " + id);
        StudentData student = studentRepository.retrieveStudentDetail(id);
        if (student == null) {
            System.out.println("Student with id " + id + " not found");
            return new ResponseEntity<List<StudentData>>(HttpStatus.NOT_FOUND);

        }
        //return new ResponseEntity<StudentData>(student, HttpStatus.OK);
        return null;
    }

    //-------------------Create a Student--------------------------------------------------------

   @RequestMapping(value = "/insertOne/", method = RequestMethod.POST,, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<Void> insertStudent(@RequestBody StudentData student, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + student.getName());
        
         boolean status = studentRepository.addStudent(student);
         SuccessMessage successMessage = new SuccessMessage();
        
          if (status) {
            successMessage.setStatus("success");
            successMessage.setCode(HttpStatus.CREATED.value());
            successMessage.setMessage("new student added.");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("student_id", students.getStudent_id());
            jsonObject.put("name", students.getName());
            jsonObject.put("age", students.getAge());
            jsonObject.put("marksSubjectA", students.getMarksSubjectA());
            jsonObject.put("marksSubjectB", students.getMarksSubjectB());
            successMessage.addData(jsonObject);
          }
            return new ResponseEntity<>(successMessage, HttpStatus.CREATED);

        /*if (studentRepository.isUserExist(user)) {
            System.out.println("A User with name " + user.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.saveUser(user);*/
    }
        
        
    //-------------------Delete A Student--------------------------------------------------------
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = {"application/json"})
    public ResponseEntity<Object> deleteStudent(@PathVariable("id") String studentId) {
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
        }
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }
        
  //------------------Update A Student--------------------------------------------------------
    @RequestMapping(method = RequestMethod.PUT, produces = {"application/json"}, consumes = {"application/json"})
    public ResponseEntity<Object> updateStudent(@RequestBody Student student) {
        int affecteRows = studentRepository.updateStudent(student);
        SuccessMessage successMessage = new SuccessMessage();
        successMessage.setStatus("success");
        successMessage.setCode(HttpStatus.OK.value());
        if(affecteRows>0){
            successMessage.setMessage("student record updated.");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userName", student.getUserName());
            successMessage.addData(jsonObject);
        }else{
            successMessage.setMessage("no matching records found to update.");
        }
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
}


}
