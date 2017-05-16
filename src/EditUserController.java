import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

/**
 * Created by gismat on 16.05.2017.
 */
public class EditUserController {

    public TextField nameInput;
    public TextField emailInput;
    public PasswordField passwordInput;
    public CheckBox userTypeCheckbox;

    public Button addButton;
    public Label resultLabel;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;

        nameInput.setText(user.getFullname());
        emailInput.setText(user.getEmail());

        if(user.getType().equals("librarian")){

            userTypeCheckbox.setSelected(true);
        }
    }

    public void addDialogButton(ActionEvent actionEvent) {

        String type = "user";
        if (userTypeCheckbox.isSelected()) {
            type = "librarian";
        }
        User result=DatabaseHandler.updateUser(new User(user.getId(),nameInput.getText(), emailInput.getText(), passwordInput.getText(), type));

        if(result!=null){

            //show okay
            resultLabel.setText(nameInput.getText()+"'s information is updated.");
            addButton.setStyle("-fx-background-color: #D6CDCF");

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
