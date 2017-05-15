import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

/**
 * Created by gismat on 16.05.2017.
 */
public class AddBookController {


    public TextField publicationInput;
    public TextField titleInput;
    public TextField authorInput;
    public TextField locationInput;

    public Button addButton;
    public Label resultLabel;

    public void addDialogButton(ActionEvent actionEvent) {



        Book result=DatabaseHandler.insertBook(new Book(titleInput.getText(), authorInput.getText(),publicationInput.getText(), locationInput.getText()));

        if(result!=null){

            //show okay
            resultLabel.setText(titleInput.getText()+" added to book list.");
            titleInput.setText("");
            publicationInput.setText("");
            authorInput.setText("");
            locationInput.setText("");
            addButton.setDisable(true);
            addButton.setStyle("-fx-background-color: #D6CDCF");

        }else {
            //show error
            resultLabel.setText("Book with location "+locationInput.getText()+" is already registered");
            locationInput.setText("");
            addButton.setDisable(true);
            addButton.setStyle("-fx-background-color: #D6CDCF");

        }
    }

    public void textTyped(KeyEvent keyEvent) {
        if(titleInput.getText().length()>0 && publicationInput.getText().length()>0 && locationInput.getText().length()>0 && authorInput.getText().length()>0 ){
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
