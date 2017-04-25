import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;

import java.util.ArrayList;

import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SearchForBooks extends Shell {
	private Table table;
	private Text bookTitleBox;
	private ArrayList<Book> books=null;
	private static User myUser;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			SearchForBooks shell = new SearchForBooks(display, myUser);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 * @param user 
	 */
	public SearchForBooks(Display display, User user) {
		super(display, SWT.SHELL_TRIM);
		setLayout(null);
		
		Label lblBookSearch = new Label(this, SWT.CENTER);
		lblBookSearch.setBounds(193, 10, 285, 35);
		lblBookSearch.setFont(SWTResourceManager.getFont("Inconsolata", 12, SWT.BOLD));
		lblBookSearch.setText("Book Search");
		
		Label lblEnterTheBook = new Label(this, SWT.NONE);
		lblEnterTheBook.setBounds(10, 49, 117, 15);
		lblEnterTheBook.setText("Enter the book title:");
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.addListener(SWT.Selection, new Listener() {
		 

			@Override
			public void handleEvent(Event e) {
				// TODO Auto-generated method stub
				  String string = "";
			        TableItem[] selection = table.getSelection();
			        
			        for (int i = 0; i < selection.length; i++){
			        	 string += selection[i] + " ";
					     
			        }
			        selection.toString();
			         
			       
			        SelfCheckout newDialog=new SelfCheckout(getShell(), SWT.NONE, books.get(table.getSelectionIndex()),user.getId());
			        newDialog.open();
			        
			        
			        
				
			}
		    });
		
		table.setBounds(10, 72, 637, 299);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("Barcode");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(157);
		tblclmnNewColumn_1.setText("Title");
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_2.setWidth(140);
		tblclmnNewColumn_2.setText("Author");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_3.setWidth(143);
		tblclmnNewColumn_3.setText("Publication");
		
		TableColumn tblclmnLocation = new TableColumn(table, SWT.NONE);
		tblclmnLocation.setWidth(93);
		tblclmnLocation.setText("Location");
		
		Label lblNotResultRelated = new Label(this, SWT.NONE);
		lblNotResultRelated.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNotResultRelated.setBounds(338, 49, 309, 15);
		lblNotResultRelated.setText("not result related to ");
		lblNotResultRelated.setVisible(false);
		
		bookTitleBox = new Text(this, SWT.BORDER);
		bookTitleBox.setBounds(129, 45, 122, 21);
		
		Button searchButton = new Button(this, SWT.NONE);
		searchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				table.clearAll();
				System.out.println(bookTitleBox.getText());
				DatabaseHandler dbHandler=new DatabaseHandler();

				books=dbHandler.searchBooks(bookTitleBox.getText());
				
				if(books==null){
					lblNotResultRelated.setVisible(true);
					lblNotResultRelated.setText("not result related to "+bookTitleBox.getText());
				}else{
					
					TableItem item;
					int i=1;
					for (Book book : books) {
						book.toString();
						
						item=new TableItem(table, SWT.NONE);
						item.setText(0,Integer.toString(book.getId()));
						item.setText(1,book.getTitle());
						item.setText(2,book.getAuthor());
						item.setText(3,book.getPublication());
						item.setText(4,book.getLocation());
						
					}
				}
			}
		});
		searchButton.setBounds(257, 44, 75, 25);
		searchButton.setText("Search");
		
	
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Book Search");
		setSize(673, 420);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
