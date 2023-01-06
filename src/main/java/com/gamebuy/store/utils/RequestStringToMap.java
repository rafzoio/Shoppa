package com.gamebuy.store.utils;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class RequestStringToMap {

	public static HashMap<String, String> requestInputStreamToMap(InputStream is) throws IOException {

		BufferedReader httpInput = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

		StringBuilder in = new StringBuilder();

		String input;

		while ((input = httpInput.readLine()) != null) {
			in.append(input).append(" ");
		}

		httpInput.close();

		String trimmed = in.toString().trim();
		if (trimmed.equals("")) {
			return new HashMap<>();
		}
		return requestStringToMap(trimmed);
	}

	public static HashMap<String, String> requestStringToMap(String request) {
		
		HashMap<String, String> hashMap = new HashMap<>();
		
		String[] pairs = request.split("&");

		for (String pair : pairs) {

			String key = pair.split("=")[0];
			key = URLDecoder.decode(key, StandardCharsets.UTF_8);

			String value = pair.split("=")[1];
			value = URLDecoder.decode(value, StandardCharsets.UTF_8);

			hashMap.put(key, value);

		}
		return hashMap;
	}
}
