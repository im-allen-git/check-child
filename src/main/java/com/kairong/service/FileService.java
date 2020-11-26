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


    String saveAndGenGcodeAndroid(MultipartFile file, Map<String, String> commandLineMap) throws IOException;

    String saveAndGenGcodeIos(MultipartFile file, Map<String, String> commandLineMap, String uuid) throws IOException;

    String saveStlImgIos(MultipartFile file, String uuid) throws IOException;

    Resource loadAndroidFileAsResource(String filePath) throws Exception;

    Resource loadIosFileAsResource(String filePath, String uuid) throws Exception;
}
