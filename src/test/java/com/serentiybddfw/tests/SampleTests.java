package com.serentiybddfw.tests;

import org.apache.commons.logging.Log;
import org.junit.Test;
import com.serenitybddfw.utils.Props;


public class SampleTests {
	//private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(SampleTests.class);
	Props props = new Props("mysession1.props");
	
	@Test
	public void myTest(){
		props.set("firstname", "Sanam");
		props.set("lastname", "Karim");
		System.out.println(props.get("firstname") +" " +  props.get("lastname"));
	}
	
	@Test
	public void myTest1(){
		System.out.println(props.get("firstname") +" " +  props.get("lastname"));
		
	}

}
