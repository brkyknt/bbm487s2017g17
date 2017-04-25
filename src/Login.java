import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;

import com.sun.java.swing.plaf.windows.resources.windows;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

import java.sql.*;

public class Login {

	protected Shell shlLibrarianLogin;
	private Text emailtext;
	private Text passwordtext;
	private DatabaseHandler dbHandler;


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlLibrarianLogin.open();
		shlLibrarianLogin.layout();
		while (!shlLibrarianLogin.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLibrarianLogin = new Shell();
		shlLibrarianLogin.setSize(450, 300);
		shlLibrarianLogin.setText(" Login");
		shlLibrarianLogin.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Label lblLibraryBookLoan = new Label(shlLibrarianLogin, SWT.CENTER);
		lblLibraryBookLoan.setLayoutData(new RowData(430, 53));
		lblLibraryBookLoan.setAlignment(SWT.CENTER);
		lblLibraryBookLoan.setFont(SWTResourceManager.getFont("Inconsolata", 16, SWT.BOLD));
		lblLibraryBookLoan.setText("Library Book Loan System");
		
		Composite logincomposite = new Composite(shlLibrarianLogin, SWT.NONE);
		
		logincomposite.setLayout(null);
		logincomposite.setLayoutData(new RowData(429, 199));
		
		emailtext = new Text(logincomposite, SWT.BORDER);
		emailtext.setBounds(174, 37, 127, 21);
		
		Label lblNewLabel = new Label(logincomposite, SWT.CENTER);
		lblNewLabel.setBounds(108, 40, 55, 15);
		lblNewLabel.setText("Email");
		
		Label lblPassword = new Label(logincomposite, SWT.CENTER);
		lblPassword.setText("Password");
		lblPassword.setBounds(118, 67, 55, 15);
		
		passwordtext = new Text(logincomposite, SWT.BORDER | SWT.PASSWORD);
		passwordtext.setBounds(174, 64, 127, 21);
		
		Button btnLogin = new Button(logincomposite, SWT.NONE);
		
		btnLogin.setForeground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		btnLogin.setBounds(175, 91, 75, 25);
		btnLogin.setText("Login");
		
		Label lblLoginFailedEmail = new Label(logincomposite, SWT.NONE);
		lblLoginFailedEmail.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblLoginFailedEmail.setBounds(174, 121, 214, 15);
		lblLoginFailedEmail.setText("Login failed. Email or password incorrect");
		lblLoginFailedEmail.setVisible(false);
		
		Composite librariancontrolpanel = new Composite(shlLibrarianLogin, SWT.NONE);
		librariancontrolpanel.setLayoutData(new RowData(428, 208));
		
		Button btnAddBook = new Button(librariancontrolpanel, SWT.NONE);
		btnAddBook.setBounds(69, 55, 75, 25);
		btnAddBook.setText("Add Book");
		
		Button btnEditBook = new Button(librariancontrolpanel, SWT.NONE);
		btnEditBook.setText("Edit Book");
		btnEditBook.setBounds(69, 96, 75, 25);
		
		Button btnDeleteBook = new Button(librariancontrolpanel, SWT.NONE);
		btnDeleteBook.setText("Delete Book");
		btnDeleteBook.setBounds(69, 138, 75, 25);
		
		Label lblBookOperations = new Label(librariancontrolpanel, SWT.NONE);
		lblBookOperations.setBounds(69, 23, 88, 15);
		lblBookOperations.setText("Book Operations");
		
		Label lblUserOperations = new Label(librariancontrolpanel, SWT.NONE);
		lblUserOperations.setText("User Operations");
		lblUserOperations.setBounds(280, 23, 88, 15);
		
		Button btnAddUser = new Button(librariancontrolpanel, SWT.NONE);
		btnAddUser.setText("Add User");
		btnAddUser.setBounds(280, 55, 75, 25);
		
		Button btnEditUser = new Button(librariancontrolpanel, SWT.NONE);
		btnEditUser.setText("Edit User");
		btnEditUser.setBounds(280, 96, 75, 25);
		
		Button btnDeleteUser = new Button(librariancontrolpanel, SWT.NONE);
		btnDeleteUser.setText("Delete User");
		btnDeleteUser.setBounds(280, 138, 75, 25);
		
		
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				dbHandler=new DatabaseHandler();
				
				User result=dbHandler.checkLogin(emailtext.getText(), passwordtext.getText());
				
				if(result==null){
					
					System.out.println("login failed");
					lblLoginFailedEmail.setVisible(true);
					
				}else{
					System.out.println(result);
					
					
					lblLoginFailedEmail.setVisible(false);
					
					if(result.getType().equals("librarian")){
						LibrarianControlPanel newWindow = new LibrarianControlPanel(result);
						shlLibrarianLogin.dispose();

						newWindow.open();
						
					}else{
						
						UserControlPanel newWindow = new UserControlPanel(result);
						shlLibrarianLogin.dispose();

						newWindow.open();
					}
					
					
					
				
				}
			}
		});
	

	}
}
