package karakter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


public class PersonTest {
    

    @Test
    @DisplayName("Test konstruktør for registrering")
    public void testConstructor() {
        Random rnd = new Random();
        int tilfeldig = Integer.parseInt(5+Integer.toString(rnd.nextInt(99999)));
        Person person = new Person("Ola Nordmann", tilfeldig, "olano@stud.ntnu.no", "Test1234!", "Test1234!");
        assertEquals("Ola Nordmann", person.getName());
        assertEquals(tilfeldig, person.getStudentID());
        assertEquals("olano@stud.ntnu.no", person.getEmail());
        assertEquals("Test1234!", person.getPassword());
        FileOperations fileOperations = new FileOperations(person);
        fileOperations.saveUserData(person);
        assertThrows(IllegalArgumentException.class, () -> {
            Person person1 = new Person("Test Testesen", 234591, "testte@stud.ntnu.no", "Test1234!", "Test1234!");
        }, "Skal throwe her pga brukeren allerede er i systemet med et annet passord");
    }

    @Test
    @DisplayName("Test logg på konstruktør")
    public void testSecondConstructor() {
        Random rnd = new Random();
        int tilfeldig = Integer.parseInt(5+Integer.toString(rnd.nextInt(99999)));
        Person person = new Person("Ola Nordmann", tilfeldig, "olano@stud.ntnu.no", "Test1234!", "Test1234!");
        FileOperations fileOperations = new FileOperations(person);
        fileOperations.saveUserData(person);
        Person person1 = new Person(234691, "Test1234!");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person2 = new Person(234691, "Test12345!");
        }, "Skal throwe når passordet ikke stemmer med det som er lagret");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person3 = new Person(339102, "Test1234!");
        }, "Skal throwe når brukeren aldri har vært registrert før ");
    }
    
    @Test
    @DisplayName("Test navnvalidering")
    public void testName() {
        assertThrows(NullPointerException.class, () -> {
            Person person2 = new Person(null, 234591, "olano@stud.ntnu.no", "Test1234!", "Test1234!");
        }, "Skal få error her siden navn er tomt");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person3 = new Person("A", 234591, "olano@stud.ntnu.no", "Test1234!", "Test1234!");
        }, "Skal få error her siden navnet er under to tegn");
    }

    @Test
    @DisplayName("Test epostvalidering")
    public void testEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Person("Ola Nordmann", 234591, "olano@stud@.ntnu.no", "Test1234!", "Test1234!");
        }, "Må inneholde akkurat 1 alfakrøll");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person1 = new Person("Ola Nordmann", 234591, "olanor@stud.ntnu.no", "Test1234!", "Test1234!");
        }, "Eposten må være på formen olano@stud.ntnu.no");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person2 = new Person("Ola Nordmann", 234591, "olano@studd.ntnu.no", "Test1234!", "Test1234!");
        }, "Eposten må være på formen olano@stud.ntnu.no");
        assertThrows(NullPointerException.class, () -> {
            Person person3 = new Person("Ola Nordmann", 234591, null, "Test1234!", "Test1234!");
        }, "Eposten skal ikke kunne være tom");
    }

    @Test
    @DisplayName("Test studentid-validering")
    public void testStudentID() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Person("Ola Nordmann", 2345911, "olano@stud.ntnu.no", "Test1234!", "Test1234!");
        }, "StudentID må være 6 siffer");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person1 = new Person("Ola Nordmann", 23459, "olano@stud.ntnu.no", "Test1234!", "Test1234!");
        }, "StudentID må være 6 siffer");
    }

    @Test
    @DisplayName("Test passord-validering")
    public void testPassword() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Person("Ola Nordmann", 234591, "olano@stud.ntnu.no", "Test1234!", "Test12345!");
        }, "Passordene er ikke like");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person1 = new Person("Ola Nordmann", 234591, "olano@stud.ntnu.no", "Test12345", "Test12345");
        }, "Passordet må inneholde minst 1 spesialtegn");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person2 = new Person("Ola Nordmann", 234591, "olano@stud.ntnu.no", "Test12!", "Test12!");
        }, "Passordet må være minst 8 tegn");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person3 = new Person("Ola Nordmann", 234591, "olano@stud.ntnu.no", "test12345!", "test12345!");
        }, "Passordet må inneholde minst 1 stor og en liten bokstav");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person4 = new Person("Ola Nordmann", 234591, "olano@stud.ntnu.no", "Testen!!!", "Testen!!!");
        }, "Passordet må inneholde minst 1 tall");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person5 = new Person("Ola Nordmann", 234591, "olano@stud.ntnu.no", "Test12345!!!2222221111111", "Test12345!!!2222221111111");
        }, "Passordet kan ikke overstige 20 tegn");

    }

    @Test
    @DisplayName("Testing av å legge til fag")
    public void testAddGrade() {
        Random rnd = new Random();
        int tilfeldig = Integer.parseInt(5+Integer.toString(rnd.nextInt(99999)));
        Person person = new Person("Ola Nordmann", tilfeldig, "olano@stud.ntnu.no", "Test1234!", "Test1234!");
        person.addGrade("TTM4100", 'A');
        person.addGrade("TTM4102", 'B');
        List<Course> testList;
        testList = new ArrayList<>();
        testList.add(new Course("TTM4100", 'A'));
        testList.add(new Course("TTM4102", 'B'));
        assertEquals(testList.toString(), person.getGrades().toString());
        assertThrows(IllegalArgumentException.class, () -> {
            person.addGrade("TTM4100", 'C');
        }, "Skal throwe siden det ikke er mulig å legge til en fagkode mer enn en gang");
    }

}

