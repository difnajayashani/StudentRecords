package hsenid.com.repository;

import hsenid.com.domain.StudentData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsenid on 7/4/17.
 */
public class StudentDataRepositoryImpl {
    StudentData student=new StudentData();



    //database integration


    //add a new StudentData document
    public int addStudent(StudentData student){
        int document=0;

        return document;
    }

    //delete a StudentData document
    public int deleteStudent(int student_id){
        int doument= 1;

        return doument;
    }

    //update a StudentData document data
    public int updateStudent(StudentData student){
        int count=1;
        return count;
    }

    //retrieve details of a given student
    public  StudentData retrieveStudentDetail(int student_id){

        //retrieve the corresponding StudentData document
        StudentData student=new StudentData();
        return  student;
    }

    //retrieve all Student details
    public List<StudentData> retrieveAllDetails(){

        //select query to get the student list from db

        List<StudentData> studentData=new ArrayList<StudentData>();

        //for loop to put each document to StudentData object


        return studentData;
    }

}
