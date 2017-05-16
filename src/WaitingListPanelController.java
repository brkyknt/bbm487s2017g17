import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * Created by gismat on 16.05.2017.
 */
public class WaitingListPanelController {

    public TableView<Book> searchBookList;
    public TableColumn<Book,String> searchbookTitle;
    public TableColumn<Book,String> searchbookAuthor;
    public TableColumn<Book,String> searchbookPublication;
    public TableColumn<Book,String> searchbookLocation;
    public TableColumn<Book,String> searchBookStatus;


    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void initilize(){

        loadUserBooks();
    }

    public void loadUserBooks(){
        searchbookTitle.setCellValueFactory(new PropertyValueFactory<Book,String>("title"));
        searchbookAuthor.setCellValueFactory(new PropertyValueFactory<Book,String>("author"));
        searchbookLocation.setCellValueFactory(new PropertyValueFactory<Book,String>("location"));
        searchbookPublication.setCellValueFactory(new PropertyValueFactory<Book,String>("publication"));
        searchBookStatus.setCellValueFactory(new PropertyValueFactory<Book,String>("status"));



        searchBookList.getItems().setAll(DatabaseHandler.getWaitingList(user.getId()));
      ;

    }


    public void searchedBookSelected(MouseEvent mouseEvent) {

        final ContextMenu contextMenu;
        Book book= searchBookList.getSelectionModel().getSelectedItem();
        if(book.getStatus().equals("available")){

            MenuItem checkout = new MenuItem("Self-checkout");
            MenuItem remove = new MenuItem("Remove");

            contextMenu = new ContextMenu();

            contextMenu.getItems().add(checkout);
            contextMenu.getItems().add(remove);

            checkout.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    DatabaseHandler.insertCheckout(user.getId(),book);

                    DatabaseHandler.removeWaiting(book,user.getId());
                    loadUserBooks();
                }
            });

            remove.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    DatabaseHandler.removeWaiting(book,user.getId());
                    loadUserBooks();
                }
            });

            searchBookList.setContextMenu(contextMenu);


        }else{
            MenuItem remove = new MenuItem("Remove");

            contextMenu = new ContextMenu();

            contextMenu.getItems().add(remove);



            remove.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    DatabaseHandler.removeWaiting(book,user.getId());
                    loadUserBooks();
                }
            });

            searchBookList.setContextMenu(contextMenu);

        }


    }

}
