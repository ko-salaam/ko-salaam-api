package com.kosalaam.api.modules.place;

import com.kosalaam.api.common.AuthUtils;
import com.kosalaam.api.common.UnRequiredAuthInterceptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * PlaceController Tester.
 *
 * @author 배채윤
 * @since <pre>12월 31, 2021</pre>
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(controllers = PlaceController.class)
public class PlaceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UnRequiredAuthInterceptor unRequiredAuthInterceptor;

    @MockBean
    private AuthUtils authUtils;

    @Test
    public void testGetPlaces() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/common"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
