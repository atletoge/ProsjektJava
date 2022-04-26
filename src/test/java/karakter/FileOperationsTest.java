package karakter;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals("913892;Test1234!", String.valueOf(fileOperations.getPerson()));
    }
}
