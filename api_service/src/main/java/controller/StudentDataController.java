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
public class StudentDataController {


    @Autowired
    StudentDataRepositoryImpl studentRepository;

    //-------------------Retrieve All Users--------------------------------------------------------

    @RequestMapping(value = "/students/", method = RequestMethod.GET)
    public ResponseEntity<List<StudentData>> listAllUsers() {
        List<StudentData> users = studentRepository.retrieveAllDetails();
        if(users.isEmpty()){
            return new ResponseEntity<List<StudentData>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<StudentData>>(users, HttpStatus.OK);
    }


    //-------------------Retrieve Single User--------------------------------------------------------

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
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

    //-------------------Create a User--------------------------------------------------------

/*    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody StudentData student, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getName());

        if (studentRepository.isUserExist(user)) {
            System.out.println("A User with name " + user.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }*/


}
