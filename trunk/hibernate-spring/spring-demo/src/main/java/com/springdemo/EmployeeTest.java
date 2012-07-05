/* ******************************************************************************
 * 	
 * 	Name	: EmployeeTest.java
 * 	Type	: com.springdemo.EmployeeTest
 * 
 * 	Created	: Jun 11, 2012
 * 	
 * 	Author	: Sabuj Das [ mailto::sabuj.das@gmail.com ]
 * 
 * -----------------------------------------------------------------------------*
 * 																				*
 * Copyright © Sabuj Das 2010 All Rights Reserved. 								*
 * <br/>No part of this document may be reproduced without written 				*
 * consent from the author.														*
 * 																				*
 ****************************************************************************** */

package com.springdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeTest {

	@Test public void testA(){System.out.println("testA");}
	
}
