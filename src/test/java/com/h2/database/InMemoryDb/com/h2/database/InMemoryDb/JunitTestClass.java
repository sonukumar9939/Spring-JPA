package com.h2.database.InMemoryDb.com.h2.database.InMemoryDb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import code.DataBaseProject.DataBaseProjectApplication;
import code.DataBaseProject.models.User;
import code.DataBaseProject.service.UserSetupService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataBaseProjectApplication.class)
public class JunitTestClass {

	private static Logger logger= LoggerFactory.getLogger(JunitTestClass.class);
	
	@Autowired
	UserSetupService usersetupService;
	 

	@Test
	@DirtiesContext
	public void updateEntity() {
		User user = usersetupService.findById(68);
		User UserDetails =usersetupService.findById(66);
		logger.info("UserName " + user.getUsername());
		usersetupService.updateEntity(user, UserDetails);
		logger.info(user.getUsername());
		assertEquals("thumsup", user.getUsername());

	}

}
