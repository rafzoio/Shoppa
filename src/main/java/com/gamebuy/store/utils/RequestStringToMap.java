package com.gamebuy.store.utils;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;

public class RequestStringToMap {

	public static HashMap<String, String> requestInputStreamToMap(InputStream is) throws IOException {

		BufferedReader httpInput = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		StringBuilder in = new StringBuilder();

		String input;

		while ((input = httpInput.readLine()) != null) {
			in.append(input).append(" ");
		}

		httpInput.close();

		String trimmed = in.toString().trim();
		if (trimmed.equals("")) {
			return new HashMap<String, String>();
		}
		return requestStringToMap(trimmed);
	}

	public static HashMap<String, String> requestStringToMap(String request) {
		
		HashMap<String, String> hashMap = new HashMap<String, String>();
		
		String[] pairs = request.split("&");
		
		for (int i = 0; i < pairs.length; i++) {
			
			String pair = pairs[i];
			
			try {
				String key = pair.split("=")[0];
				key = URLDecoder.decode(key, "UTF-8");
				
				String value = pair.split("=")[1];
				value = URLDecoder.decode(value, "UTF-8");
				
				hashMap.put(key, value);
				
			} catch (UnsupportedEncodingException e) {
				System.err.println(e.getMessage());
			}
		}
		return hashMap;
	}
}
