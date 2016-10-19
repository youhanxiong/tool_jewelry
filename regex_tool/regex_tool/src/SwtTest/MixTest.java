package SwtTest;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;

/**
 * Created by johnzheng on 2016/9/21.
 */
public class MixTest extends AbstractExample {

    private Tree tree;
    private Table table;

    public static void main(String[] args){
        new MixTest().run();
    }

    public void todo(Shell shell){
        TabFolder tabFolder=new TabFolder(shell, SWT.BORDER);

        TabItem tabItem1=new TabItem(tabFolder,SWT.NONE);
        tabItem1.setText("第一页");

        Composite composite1=new Composite(tabFolder,SWT.NONE);
        tabItem1.setControl(composite1);

        GridLayout layout=new GridLayout();
        layout.numColumns=1;
        composite1.setLayout(layout);
        Group treeGroup=new Group(composite1,SWT.NONE);
        treeGroup.setText("Tree");
        GridData gridData=new GridData(SWT.FILL,SWT.FILL,true,true,2,2);
        gridData.heightHint=50;
        treeGroup.setLayoutData(gridData);
        treeGroup.setLayout(new GridLayout(1,false));
        {
            tree=new Tree(treeGroup,SWT.SINGLE);
            tree.setLayoutData(new GridData(GridData.FILL_BOTH));

            TreeItem stu1=new TreeItem(tree,SWT.NONE);
            stu1.setText("xingoo");
            {
                TreeItem info1=new TreeItem(stu1,SWT.NONE);
                info1.setText("age:25");

                TreeItem info2=new TreeItem(stu1,SWT.NONE);
                info2.setText("tel:123456");
            }
            TreeItem stu2=new TreeItem(tree,SWT.NONE);
            stu2.setText("halo");
            {
                TreeItem info3=new TreeItem(stu2,SWT.NONE);
                info3.setText("age:25");

                TreeItem info4=new TreeItem(stu2,SWT.NONE);
                info4.setText("tel:654321");
            }

            tree.addSelectionListener(new SelectionAdapter() {
                @Override
                public void widgetSelected(SelectionEvent selectionEvent) {
                    TableItem item=new TableItem(table,SWT.NONE);
                    item.setText(new String[]{tree.getSelection()[0].toString(),tree.getSelection()[0].getText()});
                }
            });
        }
        Group tableGroup=new Group(composite1,SWT.NONE);
        tableGroup.setText("Table");
        GridData gd=new GridData(GridData.FILL_BOTH);
        gd.heightHint=20;
        tableGroup.setLayoutData(gd);
        tableGroup.setLayout(new GridLayout(1,false));
        {
            table=new Table(tableGroup,SWT.SINGLE|SWT.BORDER|SWT.FULL_SELECTION);
            table.setHeaderVisible(true);
            table.setLinesVisible(true);
            table.setLayoutData(new GridData(GridData.FILL_BOTH));

            TableColumn column1=new TableColumn(table,SWT.NULL);
            column1.setText("Tree Item");
            column1.pack();
            column1.setWidth(150);

            TableColumn column2=new TableColumn(table,SWT.NULL);
            column2.setText("Parent");
            column2.pack();
            column2.setWidth(150);
        }
        TabItem tabItem2=new TabItem(tabFolder,SWT.NONE);
        tabItem2.setText("第二页");
    }

}
