package com.github.json;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GjsonTest {

	public static void main(String[] agrs) throws Exception {
		// Gson gson = new GsonBuilder().create();
		// gson.toJson("Hello", System.out);
		// gson.toJson(123, System.out);
		// write();
		read();
	}

	public static void write() throws IOException {
		FileWriter writer = null;
		try {
			writer = new FileWriter("Output.json");
			Gson gson = new GsonBuilder().create();
			gson.toJson("Hello", writer);
			gson.toJson(123, writer);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public static void read() throws Exception {
		Reader reader = new InputStreamReader(GjsonTest.class.getResourceAsStream("/person.json"), "UTF-8");
		Gson gson = new GsonBuilder().create();
		Person p = gson.fromJson(reader, Person.class);
		System.out.println(p);
	}
}
