package karakter;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Scanner;

public class FileOperations implements FileShell {


    Person person;
    int studentID;
    String password;

    public FileOperations(Person person) {
        this.person = person;
    }

    @Override
    public void saveUserData(int studentID, String password, List<Course> grades) {
        
        try {
            FileWriter writeFile = new FileWriter("userdata.txt", true);
            BufferedWriter output = new BufferedWriter(writeFile);
            output.write(studentID+";"+password+";"+grades+"\n");
            output.close();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Finner ikke filen");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    @Override
    public List<Course> readUserData(int studentID, String password) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static void main(String[] args) {
        Person person = new Person("Atle Tøge", 913732, "atletø@stud.ntnu.no", "Test1234!", "Test1234!");
        person.addGrade("TTM4100", 'B');
        person.addGrade("TTM4102", 'C');
        FileOperations fil = new FileOperations(person);
        fil.saveUserData(person.getStudentID(), person.getPassword(), person.getGrades());
        
    }

    @Override
    public List<String> readLoginData(Person person) {
        
        return null;
    }
}
