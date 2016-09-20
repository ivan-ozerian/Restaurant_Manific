package com.ozerian.app.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(locations = {
        "file:src/main/webapp/WEB-INF/web-context.xml",
        "file:src/main/webapp/WEB-INF/application-context.xml",
        "file:src/main/webapp/WEB-INF/hibernate-context.xml",
        "file:src/main/webapp/WEB-INF/security-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MainControllerTest {

    private MockMvc mockMvc;
    private MainController mainController;

    @Before
    public void setUp() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/jsp/");
        viewResolver.setSuffix(".jsp");
        mainController = new MainController();
        mockMvc = MockMvcBuilders.standaloneSetup(mainController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testWelcomePage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/index.jsp"));
    }

    @Test
    public void testGreetingPage() throws Exception {
        mockMvc.perform(get("/greetingPage"))
                .andExpect(status().isOk())
                .andExpect(view().name("greetingPage"))
                .andExpect(forwardedUrl("/WEB-INF/views/jsp/greetingPage.jsp"));
    }
}