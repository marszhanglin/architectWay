package com.mars.maven;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HelloWorldT
{
	@Test
	public  void t1()
	{
		HelloWorld h=new HelloWorld();
		String result = h.say();
		
		System.out.println(result);
		
		assertEquals("hello-" , result);
		
		
	}
}