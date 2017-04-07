package com.demo.console.test;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.nio.charset.Charset;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:test-context.xml" })
@WebAppConfiguration
public abstract class BaseTestCase {
  protected MediaType jsonContentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
      MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

  protected MediaType textHtmlContentType = new MediaType(MediaType.TEXT_HTML.getType(),
      MediaType.TEXT_HTML.getSubtype(), Charset.forName("utf8"));

  @Autowired
  protected WebApplicationContext wac;
  protected MockMvc mockMvc;


  @Before
  @Transactional(value = "sessionTxm", propagation = Propagation.REQUIRES_NEW)
  public void setup() {
    System.out.println("\n\nCalling setup by :::::::::::::::::: " + this.getClass().getName());
    this.mockMvc = webAppContextSetup(this.wac).build();

   // testMasterDataGenerator.populateMriParameterMaster();

  }

  @After
  public void teardown() {
    System.out.println("Calling teardown by :::::::::::::::::: " + this.getClass().getName());
   // testMasterDataGenerator.removeMriParameterMaster();

  }
}
