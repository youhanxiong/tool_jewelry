package View;

import Controller.CommitController;
import Controller.CommitUploadController;
import Controller.UploadController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * Created by johnzheng on 2016/10/9.
 */
    public class UpdateRecordView {

    public Group groupMain;

    public static void main(String args[]){
        Display display=new Display();
        Shell shell=new Shell(display);
        shell.setBounds(1000,100,800,600);
        shell.setLayout(new FillLayout());

        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.beep();
        display.dispose();
    }
    public  void UploadView(Shell shell,String nameStr,String numStr,String imgStr){


        FormLayout flShell=new FormLayout();
        shell.setLayout(flShell);
        shell.setText("修改页面");

        FormData dataComMain=new FormData();
        dataComMain.top=new FormAttachment(20,5);
        dataComMain.left=new FormAttachment(0,300);
        dataComMain.right=new FormAttachment(100,-300);

        groupMain=new Group(shell, SWT.NONE);
        groupMain.setLayoutData(dataComMain);
        GridLayout glMain=new GridLayout();
        glMain.numColumns=2;
        groupMain.setLayout(glMain);

        Label name=new Label(groupMain,SWT.NONE);
        name.setText("名称：");

        Text nameText=new Text(groupMain,SWT.BORDER);
        nameText.setText(nameStr);

        GridData dataNameText=new GridData();
        dataNameText.horizontalAlignment=GridData.FILL;
        dataNameText.horizontalSpan=1;
        dataNameText.grabExcessHorizontalSpace=true;
        dataNameText.grabExcessVerticalSpace=true;
        nameText.setLayoutData(dataNameText);

        Label num=new Label(groupMain,SWT.NONE);
        num.setText("编号：");

        Text textNum=new Text(groupMain,SWT.BORDER);
        textNum.setText(numStr);

        GridData dataTextNum=new GridData();
        dataTextNum.horizontalSpan=1;
        dataTextNum.horizontalAlignment=GridData.FILL;
        dataTextNum.grabExcessVerticalSpace=true;
        dataTextNum.grabExcessHorizontalSpace=true;
        textNum.setLayoutData(dataTextNum);

        Label linePic=new Label(groupMain,SWT.NONE);
        linePic.setText("条形码：");

        Button buttonUploadLinePic=new Button(groupMain,SWT.PUSH);
        buttonUploadLinePic.setText("上传");
        GridData dataButtonUploadLinePic=new GridData(120,30);
        dataButtonUploadLinePic.horizontalAlignment=GridData.CENTER;
        buttonUploadLinePic.setLayoutData(dataButtonUploadLinePic);

        UploadController uc=new UploadController();

        Label labelFileName=new Label(groupMain,SWT.NONE);
        String[] img=imgStr.split("/");
        int len=img.length-1;
        String imgName=img[len];
        labelFileName.setText("已上传： "+imgName);

        GridData dataFileName=new GridData(150,30);
        dataFileName.horizontalSpan=2;
        labelFileName.setLayoutData(dataFileName);

        uc.sm.setShell(shell);
        uc.sm.setLabel(labelFileName);
        buttonUploadLinePic.addSelectionListener(uc);

        CommitUploadController commitC=new CommitUploadController();
        commitC.sm.setTextName(nameText);
        commitC.sm.setTextNum(textNum);
        commitC.sm.setShell(shell);
        commitC.sm.setFilePathLabel(labelFileName);
        commitC.sm.setName(nameStr);
        commitC.sm.setNum(numStr);

        Button buttonCommit=new Button(groupMain,SWT.PUSH);
        buttonCommit.setText("提交");
        GridData dataButtonCommit=new GridData(120,30);
        dataButtonCommit.horizontalAlignment=GridData.CENTER;
        dataButtonCommit.horizontalSpan=2;
        buttonCommit.setLayoutData(dataButtonCommit);
        buttonCommit.addSelectionListener(commitC);

    }
}
