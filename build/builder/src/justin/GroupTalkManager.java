package justin;

public class GroupTalkManager {
	private static GroupTalkManager instance=null;
	private GroupTalkPreference preferences;
	//add some object can not access by plugin directly
	
	public static GroupTalkManager getIntance(){
		if (instance == null) {
			instance =new GroupTalkManager();
		}
		return instance;
	}
	
	private GroupTalkManager(){
		//do something before 
		preferences = new GroupTalkPreference();
	}
	public GroupTalkPreference getGroupTalkPreference(){
		return preferences;
	}
	
}
