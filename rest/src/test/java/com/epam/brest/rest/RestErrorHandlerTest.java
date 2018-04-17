package com.epam.brest.rest;

import com.epam.brest.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.ParseException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Class RestErrorHandlerTest tests RestErrorHandler.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:rest-test.xml", "classpath:test-db-spring.xml", "classpath:dao.xml"})
@Rollback
public class RestErrorHandlerTest {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRestController studentRestController;
    @Autowired
    RestErrorHandler restErrorHandler;
    private static final int ID = 100;

    private MockMvc mockMvc;

    /**
     * Test set up method.
     */
    @Before
    public void testSetUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentRestController)
                .setControllerAdvice(restErrorHandler)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();
    }

    /**
     * Method handleException tests handle of exception by RestErrorHandler controller.
     *
     * @throws Exception exception
     */
    @Test
    public void handleException() throws Exception {

        mockMvc.perform(get("/students/{id}", ID))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("\"Exception:Record is absent\""));
    }

}