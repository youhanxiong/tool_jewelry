package Controller;

import Configure.Dao;
import Model.ShellModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by johnzheng on 2016/10/9.
 */
public class CommitUploadController extends SelectionAdapter {

    public ShellModel sm=new ShellModel();
    String name="";
    String num="";
    String filePath="";
    Dao dao=new Dao();
    ResultSet rs=null;

    public void widgetSelected(SelectionEvent e){
        String sqlOfId="SELECT MAX(id) FROM jewelry_list";
        rs=dao.SelectResult(sqlOfId);
        int maxId=1;
        try {
            while (rs.next()){
                maxId=rs.getInt(1)+1;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        name=sm.getTextName().getText();
        num=sm.getTextNum().getText();
        String[] str=sm.getFilePathLabel().getText().split(" ");
        String fileName="";
        if(str.length>1){
            fileName=str[str.length-1];
        }
        filePath="E:/image/"+fileName;
        if(name.equals("")||name.equals(null)){
            MessageBox dialog=new MessageBox(sm.getShell(), SWT.OK|SWT.ICON_INFORMATION);
            dialog.setText("提示");
            dialog.setMessage("名称不能为空！");
            dialog.open();
        }else if(num.equals("")||num.equals(null)){
            MessageBox dialog=new MessageBox(sm.getShell(), SWT.OK|SWT.ICON_INFORMATION);
            dialog.setText("提示");
            dialog.setMessage("编号不能为空！");
            dialog.open();
        }else if(fileName.equals("")||fileName.equals(null)){
            MessageBox dialog=new MessageBox(sm.getShell(), SWT.OK|SWT.ICON_INFORMATION);
            dialog.setText("提示");
            dialog.setMessage("请上传条形图！");
            dialog.open();
        }else{
            String sqlOfAdd="UPDATE jewelry_list SET NAME='"+name+"' , NUM='"+num+"',imgpath='"+filePath+"' WHERE NAME='"+sm.getName()+"' AND num='"+sm.getNum()+"'";
            int result=dao.UpdateResult(sqlOfAdd);
            if(result==1){
                MessageBox dialog=new MessageBox(sm.getShell(), SWT.OK|SWT.ICON_INFORMATION);
                dialog.setText("提示");
                dialog.setMessage("修改成功！");
                dialog.open();
            }
        }
    }

}
