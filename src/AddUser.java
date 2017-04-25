import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class AddUser extends Shell {
	private Text nametext;
	private Text emailtext;
	private Text passwordtext;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			AddUser shell = new AddUser(display);
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
	 */
	public AddUser(Display display) {
		super(display, SWT.SHELL_TRIM);
		setSize(455, 304);
		
		Label lblPleaseEnterThe = new Label(this, SWT.CENTER);
		lblPleaseEnterThe.setFont(SWTResourceManager.getFont("Inconsolata", 12, SWT.BOLD));
		lblPleaseEnterThe.setBounds(10, 10, 414, 28);
		lblPleaseEnterThe.setText("Please enter the user information");
		
		Label lblTitle = new Label(this, SWT.NONE);
		lblTitle.setBounds(102, 69, 55, 15);
		lblTitle.setText("Full name");
		
		nametext = new Text(this, SWT.BORDER);
		nametext.setBounds(178, 66, 148, 21);
		
		Label lblAuthor = new Label(this, SWT.NONE);
		lblAuthor.setText("Email");
		lblAuthor.setBounds(102, 96, 55, 15);
		
		emailtext = new Text(this, SWT.BORDER);
		emailtext.setBounds(178, 90, 148, 21);
		
		Label lblPublication = new Label(this, SWT.NONE);
		lblPublication.setText("Password");
		lblPublication.setBounds(102, 120, 70, 15);
		
		passwordtext = new Text(this, SWT.BORDER);
		passwordtext.setBounds(178, 114, 148, 21);
		
		Label labelUserAdded = new Label(this, SWT.NONE);
		labelUserAdded.setVisible(false);
		labelUserAdded.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		labelUserAdded.setBounds(178, 201, 92, 15);
		labelUserAdded.setText("User added");
		
		Label errorlabel = new Label(this, SWT.NONE);
		errorlabel.setVisible(false);
		errorlabel.setText("Already exists");
		errorlabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		errorlabel.setBounds(178, 201, 92, 15);
		
		Button librarianCheckButton = new Button(this, SWT.CHECK);
		librarianCheckButton.setBounds(178, 147, 93, 16);
		librarianCheckButton.setText("Librarian");
		
		
		Button btnAddBook = new Button(this, SWT.NONE);
		btnAddBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				
				if(nametext.getText().length()<3 || emailtext.getText().length()<3 || passwordtext.getText().length()<3 ){
					
				}else{
					
					User newUser=new User(nametext.getText(),emailtext.getText(),"customer",passwordtext.getText());
					if(librarianCheckButton.getSelection())
					{
						newUser.setType("librarian");
					}
					
					DatabaseHandler dbHandler=new DatabaseHandler();

					if(dbHandler.insertUser(newUser)!=null){
						
						labelUserAdded.setVisible(true);
						nametext.setText("");
						emailtext.setText("");
						passwordtext.setText("");
						librarianCheckButton.setSelection(false);
						
					}else{
						errorlabel.setVisible(true);
					}
					
				}
				
				
				
			
			}
		});
		btnAddBook.setBounds(178, 170, 75, 25);
		btnAddBook.setText("Add User");
		
		

		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
