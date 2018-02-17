package org.jcommunique.tests;

import static org.junit.Assert.*;

import org.jcommunique.notifications.Notification;
import org.jcommunique.notifications.types.TextNotification;
import org.junit.Test;

public class NotificationTest {
	@Test
	public void notificationShouldStartUnmanaged() {
		Notification note = new TextNotification();
		assertFalse("Notification should start unmanaged", note.isManaged());
	}
}
