package karakter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class KarakterController {
    @FXML
    public TextField epostr, studentidl, studentid,navn,karakter,fagkode;

    @FXML
    public Button loggInn, registrer,lagre,loggUt, leggTil;

    @FXML
    private ListView<Course> liste;

    @FXML
    private PasswordField passordr1, passordr2, passordl;

    private FileOperations fileOperations;
    private Person person;
    private ObservableList<Course> courses = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        logout();
    }

    @FXML
    public void login() {
        registrer.setVisible(false);
        loggInn.setVisible(false);
        liste.setVisible(true);
        loggUt.setVisible(true);
        fagkode.setVisible(true);
        karakter.setVisible(true);
        leggTil.setVisible(true);
        lagre.setVisible(true);
    }

    @FXML
    public void logout() {
        liste.setVisible(false);
        registrer.setVisible(true);
        loggInn.setVisible(true);
        fagkode.setVisible(false);
        karakter.setVisible(false);
        loggUt.setVisible(false);
        leggTil.setVisible(false);
        lagre.setVisible(false);
        courses.clear();
    }

    @FXML
    public void handleLoggInn() {
        Person person = new Person(Integer.parseInt(studentidl.getText()), passordl.getText());
        this.fileOperations = new FileOperations(person);
        if(fileOperations.validateLoginData(person)) {
            this.person = person;
            courses.addAll(fileOperations.readUserData(person));
            login();
            showGrades();
        } else {
            throw new IllegalArgumentException("Brukeren eksisterer ikke, sjekk innlogging eller registrer deg.");
        }
        
    }

    @FXML
    public void handleRegistrerPress() {
        Person person = new Person(navn.getText(), Integer.parseInt(studentid.getText()), epostr.getText(), passordr1.getText(), passordr2.getText());
        this.person = person;
        this.fileOperations = new FileOperations(person);
        login();
    }

    @FXML
    public void handleLoggUt() {
        logout();
    }

    @FXML
    public void handleLeggtil() {
        person.addGrade(fagkode.getText(), karakter.getText().charAt(0));
        courses.clear();
        courses.addAll(person.getGrades());
        showGrades();
    }

    @FXML
    public void handleLagre() {
        fileOperations.saveUserData(person);
    }

    @FXML
    public void showGrades() {
        liste.setItems(courses);
    }
    


}
