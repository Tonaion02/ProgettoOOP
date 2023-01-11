package Utility;

import java.time.LocalDateTime;

public class Time {	
	public static LocalDateTime getCurrentTime() {
		return currentTime;
	}
	
	private static LocalDateTime currentTime = LocalDateTime.now();
}
