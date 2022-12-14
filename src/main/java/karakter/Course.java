package karakter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Course {
    
    private String courseCode;
    private char grade;

    public Course(String courseCode, char grade){
        setCourseCode(courseCode);
        setGrade(grade);
    }


    private boolean validateCourseCode(String courseCode) {
        if(courseCode.matches("^([A-Z]{3}[0-9]{4})")) {
            return true;
        } throw new IllegalArgumentException("Ugyldig fagkode, må være på formen AAA0000");
    }

    private void setCourseCode(String courseCode) {
        if(validateCourseCode(courseCode)) {
            this.courseCode = courseCode;
        }
    }

    public String getCourseCode() {
        return courseCode;
    }

    private boolean validateGrade(char grade) {
        String valid = "ABCDEF";
        if(valid.contains(String.valueOf(grade))) {
            return true;
        } throw new IllegalArgumentException("Grade is not valid, must be A-F ");
    }
    private void setGrade(char grade) {
        validateGrade(grade);
        this.grade = grade;
    }

    public char getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return courseCode+",\t\t"+grade;
    }
}  
