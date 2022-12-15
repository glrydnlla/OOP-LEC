package observable;

import java.util.Vector;

import models.Subscriber;
import models.Video;
import observer.MemberSubscriber;
import observer.RegularSubscriber;

public class Youtuber {

	// private vector yang berisi subscriber yang dimiliki suatu youtuber
	private Vector<Subscriber> subscribers;

	public Youtuber() {
		this.subscribers = new Vector<>();
	}
	
	// method untuk add subscriber ke vector subscribers
	public void addSubscriber(Subscriber subscriber) {
		subscribers.add(subscriber);
	}
	
	// method untuk remove subscriber pada index tertentu
	public void removeSubscriber(int index) {
		if (index>-1) { // validasi jika index>-1 / subscriber ada pada list
			System.out.println("Successfully deleted " + subscribers.get(index).getSubscriberName());
			subscribers.remove(index);
		}
		else {
			// jika subscriber tidak ketemu
			System.out.println("Subscriber not found");
		}
	}
	
	// getter subscribers
	public Vector<Subscriber> getSubscribers() {
		return subscribers;
	}
	
	// method change membership, yang akan mengubah subscriber pada index tertentu
	// cara mengubahnya dengan set subscribers pada index tersebut menjadi new subscriber
	// dengan type yaitu regular atau member (new RegularSubscriber atau new MemberSubscriber) 
	public void changeMembership(int index, int type, String subscriberName) {
		if (type==0) {
			subscribers.set(index, new RegularSubscriber(subscriberName));
		}
		else {
			subscribers.set(index, new MemberSubscriber(subscriberName));			
		}
	}

	// method untuk view semua subscriber dalam subscribers
	public void viewSubscribers() {
		if (subscribers.size()==0) { // jika tidak ada subscribers, print message dan return
			System.out.println("There is no subscribers yet");
			return;
		}
		for (Subscriber subscriber : subscribers) { 
			// jika ada subscribers, looping untuk print subscriber dan notificationnya
			System.out.println(subscriber.viewNewestNotification());;
		}
	}
	
	// method untuk notify semua subscribers tentang new video,
	// isinya looping memanggil method receiveNotification untuk semua subscribers
	public void notifySubscribers(Video newVideo, int type) {
		for (Subscriber subscriber : subscribers) {
			subscriber.receiveNotification(newVideo, type);
		}
	}
	
}
