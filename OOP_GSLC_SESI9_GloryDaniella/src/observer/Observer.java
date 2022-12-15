package observer;

import models.Video;

public interface Observer {
	// interface observer yang akan diimplement oleh abstract class subscriber
	// sehingga child class regularSubscriber dan memberSubscriber akan override method ini
	public void receiveNotification(Video newVideo, int type);
}
