package karakter;

import java.util.List;

public interface FileShell {
    

    void saveUserData(Person person);

    List<Course> readUserData(Person person);

    //List<String> readLoginData(Person person);
}
