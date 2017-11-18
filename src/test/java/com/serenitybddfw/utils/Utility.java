package com.serenitybddfw.utils;
/*
 * This class is completed.
 */

import static com.serenitybddfw.utils.files.open;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeMessage;

public class Utility {

	private static String OS = System.getProperty("os.name").toLowerCase();

	public static boolean is_windows() {
		return OS.indexOf("win") >= 0;
	}

	public static boolean is_unix() {
		return OS.indexOf("nux") >= 0;
	}

	// the biggest invention in Java-world since sliced-bread ;)
	public static <ARG> void say(ARG arg) {
		System.out.println(arg);
	}

	public static boolean match(String re, String str) {
		Pattern pat = Pattern.compile(re);
		Matcher m = pat.matcher(str);
		if (m.find())
			return true;
		return false;
	}

	public static String match(String re, String str, int idx) {
		Pattern pat = Pattern.compile(re);
		Matcher m = pat.matcher(str);
		if (m.find())
			return m.group(idx);
		return "";
	}

	public static String replace(String re, String str, String repl) {
		Pattern pat = Pattern.compile(re);
		Matcher m = pat.matcher(str);
		return m.replaceAll(repl);
	}

	// joins iterable or collection into a String
	public static <T> String join(String delimiter, Iterable<T> lst) {
		StringBuilder str = new StringBuilder();
		Iterator<T> it = lst.iterator();
		while (it.hasNext()) {
			str.append(it.next().toString());

		}
		if (it.hasNext())
			str.append(delimiter);

		return str.toString();
	}

	// for classes check if the value is SCALAR
	public static boolean is_scalar(Object var) {

		if ((var instanceof Number) || (var instanceof String) || (var instanceof Boolean))
			return true;
		return false;
		// Page 1
	}

	public static HashMap<Integer, ?> list2map(List lst) {
		HashMap map = new HashMap();
		for (int i = 0; i < lst.size(); i++) {
			map.put(i, lst.get(i));
		}
		return map;

	}

	public static String dump(List lst, String... offset) {
		return dump(list2map(lst), offset);
	}

	// Pretty print a Lot. structure, for debugging purposes
	public static String dump(Map m, String... offset) {
		StringBuilder rv = new StringBuilder();
		String delta = offset.length == 0 ? "" : offset[0];

		for (Entry e : (Set<Map.Entry>) m.entrySet()) {
			rv.append(delta + e.getKey() + " : ");
			Object value = e.getValue();
			if (value instanceof Map)
				rv.append(">\n" + dump((Map) value, delta + " "));

			if (value instanceof Collection)
				rv.append("[" + join(",", (Collection) value) + "]\n");
			if (is_scalar(value))
				rv.append(value + "\n");
		}

		return rv.toString();
	}

	public static String word_wrap(String str, int width, String... args) {
		if (str == null)
			return null;
		String wrap_str = args.length > 0 ? args[0] : "\n";
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= str.length() / width; i++) {
			int start = i * width;
			int end = (i + 1) * width;
			end = end > str.length() ? str.length() : end;
			sb.append(str.substring(start, end) + wrap_str);

		}
		return sb.toString();
	}

	public static int rand(int max) {
		Random r = new Random();
		return r.nextInt(max);
	}

	public static String rand_numstr(int len) {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < len; i++)
			str.append(rand(10));
		return str.toString();
	}
	
	public static String rand_string(int len) {
		return rand_string("ABCDEFGHIJKLMNOPQRSTUVWXYZ", len);
	}

	public static String rand_string(String characters, int len) {
		Random rng = new Random();
		char[] text = new char[len];
		for (int i = 0; i < len; i++) {
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		return new String(text);
	}

	public static String pad_left(String str, int n) {
		return String.format("%1$" + n + "s", str);
	}

	public static String pad_right(String str, int n) {
		return String.format("%1$-" + n + "s", str);
	}

	public static String spaces(int cnt) {
		return (cnt == 0) ? "" : String.format("%" + cnt + "s", "");
	}

	public static void send_email(String server, String from, String to, String subject, String text) {
		Properties properties = System.getProperties();
		//Set up mail server
		properties.setProperty("mail.smtp.host", server);
		Session session =   Session.getDefaultInstance(properties);
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setText(text);
			Transport.send(message);
			say("Sent message :" + subject);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	public static void send_email (String from, String to, String subject, String text) {
		send_email("mailhost1.mycorp.inf.com", from, to, subject,text);
	}
	
	
	public static void main(String[] args) {
		say(rand_numstr(10));
		say(rand_string(20));
		say(word_wrap(null, 80, "I\n"));
	}
}
