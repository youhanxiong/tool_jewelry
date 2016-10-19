package Controller;

import Model.ShellModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;

/**
 * Created by johnzheng on 2016/9/30.
 */
public class RightKeyController implements org.eclipse.swt.events.MouseListener {

    public ShellModel sm=new ShellModel();
    Menu menu;

    @Override
    public void mouseDoubleClick(org.eclipse.swt.events.MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDown(org.eclipse.swt.events.MouseEvent mouseEvent) {
        if(mouseEvent.button==3){
            menu=new Menu(sm.getShell(), SWT.POP_UP);
            sm.getTableResult().setMenu(menu);
            MenuItem del=new MenuItem(menu,SWT.PUSH);
            MenuItem edit=new MenuItem(menu,SWT.PUSH);
            del.setText("É¾³ý");
            del.addListener(SWT.Selection, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    sm.getTableResult().remove(sm.getTableResult().getSelectionIndices());
                }
            });
            edit.setText("ÐÞ¸Ä");
            edit.addListener(SWT.Selection, new Listener() {
                @Override
                public void handleEvent(Event event) {
                    TableItem[] items=sm.getTableResult().getSelection();
                    for(int i=0;i<items.length;i++){
                        System.out.println(items[i].getText());
                    }
                }
            });
            sm.getTableResult().setMenu(menu);
        }
    }

    @Override
    public void mouseUp(org.eclipse.swt.events.MouseEvent mouseEvent) {

    }
}
