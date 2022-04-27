package karakter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Kalkulator {

    //private String grades;
    private List<Character> gradeList = new ArrayList<>();
    private String median;
    private String meanValue;

    public Kalkulator(Person person) {
        String grades = person.getGrades().toString().replace("[", "").replace("]","").replace("\t","").replace(" ", "");
        String[] gradeArray = grades.split(",");
        List<String> tempList = Arrays.asList(gradeArray);
        for (int i = 1; i < tempList.size(); i+=2) {
            this.gradeList.add(tempList.get(i).charAt(0));
        }
    }

    public String getMeanValue() {
        return meanValue;
    }

    public void setMeanValue(List<Character> gradeList) {
        Double meanValue1 = calculateMeanValue(gradeList);
        this.meanValue = String.valueOf(meanValue1);
    }

    public String getMedian() {
        return median;
    }

    public void setMedian(List<Character> gradeList) {
        char median = calculateMedian(gradeList);
        this.median = String.valueOf(median);
    }

    private double calculateMeanValue(List<Character> gradeList) {
        if(gradeList.size() > 0) {
            double sum = 0;
            double size = Double.valueOf(gradeList.size());
            for (Character character : gradeList) {
                sum += toNumber(character);
            } return  sum/size;
        } return 0.0;
    }

    // Tror ikke denne trenger å testes, bedre å ha den som private. Blir indirekte testet i calculatemeanvalue
    private int toNumber(char chr) {
        return (71 - chr);
    }

    
    private char calculateMedian(List<Character> gradeList) {
        if(gradeList.size() > 0) {
            List<Character> sortedList = gradeList.stream().sorted().collect(Collectors.toList());
            // Returnerer tallet på index nr liste heldividert på 2 (Runder ned på .5)
            return sortedList.get(sortedList.size()/2);
        } return '0';
        
    }

    public List<Character> getGradeList() {
        return new ArrayList<Character>(gradeList);
    }

}
