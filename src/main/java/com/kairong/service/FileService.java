package com.kairong.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.service
 * @date:2020/6/8
 */
public interface FileService {


    String saveAndGenGcode(MultipartFile file, Map<String, String> commandLineMap) throws IOException;

    Resource loadFileAsResource(String filePath) throws Exception;
}
