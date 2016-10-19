import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.*;


public class ComRegex extends Composite {
	private Button importButton;
	private Button exportButton;
	private Button doButton;
	private Button removeButton;
	private Button findButton;
	private Button exchButton;
	private Button cancelButton;
	private Label regexLabel;
	private Label exchLabel;
	private Text regexText;
	private Text exchText;
	private Text showText;
	private static String fn="";
	private static String[] lastText=new String[100];
	private static int flag=0;
	
	public ComRegex(final Composite parent,final int style){
		super(parent,style);
		final Label compositeLable=new Label(this,SWT.NONE);
		compositeLable.setBounds(178,142,172,17);
		importButton=new Button(this,SWT.PUSH);
		importButton.setLocation(2,5);
		importButton.setSize(50,25);
		importButton.setText("导入");
		exportButton=new Button(this,SWT.PUSH);
		exportButton.setLocation(54,5);
		exportButton.setSize(50,25);
		exportButton.setText("导出");
		regexLabel=new Label(this,SWT.NONE);
		regexLabel.setLocation(106,10);
		regexLabel.setSize(105,15);
		regexLabel.setText("请输入正则表达式：");
		regexText=new Text(this,SWT.MULTI|SWT.BORDER|SWT.WRAP);
		regexText.setLocation(213,5);
		regexText.setSize(200,25);
		exchLabel=new Label(this,SWT.None);
		exchLabel.setLocation(106,37);
		exchLabel.setText("请输入替换内容：");
		exchLabel.setSize(105,15);
		exchText=new Text(this,SWT.MULTI|SWT.BORDER|SWT.WRAP);
		exchText.setLocation(213, 32);
		exchText.setSize(200,25);
		doButton =new Button(this,SWT.PUSH);
		doButton.setLocation(415,5);
		doButton.setSize(50,25);
		doButton.setText("执行");
		cancelButton=new Button(this,SWT.PUSH);
		cancelButton.setLocation(415,32);
		cancelButton.setText("撤销");
		cancelButton.setSize(50, 25);
		findButton=new Button(this,SWT.RADIO);
		findButton.setText("查找");
		findButton.setSelection(true);
		findButton.setLocation(213,57);
		findButton.setSize(50, 25);
		exchButton=new Button(this,SWT.RADIO);
		exchButton.setText("替换");
		exchButton.setLocation(270, 57);
		exchButton.setSize(50, 25);
		showText=new Text(this,SWT.MULTI|SWT.BORDER|SWT.V_SCROLL|SWT.WRAP);
		showText.setLocation(2,85);
		showText.setSize(465,225);
		
		
		importButton.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event){
				ComRegex comR=new ComRegex(parent, style);
				FileDialog dlg=new FileDialog(comR.getShell(),SWT.OPEN);
				String fileName=dlg.open();
				try{
					if(fileName !=null)
					{
						FileInputStream fis=new FileInputStream(fileName);
						showText.setText("");
						BufferedReader in=new BufferedReader(new InputStreamReader(fis));
						String s =null;
						while ((s=in.readLine())!=null)
							showText.append(s+"\r\n");
						if(!showText.getText().equals("")){
							flag++;
							lastText[flag]=showText.getText();
						}
						fn=fileName;
					    MessageBox successBox=new MessageBox(comR.getShell());
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
					MessageBox errorBox=new MessageBox(comR.getShell(),SWT.ICON_ERROR);
					errorBox.setText("错误");
					errorBox.setMessage("打开文件失败");
					errorBox.open();
				}
			}
		});
	/*	
		showText.MouseTrackEvent(new MouseTrackAdapter(){    //文本域需要增加鼠标事件，鼠标一离开就捕获文本域内容
			
		});
	*/	
		
		exportButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				ComRegex comR=new ComRegex(parent,style);
				try{
					String name=null;
					String fileName=null;
		//			if(fn.equals("")){
						FileDialog dlg=new FileDialog(comR.getShell(),SWT.SAVE);
						name=dlg.open();
						
						if(name==null){
							MessageBox errorBox=new MessageBox(comR.getShell(),SWT.ICON_ERROR);
							errorBox.setText("提示");
							errorBox.setMessage("你取消了对话框");
							errorBox.open();
						}
						else{
							fileName=name+".txt";
							FileOutputStream fos=new FileOutputStream(fileName);
							OutputStreamWriter out=new OutputStreamWriter(fos);
							out.write(showText.getText());
							out.close();
							alertMsg(comR.getShell(),"创建文件成功");
							if(fileName!=null){
								fn=fileName;
								comR.getShell().setText(fn);
							}
						}						
		//			}
		//			else{
		//				FileOutputStream fos=new FileOutputStream(fn);
		//				OutputStreamWriter out=new OutputStreamWriter(fos);
		//				out.write(showText.getText());
		//				out.close();
		//			}
				}catch(NullPointerException en){
					System.out.println("");
				}catch(Exception e){
					e.printStackTrace();
					MessageBox errorBox=new MessageBox(comR.getShell(),SWT.ICON_ERROR);
					errorBox.setText("ERROR");
					errorBox.setMessage("SAVE FAILD!");
					errorBox.open();
				}
			}
		});
		
		
		doButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				ComRegex comR=new ComRegex(parent,style);
				String rtext=regexText.getText();
				String stext=showText.getText();
				if(!stext.equals("")){                               //这段改在鼠标离开文本域时执行
					flag++;
					lastText[flag]=stext;
					System.out.println(stext+"flag="+flag);
				}
				if(rtext.equals("")){
					MessageBox errorBox=new MessageBox(comR.getShell(),SWT.ICON_ERROR);
					errorBox.setText("错误");
					errorBox.setMessage("请输入正则表达式！");
					errorBox.open();
				}
				else{
					if(findButton.getSelection()){						
					    Pattern pattern=Pattern.compile(rtext);
					    Matcher matcher=pattern.matcher(stext);
					    StringBuffer buffer=new StringBuffer();
					while(matcher.find()){
						buffer.append(matcher.group());
						buffer.append("\n");	
					}
					showText.setText(buffer.toString());
			//		if(!buffer.toString().equals(""))
			//		{
						flag++;
						lastText[flag]=buffer.toString();
						
			//		}
				//	System.out.println("执行"+lastText[flag]+"flag="+flag);
					}
					else if(exchButton.getSelection())
					{
						
					}
				}
			}
		});
		
		cancelButton.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent event){
				//System.out.println(lastText[flag]+"flag="+flag);
				if(flag>=2)
				{
					flag--;
					showText.setText(lastText[flag]);
			    }
				else if(flag==1)
				{
					showText.setText(lastText[flag]);
				}
			//	System.out.println(lastText[flag]+"flag="+flag);
			}
		});
		
		
	}
	
	public static void alertMsg(Shell shell,String msg){
		MessageBox alertBox=new MessageBox(shell);
		alertBox.setMessage(msg);
		alertBox.setText("信息");
		alertBox.open();
	}

}
