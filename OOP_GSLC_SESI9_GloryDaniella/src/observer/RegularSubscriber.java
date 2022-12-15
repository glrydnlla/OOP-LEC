package observer;

import models.Subscriber;
import models.Video;

public class RegularSubscriber extends Subscriber {

	// class regular subscriber yang merupakan child dari abstract class Subscriber
	
	public RegularSubscriber(String subscriberName) {
		super(subscriberName);
		this.subscriberMembershipType = "Regular";
	}

	// override method receiveNotification
	@Override
	public void receiveNotification(Video newVideo, int type) {
		if (type==0) {
			// notifikasi video baru akan disimpan oleh subscriber apabila
			// video baru tersebut untuk semua subscriber
			this.newVideo = newVideo;
		}
	}
	
}
