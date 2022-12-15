package models;

import observer.Observer;

public abstract class Subscriber implements Observer {

	// Abstract class Subscriber dengan atribut subscriberName, subscriberMembershipType,
	// dan newVideo
	
	protected String subscriberName;
	protected String subscriberMembershipType;
	protected Video newVideo;
	
	// constructor Subscriber
	public Subscriber(String subscriberName) {
		this.subscriberName = subscriberName;
	}
	
	// getter subscriberName
	public String getSubscriberName() {
		return subscriberName;
	}
	
	// getter subscriberMembershipType
	public String getSubscriberMembershipType() {
		return subscriberMembershipType;
	}

	// method viewNewestNotification untuk view notifikasi terbaru setiap user
	public String viewNewestNotification() {
		// set value awal newestNotification yaitu tidak ada promo
		String newestNotification = subscriberName+ "(" + subscriberMembershipType + "): There is no video notification yet";
		if (newVideo!=null) {
			// jika ada notification yang disimpan dalam subscriber, maka ganti value variable newestNotification
			// supaya menampilkan nama subscriber, membership type, title video terbaru dan description video terbaru 
			// yang notifikasinya ia dapatkan
			newestNotification = subscriberName+ "(" + subscriberMembershipType + "): " + newVideo.getVideoTitle() + "[" + newVideo.getVideoDescription() + "]"; 
		}
		return newestNotification; // return string newestNotification
	}
	
}
