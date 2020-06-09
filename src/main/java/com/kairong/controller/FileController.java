package com.kairong.controller;

import com.alibaba.fastjson.JSONObject;
import com.kairong.service.FileService;
import com.kairong.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.controller
 * @date:2020/6/1
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;


    @PostMapping("/uploadFileAndGenGcode")
    public CommonResult uploadFileAndGenGcode(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

        try {
            // , @RequestParam("commandLineMap") Map<String, String> commandLineMap

            Assert.notNull(file, "file null");

            String parameter = request.getParameter("commandLineMap");
            Map<String, String> commandLineMap = new HashMap<>();
            if (StringUtils.isNotBlank(parameter)) {
                JSONObject jsonObject = JSONObject.parseObject(parameter);
                jsonObject.forEach((k, v) -> commandLineMap.put(k, v.toString()));
            }

            String fileName = fileService.saveAndGenGcode(file, commandLineMap);
            if (null != fileName) {
                return CommonResult.success(fileName);
            } else {
                return CommonResult.failed("uploadFileAndGenGcode error: no file");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("uploadFileAndGenGcode, error", e);
            return CommonResult.failed(e.getMessage());
        }
    }


    @GetMapping("/downloadFile")
    public ResponseEntity<Resource> downloadFile(@RequestParam(name = "fileName") String fileName,
                                                 HttpServletRequest request, HttpServletResponse response) throws Exception {

        Assert.notNull(fileName, "fileName null");
        // Load file as Resource
        Resource resource = fileService.loadFileAsResource(fileName);
        String contentType = null;
        try {

            // Try to determine file's content type
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            // Fallback to the default content type if type could not be determined
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("downloadFile fileName[{}]", fileName, e);
        }
        response.setStatus(200);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);

    }

}
