package com.mars.maven;

import org.junit.Test;

public class HelloWorldTest
{
	@Test
	public  void t1()
	{
		HelloWorld h=new HelloWorld();
		String result = h.say();
		
		System.out.println(result); 
		
	}
}