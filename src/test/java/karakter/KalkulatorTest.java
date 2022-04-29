package karakter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KalkulatorTest {
    
    private Person person;
    private Person person1;

    @BeforeEach
    public void beforeEach() {
        Person person4 = new Person("Ola Nordmann", 910092, "olano@stud.ntnu.no", "Test1234!", "Test1234!");
        person4.addGrade("TTM4100", 'C');
        person4.addGrade("TTM4102", 'B');
        person4.addGrade("TTM4105", 'A');
        person4.addGrade("TTM4122", 'B');
        person4.addGrade("TTM4222", 'A');
        person4.addGrade("TTM4132", 'B');
        this.person = person4;
        Person person1 = new Person("Ola Nordmann", 872101, "olano@stud.ntnu.no", "Test1234!", "Test1234!");
        this.person1 = person1;
    }

    @Test
    @DisplayName("Test konstruktør")
    public void testConstructor() {
        Kalkulator kalkulator = new Kalkulator(person);
        List<Character> korrekt = Arrays.asList('C','B','A','B','A','B');
        assertEquals(korrekt, kalkulator.getGradeList());
        //Sjekker om konstruktør virker med en person uten karakterer
        Kalkulator kalkulator1 = new Kalkulator(person1);
        List<Character> korrekt2 = new ArrayList<>();
        assertEquals(korrekt2, kalkulator1.getGradeList());
    }

    @Test
    @DisplayName("Test set og validate-meanvalue")
    public void testSetMeanValue() {
        Kalkulator kalkulator = new Kalkulator(person);
        String korrekt = "5.166666666666667";
        kalkulator.setMeanValue(kalkulator.getGradeList());
        assertEquals(korrekt, kalkulator.getMeanValue()); // Sjekker at det kommer riktig gjennomsnittsverdi i retur når det kalkuleres med en populert liste
        // Tester med tom liste
        String korrekt1 = "0.0";
        Kalkulator kalkulator1 = new Kalkulator(person1);
        kalkulator1.setMeanValue(kalkulator1.getGradeList());
        assertEquals(korrekt1, kalkulator1.getMeanValue()); // Sjekker at returverdi er "0.0" når listen er tom
    }

    @Test
    @DisplayName("Test set og validat-emedian")
    public void testSetMedian() {
        Kalkulator kalkulator = new Kalkulator(person);
        char korrekt = 'B';
        kalkulator.setMedian(kalkulator.getGradeList());
        assertEquals(String.valueOf(korrekt), String.valueOf(kalkulator.getMedian())); // Sjekker at mediankarakter blir riktig
        char korrekt1 = '0';
        Kalkulator kalkulator1 = new Kalkulator(person1);
        kalkulator1.setMedian(kalkulator1.getGradeList());
        assertEquals(String.valueOf(korrekt1), String.valueOf(kalkulator1.getMedian())); // Sjekker at det returneres '0' her siden det er hva metoden skal gjøre om listen med karakterer er tom

    }

    
}
