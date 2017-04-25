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

public class AddBook extends Shell {
	private Text titletext;
	private Text autthortext;
	private Text publicationtext;
	private Text locationtext;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			AddBook shell = new AddBook(display);
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
	public AddBook(Display display) {
		super(display, SWT.SHELL_TRIM);
		setSize(456, 301);
		
		Label lblPleaseEnterThe = new Label(this, SWT.CENTER);
		lblPleaseEnterThe.setFont(SWTResourceManager.getFont("Inconsolata", 12, SWT.BOLD));
		lblPleaseEnterThe.setBounds(10, 10, 414, 28);
		lblPleaseEnterThe.setText("Please enter the book information");
		
		Label lblTitle = new Label(this, SWT.NONE);
		lblTitle.setBounds(102, 72, 55, 15);
		lblTitle.setText("Title");
		
		titletext = new Text(this, SWT.BORDER);
		titletext.setBounds(178, 66, 148, 21);
		
		Label lblAuthor = new Label(this, SWT.NONE);
		lblAuthor.setText("Author");
		lblAuthor.setBounds(102, 96, 55, 15);
		
		autthortext = new Text(this, SWT.BORDER);
		autthortext.setBounds(178, 90, 148, 21);
		
		Label lblPublication = new Label(this, SWT.NONE);
		lblPublication.setText("Publication");
		lblPublication.setBounds(102, 120, 70, 15);
		
		publicationtext = new Text(this, SWT.BORDER);
		publicationtext.setBounds(178, 114, 148, 21);
		
		Label lblLocation = new Label(this, SWT.NONE);
		lblLocation.setText("Location");
		lblLocation.setBounds(102, 144, 55, 15);
		
		locationtext = new Text(this, SWT.BORDER);
		locationtext.setBounds(178, 138, 148, 21);
		
		Label lblBookAdded = new Label(this, SWT.NONE);
		lblBookAdded.setVisible(false);
		lblBookAdded.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		lblBookAdded.setBounds(178, 201, 92, 15);
		lblBookAdded.setText("Book added");
		
		Label errorlabel = new Label(this, SWT.NONE);
		errorlabel.setVisible(false);
		errorlabel.setText("Already Exists");
		errorlabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		errorlabel.setBounds(178, 201, 92, 15);
		
		Button btnAddBook = new Button(this, SWT.NONE);
		btnAddBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				if(titletext.getText().length()<3 || autthortext.getText().length()<3 || publicationtext.getText().length()<3  || locationtext.getText().length()<3){
					
					
				}else{
					
					
					Book newbook=new Book(titletext.getText(),autthortext.getText(),publicationtext.getText(),locationtext.getText());
					DatabaseHandler dbHandler=new DatabaseHandler();
					if(dbHandler.insertBook(newbook)!=null){
						
						lblBookAdded.setVisible(true);
						titletext.setText("");
						autthortext.setText("" );
						publicationtext.setText("");
						locationtext.setText("");
						
					}else{
						errorlabel.setVisible(true);
					}
				}
				
				
				
			
			}
		});
		btnAddBook.setBounds(178, 170, 75, 25);
		btnAddBook.setText("Add Book");
		
		Label lblPleaseFillThe = new Label(this, SWT.NONE);
		lblPleaseFillThe.setVisible(false);
		lblPleaseFillThe.setText("Please fill the fields.");
		lblPleaseFillThe.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_RED));
		lblPleaseFillThe.setBounds(178, 201, 112, 15);
		

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
