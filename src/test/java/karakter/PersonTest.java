package karakter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;


public class PersonTest {
    

    @Test
    @DisplayName("Test konstruktør for ny student")
    public void testConstructor() {
        Person person = new Person("Atle Tøge", 234591, "atletø@stud.ntnu.no", "Test1234!", "Test1234!");
        assertEquals("Atle Tøge", person.getName());
        assertEquals(234591, person.getStudentID());
        assertEquals("atletø@stud.ntnu.no", person.getEmail());
        assertEquals("Test1234!", person.getPassword());
    }
    
    @Test
    @DisplayName("Test navnvalidering")
    public void testName() {
        assertThrows(NullPointerException.class, () -> {
            Person person2 = new Person(null, 234591, "atletø@stud.ntnu.no", "Test1234!", "Test1234!");
        }, "Navn kan ikke være tomt");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person3 = new Person("A", 234591, "atletø@stud.ntnu.no", "Test1234!", "Test1234!");
        }, "Navnet må bestå av minst 2 bokstaver");
    }

    @Test
    @DisplayName("Test epostvalidering")
    public void testEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Person("Atle Tøge", 234591, "atletø@stud@.ntnu.no", "Test1234!", "Test1234!");
        }, "Må inneholde akkurat 1 alfakrøll");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person1 = new Person("Atle Tøge", 234591, "atletøg@stud.ntnu.no", "Test1234!", "Test1234!");
        }, "Eposten må være på formen atletø@stud.ntnu.no");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person2 = new Person("Atle Tøge", 234591, "atletø@studd.ntnu.no", "Test1234!", "Test1234!");
        }, "Eposten må være på formen atletø@stud.ntnu.no");
        assertThrows(NullPointerException.class, () -> {
            Person person3 = new Person("Atle Tøge", 234591, null, "Test1234!", "Test1234!");
        }, "Eposten kan ikke være tom");
    }

    @Test
    @DisplayName("Test studentid-validering")
    public void testStudentID() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Person("Atle Tøge", 2345911, "atletø@stud.ntnu.no", "Test1234!", "Test1234!");
        }, "StudentID må være 6 siffer");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person1 = new Person("Atle Tøge", 23459, "atletø@stud.ntnu.no", "Test1234!", "Test1234!");
        }, "StudentID må være 6 siffer");
    }

    @Test
    @DisplayName("Test passord-validering")
    public void testPassword() {
        assertThrows(IllegalArgumentException.class, () -> {
            Person person = new Person("Atle Tøge", 234591, "atletø@stud.ntnu.no", "Test1234!", "Test12345!");
        }, "Passordene er ikke like");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person1 = new Person("Atle Tøge", 234591, "atletø@stud.ntnu.no", "Test12345", "Test12345");
        }, "Passordet må inneholde minst 1 spesialtegn");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person2 = new Person("Atle Tøge", 234591, "atletø@stud.ntnu.no", "Test12!", "Test12!");
        }, "Passordet må være minst 8 tegn");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person3 = new Person("Atle Tøge", 234591, "atletø@stud.ntnu.no", "test12345!", "test12345!");
        }, "Passordet må inneholde minst 1 stor og en liten bokstav");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person4 = new Person("Atle Tøge", 234591, "atletø@stud.ntnu.no", "Testen!!!", "Testen!!!");
        }, "Passordet må inneholde minst 1 tall");
        assertThrows(IllegalArgumentException.class, () -> {
            Person person5 = new Person("Atle Tøge", 234591, "atletø@stud.ntnu.no", "Test12345!!!2222221111111", "Test12345!!!2222221111111");
        }, "Passordet kan ikke overstige 20 tegn");

    }

    @Test
    @DisplayName("Testing av å legge til fag")
    public void testAddGrade() {
        Person person = new Person("Atle Tøge", 234591, "atletø@stud.ntnu.no", "Test1234!", "Test1234!");
        person.addGrade("TTM4100", 'A');
        person.addGrade("TTM4102", 'B');
        List<Course> testList;
        testList = new ArrayList<>();
        testList.add(new Course("TTM4100", 'A'));
        testList.add(new Course("TTM4102", 'B'));
        assertEquals(testList.toString(), person.getGrades().toString());
        assertThrows(IllegalArgumentException.class, () -> {
            person.addGrade("TTM4100", 'C');
        }, "Du kan ikke legge til samem fag to ganger");
    }

}

