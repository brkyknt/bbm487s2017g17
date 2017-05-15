import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    public TextField emailInput;
    public PasswordField passwordInput;
    public Label errorText;
    public Button loginButton;


    public void checkLogin(ActionEvent actionEvent) {


        User result = DatabaseHandler.checkLogin(emailInput.getText(), passwordInput.getText());

        if (result == null) {

            System.out.println("login failed");
            errorText.setVisible(true);

        } else {
            System.out.println(result);

            if (result.getType().equals("librarian")) {

                openLibrarianPanel(result,actionEvent);

            } else {


                System.out.println("it is user");
            }


        }
    }

    private void openLibrarianPanel(User librarian, ActionEvent actionEvent){
        System.out.println("it is librarian");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("librarianpanel.fxml"));

        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 800, 600);

            Stage stage = new Stage();
            stage.setTitle("Control Panel");
            stage.setScene(scene);
            LibrarianPanelController librarianController=fxmlLoader.getController();
            librarianController.setLibrarian(librarian);

            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }





    public void checkChars(){
        if((emailInput.getText().length())>4 && (passwordInput.getText().length())>0 ){
            loginButton.setStyle("-fx-background-color: #00E816");
            loginButton.setDisable(false);
        }else{
            loginButton.setStyle("-fx-background-color: #D6CDCF");
            loginButton.setDisable(true);
        }
    }

}
