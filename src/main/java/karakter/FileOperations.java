package karakter;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;

public class FileOperations implements FileShell {


    private Person person;
    private String oldGrades;

    public FileOperations(Person person) {
            this.person = person;
        
        
    }

    public Person getPerson() {
        return this.person;
    }


    @Override
    public void saveUserData(Person person) {
        try {
            // Brukeren eksisterer allerede så vi må trikse litt og skrive over filen før vi legger til de nye dataene
            if(this.validateLoginData(person)) {
                RandomAccessFile tempFile = new RandomAccessFile("userdata.txt", "rw");
                String remove;
                String tempString = "";
                while ((remove = tempFile.readLine()) != null) {
                    if(remove.startsWith(String.valueOf(person.getStudentID()))) {
                        continue;
                    }
                    tempString += remove+"\n";
                }
                BufferedWriter appender = new BufferedWriter(new FileWriter("userdata.txt"));
                appender.write(tempString);
                tempFile.close();
                appender.close(); 
            }
            FileWriter writeFile = new FileWriter("userdata.txt", true);
            BufferedWriter output = new BufferedWriter(writeFile);
            output.write(person.getStudentID()+";"+person.getPassword()+";"+person.getGrades()+"\n");
            output.close();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Finner ikke filen");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public List<Course> readUserData(Person person) {
        List<Course> courses = new ArrayList<>();
        String replacedString = oldGrades.replace("[", "").replace("]","").replace("\t","").replace(" ", "");
        String[] gradeArray = replacedString.split(",");
        List<String> gradeList = Arrays.asList(gradeArray);
        if(gradeList.size() > 1) { // Om det allerede er noe i listen som ble lest fra fil i validateLoginData, så må dette populeres på nytt og gjøres her.
            for (int i = 0; i < gradeList.size(); i+=2) {
                String course = gradeList.get(i);
                char grade = gradeList.get(i+1).charAt(0);
                person.addGrade(course, grade);
            } 
        }
         //System.out.println(person.getGrades());
        
        return person.getGrades();
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
                    // Sjekker om det som ble lest fra fil av studentID og PW samsvarer med det inputen i appen er
                    this.oldGrades = liste.get(2);
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } return false;
        
    }
}
