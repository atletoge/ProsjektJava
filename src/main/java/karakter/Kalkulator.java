package karakter;

public class Kalkulator {

    private int karakterTall;

    public Kalkulator() {
        
    }

    public Kalkulator(Course course) {
        this.karakterTall = toNumber(course.getGrade());
    }

    private int toNumber(char chr) {
        return (71 - chr);
    }

    public static void main(String[] args) {
        Kalkulator kalkulator = new Kalkulator();
        System.out.println(kalkulator.toNumber('A'));
    }

}
