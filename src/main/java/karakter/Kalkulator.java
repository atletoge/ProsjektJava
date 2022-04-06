package karakter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Kalkulator {

    //private String grades;
    private List<Character> gradeList = new ArrayList<>();

    public Kalkulator(Person person) {
        String grades = person.getGrades().toString().replace("[", "").replace("]","").replace("\t","").replace(" ", "");
        String[] gradeArray = grades.split(",");
        List<String> tempList = Arrays.asList(gradeArray);
        for (int i = 1; i < tempList.size(); i+=2) {
            this.gradeList.add(tempList.get(i).charAt(0));
        }System.out.println(gradeList);
    }

    private double calculateMeanValue(List<Character> gradeList) {
        double sum = 0;
        double size = Double.valueOf(gradeList.size());
        for (Character character : gradeList) {
            sum += toNumber(character);
        } return  sum/size;
    }

    private int toNumber(char chr) {
        return (71 - chr);
    }

    private char calculateMedian(List<Character> gradeList) {
        List<Character> sortedList = gradeList.stream().sorted().collect(Collectors.toList());
        return sortedList.get(sortedList.size()/2);
    }

    public List<Character> getGradeList() {
        return gradeList;
    }


    public static void main(String[] args) {
        Person person4 = new Person("Atle Tøge", 910792, "atletø@stud.ntnu.no", "Test1234!", "Test1234!");
        person4.addGrade("TTM4100", 'C');
        person4.addGrade("TTM4102", 'B');
        person4.addGrade("TTM4105", 'A');
        person4.addGrade("TTM4122", 'B');
        person4.addGrade("TTM4222", 'A');
        person4.addGrade("TTM4132", 'B');
        Kalkulator kalkulator = new Kalkulator(person4);
        System.out.println(kalkulator.calculateMedian(kalkulator.getGradeList()));
        System.out.println(kalkulator.calculateMeanValue(kalkulator.getGradeList()));
    }

}
