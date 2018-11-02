import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Icon;

/**
 * 
 */

/**
 * @author Eliana
 * Action class 
 *
 */
public class SoundAction extends AbstractAction {

	Instrument insArr = ExportInstrument.getArray();
	int index;
	
	public SoundAction(int insIndex) {
		index = insIndex;
	}
	
	public SoundAction(int insIndex, String name, Icon icon) {
		index = insIndex;
		super(name, icon); //expand so that actually assigns icon to the key
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		insArr[index].playNote();
	}

}
