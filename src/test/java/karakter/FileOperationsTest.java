package karakter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.File;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileOperationsTest {
    
    Person person;

    @BeforeEach
    public void beforeEach() {
        Person person = new Person(913892, "Test1234!");
        this.person = person;
    }

    @Test
    @DisplayName("Test konstruktør")
    public void testConstructor() {
        FileOperations fileOperations = new FileOperations(person);
        assertEquals("913892;Test1234!", fileOperations.getPerson().toString()); // Tester om konstruktøren virket med å få ut person-objektet i fileoperations som en string
    }

    @Test
    @DisplayName("Test saving user data")
    public void testSaveUserData() {
        File file = new File("userdata.txt");
        long lengde = file.length();
        Random rnd = new Random();
        int tilfeldig = Integer.parseInt(5+Integer.toString(rnd.nextInt(99999)));
        Person person1 = new Person("Test Testesen", tilfeldig, "testte@stud.ntnu.no", "Test1234!", "Test1234!");
        FileOperations fileOperations = new FileOperations(person1);
        fileOperations.saveUserData(person1);
        assertNotEquals(lengde, file.length()); //Tester at det er lagt til info i filen, ved å sammenligne lengde før eller etter
    }

    @Test
    @DisplayName("Test reading user data")
    public void testReadUserData() {
        FileOperations fileOperations = new FileOperations(person);
        fileOperations.saveUserData(person); // Saver først, slik at validateLoginData blir kjørt, og derav person får oppdatert karakterene til å samsvare med det fra fil.
        assertEquals(fileOperations.readUserData(person), person.getGrades());
        Random rnd = new Random();
        person.addGrade(("TTX"+rnd.nextInt(9999)).toString(), 'B'); // Legger til et TTX fag med semi-tilfeldig emnekode
        fileOperations.saveUserData(person); // Saver den nye karakteren til fil, sånn vi kan lese etterpå
        assertEquals(fileOperations.readUserData(person), person.getGrades());
    }

    @Test
    @DisplayName("Test validation of login data")
    public void testValidateLoginData() {
        FileOperations fileOperations = new FileOperations(person);
        assertEquals(true, fileOperations.validateLoginData(person)); // Skal bli true siden brukeren allerede eksisterer i databasen
        Person person1 = new Person("Test Testesen", 913892, "testte@stud.ntnu.no", "Test12345!", "Test12345!");
        FileOperations fileOperations2 = new FileOperations(person1);
        assertEquals(false, fileOperations2.validateLoginData(person1)); // Skal bli false siden passordet ikke stemmer overens med det som ligger i fil
    }
}
