package karakter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class karakterController {
    @FXML
    public TextField epostr, epostl, studentid,navn,karakter,fagkode;

    @FXML
    public Button loggInn, registrer,leggTil,slettAlt,loggUt;

    @FXML
    private ListView<Course> liste;

    @FXML
    private PasswordField passordr1, passordr2, passordl;

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
        slettAlt.setVisible(true);
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
        slettAlt.setVisible(false);
    }

    @FXML
    public void handleRegistrerPress() {
        Person person = new Person(navn.getText(), Integer.parseInt(studentid.getText()), epostr.getText(), passordr1.getText(), passordr2.getText());
        this.person = person;
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
    public void showGrades() {
        liste.setItems(courses);
    }
    


}
