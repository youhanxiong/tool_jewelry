
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;


public class mainFrame {
	protected Shell shell;
	
	public static void main(String[] args){
		try{
			mainFrame frame=new mainFrame();
			frame.open();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void open()
	{
		final Display display=Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while(!shell.isDisposed()){
			if(!display.readAndDispatch())
				display.sleep();
		}
	}
	
	protected void createContents(){
		shell=new Shell();
		shell.setSize(515,400);
		shell.setLocation(Display.getCurrent().getClientArea().width / 2 - shell.getShell().getSize().x/2, Display.getCurrent()  
                .getClientArea().height / 2 - shell.getSize().y/2);  
		shell.setText("工具集");
		final TabFolder tabFolder=new TabFolder(shell,SWT.None);
		tabFolder.setBounds(10,10,479,344);
		final TabItem comaTabItem=new TabItem(tabFolder,SWT.None);
		comaTabItem.setText("正则表达式工具");
	    final Composite compositea = new ComRegex(tabFolder,SWT.NONE);
		comaTabItem.setControl(compositea);
		final TabItem combTabItem=new TabItem(tabFolder,SWT.NONE);
		combTabItem.setText("accesslog工具");
		final Composite compositeb =new ComAccesslog(tabFolder,SWT.NONE);
		combTabItem.setControl(compositeb);
		
	}

}
