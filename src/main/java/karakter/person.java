package karakter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class Person {
    

    private String name;
    private int studentID;
    private String email;
    private List<String> nameList = new ArrayList();
    private String password;

    //Konstruktør for registrering
    public Person(String name, int studentID, String email, String password, String password1) {
        setName(name);
        setEmail(email);
        setStudentID(studentID);
        setPassword(password, password1);
    }

    private boolean validateName(String name) {
        if(name.matches("^([a-zA-ZæøåÆØÅ]{2,}\\s[a-zA-ZæøåÆØÅ]{1,}'?-?[a-zA-ZæøåÆØÅ]{2,}\\s?([a-zA-ZæøåÆØÅ]{1,})?)")) {
           return true; 
        } 
        else {
            throw new IllegalArgumentException("Navnet du oppga er ikke gyldig");
        }
    }

    private void setName(String name) {
        validateName(name);
        this.name = name;
        for (String string : name.split(" ")) {
            this.nameList.add(string);
        }
    }

    public String getName() {
        return name;
    }

    private boolean validateEmail(String email) {
        String[] splitEmail = email.split("@");
        if(splitEmail.length == 2) {
            if(splitEmail[0].toLowerCase().equals(nameList.get(0).toLowerCase()+nameList.get(nameList.size()-1).substring(0, 2).toLowerCase())) {
                if(splitEmail[1].equals("stud.ntnu.no")) {
                    return true;
                }
            }throw new IllegalArgumentException("Mailadressen må være på format olano@stud.ntnu.no");

        }
        throw new IllegalArgumentException("Mailadressen må inneholde akkurat 1 alfakrøll");
    }

    private void setEmail(String email) {
        validateEmail(email);
        this.email = email.toLowerCase();
    }


    public String getEmail() {
        return email;
    }

    private boolean validateStudentID(int studentID) {
        String asString = String.valueOf(studentID);
        if(asString.length() == 6 && asString.charAt(0) != 0) {
            return true;
        } throw new IllegalArgumentException("Lengden av studentID må være 6 tall og kan ikke starte med 0");
    }

    private void setStudentID(int studentID) {
        validateStudentID(studentID);
        this.studentID = studentID;

    }
    
    public int getStudentID() {
        return studentID;
    }
    private boolean validatePassword(String password, String password1) {
        if(password.equals(password1)) {
            if(password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%!?]).{8,20}$")) {
                return true;
            }throw new IllegalArgumentException("Passordet må inneholde minst en stor og liten bokstav, et tall, ett spesialsymbol og være mellom 8 og 20 tegn. ");

        }throw new IllegalArgumentException("Passordene er ikke like");
    }

    private void setPassword(String password, String password1) {
        validatePassword(password, password1);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public static void main(String[] args) {
        Person person = new Person("Atle Østrem Tøge", 911002, "atLetØ@stud.ntnu.no", "Test1234?Test1234?Test1234?", "Test1234?Test1234?Test1234?");
        System.out.println(person.getStudentID());
    }




}
