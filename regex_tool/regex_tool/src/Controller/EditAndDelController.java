package Controller;

import Configure.Dao;
import Model.ShellModel;
import View.UpdateRecordView;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.TableItem;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by johnzheng on 2016/10/9.
 */
public class EditAndDelController implements Listener {

    public ShellModel sm=new ShellModel();
    public static int flagCheck=1;
    public UpdateRecordView ur=new UpdateRecordView();
    Dao dao=new Dao();
    String sql="";
    ResultSet rs=null;
    String nameStr="";
    String numStr="";
    String imgStr="";

    @Override
    public void handleEvent(Event event) {
        TableItem[] items=sm.getTableResult().getItems();

        if(event.widget==sm.getEditItem()){
            int index=0;
            int sum=0;
            int a=0;
            for(int i=items.length-1;i>=0;i--){
                if(!items[i].getChecked()){
                    continue;
                }
                index=sm.getTableResult().indexOf(items[i]);
                if(index<0){
                    continue;
                }
                sum++;
                a=i;
            }
            if(sum>1){
                MessageBox dialog=new MessageBox(sm.getShell(), SWT.OK|SWT.ICON_INFORMATION);
                dialog.setText("提示");
                dialog.setMessage("修改只能单选！");
                dialog.open();
            }else if(sum==0){
                MessageBox dialog=new MessageBox(sm.getShell(), SWT.OK|SWT.ICON_INFORMATION);
                dialog.setText("提示");
                dialog.setMessage("请先选择修改数据！");
                dialog.open();
            }
            else{
                if(sm.getFlagUpload()==0){
                    nameStr=items[a].getText(1);
                    numStr=items[a].getText(2);
                    sql="SELECT imgpath FROM jewelry_list WHERE NAME='"+nameStr+"' AND num='"+numStr+"';";
                    rs=dao.SelectResult(sql);
                    try{
                        while (rs.next()){
                            imgStr=rs.getString(1);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    if(!imgStr.equals("")){
                        sm.getComMain().dispose();
                        ur.UploadView(sm.getShell(),nameStr,numStr,imgStr);
                    }
                    sm.getShell().layout();
                    flagCheck=0;
                }
            }
        }
        else if(event.widget==sm.getDelItem()){
            for (int i=items.length-1;i>=0;i--){
                if(!items[i].getChecked()){
                    continue;
                }
                int index=sm.getTableResult().indexOf(items[i]);
                if(index<0){
                    continue;
                }
                sql="DELETE FROM jewelry_list WHERE NAME='"+items[i].getText(1)+"' AND num='"+items[i].getText(2)+"';";
                sm.getTableResult().remove(index);
                if(dao.UpdateResult(sql)==1){
                    MessageBox dialog=new MessageBox(sm.getShell(), SWT.OK|SWT.ICON_INFORMATION);
                    dialog.setText("提示");
                    dialog.setMessage("删除成功！");
                    dialog.open();
                }
                //System.out.println("del text: "+items[i].getText(2));
            }
        }
    }

}
