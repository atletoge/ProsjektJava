package karakter;

public class Kalkulator {

    private int karakterTall;

    public Kalkulator() {
        
    }


    public int getKarakterTall() {
        return karakterTall;
    }


    private void setKarakterTall(int karakterTall) {
        this.karakterTall = karakterTall;
    }


    public Kalkulator(Course course) {
        setKarakterTall(toNumber(course.getGrade()));
    }

    private int toNumber(char chr) {
        return (71 - chr);
    }

    public static void main(String[] args) {
        
    }

}
