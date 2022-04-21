package karakter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CourseTest {
    
    @Test
    @DisplayName("Test riktig oppsett av konstruktør og gettere")
    public void testConstructor() {
        Course course = new Course("TTM4100", 'C');
        assertEquals("TTM4100", course.getCourseCode());
        assertEquals('C', course.getGrade());
    }

    @Test
    @DisplayName("Test at fagkode-validering")
    public void testValidateCourseCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            Course course = new Course("TTMM4100", 'C');
        }, "Skal throwe siden fagkode må være på formen AAA1111");
        assertThrows(IllegalArgumentException.class, () -> {
            Course course2 = new Course("TTM100", 'C');
        }, "Skal throwe siden fagkode må være på formen AAA1111");
        assertThrows(IllegalArgumentException.class, () -> {
            Course course3 = new Course("TTm4100", 'C');
        }, "Skal throwe siden fagkode må være på formen AAA1111");
        assertThrows(IllegalArgumentException.class, () -> {
            Course course4 = new Course("TTM41000", 'C');
        }, "Skal throwe siden fagkode må være på formen AAA1111");
        assertThrows(NullPointerException.class, () -> {
            Course course5 = new Course(null, 'C');
        }, "Skal throwe siden fagkode er null, må være på formen AAA0000");
    } 

    @Test
    @DisplayName("Test av grade-validering")
    public void testValidateGrade() {
        assertThrows(IllegalArgumentException.class, () -> {
            Course course = new Course("TTM4100", 'G');
        }, "Karakteren må være mellom A-F");
        assertThrows(IllegalArgumentException.class, () -> {
            Course course2 = new Course("TTM4100", '!');
        }, "Karakteren må være mellom A-F");
        assertThrows(IllegalArgumentException.class, () -> {
            Course course3 = new Course("TTM4100", 'a');
        }, "Karakteren må være mellom A-F");
        assertThrows(IllegalArgumentException.class, () -> {
            Course course4 = new Course("TTM4100", '0');
        }, "Karakteren må være mellom A-F");
        
    }
}
