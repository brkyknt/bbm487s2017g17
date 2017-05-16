import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by gismat on 16.05.2017.
 */
public class UserPanelController {


    public Menu notificationsMenu;

    
    public Label bookCount;
    public TableView<Book> bookList;
    public TableColumn<Book,String> bookTitle;
    public TableColumn<Book,String> bookAuthor;
    public TableColumn<Book,String> bookPublication;
    public TableColumn<Book,String> bookLocation;



    public TableView<Book> searchBookList;
    public TableColumn<Book,String> searchbookTitle;
    public TableColumn<Book,String> searchbookAuthor;
    public TableColumn<Book,String> searchBookPublication;
    public TableColumn<Book,String> searchbookLocation;
    public TableColumn<Book,String> searchBookStatus;


    public Label welcomeName;
    public Label totalFine;
    public Label waitingListCount;
    public TextField searchField;

    boolean called=false;
    private User user;

    private User getUser() {
        return user;
    }

    public void setUser(User user) {

        this.user = user;
    }


    public void initilize(){
        System.out.println(user);
        loadUserBooks();
        loadDefaultBook();

        welcomeName.setText("Welcome "+user.getFullname());
        totalFine.setText("total fine: "+user.getTotalFine());

        ArrayList<Book> waitingBooks=DatabaseHandler.getWaitingList(user.getId());
        waitingListCount.setText("waiting for: "+waitingBooks.size()+" books");

        notificationsMenu.setText("Notifications");
        notificationsMenu.setStyle("-fx-background-color: #FFFFFF");
        notificationsMenu.getItems().clear();

        for (Book waitingBook : waitingBooks) {

            if(waitingBook.getStatus().equals("available")){
                MenuItem bookavailable = new MenuItem("One of the books you waiting for is available!");
                notificationsMenu.setText("Notifications (1)");
                notificationsMenu.setStyle("-fx-background-color: #00E816");
                notificationsMenu.getItems().add(bookavailable);
                bookavailable.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        openWaitingList();
                    }
                });
            }
        }

    }

    public void openWaitingList(){

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("waitingListPanel.fxml"));

        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 580, 300);

            Stage stage = new Stage();
            stage.setTitle("Waiting List");
            stage.setScene(scene);
            WaitingListPanelController waitingController=fxmlLoader.getController();
            waitingController.setUser(user);
            waitingController.initilize();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadDefaultBook() {

        searchbookTitle.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        searchbookAuthor.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        searchbookLocation.setCellValueFactory(new PropertyValueFactory<Book,String>("location"));
        searchBookPublication.setCellValueFactory(new PropertyValueFactory<Book,String>("publication"));
        searchBookStatus.setCellValueFactory(new PropertyValueFactory<Book,String>("status"));

        DatabaseHandler.getUserBooks(user.getId());

        searchBookList.getItems().setAll(DatabaseHandler.getAllBooks());
    }


    public void bookSelected(MouseEvent mouseEvent) {

        final ContextMenu contextMenu;
        Book book= bookList.getSelectionModel().getSelectedItem();


            MenuItem selfreturn = new MenuItem("Return the book");

            contextMenu = new ContextMenu();

            contextMenu.getItems().add(selfreturn);
            selfreturn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    DatabaseHandler.returnBook(book,user.getId());
                    loadUserBooks();
                    loadDefaultBook();
                }
            });




        bookList.setContextMenu(contextMenu);
    }



    public void search(){
        if(searchField.getText().length()==0){

            loadDefaultBook();
        }else{
            searchbookTitle.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
            searchbookAuthor.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
            searchbookLocation.setCellValueFactory(new PropertyValueFactory<Book,String>("location"));
            searchBookPublication.setCellValueFactory(new PropertyValueFactory<Book,String>("publication"));
            searchBookStatus.setCellValueFactory(new PropertyValueFactory<Book,String>("status"));


            DatabaseHandler.getUserBooks(user.getId());


            searchBookList.getItems().setAll(DatabaseHandler.searchBooks(searchField.getText()));

        }
    }


    public void searchedBookSelected(MouseEvent mouseEvent) {

        final ContextMenu contextMenu;
        Book book= searchBookList.getSelectionModel().getSelectedItem();
                if(book.getStatus().equals("available")){

                    MenuItem checkout = new MenuItem("Self-checkout");

                     contextMenu = new ContextMenu();

                    contextMenu.getItems().add(checkout);
                    checkout.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            DatabaseHandler.insertCheckout(user.getId(),book);

                            loadDefaultBook();
                            loadUserBooks();
                        }
                    });



                }else{//taken

                    MenuItem waitFor = new MenuItem("Add to wait list");

                     contextMenu = new ContextMenu();

                    contextMenu.getItems().add(waitFor);
                    waitFor.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {



                                DatabaseHandler.addToWaitList(user.getId(),book);
                                waitingListCount.setText("waiting for: "+DatabaseHandler.getWaitingList(user.getId()).size()+" books");





                        }
                    });

                }


            searchBookList.setContextMenu(contextMenu);

    }

   /* @Override
    public void initialize(URL location, ResourceBundle resources) {
     //   System.out.println("in panel "+user);
        // loadUserBooks();

        System.out.println(called);
        called=true;
    }*/

     public void loadUserBooks(){
         bookTitle.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
         bookAuthor.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
         bookLocation.setCellValueFactory(new PropertyValueFactory<Book,String>("location"));
         bookPublication.setCellValueFactory(new PropertyValueFactory<Book,String>("publication"));

         DatabaseHandler.getUserBooks(user.getId());

         bookList.getItems().setAll(DatabaseHandler.getUserBooks(user.getId()));
         bookCount.setText("("+bookList.getItems().size()+")");

     }

    public void exit(ActionEvent actionEvent) {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("login.fxml"));

        Scene scene = null;
        try {


            scene = new Scene(fxmlLoader.load(), 800, 450);

            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(scene);



            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
