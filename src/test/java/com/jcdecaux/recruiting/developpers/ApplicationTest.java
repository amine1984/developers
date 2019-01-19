package com.jcdecaux.recruiting.developpers;

import com.jcdecaux.recruiting.developpers.domain.service.impl.DevelopersServiceImpl;
import com.jcdecaux.recruiting.developpers.domain.service.impl.LanguagesServiceImpl;
import com.jcdecaux.recruiting.developpers.service.rest.impl.DevelopersResourceImpl;
import com.jcdecaux.recruiting.developpers.service.rest.impl.LanguagesResourceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jcdecaux.recruiting.developpers.domain.repository.IdevelopersRepository;
import com.jcdecaux.recruiting.developpers.domain.repository.IlanguagesRepository;
import com.jcdecaux.recruiting.developpers.domain.service.IdevelopersService;
import com.jcdecaux.recruiting.developpers.domain.service.IlanguagesService;
import com.jcdecaux.recruiting.developpers.service.rest.IdevelopersResource;
import com.jcdecaux.recruiting.developpers.service.rest.IlanguagesResource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTest {
	
	@Autowired
	private IdevelopersResource developersResouce;
	@Autowired
	private DevelopersResourceImpl developersResourceImpl;
	@Autowired
	private IlanguagesResource languagesResource;
	@Autowired
	private LanguagesResourceImpl languagesResourceImpl;
	@Autowired
	private IdevelopersService developerService;
	@Autowired
	private DevelopersServiceImpl developersServiceImpl;
	@Autowired
	private IlanguagesService languagesService;
	@Autowired
	private LanguagesServiceImpl languagesServiceImpl;
	@Autowired
	private IdevelopersRepository developerRepository;
	@Autowired
	private IlanguagesRepository languagesRepository;
	
	@Test
	public void test_contextLoads() {
		Assert.assertNotNull(developersResouce.getClass());
		Assert.assertNotNull(languagesResource.getClass());
		Assert.assertNotNull(developersResourceImpl.getClass());
		Assert.assertNotNull(languagesResourceImpl.getClass());
		Assert.assertNotNull(developerService.getClass());
		Assert.assertNotNull(developersServiceImpl.getClass());
		Assert.assertNotNull(languagesService.getClass());
		Assert.assertNotNull(languagesServiceImpl.getClass());
		Assert.assertNotNull(developerRepository.getClass());
		Assert.assertNotNull(languagesRepository.getClass());
	}
	
	@Test
	public void applicationStarts() throws Exception {
		Application.main(new String[] {});
	}

}
