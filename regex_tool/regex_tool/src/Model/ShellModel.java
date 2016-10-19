package Model;

import javafx.scene.control.Tab;
import org.eclipse.swt.widgets.*;


/**
 * Created by johnzheng on 2016/9/27.
 */
public class ShellModel {

    Display display;
    Shell shell;
    Label label;
    Label filePathLabel;
    Text textName;
    Text textNum;
    Text textCheck;
    Table tableResult;
    ToolItem editItem;
    ToolItem delItem;
    Composite comMain;
    int flagUpload;
    String name;
    String num;

    public Display getDisplay(){
        return this.display;
    }

    public void setDisplay(Display display){
        this.display=display;
    }

    public Shell getShell(){
        return this.shell;
    }

    public void setShell(Shell shell){
        this.shell=shell;
    }

    public Label getFilePathLabel(){
        return this.filePathLabel;
    }

    public void setFilePathLabel(Label filePathLabel){
        this.filePathLabel=filePathLabel;
    }

    public Label getLabel(){
        return this.label;
    }

    public void setLabel(Label label){
        this.label=label;
    }

    public Text getTextName(){
        return this.textName;
    }

    public void setTextName(Text textName){
        this.textName=textName;
    }

    public Text getTextNum(){
        return this.textNum;
    }

    public void setTextNum(Text textNum){
        this.textNum=textNum;
    }

    public Text getTextCheck(){
        return this.textCheck;
    }

    public void setTextCheck(Text textCheck){
        this.textCheck=textCheck;
    }

    public Table getTableResult(){
        return this.tableResult;
    }

    public void setTableResult(Table tableResult){
        this.tableResult=tableResult;
    }

    public ToolItem getEditItem(){
        return this.editItem;
    }

    public void setEditItem(ToolItem editItem){
        this.editItem=editItem;
    }

    public ToolItem getDelItem(){
        return this.delItem;
    }

    public void setDelItem(ToolItem delItem){
        this.delItem=delItem;
    }

    public Composite getComMain(){
        return this.comMain;
    }

    public void setComMain(Composite comMain){
        this.comMain=comMain;
    }

    public int getFlagUpload(){
        return this.flagUpload;
    }

    public void setFlagUpload(int flagUpload){
        this.flagUpload=flagUpload;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getNum(){
        return this.num;
    }

    public void setNum(String num){
        this.num=num;
    }

}
