package com.kairong.controller;

import com.alibaba.fastjson.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.controller
 * @date:2020/6/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class FileControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }


    @Test
    public void testUploadFileAndGenGcode() throws Exception {
        File testFile = new File("F:/3d/test/123.stl");


        Map<String, String> commandLineMap = new HashMap<>();

        MockMultipartFile uploadFile = new MockMultipartFile("file", testFile.getName(), "multipart/form-data", Files.readAllBytes(Paths.get(testFile.getAbsolutePath())));


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/file/uploadFileAndGenGcode")
                .file(uploadFile)
                .param("commandLineMap", commandLineMap.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String rs = result.getResponse().getContentAsString();

        Assert.assertNotNull("rs null", rs);
        JSONObject json = JSONObject.parseObject(rs);
        Assert.assertTrue("code not 200", json != null && json.containsKey("code") && 200 == json.getInteger("code"));
        System.err.println(rs);
    }


    @Test
    public void testDownloadFile() throws Exception {
        /*String fileName = "F:/threeModel/2020-06/1591596207748_6255.gcode";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/file/downloadFile/" + fileName))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();*/

    }
}