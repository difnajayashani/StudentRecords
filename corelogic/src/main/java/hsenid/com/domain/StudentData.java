package hsenid.com.domain;

/**
 * StudentData domain Class
 */
public class StudentData {
    private int student_id;
    private String name;
    private String age;
    private int marksSubjectA;
    private int marksSubjectB;


    public StudentData(){

    }

    //for update method
    public StudentData(int student_id, String name,String age, int marksSubjectA, int marksSubjectB){

        this.student_id = student_id;
        this.name = name;
        this.age = age;
        this.marksSubjectA = marksSubjectA;
        this.marksSubjectB = marksSubjectB;
    }

    public int getStudent_id() {
        return student_id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public int getMarksSubjectA() {
        return marksSubjectA;
    }

    public int getMarksSubjectB() {
        return marksSubjectB;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Name='" + name + '\'' +
                ", Student Id='" + student_id + '\'' +
                ", Student Age='" + age + '\'' +
                ", Subject A Marks'" + marksSubjectA + '\'' +
                ", Subject B Marks" + marksSubjectB + '\'' +
                '}';
    }
}
