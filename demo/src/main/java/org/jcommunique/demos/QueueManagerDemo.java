package org.jcommunique.demos;

import org.jcommunique.notifications.NotificationFactory;
import org.jcommunique.notifications.NotificationFactory.Location;
import org.jcommunique.notifications.manager.QueueManager;
import org.jcommunique.notifications.types.WindowNotification;
import org.jcommunique.platform.Platform;
import org.jcommunique.theming.ThemePackagePresets;
import org.jcommunique.util.IconUtils;
import org.jcommunique.util.Time;

public class QueueManagerDemo {
	@SuppressWarnings("incomplete-switch")
	public static void main(String[] args) throws Exception {
		Platform.instance().setAdjustForPlatform(true);

		NotificationFactory factory = new NotificationFactory(ThemePackagePresets.cleanLight());
		QueueManager manager = new QueueManager(Location.NORTHWEST);
		// sets how quickly old notifications should move out of the way for new ones
		manager.setSnapFactor(0.23);

		for (int i = 0; i < 10; i++) {
			int type = i % 3;
			WindowNotification note = null;
			switch (type) {
			case 0:
				note = factory.buildIconNotification("IconNotification", "Subtitle",
						IconUtils.createIcon("/org/jcommunique/demos/exclamation.png", 50, 50));
				break;
			case 1:
				note = factory.buildTextNotification("TextNotification", "Subtitle");
				break;
			case 2:
				note = factory.buildAcceptNotification("AcceptNotification", "Do you accept?");
				break;
			}
			// when the notification is clicked, it should hide itself
			note.setCloseOnClick(true);
			// make it show in the queue for five seconds
			manager.addNotification(note, Time.seconds(10));
			// one second delay between creations
			Thread.sleep(1000);
		}
	}
}