package observer;

import models.Subscriber;
import models.Video;

public class MemberSubscriber extends Subscriber {
	
	// class member subscriber yang merupakan child dari abstract class Subscriber

	public MemberSubscriber(String subscriberName) {
		super(subscriberName);
		this.subscriberMembershipType = "Member";
	}

	@Override
	public void receiveNotification(Video newVideo, int type) {
		// notifikasi video baru akan disimpan oleh subscriber
		// tidak ada validasi karena member pasti bisa menonton semua video
		// yang dipost youtuber, baik video khusus member maupun video
		// untuk semua subscriber
		this.newVideo = newVideo;
	}
	
}
