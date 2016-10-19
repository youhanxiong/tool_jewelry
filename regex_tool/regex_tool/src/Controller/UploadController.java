package Controller;

import Configure.Common;
import Model.ShellModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import java.io.*;

/**
 * Created by johnzheng on 2016/9/23.
 * 上传图片事件控制器
 */


public class UploadController extends SelectionAdapter{

    public ShellModel sm = new ShellModel();

    public void widgetSelected(SelectionEvent e){

        FileDialog dlg=new FileDialog(sm.getShell(),SWT.OPEN);
        String filePath=dlg.open();
        if(filePath!=null){
            //sm.setFilePath(filePath);
            String[] str=filePath.split("\\\\");
            int len=str.length-1;
            String fileName=str[len];
            sm.getLabel().setText("已上传： "+fileName);
            //String writeFilePath=Common.getValue("imgPath")+fileName;
            String writeFilePath="E:/image/"+fileName;

            ImageData imageData=new ImageData(filePath).scaledTo(300,80);
            ImageLoader imageLoader=new ImageLoader();
            imageLoader.data=new ImageData[]{imageData};
            imageLoader.save(writeFilePath,SWT.IMAGE_JPEG|SWT.IMAGE_PNG);

               /* FileInputStream fis=new FileInputStream(filePath);
                FileOutputStream fos=new FileOutputStream(writeFilePath);
                int read=0;
                byte b[]=new byte[1024];
                read=fis.read(b);
                while (read!=-1){
                    fos.write(b,0,read);
                    read=fis.read(b);
                }
                fis.close();
                fos.close();*/

        }

    }
}
