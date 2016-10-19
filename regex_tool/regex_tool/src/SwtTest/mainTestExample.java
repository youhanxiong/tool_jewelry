package SwtTest;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * Created by johnzheng on 2016/9/20.
 */

abstract class AbstractExample{
    public void run(){
        Display display=new Display();
        Shell shell=new Shell(display);
        shell.setText("shell example");
        shell.setBounds(1400,100,400,280);
        shell.setLayout(new FillLayout());
        todo(shell);
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
            display.beep();
            display.dispose();
        }
    public abstract void todo(Shell shell);
}

public class mainTestExample extends AbstractExample{
    public static void main(String[] args){
        new mainTestExample().run();
    }
    public void todo(Shell shell){
        Label label_1=new Label(shell, SWT.CENTER);
        label_1.setText("this is the text of a label");
    }
}
