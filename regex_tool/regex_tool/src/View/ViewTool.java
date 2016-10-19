package View;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by johnzheng on 2016/9/22.
 */
abstract class AbstractView{
    public void run(){
        Display display=new Display();
        Shell shell=new Shell(display);
        shell.setText("珠宝管理系统");
        shell.setBounds(600,100,1065,800);
        shell.setLayout(new FillLayout());
        todo(display,shell);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.beep();
        display.dispose();
    }
    public abstract void todo(Display display,Shell shell);
}

public class ViewTool extends AbstractView {
    public static void main(String[] args){
        new SwtTest.mainTestExample().run();
    }
    public void todo(Display display,Shell shell){
        Label label_1=new Label(shell, SWT.CENTER);
        label_1.setText("this is the text of a label");
    }
}
