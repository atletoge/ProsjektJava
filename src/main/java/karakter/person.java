package karakter;
import java.util.regex.Pattern;


public class Person {
    

    private String name;
    private int studentID;
    private String email;

    public Person(String name, int studentID, String email) {
        validateName(name);
        this.name = name;
    }

    private boolean validateName(String name) {
        if(name.matches("^([a-zA-ZæøåÆØÅ]{2,}\\s[a-zA-ZæøåÆØÅ]{1,}'?-?[a-zA-ZæøåÆØÅ]{2,}\\s?([a-zA-ZæøåÆØÅ]{1,})?)")) {
           return true; 
        } 
        else {
            throw new IllegalArgumentException("Navnet du oppga er ikke gyldig");
        }
    }

    public String getName() {
        return name;
    }

    


    public static void main(String[] args) {
        Person person = new Person("Atle Østrem Tøge", 111002, "atletoge@gmail.com");
    }

}
