import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
import java.io.*;

public class toolFrame {
private static String fn="";
private static Display display;
private static Shell shell;
private static Text text;
private Button newButton;
private Button openButton;
private Button saveButton;
private Button delButton;
private Button quitButton;

public toolFrame(){
	display=new Display();
	shell=new Shell(display,SWT.DIALOG_TRIM);
	shell.setText("note pad");
	shell.setSize(600,400);
	newButton=new Button(shell,SWT.PUSH);
	newButton.setLocation(2,5);
	newButton.setSize(50,20);
	newButton.setText("new");
	openButton=new Button(shell,SWT.PUSH);
	openButton.setLocation(60,5);
	openButton.setSize(50,20);
	openButton.setText("open");
	saveButton=new Button(shell,SWT.PUSH);
	saveButton.setLocation(118,5);
	saveButton.setSize(50,20);
	saveButton.setText("save");
	delButton=new Button(shell,SWT.PUSH);
	delButton.setLocation(180,5);
	delButton.setSize(50,20);
	delButton.setText("delete");
	quitButton=new Button(shell,SWT.PUSH);
	quitButton.setLocation(540,5);
	quitButton.setSize(50,20);
	quitButton.setText("quit");
	text=new Text(shell,SWT.MULTI|SWT.BORDER|SWT.V_SCROLL|SWT.WRAP);
	text.setLocation(2,30);
	text.setSize(shell.getClientArea().width-4,shell.getClientArea().height-35);
		
	newButton.addSelectionListener(new SelectionAdapter(){
		public void widgetSelected(SelectionEvent event)
		{
		fn = "";
		shell.setText("第一个SWT程序");
		text.setText("你好啊");
		}
		});
	
	openButton.addSelectionListener(new SelectionAdapter()
	{
		public void widgetSelected(SelectionEvent event){
			FileDialog dlg=new FileDialog(shell,SWT.OPEN);
			String fileName=dlg.open();
			try{
				if(fileName !=null)
				{
					FileInputStream fis=new FileInputStream(fileName);
					text.setText("");
					BufferedReader in=new BufferedReader(new InputStreamReader(fis));
					String s =null;
					while ((s=in.readLine())!=null)
						text.append(s+"\r\n");
					fn=fileName;
				    shell.setText(fn);
				    MessageBox successBox=new MessageBox(shell);
				    successBox.setMessage("打开文件成功");
				    successBox.setText("信息");
				    successBox.open();
				    in.close();
				}
			}catch(NullPointerException en)
			{
				System.out.println("warning no file selected");
			}catch(Exception e)
			{
				MessageBox errorBox=new MessageBox(shell,SWT.ICON_ERROR);
				errorBox.setText("错误");
				errorBox.setMessage("打开文件失败");
				errorBox.open();
			}
		}
	});
	
	saveButton.addSelectionListener(new SelectionAdapter(){
		public void widgetSelected(SelectionEvent event){
			try{
				String fileName=null;
				if(fn.equals("")){
					FileDialog dlg=new FileDialog(shell,SWT.SAVE);
					fileName=dlg.open()+".txt";
					FileOutputStream fos=new FileOutputStream(fileName);
					OutputStreamWriter out=new OutputStreamWriter(fos);
					out.write(text.getText());
					out.close();
					alertMsg(shell,"创建文件成功");
					if(fileName!=null){
						fn=fileName;
						shell.setText(fn);
					}
				}
				else{
					FileOutputStream fos=new FileOutputStream(fn);
					OutputStreamWriter out=new OutputStreamWriter(fos);
					out.write(text.getText());
					out.close();
				}
			}catch(NullPointerException en){
				System.out.println("warning no file selected");
			}catch(Exception e)
			{
				e.printStackTrace();
				MessageBox errorBox=new MessageBox(shell,SWT.ICON_ERROR);
				errorBox.setText("错误");
				errorBox.setMessage("保存文件失败");
				errorBox.open();
			}
		}
	});
	
	quitButton.addSelectionListener(new SelectionAdapter(){
		public void widgetSelected(SelectionEvent e)
		{
			System.out.println(e.toString());
			display.dispose();
		}
	});
	
	shell.open();
	while(!shell.isDisposed()){
		if(!display.readAndDispatch()){
			display.sleep();
		}
	}
	System.out.println("closed");
	display.dispose();
}
public static void main(String[] args){
	new toolFrame();
}

public static void alertMsg(Shell shell,String msg){
	MessageBox alertBox=new MessageBox(shell);
	alertBox.setMessage(msg);
	alertBox.setText("信息");
	alertBox.open();
}
}
