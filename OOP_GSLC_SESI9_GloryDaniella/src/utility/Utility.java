package utility;

import java.util.Scanner;

public class Utility {

	private static Scanner scan = new Scanner(System.in);
	
	public static void enter() {
		System.out.println("Press Enter to Continue...");
		scan.nextLine();
	}
	
}
