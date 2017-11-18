package com.serenitybddfw.utils;
/*
 * This class is reviewed.
 */

import static com.serenitybddfw.utils.Utility.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class files {

	// open resource
	// as BufferedReader
	// Example usage :
	// BufferedReader br = open("file_name.txt");
	// .... do something .. br.readLine() ...
	// br.close();

	public static BufferedReader open(Class ref, String file_name) throws FileNotFoundException {

		InputStream stream = ref.getResourceAsStream(file_name);
		if (stream == null)
			throw new FileNotFoundException("Can't locate :" + file_name);
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		return br;
	}

	// open file as BuffferedReader
	public static BufferedReader open(String file_name) throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(file_name));
		return br;
	}

	public static void write_file(String file_name, String str) throws IOException {
		BufferedWriter wr = new BufferedWriter(new FileWriter(file_name));
		wr.write(str);
		wr.close();
	}

	// internal function to process a file for the slurps...
	private static String slurpit(BufferedReader br, String skip, String eol) throws Exception {
		// List<String> lst = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			if (skip == null || (skip != null && !line.matches(skip))) {
				if (eol == null) { sb.append(line); } else { sb.append(line + eol); };
			}
		};
		br.close();
		say ("Slurpit:" + sb.toString());
		return sb.toString();
	}

	// read a whole file into a string, but skip lines that mactch a regex
	public static String slurp_skip(String file_name, String skip, String eol) throws Exception {
		BufferedReader br = open(file_name);
		return slurpit(br, skip, eol);

	}

	// read a whole file into a string from a jar file.
	// but skip lies that match a regex
	public static String slurp_skip(Class ref, String file_name, String skip, String eol) throws Exception {
		BufferedReader br = open(ref, file_name);// read resource
		return slurpit(br, skip, eol);
	}

	// Just read the whole file in a string
	public static String slurp(String file_name) throws Exception {
		return slurp_skip(file_name, null, null);
	}

	public static String slurp(Class ref, String file_name) throws Exception {
		return slurp_skip(ref, file_name, null, null);
	}

	public static String slurp(Class ref, String file_name, String eol) throws Exception {
		return slurp_skip(ref, file_name, null, eol);
	}

	public static String slurp(String file_name, String eol) throws Exception {
		return slurp_skip(file_name, null, eol);
	}

}
