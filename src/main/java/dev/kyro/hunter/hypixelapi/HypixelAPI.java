package dev.kyro.hunter.hypixelapi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

public class HypixelAPI {

	public static JSONObject request(UUID uuid) {

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("https://api.hypixel.net/player?uuid=" + uuid + "&key=" + APIKeys.getAPIKey());
		HttpResponse response;
		String result;

		try {
			response = client.execute(request);
			HttpEntity entity = response.getEntity();
			InputStream inStream = entity.getContent();
			result = convertStreamToString(inStream);

			JSONObject playerObj = new JSONObject(result);
			if(!playerObj.getBoolean("success")) return null;
			return playerObj;
		} catch(Exception exception) {

//			exception.printStackTrace();
			return null;
		}
	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
