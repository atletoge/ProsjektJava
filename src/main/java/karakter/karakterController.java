package karakter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class KarakterController {
    @FXML
    public TextField epostr, studentidl, studentid,navn,karakter,fagkode;

    @FXML
    public Button loggInn, registrer,lagre,loggUt, leggTil, slett;

    @FXML
    private ListView<Course> liste;

    @FXML
    private PasswordField passordr1, passordr2, passordl;

    @FXML
    public Label mlabel, glabel, median, gjennomsnitt;

    private FileOperations fileOperations;
    private Person person;
    private ObservableList<Course> courses = FXCollections.observableArrayList();
    private Kalkulator kalkulator;
    private String medianString, meanValueString;

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
        gjennomsnitt.setVisible(true);
        median.setVisible(true);
        mlabel.setVisible(true);
        glabel.setVisible(true);
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
        gjennomsnitt.setText("");
        gjennomsnitt.setVisible(false);
        median.setText("");
        median.setVisible(false);
        mlabel.setVisible(false);
        glabel.setVisible(false);
    }

    @FXML
    public void handleLoggInn() {
        Person person = new Person(Integer.parseInt(studentidl.getText()), passordl.getText());
        this.fileOperations = new FileOperations(person);
        this.person = person;
        fileOperations.validateLoginData(person);
        courses.addAll(fileOperations.readUserData(person));
        login();
        showGrades();
        handleMeanValue();
        handleMedian();
        
    }

    @FXML
    public void handleRegistrerPress() {
        Person person = new Person(navn.getText(), Integer.parseInt(studentid.getText()), epostr.getText(), passordr1.getText(), passordr2.getText());
        this.person = person;
        this.fileOperations = new FileOperations(this.person);
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
        handleMeanValue();
        handleMedian();
    }

    @FXML
    public void handleLagre() {
        fileOperations.saveUserData(person);
    }

    @FXML
    public void showGrades() {
        liste.setItems(courses);
    }
    
    @FXML
    public void handleMedian() {
        // if(this.person.getGrades().size() > 0) {
        this.kalkulator = new Kalkulator(this.person);
        this.kalkulator.setMedian(this.kalkulator.getGradeList());
        this.medianString = this.kalkulator.getMedian();
        median.setText(medianString);
        // }
        
    }
    
    @FXML
    public void handleMeanValue() {
        // if(this.person.getGrades().size() > 0) {
        this.kalkulator = new Kalkulator(this.person);
        this.kalkulator.setMeanValue(this.kalkulator.getGradeList());
        this.meanValueString = this.kalkulator.getMeanValue();
        gjennomsnitt.setText(meanValueString);
        // }
    }

    @FXML
    public void handleDelete() {
        this.person.deleteHistory();
        logout();
        login();
    }

    
}
