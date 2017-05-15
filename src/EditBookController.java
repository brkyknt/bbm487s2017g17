import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gismat on 16.05.2017.
 */
public class EditBookController {


    public TextField titleInput;
    public TextField authorInput;
    public TextField publicationInput;
    public TextField locationInput;

    public Label resultLabel;
    public Button addButton;

    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;

        titleInput.setText(book.getTitle());
        authorInput.setText(book.getAuthor());
        publicationInput.setText(book.getPublication());
        locationInput.setText(book.getLocation());
    }

    public void addDialogButton(ActionEvent actionEvent) {

        Book result=DatabaseHandler.updateBook(new Book(book.getId(),titleInput.getText(), authorInput.getText(),publicationInput.getText(), locationInput.getText()));

        if(result!=null){

            //show okay
            resultLabel.setText(titleInput.getText()+" is updated.");

            addButton.setDisable(true);
            addButton.setStyle("-fx-background-color: #D6CDCF");

        }else {
            //show error
            //resultLabel.setText("Book with location "+locationInput.getText()+" is already registered");
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
