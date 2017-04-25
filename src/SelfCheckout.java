import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class SelfCheckout extends Dialog {

	protected Object result;
	protected Shell shell;
	private Book thisBook;
	private int user;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public SelfCheckout(Shell parent, int style,Book input,int userId) {
		super(parent, style);
		this.thisBook=input;
		this.user=userId;
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 300);
		shell.setText(getText());
		
		Label lblDoYouWant = new Label(shell, SWT.NONE);
		lblDoYouWant.setFont(SWTResourceManager.getFont("Inconsolata", 12, SWT.BOLD));
		lblDoYouWant.setBounds(82, 83, 283, 30);
		lblDoYouWant.setText("Do you want to checkout this book ?");
		
		Label bookDetail = new Label(shell, SWT.CENTER);
		bookDetail.setFont(SWTResourceManager.getFont("Inconsolata", 12, SWT.NORMAL));
		bookDetail.setBounds(40, 36, 361, 30);
		bookDetail.setText("New Label");
		
		Button yesButton = new Button(shell, SWT.NONE);
		yesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				DatabaseHandler dbHandler=new DatabaseHandler();

				//DatabaseHandler.insertCheckout(userId, bookId);  burda da databse insert iþlemi yapýlacak
				if(dbHandler.insertCheckout(user, thisBook.getId())){
					System.out.println("inserted");
					shell.close();
					//success
				}else{
					//fail
				}
			}
		});
		yesButton.setBounds(109, 165, 75, 25);
		yesButton.setText("Yes");
		
		Button noButton = new Button(shell, SWT.NONE);
		noButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				
				shell.close();
			}
		});
		noButton.setBounds(242, 165, 75, 25);
		noButton.setText("No");
		
		bookDetail.setText(thisBook.getTitle()+" "+thisBook.getAuthor());

	}

}
