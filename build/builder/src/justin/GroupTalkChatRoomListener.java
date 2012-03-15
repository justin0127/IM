package justin;

import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.ui.ChatRoom;
import org.jivesoftware.spark.ui.ChatRoomListener;
import org.jivesoftware.spark.ui.rooms.GroupChatRoom;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smackx.muc.Occupant;
public class GroupTalkChatRoomListener implements ChatRoomListener {
	private Occupant mysself;
    /**
     * Creates {@link SpellcheckChatRoomListener}
     */
    public GroupTalkChatRoomListener() {
    	
    }
	@Override
	public void chatRoomOpened(ChatRoom room) {
		// TODO Auto-generated method stub
		Message message= new Message();
		GroupChatRoom groom = (GroupChatRoom) room;
		Occupant messagefrom = SparkManager.getUserManager().getOccupant(groom, message.getFrom());
		
		
	}

	@Override
	public void chatRoomLeft(ChatRoom room) {
		// TODO Auto-generated method stub

	}

	@Override
	public void chatRoomClosed(ChatRoom room) {
		// TODO Auto-generated method stub

	}

	@Override
	public void chatRoomActivated(ChatRoom room) {
		// TODO Auto-generated method stub

	}

	@Override
	public void userHasJoined(ChatRoom room, String userid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void userHasLeft(ChatRoom room, String userid) {
		// TODO Auto-generated method stub

	}
	//invoke by a listener of send message?
	//Block in client?
	private Boolean BlockMesage(Occupant sender,Occupant reciver){
		if(SparkManager.getUserManager().isOwnerOrAdmin(sender)){
			//Owner or admin can send message to everyone
			return true;
		}else if(SparkManager.getUserManager().isOwnerOrAdmin(reciver)){
			//if sender is not Owner or admin check reciver
			//if reciver is owner or admin this message can be send 
			return true;
		}
			//otherwise bolck message
		return false;
		
	}
}
