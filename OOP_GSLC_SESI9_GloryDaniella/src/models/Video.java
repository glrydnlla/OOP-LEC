package models;

public class Video {
	
	// Class Video memiliki atribut videoTitle dan videoDescription
	
	private String videoTitle;
	private String videoDescription;
	
	// constructor Video
	public Video(String videoTitle, String videoDescription) {
		this.videoTitle = videoTitle;
		this.videoDescription = videoDescription;
	}

	// getter atribut videoTitle
	public String getVideoTitle() {
		return videoTitle;
	}

	// getter atribut videoDescription
	public String getVideoDescription() {
		return videoDescription;
	}
	
}
