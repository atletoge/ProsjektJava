package karakter;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileOperations implements FileShell {


    Person person;
    int studentID;
    String password;
    String oldGrades;

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
    public List<Course> readUserData(Person person) {
        List<Course> courses = new ArrayList<>();
        //System.out.println(
        String replacedString = oldGrades.replace("[", "").replace("]","").replace("\t","").replace(" ", "");
        String[] gradeArray = replacedString.split(",");
        System.out.println(gradeArray);
        List<String> gradeList = Arrays.asList(gradeArray);
        for (int i = 0; i < gradeList.size(); i+=2) {
            //courses.add(person.addGrade(gradeList.get(i), gradeList.get(i+1).charAt(0));)
            String course = gradeList.get(i);
            char grade = gradeList.get(i+1).charAt(0);
            person.addGrade(course, grade);
        } System.out.println(person.getGrades());
        
        return person.getGrades();
    }
    
    public static void main(String[] args) {
        // Person person = new Person("Atle Tøge", 913792, "atletø@stud.ntnu.no", "Test1234!", "Test1234!");
        // person.addGrade("TTM4100", 'B');
        // person.addGrade("TTM4102", 'C');
        // FileOperations fil = new FileOperations(person);
        // fil.saveUserData(person.getStudentID(), person.getPassword(), person.getGrades());
        Person person2 = new Person(913792, "Test1234!");
        FileOperations fil2 = new FileOperations(person2);
        System.out.println(fil2.validateLoginData(person2));
        fil2.readUserData(person2); 
        
    }

    
    public boolean validateLoginData(Person person) {
        try {
            Scanner s = new Scanner(new FileReader("userdata.txt"));
            while(s.hasNextLine()) {
                List<String> liste = new ArrayList();
                String line = s.nextLine();
                String[] liste1 = line.split(";");
                for (String string : liste1) {
                    liste.add(string);
                }
                if((Integer.parseInt(liste.get(0)) == this.person.getStudentID()) && liste.get(1).equals(this.person.getPassword())) {
                    this.oldGrades = liste.get(2);
                    return true;
                }
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
        throw new IllegalArgumentException("Brukeren eksisterer ikke, sjekk innlogging eller registrer deg.");
    }
}
