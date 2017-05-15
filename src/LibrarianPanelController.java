import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by gismat on 14.05.2017.
 */
public class LibrarianPanelController implements Initializable{

    public Label bookCount;
    public TableView<Book> bookList;
    public TableColumn<Book,String> bookTitle;
    public TableColumn<Book,String> bookAuthor;
    public TableColumn<Book,String> bookLocation;


    public TableView<User> userList;
    public Label userCount;
    public TableColumn<User,String> userName;
    public TableColumn<User,String> userEmail;
    public TableColumn<User,String> userType;
    public TableColumn<User,Float> totalFine;


    public Button addButton;
    public TextField nameInput;
    public TextField emailInput;
    public PasswordField passwordInput;
    public CheckBox userTypeCheckbox;


    private User librarian;


    private User getLibrarian() {
        return librarian;
    }

    public void setLibrarian(User librarian) {
        this.librarian = librarian;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        loadBookList();
        loadUserList();

    }

    private void loadUserList(){
       // userList.getItems().clear();
        userName.setCellValueFactory(new PropertyValueFactory<User,String>("fullname"));
        userEmail.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        userType.setCellValueFactory(new PropertyValueFactory<User,String>("type"));
        totalFine.setCellValueFactory(new PropertyValueFactory<User,Float>("totalFine"));

        userList.getItems().setAll(DatabaseHandler.getAllUsers());
        userCount.setText("("+userList.getItems().size()+")");

    }

    private void loadBookList(){
//        bookList.getItems().clear();
        bookTitle.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        bookAuthor.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        bookLocation.setCellValueFactory(new PropertyValueFactory<Book,String>("location"));


        bookList.getItems().setAll(DatabaseHandler.getAllBooks());
        bookCount.setText("("+bookList.getItems().size()+")");
    }

    public void addUser(MouseEvent mouseEvent) {

        System.out.println("clicked");
        showAddUserDialog();

    }

    private void showAddUserDialog() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("adduserpanel.fxml"));

        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 480, 280);

            Stage stage = new Stage();
            stage.setTitle("Add User");
            stage.setScene(scene);



            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addDialogButton(ActionEvent actionEvent) {


    }
}
