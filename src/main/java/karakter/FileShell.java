package karakter;

import java.util.List;

public interface FileShell {
    

    void saveUserData(int studentID, String password, List<Course> grades);

    List<Course> readUserData(Person person);

    //List<String> readLoginData(Person person);
}
