import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;



public class ComAccesslog extends Composite{
		public ComAccesslog(Composite parent,int style){
			super(parent,style);
			final Label compositeLabel=new Label(this,SWT.NONE);
			compositeLabel.setText("");
			compositeLabel.setBounds(156,154,193,17);
		}
	
}
