import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
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

    public void loadUserList(){
       // userList.getItems().clear();
        userName.setCellValueFactory(new PropertyValueFactory<User,String>("fullname"));
        userEmail.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        userType.setCellValueFactory(new PropertyValueFactory<User,String>("type"));
        totalFine.setCellValueFactory(new PropertyValueFactory<User,Float>("totalFine"));

        userList.getItems().setAll(DatabaseHandler.getAllUsers());
        userCount.setText("("+userList.getItems().size()+")");

    }

    public void loadBookList(){
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



    public void addBook(MouseEvent mouseEvent) {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("addbookpanel.fxml"));

        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 480, 280);

            Stage stage = new Stage();
            stage.setTitle("Add Book");
            stage.setScene(scene);



            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void userSelected(MouseEvent mouseEvent) {

       // System.out.println(userList.getSelectionModel().getSelectedItem().getFullname());
        final ContextMenu contextMenu = new ContextMenu();
        MenuItem edit = new MenuItem("Edit");
        MenuItem delete = new MenuItem("Delete");
        contextMenu.getItems().addAll(edit,delete);
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("editUserPanel.fxml"));

                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 480, 280);

                    Stage stage = new Stage();
                    stage.setTitle("Edit User");
                    stage.setScene(scene);
                    EditUserController editUserController=fxmlLoader.getController();
                    editUserController.setUser(userList.getSelectionModel().getSelectedItem());

                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                DatabaseHandler.deleteUser( userList.getSelectionModel().getSelectedItem().getId());
                loadUserList();


            }
        });

        userList.setContextMenu(contextMenu);

    }

    public void bookSelected(MouseEvent mouseEvent) {

        final ContextMenu contextMenu = new ContextMenu();
        MenuItem edit = new MenuItem("Edit");
        MenuItem delete = new MenuItem("Delete");
        contextMenu.getItems().addAll(edit,delete);
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("editBookPanel.fxml"));

                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 480, 280);

                    Stage stage = new Stage();
                    stage.setTitle("Edit Book");
                    stage.setScene(scene);
                    EditBookController editBookController=fxmlLoader.getController();
                    editBookController.setBook(bookList.getSelectionModel().getSelectedItem());

                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                DatabaseHandler.deleteBook( bookList.getSelectionModel().getSelectedItem().getId());
                loadBookList();


            }
        });

        bookList.setContextMenu(contextMenu);

    }

    public void exit(MouseEvent mouseEvent) {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("login.fxml"));

        Scene scene = null;
        try {


            scene = new Scene(fxmlLoader.load(), 800, 450);

            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(scene);



            stage.show();
            ((Node)(mouseEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
