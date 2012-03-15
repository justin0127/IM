package justin;

import javax.swing.JOptionPane;

import org.jivesoftware.spark.SparkManager;
import org.jivesoftware.spark.plugin.Plugin;

/**
 * Example Plugin to demonstrate the use of Plugins
 * @author wolf.posdorfer
 *
 */
public class GroupTalkPlugin implements Plugin{

	private GroupTalkChatRoomListener listener;
	private GroupTalkPreference preference;
	
    public boolean canShutDown() {
    	return false;
    }
    @Override
    public void initialize() {
	// Use this method to initialize your Plugin

	// The following will add an Entry into the Spark Preferences Window
    	preference = GroupTalkManager.getIntance().getGroupTalkPreference();
    	SparkManager.getPreferenceManager().addPreference(preference);	
    	//add a listener to Spark
    	listener = new GroupTalkChatRoomListener();
    	SparkManager.getChatManager().addChatRoomListener(listener);
	// Show a Message When my plugin is loaded
    	JOptionPane.showMessageDialog(null, "group talk plugin has been added");
    }

    @Override
    public void shutdown() {
    	JOptionPane.showMessageDialog(null, "group talk Plugin has been shutdown");	
    }
    @Override
    public void uninstall() {
    	SparkManager.getChatManager().removeChatRoomListener(listener);
	// use this method to remove stored preferences used by this plugin
	JOptionPane.showMessageDialog(null, "group talk Plugin has been uninstalled");
	
    }

}
