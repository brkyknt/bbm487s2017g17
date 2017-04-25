import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

public class UserControlPanel {

	protected Shell shlLibrarianControlPanel;
	private static User userAccount;
	private Table table;
	private ArrayList<Book> books=null;
	Display mydisp;
	/**
	 * Launch the application.
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		try {
			UserControlPanel window = new UserControlPanel(userAccount);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UserControlPanel(User libAccount) {
		super();
		this.userAccount = libAccount;
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		mydisp=display;
		createContents();
		shlLibrarianControlPanel.open();
		shlLibrarianControlPanel.layout();
		while (!shlLibrarianControlPanel.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLibrarianControlPanel = new Shell();
		shlLibrarianControlPanel.setSize(620, 421);
		shlLibrarianControlPanel.setText("User home");
		
		table = new Table(shlLibrarianControlPanel, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setBounds(10, 78, 220, 280);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(74);
		tableColumn.setText("Barcode");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(144);
		tableColumn_1.setText("Title");
		
table.clearAll();
DatabaseHandler dbHandler=new DatabaseHandler();

		books=dbHandler.getUserBooks(userAccount.getId());
		
		
			
			TableItem item;
			int i=1;
			for (Book book : books) {
				book.toString();
				
				item=new TableItem(table, SWT.NONE);
				item.setText(0,Integer.toString(book.getId()));
				item.setText(1,book.getTitle());
				
				
			}
		
		Label label = new Label(shlLibrarianControlPanel, SWT.NONE);
		label.setText("My Books");
		label.setBounds(10, 57, 55, 15);
		
		Label label_1 = new Label(shlLibrarianControlPanel, SWT.CENTER);
		label_1.setText("Library Book Loan System");
		label_1.setFont(SWTResourceManager.getFont("Inconsolata", 12, SWT.BOLD));
		label_1.setBounds(10, 10, 575, 28);
		
		Button button = new Button(shlLibrarianControlPanel, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				SearchForBooks newSearch=new SearchForBooks(mydisp,userAccount);
				newSearch.open();
			}
		});
		button.setText("Book Search");
		button.setBounds(389, 78, 75, 25);
		
		Button button_1 = new Button(shlLibrarianControlPanel, SWT.NONE);
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				
				Login newlogin = new Login();
				mydisp.dispose();

				newlogin.open();
			}
		});
		button_1.setText("Logout");
		button_1.setBounds(389, 173, 75, 25);
		
		Button button_2 = new Button(shlLibrarianControlPanel, SWT.NONE);
		button_2.setText("Pay Fine");
		button_2.setBounds(389, 125, 75, 25);
		
		
		System.out.println("in control panel: "+userAccount);
	}
}
