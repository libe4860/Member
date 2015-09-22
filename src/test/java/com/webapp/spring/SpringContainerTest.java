package com.webapp.spring;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.StringContent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringContainerTest {

	static Log log = LogFactory.getLog(SpringContainerTest.class);
	
	static BeanFactory factory;
	SimpleDateFormat format;
	
	public void setFormat(SimpleDateFormat format){
		this.format = format;
	}
	
	public void print() throws InterruptedException{
		for(int i=0; i<5; i++){
			Date curr = factory.getBean(Date.class);
			log.info("Current Time =" + format.format(curr));
			Thread.sleep(1000);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		factory = new FileSystemXmlApplicationContext("beans.xml");
		
		
		
		
	}

}
