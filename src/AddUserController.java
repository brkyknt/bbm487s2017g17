import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

/**
 * Created by gismat on 15.05.2017.
 */
public class AddUserController  {
    public TextField nameInput;
    public TextField emailInput;
    public PasswordField passwordInput;
    public CheckBox userTypeCheckbox;
    public Button addButton;
    public Label resultLabel;

    public void addDialogButton(ActionEvent actionEvent) {



            System.out.println(nameInput.getText());
            System.out.println(emailInput.getText());
            System.out.println(passwordInput.getText());
            System.out.println(userTypeCheckbox.isSelected());
            String type = "user";
            if (userTypeCheckbox.isSelected()) {
                type = "librarian";
            }
            User result=DatabaseHandler.insertUser(new User(nameInput.getText(), emailInput.getText(), type,passwordInput.getText()));

            if(result!=null){

                //show okay
                resultLabel.setText(nameInput.getText()+" added to user list.");
                nameInput.setText("");
                emailInput.setText("");
                passwordInput.setText("");
                userTypeCheckbox.setSelected(false);
                addButton.setDisable(true);
            }else {
                //show error
                resultLabel.setText(emailInput.getText()+" is already registered");
                emailInput.setText("");
                addButton.setDisable(true);
                addButton.setStyle("-fx-background-color: #D6CDCF");

            }
    }

    public void textTyped(KeyEvent keyEvent) {
        if(nameInput.getText().length()>3 && emailInput.getText().length()>4 && passwordInput.getText().length()>0 ){
            addButton.setDisable(false);
            addButton.setStyle("-fx-background-color: #00E816");

        }else{
            addButton.setDisable(true);
            addButton.setStyle("-fx-background-color: #D6CDCF");

        }
    }

    public void closeWindow(ActionEvent actionEvent) {

        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }
}
