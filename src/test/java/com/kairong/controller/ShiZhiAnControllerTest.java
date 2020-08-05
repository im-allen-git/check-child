package com.kairong.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.controller
 * @date:2020/6/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ShiZhiAnControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void testRegister() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/shiZhiAn/register")
                .param("phone", "1393939439")
                .param("mac_address", "mac_address")
                .param("wechat_ssid", "wechat_ssid")
                .param("wechat_name", "hello")
                .param("gender", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        String rs = result.getResponse().getContentAsString();

        Assert.assertNotNull("rs null", rs);
        JSONObject json = JSONObject.parseObject(rs);
        Assert.assertTrue("code not 200", json != null && json.containsKey("code") && 200 == json.getInteger("code"));
        System.err.println(rs);
    }


}