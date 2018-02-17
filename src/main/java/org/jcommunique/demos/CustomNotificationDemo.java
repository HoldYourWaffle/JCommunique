package org.jcommunique.demos;

import org.jcommunique.notifications.NotificationFactory;
import org.jcommunique.notifications.NotificationManager;
import org.jcommunique.notifications.NotificationFactory.Location;
import org.jcommunique.notifications.manager.SimpleManager;
import org.jcommunique.platform.Platform;
import org.jcommunique.theming.ThemePackagePresets;
import org.jcommunique.util.Time;

public class CustomNotificationDemo {
	public static void main(String[] args) throws Exception {
		Platform.instance().setAdjustForPlatform(true);
		// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		// register the custom builder with the factory
		NotificationFactory factory = new NotificationFactory(ThemePackagePresets.cleanLight());
		factory.addBuilder(CustomNotification.class, new CustomNotification.CustomBuilder());

		// add the Notification
		NotificationManager manager = new SimpleManager(Location.NORTH);
		manager.addNotification(factory.build(CustomNotification.class, "this is test text"), Time.infinite());
	}
}
