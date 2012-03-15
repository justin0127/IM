package justin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.dts.spell.swing.JTextComponentSpellChecker;
import org.jivesoftware.spark.component.RolloverButton;
import org.jivesoftware.spark.ui.ChatRoom;
import org.jivesoftware.spark.ui.ChatRoomClosingListener;
import org.jivesoftware.spark.util.SwingWorker;

public class GroupTalkChatRoomDecorator implements ActionListener,
		ChatRoomClosingListener {
    private ChatRoom _room;
    public GroupTalkChatRoomDecorator(ChatRoom room){
    	_room = room; 
    	SwingWorker worker = new SwingWorker() {
    		 public Object construct() {

    				return true;
    			    }
    		    public void finished() {
    		    	//use _room add some change in spark here
    		    	_room.getChatPanel();
    		    	
    		    }
    	};
    	worker.start();
    }
	@Override
	public void closing() {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Do something on Send messageEvents
		

	}

}
