package com.kosalaam.api.modules.prayerroom;

import com.kosalaam.api.common.RequiredAuthInterceptor;
import com.kosalaam.api.common.UnRequiredAuthInterceptor;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = PrayerroomController.class)
public class PrayerroomControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UnRequiredAuthInterceptor unRequiredAuthInterceptor;

    @MockBean
    private RequiredAuthInterceptor requiredAuthInterceptor;

    @MockBean
    private PrayerroomService prayerroomService;

    @BeforeEach
    void initTest() throws Exception{
        when(unRequiredAuthInterceptor.preHandle(any(), any(), any())).thenReturn(true);
        when(requiredAuthInterceptor.preHandle(any(), any(), any())).thenReturn(true);
    }

    @Test
    public void testGetPrayerrooms() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/prayerroom"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testSavePrayerroom() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/api/prayerroom"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
