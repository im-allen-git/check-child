package com.kairong.controller;

import com.alibaba.fastjson.JSONObject;
import com.kairong.service.FileService;
import com.kairong.util.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Value("${file.upload-dir.android}")
    private String uploadDir;


    @PostMapping("/uploadFileAndGenGcodeAndrioid")
    public CommonResult uploadFileAndGenGcodeAndrioid(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

        try {
            // , @RequestParam("commandLineMap") Map<String, String> commandLineMap

            Assert.notNull(file, "file null");

            request.getParameterMap().forEach((k, v) -> System.err.println("k:" + k + ",v:" + Arrays.toString(v)));

            String parameter = request.getParameter("commandLineMap");
            Map<String, String> commandLineMap = new HashMap<>();
            if (StringUtils.isNotBlank(parameter)) {
                JSONObject jsonObject = JSONObject.parseObject(parameter);
                jsonObject.forEach((k, v) -> commandLineMap.put(k, v.toString()));
            }

            String fileName = fileService.saveAndGenGcodeAndroid(file, commandLineMap);
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


    @PostMapping("/uploadFileAndGenGcodeIos")
    public CommonResult uploadFileAndGenGcodeIos(HttpServletRequest request, @RequestParam("file") MultipartFile[] files) {

        try {
            // , @RequestParam("commandLineMap") Map<String, String> commandLineMap

            Assert.notNull(files, "file null");

            request.getParameterMap().forEach((k, v) -> System.err.println("k:" + k + ",v:" + Arrays.toString(v)));

            String parameter = request.getParameter("commandLineMap");
            Map<String, String> commandLineMap = new HashMap<>();
            if (StringUtils.isNotBlank(parameter)) {
                JSONObject jsonObject = JSONObject.parseObject(parameter);
                jsonObject.forEach((k, v) -> commandLineMap.put(k, v.toString()));
            }

            String uuid = request.getParameter("uuid");
            if (StringUtils.isBlank(uuid)) {
                return CommonResult.failed("uuid null");
            }

            int count = 0;
            Map<String, String> rsMap = new HashMap<>();
            for (MultipartFile file : files) {
                if (file.getOriginalFilename().contains(".zip")) {
                    String fileName = fileService.saveAndGenGcodeIos(file, commandLineMap, uuid);
                    if (StringUtils.isNotBlank(fileName)) {
                        rsMap.put("gcode", fileName);
                        count++;
                    }
                } else if (file.getOriginalFilename().contains(".png")) {
                    String fileName = fileService.saveStlImgIos(file, uuid);
                    if (StringUtils.isNotBlank(fileName)) {
                        rsMap.put("img", fileName);
                        count++;
                    }
                }
            }

            if (count == files.length) {
                return CommonResult.success(JSONObject.toJSONString(rsMap));
            } else {
                return CommonResult.failed("uploadFileAndGenGcodeIos error: no file");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("uploadFileAndGenGcodeIos, error", e);
            return CommonResult.failed(e.getMessage());
        }
    }


    @GetMapping("/downloadFileAndroid")
    public ResponseEntity<Resource> downloadAndroidFile(@RequestParam(name = "fileName") String fileName,
                                                        HttpServletRequest request, HttpServletResponse response) throws Exception {

        Assert.notNull(fileName, "fileName null");
        // Load file as Resource
        Resource resource = fileService.loadAndroidFileAsResource(fileName);
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

    @GetMapping("/downloadFileIos")
    public ResponseEntity<Resource> downloadIosFile(@RequestParam(name = "fileName") String fileName,
                                                    @RequestParam(name = "uuid") String uuid,
                                                    HttpServletRequest request, HttpServletResponse response) throws Exception {

        Assert.notNull(fileName, "fileName null");
        // Assert.notNull(uuid, "uuid null");
        // Load file as Resource
        Resource resource = fileService.loadIosFileAsResource(fileName, uuid);
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

    @CrossOrigin("*")
    @PostMapping("/testUpload")
    public CommonResult testUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {

        try {
            // , @RequestParam("commandLineMap") Map<String, String> commandLineMap

            Assert.notNull(file, "file null");

            String fileName = uploadDir + "/" + System.currentTimeMillis() + "_" + file.getOriginalFilename();
            file.transferTo(new File(fileName));
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


}
