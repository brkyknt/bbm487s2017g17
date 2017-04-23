import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class LibrarianControlPanel {

	protected Shell shell;
	private static Librarian libAccount;
	/**
	 * Launch the application.
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		try {
			LibrarianControlPanel window = new LibrarianControlPanel(libAccount);
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LibrarianControlPanel(Librarian libAccount) {
		super();
		this.libAccount = libAccount;
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setBounds(0, 0, 428, 251);
		
		Button button = new Button(composite, SWT.NONE);
		button.setText("Add Book");
		button.setBounds(69, 55, 75, 25);
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.setText("Edit Book");
		button_1.setBounds(69, 96, 75, 25);
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setText("Delete Book");
		button_2.setBounds(69, 138, 75, 25);
		
		Label label = new Label(composite, SWT.NONE);
		label.setText("Book Operations");
		label.setBounds(69, 23, 88, 15);
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("User Operations");
		label_1.setBounds(280, 23, 88, 15);
		
		Button button_3 = new Button(composite, SWT.NONE);
		button_3.setText("Add User");
		button_3.setBounds(280, 55, 75, 25);
		
		Button button_4 = new Button(composite, SWT.NONE);
		button_4.setText("Edit User");
		button_4.setBounds(280, 96, 75, 25);
		
		Button button_5 = new Button(composite, SWT.NONE);
		button_5.setText("Delete User");
		button_5.setBounds(280, 138, 75, 25);
		
		Button btnLogout = new Button(composite, SWT.NONE);
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				LibrarianLogin newlogin = new LibrarianLogin();
				shell.dispose();

				newlogin.open();
			}
		});
		btnLogout.setBounds(361, 216, 67, 25);
		btnLogout.setText("Logout");
		
		
		System.out.println("in control panel: "+libAccount);
	}
}
