package com.gyang.learn.log4j;

import org.apache.log4j.Logger;

/**
 * 日志输出的简单测试类
 * 
 * @author gyang
 *
 */
public class HelloLog4j {

	/**
	 * @param args
	 */
	private static Logger logger = Logger.getLogger(HelloLog4j.class);
	  
    /** 
     * @param args 
     */  
    public static void main(String[] args) {  
        // 记录debug级别的信息  
        logger.debug("This is debug message.");  
        // 记录info级别的信息  
        logger.info("This is info message.");  
        // 记录error级别的信息  
        logger.error("This is error message."); 
        
        display();
    }  

    private static void display(){
    	System.out.println("Hello!");
    	logger.debug("display() : This is debug message.");  
    }
}
