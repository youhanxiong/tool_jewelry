package Controller;

import Configure.Dao;
import Model.ShellModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by johnzheng on 2016/9/29.
 */
public class CheckController extends SelectionAdapter {

    public ShellModel sm=new ShellModel();
    String[] name;
    String[] num;
    String[] filePath;
    Dao dao=new Dao();
    ResultSet rs=null;

    public void widgetSelected(SelectionEvent e){
        String textCheck=sm.getTextCheck().getText();
        int resultCount=0;
        int flag=0;
        String sqlCount="";
        String sqlResult="";
        sm.getTableResult().removeAll();
        if(!textCheck.equals("")){
            sqlCount="SELECT COUNT(*) FROM jewelry_list WHERE NAME LIKE '%"+textCheck+"%' OR NUM LIKE '"+textCheck+"%';";
            sqlResult="SELECT * FROM jewelry_list WHERE NAME LIKE '%"+textCheck+"%' OR NUM LIKE '%"+textCheck+"%';";
            rs=dao.SelectResult(sqlCount);
            //System.out.println(sqlCount);
            try {
                while (rs.next()){
                    resultCount=rs.getInt(1);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            if(resultCount==0){
                MessageBox dialog=new MessageBox(sm.getShell(),SWT.OK|SWT.ICON_INFORMATION);
                dialog.setText("提示");
                dialog.setMessage("没有结果，请检查搜索词是否有误！");
                dialog.open();
            }else {
                name=new String[resultCount];
                num=new String[resultCount];
                filePath=new String[resultCount];

                rs=dao.SelectResult(sqlResult);
                try {
                    while (rs.next()){
                        name[flag]=rs.getString(2);
                        num[flag]=rs.getString(3);
                        filePath[flag]=rs.getString(4);
                        Image img=new Image(sm.getDisplay(),filePath[flag]);
                        TableItem itemResult=new TableItem(sm.getTableResult(),SWT.NONE);
                        itemResult.setText(1,name[flag]);
                        itemResult.setText(2,num[flag]);
                        itemResult.setImage(3,img);
                        flag++;
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }else
        {
            MessageBox dialog=new MessageBox(sm.getShell(), SWT.OK|SWT.ICON_INFORMATION);
            dialog.setText("提示");
            dialog.setMessage("请先输入搜索词！");
            dialog.open();
        }
    }

}
