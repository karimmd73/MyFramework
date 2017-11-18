package com.serenitybddfw.utils;
/*
* This class is completed.
*/
import static com.serenitybddfw.utils.Utility.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Props {

	public String dir = "target/tmp";
	public String file_name;
	public Properties props = new Properties();
	public final static Logger log = LogManager.getLogger(Props.class);

	public Props(String dir, String file_name) {
		this.dir = dir;
		this.file_name = file_name;
		load();
	}

	public Props(String file_name) {

		this.file_name = file_name;
		load();
	}

	public void log_assert(String msg) {
		log.error(msg);
		throw new AssertionError(msg);
	}

	public void load() {
		try {
			InputStream in = new FileInputStream(dir + "/" + file_name);
			System.out.println(dir + "/" + file_name);
			props.load(in);
			in.close();
		} catch (Exception e) {
			log.debug("Can't load props file " + e.getMessage());
		}
	}

	public void save() {
		try {
			File file = new File(dir);
			file.mkdirs();
			FileOutputStream out = new FileOutputStream(dir + "/" + file_name);
			props.store(out, "comments");
			out.close();

		} catch (IOException e) {
			log_assert("Can't store props file :" + e.getMessage());
		}

	}

	public String get(String name) {
		return props.getProperty(name);
	}

	public void set(String name, String value) {
		props.setProperty(name, value);
		save();
	}

	public static void main(String[] args) {
		//org.apache.log4j.BasicConfigurator.configure();
		
		Props p = new Props("test.props");
		p.set("a", "val");
		p.save();
	}
}
