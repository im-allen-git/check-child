package com.kairong.service.impl;

import com.kairong.service.FileService;
import com.kairong.util.Slic3rUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.service.impl
 * @date:2020/6/8
 */
@Service
public class FileServiceImpl implements FileService {

    private final Slic3rUtil slic3rUtil;


    @Autowired
    public FileServiceImpl(Slic3rUtil slic3rUtil) {

        this.slic3rUtil = slic3rUtil;
    }


    @Override
    public String saveAndGenGcode(MultipartFile file, Map<String, String> commandLineMap) throws IOException {

        String filePath = slic3rUtil.getFilePath(Objects.requireNonNull(file.getOriginalFilename()));
        File saveFile = new File(filePath);
        if (!saveFile.getParentFile().exists() || !saveFile.getParentFile().isDirectory()) {
            saveFile.getParentFile().mkdirs();
        }
        file.transferTo(saveFile);
        boolean suc = slic3rUtil.exportGcode(filePath, filePath.replace("stl", "gcode"), commandLineMap);
        return suc ? filePath : null;
    }

    @Override
    public Resource loadFileAsResource(String fileName) throws Exception {
        return slic3rUtil.loadFileAsResource(fileName);
    }


}
