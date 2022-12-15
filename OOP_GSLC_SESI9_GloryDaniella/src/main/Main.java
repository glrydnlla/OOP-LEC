package main;

import java.util.Scanner;

import models.Subscriber;
import models.Video;
import observable.Youtuber;
import observer.MemberSubscriber;
import observer.RegularSubscriber;
import utility.Utility;

public class Main {

	/*
	 * NotEAfy adalah program yang mengelola proses subscribe, unsubscribe, dan notify
	 * seperti pada YouTube. Dalam notEAfy ada beberapa menu, yaitu Add New Subscriber, 
	 * View All Subscribers and Their Newest Notification, Remove Subscriber, 
	 * Change Subscriber's Membership, Upload New Video and Notify Subscribers, and Exit.
	 * 
	 * NotEAfy menggunakan prinsip design pattern observer, dimana dalam penerapannya
	 * akan menggunakan abstract dan interface.
	 * 
	 * Seperti pada aplikasi YouTube, setiap channel bisa mensubscribe channel lain,
	 * dan seperti fitur membership pada YouTube, di NotEAfy juga terdapat 2 tipe subscribers
	 * yaitu regular subscriber dan member subscriber.
	 * 
	 * Youtuber dapat menentukan apakah video yang diupload khusus untuk member atau untuk
	 * semua subscriber, dan untuk memberi notifikasi pada subscriber, digunakan design pattern
	 * observer, dimana sebelum subscriber menerima notifikasi, tipe video 
	 * (untuk semua subscriber atau hanya member) akan dicek terlebih dahulu.
	 * */
	
	private Scanner scan = new Scanner(System.in); 
	// membuat object scanner untuk menerima input user
	private Youtuber youtuber = new Youtuber();
	// membuat object youtuber

	private void addNewSubscriber() { // method untuk menambahkan subscriber baru
		// ke list subscriber dari youtuber
		System.out.println("\n\nAdd New Subscriber");
		System.out.println("==============================");
		System.out.print("Subscriber's name: ");
		String name = scan.nextLine(); // menerima input nama subscriber
		String type;
		// validasi join membership
		do {
			System.out.print("Join membership? [yes|no] (case insensitive): ");
			type = scan.nextLine();
		} while (!type.equalsIgnoreCase("yes") && !type.equalsIgnoreCase("no")); 
		// menggunakan ternary operator, jika user menginput no (tidak mau join membership),
		// maka create new regular subscriber, selain itu (input adalah yes, 
		// mau join membership), maka create new member subscriber 
		Subscriber subscriber = (type.equalsIgnoreCase("no")) ? new RegularSubscriber(name) : new MemberSubscriber(name);
		youtuber.addSubscriber(subscriber); // memanggil method addSubscriber dari object youtuber
		// untuk add subscriber ke subscriber list youtuber
		System.out.println(name + " successfully subscribed youtuber"); // print success message
		Utility.enter(); // memanggil static method enter() pada class Utility  
	}
	
	private void viewAllSubscribers() {
		System.out.println("\n\nView All Subscribers");
		System.out.println("==============================");
		youtuber.viewSubscribers(); // memanggil method viewSubscribers dari object youtuber
		// untuk view subscriber list youtuber
		Utility.enter(); // memanggil static method enter() pada class Utility
	}
	
	// method yang akan mereturn index subscriber pada subscriber list
	// akan return -1 jika subscriber dengan nama tersebut tidak ditemukan
	private int searchSubscriber(String name) {
		int found = -1;
		for (int i = 0; i < youtuber.getSubscribers().size(); i++) {
			// looping setiap subscriber pada subscriber list
			if (youtuber.getSubscribers().get(i).getSubscriberName().equals(name)) {
				// jika nama subscriber di list sama dengan nama yang dipassing ke method ini,
				// maka ubah value found menjadi index subscriber tersebut, dan break looping
				// untuk mencegah loopingan yang tidak diperlukan
				found = i;
				break;
			}
		}
		return found; // return index subscriber pada subscriber list
		// atau return -1 jika subscriber dengan nama tersebut tidak ditemukan
	}
	
	// method untuk delete subscriber
	private void deleteSubscriber() {
		System.out.println("\n\nDelete Subscriber");
		System.out.println("==============================");
		// validasi jika subscriber list empty maka print message
		if (youtuber.getSubscribers().isEmpty()) {
			System.out.println("There is no subscribers yet");
		}
		// jika subscriber list ada isi
		else {
			System.out.print("Subsciber name: ");
			String name = scan.nextLine(); // meminta nama subscriber yang ingin didelete
			youtuber.removeSubscriber(searchSubscriber(name)); 
			// memanggil method removeSubscriber dari object youtuber
			Utility.enter(); // memanggil static method enter() pada class Utility
		}
	}
	
	// method untuk mengubah membership dari subscriber
	private void changeMembership() {
		System.out.println("\n\nChange Membership");
		System.out.println("==============================");
		System.out.print("Subsciber name: ");
		String name = scan.nextLine();  // meminta nama subscriber yang ingin diubah membershipnya
		int found = searchSubscriber(name); // search apakah nama subscriber ada di list
		String decision;
		if (found>-1) { // jika subscriber ada di list
			if (youtuber.getSubscribers().get(found).getSubscriberMembershipType().equals("Member")) {
				// jika subscriber adalah member, konfirmasi apakah subscriber mau remove membershipnya
				System.out.println(name + " is a member, remove membership subscription? [yes|no] (case insensitive)");
				do {
					System.out.print(">> ");
					decision = scan.nextLine(); // validasi decision yes atau no
				} while (!decision.equalsIgnoreCase("yes") && !decision.equalsIgnoreCase("no"));
				if (decision.equalsIgnoreCase("yes")) { // jika mau remove subscription
					youtuber.changeMembership(found, 0, name); // panggil method change membership dari object youtuber
					// untuk change dari member jadi regular
					System.out.println("Removed membership subscription for " + name);
				}
				else { // jika no, maka print message
					System.out.println("Cancelled membership subscription removal");
				}
			}
			else {
				// jika subscriber adalah regular subscriber, 
				// konfirmasi apakah subscriber mau start membership
				System.out.println(name + " is a regular subscriber, start membership subscription? [yes|no] (case insensitive)");
				do {
					System.out.print(">> ");
					decision = scan.nextLine(); // validasi decision yes or no
				} while (!decision.equalsIgnoreCase("yes") && !decision.equalsIgnoreCase("no"));
				if (decision.equalsIgnoreCase("yes")) { // kalau ingin start subscription
					youtuber.changeMembership(found, 1, name); // panggil method change membership dari object youtuber
					// untuk change dari regular menjadi member
					System.out.println("Started membership subscription for " + name);
				}
				else { // jika no, maka print message
					System.out.println("Cancelled membership subscription");
				}
			}
		}
		else { // jika subscriber tidak ketemu, print message
			System.out.println("Subscriber not found");
		}
		Utility.enter(); // memanggil static method enter() pada class Utility
	}
	
	private void uploadVideoAndNotify() {
		// meminta input title video baru yang akan diupload oleh youtuber
		System.out.print("Input Video Title: ");
		String videoTitle = scan.nextLine();
		// meminta input description video baru yang akan diupload oleh youtuber
		System.out.print("Input Video Description: ");
		String videoDescription = scan.nextLine();
		// meminta input type video baru yang akan diupload oleh youtuber,
		// apakah untuk semua subscribers atau member saja
		System.out.print("Specify video type [All Subscribers (0) | Member only (1)]: ");
		int type = -1;
		do {
			System.out.print(">> ");
			try { // try catch untuk menangkap error apabila input bukan integer
				type = scan.nextInt();
			} catch (Exception e) {
				e.printStackTrace();
			}
			scan.nextLine();
		} while (type<0||type>1); // validasi type yang diinput 0 atau 1 
		youtuber.notifySubscribers(new Video(videoTitle, videoDescription), type);
		// memanggil method notifySubscribers dari object youtuber, untuk notify subscribers
		// tentang video baru
		System.out.println("Successfully notified subscribers about the new video");
		Utility.enter(); // memanggil static method enter() pada class Utility
	}
	
	// method print ascii title
	private void title() { 
		String title = "\r\n" + 
				"              _   ______           __       \r\n" + 
				"             | | |  ____|   /\\    / _|      \r\n" + 
				"  _ __   ___ | |_| |__     /  \\  | |_ _   _ \r\n" + 
				" | '_ \\ / _ \\| __|  __|   / /\\ \\ |  _| | | |\r\n" + 
				" | | | | (_) | |_| |____ / ____ \\| | | |_| |\r\n" + 
				" |_| |_|\\___/ \\__|______/_/    \\_\\_|  \\__, |\r\n" + 
				"                                       __/ |\r\n" + 
				"                                      |___/ \r";
		System.out.println(title);
	}
	
	public Main() {
		
		int menu=-1;
		
		do {
			
			do {
				title(); // print logo title
				System.out.println("Youtuber's Subscription App");
				System.out.println("==================================");
				System.out.println("1. Add New Subscriber");
				System.out.println("2. View All Subscribers and Their Newest Notification");
				System.out.println("3. Remove Subscriber");
				System.out.println("4. Change Subscriber's Membership");
				System.out.println("5. Upload New Video and Notify Subscribers");
				System.out.println("6. Exit");
				System.out.print(">> ");
				try { // try catch untuk menangkap error apabila input bukan integer
					menu = scan.nextInt();
				} catch (Exception e) {
					e.printStackTrace();
				}
				scan.nextLine();
			} while (menu<1||menu>6); // validasi inputan menu 1-6
			
			if (menu==1) {
				// jika menu == 1, maka panggil method addNewSubscriber
				addNewSubscriber();
			}
			else if (menu==2) {
				// jika menu == 2, maka panggil method viewAllSubscribers
				viewAllSubscribers();
			}
			else if (menu==3) {
				// jika menu == 3, maka panggil method deleteSubscriber
				deleteSubscriber();
			}
			else if (menu==4) {
				// jika menu == 4, maka panggil method changeMembership
				changeMembership();
			}
			else if (menu==5) {
				// jika menu == 5, maka panggil method uploadVideoAndNotify
				uploadVideoAndNotify();
			}
			
		} while (menu!=6); // looping selama user belum exit
	}


	public static void main(String[] args) {
		new Main();
	}

}
