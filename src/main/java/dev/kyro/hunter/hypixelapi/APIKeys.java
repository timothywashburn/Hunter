package dev.kyro.hunter.hypixelapi;

import dev.kyro.hunter.Misc;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class APIKeys {

	public static File keysFile;
	public static List<String> apiKeys = new ArrayList<>();

	public static int count = 0;

	static {

		try {
			keysFile = Misc.getFileFromResource("api-keys.txt");
			updateAPIKeys();
		} catch(URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static String getAPIKey() {

		if(apiKeys.size() == 0) return null;
		return apiKeys.get(count++ % apiKeys.size());
	}

	public static void updateAPIKeys() {

		List<String> keys = new ArrayList<>();

		if(keysFile == null) return;

		Scanner fileReader;
		try {
			fileReader = new Scanner(keysFile);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			return;
		}

		while(fileReader.hasNextLine()) {

			String key = fileReader.nextLine();
			keys.add(key);
		}

		apiKeys = keys;
	}
}
