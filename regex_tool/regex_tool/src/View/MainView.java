package View;

import Controller.CheckController;
import Controller.EditAndDelController;
import Controller.RightKeyController;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * Created by johnzheng on 2016/9/22.
 */
public class MainView extends AbstractView {

    public  Composite comMain;
    public int flagAdd=0;
    public int flagCheck=1;
    public int flagUpload=0;
    private Table table;
    TableCursor cursor;

    public static void main(String args[]){
        new MainView().run();
    }

    @Override
    public void todo(final Display display, final Shell shell) {

        //工具菜单栏
        final CreateRecordView cr=new CreateRecordView();
        final EditAndDelController eadc=new EditAndDelController();

        //主视图
        FormLayout formLayout=new FormLayout();
        shell.setLayout(formLayout);
        shell.setText("珠宝管理系统");
        comMain=new Composite(shell,SWT.NONE);
        FormData data=new FormData();
        data.top=new FormAttachment(10,5);
        data.left=new FormAttachment(0,100);
        data.right=new FormAttachment(100,-100);
        //data.bottom=new FormAttachment(60,-5);
        comMain.setLayoutData(data);

        GridLayout glMain=new GridLayout();
        glMain.numColumns=2;
        comMain.setLayout(glMain);

        Text checkText=new Text(comMain,SWT.BORDER);
        GridData gdCheckText=new GridData();
        gdCheckText.horizontalSpan=1;
        gdCheckText.grabExcessHorizontalSpace=true;
        gdCheckText.grabExcessVerticalSpace=true;
        gdCheckText.horizontalAlignment=GridData.FILL;
        checkText.setLayoutData(gdCheckText);

        Button doButton=new Button(comMain,SWT.PUSH);
        doButton.setSize(50,15);
        doButton.setText("Serch！");
        GridData gdDoButton=new GridData();
        gdDoButton.horizontalSpan=1;
        gdDoButton.horizontalAlignment=GridData.CENTER;
        doButton.setLayoutData(gdDoButton);

        Group tableGroup=new Group(comMain,SWT.NONE);
        tableGroup.setText("查询结果");

        ViewForm view=new ViewForm(tableGroup,SWT.NONE);

        ToolBar toolBar=new ToolBar(view,SWT.FLAT);
        final ToolItem edit=new ToolItem(toolBar,SWT.PUSH);
        edit.setText("修改");
        final ToolItem del=new ToolItem(toolBar,SWT.PUSH);
        del.setText("删除");

        GridData gdResult=new GridData(GridData.FILL_BOTH);
        gdResult.horizontalSpan=2;
        gdResult.heightHint=500;
        tableGroup.setLayoutData(gdResult);
        tableGroup.setLayout(new GridLayout(1,false));{
            table=new Table(tableGroup,SWT.SINGLE|SWT.CHECK|SWT.FULL_SELECTION|SWT.ALL);
            table.setHeaderVisible(true);
            table.setLinesVisible(true);
            table.setLayoutData(new GridData(GridData.FILL_BOTH));

            TableColumn colSel=new TableColumn(table,SWT.LEFT);
            colSel.setText("是否选中");
            /*colSel.pack();
            colSel.setResizable(true);
            colSel.setMoveable(true);*/

            colSel.setWidth(170);

            TableColumn colName=new TableColumn(table,SWT.NONE);
            colName.setText("名称");
            //colName.pack();
            colName.setWidth(120);

            TableColumn colNum=new TableColumn(table,SWT.NONE);
            colNum.setText("编号");
            //colNum.pack();
            colNum.setWidth(120);

            TableColumn colPath=new TableColumn(table,SWT.IMAGE_JPEG|SWT.IMAGE_PNG);
            colPath.setText("图片");
            //colPath.pack();
            colPath.setWidth(413);
        }

        eadc.sm.setTableResult(table);
        eadc.sm.setEditItem(edit);
        eadc.sm.setDelItem(del);
        eadc.sm.setShell(shell);
        eadc.sm.setComMain(comMain);
        eadc.sm.setFlagUpload(flagUpload);

        edit.addListener(SWT.Selection,eadc);
        del.addListener(SWT.Selection,eadc);

        view.setTopLeft(toolBar);

        CheckController checkC=new CheckController();
        checkC.sm.setTextCheck(checkText);
        checkC.sm.setDisplay(display);
        checkC.sm.setShell(shell);
        checkC.sm.setTableResult(table);
        doButton.addSelectionListener(checkC);
        //tableGroup.setVisible(false);

        Menu mainMenu=new Menu(shell, SWT.BAR);
        shell.setMenuBar(mainMenu);
        {
            MenuItem operationItem=new MenuItem(mainMenu,SWT.CASCADE);
            operationItem.setText("操作&O");
            Menu fileMenu=new Menu(shell,SWT.DROP_DOWN);
            operationItem.setMenu(fileMenu);
            {
                MenuItem addItem=new MenuItem(fileMenu,SWT.CASCADE);
                addItem.setText("录入&A");

                Menu newFileMenu=new Menu(shell,SWT.DROP_DOWN);
                addItem.setMenu(newFileMenu);
                {
                    final MenuItem newProjectItem=new MenuItem(newFileMenu,SWT.PUSH);
                    newProjectItem.setText("玉器\tCtrl+Shift+N");

                    newProjectItem.setAccelerator(SWT.CTRL+SWT.SHIFT+'N');
                    newProjectItem.addSelectionListener(new SelectionAdapter() {
                        @Override
                        public void widgetSelected(SelectionEvent selectionEvent) {

                            if(flagAdd==0){
                                //com1.setVisible(false);
                                comMain.dispose();
                                cr.CreateView(shell);
                                //newProjectItem.getParent().dispose();
                                //com1.dispose();
                                shell.layout();
                                flagAdd=1;
                                eadc.flagCheck=1;
                                flagCheck=0;
                            }else{
                                MessageBox dialog=new MessageBox(shell,SWT.OK|SWT.ICON_INFORMATION);
                                dialog.setText("提示");
                                dialog.setMessage("当前已是新增页面！");
                                dialog.open();
                            }

                        }
                    });
                    //new MenuItem(newFileMenu,SWT.SEPARATOR);
                    new MenuItem(newFileMenu,SWT.PUSH).setText("钻石");
                }
                MenuItem checkFileItem=new MenuItem(fileMenu,SWT.CASCADE);
                checkFileItem.setText("查询&C");
                checkFileItem.addSelectionListener(new SelectionAdapter() {
                    @Override
                    public void widgetSelected(SelectionEvent selectionEvent) {
                        if(flagCheck==0||eadc.flagCheck==0){
                            if(cr.groupMain!=null){
                                cr.groupMain.setVisible(false);
                            }
                            if(eadc.ur.groupMain!=null){
                                eadc.ur.groupMain.setVisible(false);
                            }
                            todo(display,shell);
                            shell.layout();
                            flagCheck=1;
                            eadc.flagCheck=1;
                            flagAdd=0;
                        }else {
                            MessageBox dialog=new MessageBox(shell,SWT.OK|SWT.ICON_INFORMATION);
                            dialog.setText("提示");
                            dialog.setMessage("当前已是查询页面！");
                            dialog.open();
                        }
                    }
                });

                MenuItem exitItem=new MenuItem(fileMenu,SWT.CASCADE);
                exitItem.setText("退出&E");
            }
            MenuItem helpItem=new MenuItem(mainMenu,SWT.CASCADE);
            helpItem.setText("帮助&H");
        }



    }
}
