package hsenid.com.repository;

import org.assertj.core.util.Strings;
import com.mongodb.WriteResult;
import hsenid.com.domain.StudentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hsenid on 7/4/17.
 */
@Service
public class StudentDataRepositoryImpl {
    
    @Autowired
    private MongoTemplate mongoTemplate;


    //add a new StudentData document
    //@Override
    public boolean addStudent(StudentData student){
        boolean document=false;
        mongoTemplate.insert(student, "classdata");
        document = true;
        return document;
    }

    //delete a StudentData document
    //@Override
    public int deleteStudent(int student_id){
       
        Query query = new Query();
        query.addCriteria(Criteria.where("student_id").is(student_id));
        WriteResult writeResult = mongoTemplate.remove(query, StudentData.class, "classdata");
        return writeResult.getN();
    }

    //update a StudentData document data
    //@Override
    public int updateStudent(StudentData student){
        Query query = new Query();
        query.addCriteria(Criteria.where(" student_id").is(student.getStudent_id()));
        Update update = new Update();
        if (!Strings.isNullOrEmpty(student.getName())) {
            update.set("name", student.getName());
        }
        if (!Strings.isNullOrEmpty(student.getAge())) {
            update.set("age", student.getAge());
        }
        if (student.getMarksSubjectA() != 0) {
            update.set("marksSubjectA", student.getMarksSubjectA());
        }
        if (student.getMarksSubjectB() != 0) {
            update.set("marksSubjectB", student.getMarksSubjectB());
        }
        WriteResult writeResult = mongoTemplate.updateMulti(query, update, StudentData.class, "classdata");
        return writeResult.getN();
       
    }

    //retrieve details of a given student
   // @Override
    public  StudentData retrieveStudentDetail(int student_id){

        //retrieve the corresponding StudentData document
        StudentData student=new StudentData();
        return  student;
    }

    //retrieve all Student details
    //@Override
    public List<StudentData> retrieveAllDetails(){
        List<StudentData> studentList=new ArrayList<StudentData>();
        studentList = mongoTemplate.findAll(StudentData.class, "classdata");
        return studentList;
    }

}
