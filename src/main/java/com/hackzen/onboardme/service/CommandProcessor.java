package com.hackzen.onboardme.service;

public class CommandProcessor {

	public static String extractKey(String command) {
		String commandToProcess = command.trim().toLowerCase();
		StringBuilder sb = new StringBuilder();
		int spaceCharCount = 0;

		for (char c : commandToProcess.toCharArray()) {
			if (c == ' ' && spaceCharCount == 0 ) {
				spaceCharCount++;
				sb.append("_");
			} else if(c != ' ') {
				sb.append(c);
				spaceCharCount = 0;
			}
		}
		return sb.toString();
	}
}
