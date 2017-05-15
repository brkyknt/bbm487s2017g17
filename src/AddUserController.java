import javafx.event.ActionEvent;
import javafx.scene.control.*;

/**
 * Created by gismat on 15.05.2017.
 */
public class AddUserController extends Dialog<User> {
    public TextField nameInput;
    public TextField emailInput;
    public PasswordField passwordInput;
    public CheckBox userTypeCheckbox;


    public Button addButton;

    public void addDialogButton(ActionEvent actionEvent) {

        if(nameInput.getText().length()>3 && emailInput.getText().length()>4 || passwordInput.getText().length()>0 ) {

            System.out.println(nameInput.getText());
            System.out.println(emailInput.getText());
            System.out.println(passwordInput.getText());
            System.out.println(userTypeCheckbox.isSelected());
            String type = "user";
            if (userTypeCheckbox.isSelected()) {
                type = "librarian";
            }
            DatabaseHandler.insertUser(new User(nameInput.getText(), emailInput.getText(), passwordInput.getText(), type));
        }else{

        }
    }
}
