package com.kairong.service.impl;

import com.kairong.service.FileService;
import com.kairong.util.Slic3rUtil;
import com.kairong.util.ZipFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
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
    public String saveAndGenGcodeAndroid(MultipartFile file, Map<String, String> commandLineMap) throws IOException {

        /*if (file.getOriginalFilename().endsWith(".png")) {
            return saveImg(file);
        }*/

        String rsPath = null;

        System.err.println("originalFilename:" + file.getOriginalFilename());
        String filePath = slic3rUtil.getAndroidFilePath(Objects.requireNonNull(file.getOriginalFilename()));
        File saveFile = new File(filePath);
        if (!saveFile.getParentFile().exists() || !saveFile.getParentFile().isDirectory()) {
            saveFile.getParentFile().mkdirs();
        }
        file.transferTo(saveFile);

        String stlUnZipPath = filePath.substring(0, filePath.indexOf(".")) + ".stl";
        // filePath.replace(".zip", ".stl");


        ZipFileUtil.upZipFile(saveFile, saveFile.getParentFile().getAbsolutePath().replace("\\", "/"));
        //解压文件
        File stlUnZip = new File(stlUnZipPath);

        if (stlUnZip.exists() && stlUnZip.isFile()) {
            // 准备生成的gcode文件
            String stlGcode = stlUnZipPath.replace("stl", "gcode");
            boolean suc = slic3rUtil.exportGcode(stlUnZipPath, stlGcode, commandLineMap);
            if (suc) {
                ZipFileUtil.ZipFolder(stlGcode, stlGcode + ".zip");
                File gcodeZipFile = new File(stlGcode);
                if (gcodeZipFile.exists() && gcodeZipFile.isFile()) {
                    rsPath = stlGcode + ".zip";
                }
            }
        }
        return rsPath;
    }


    @Override
    public String saveAndGenGcodeIos(MultipartFile file, Map<String, String> commandLineMap, String uuid) throws IOException {

        /*if (file.getOriginalFilename().endsWith(".png")) {
            return saveImg(file);
        }*/

        String rsPath = null;

        System.err.println("originalFilename:" + file.getOriginalFilename());
        String filePath = slic3rUtil.getIosFilePath(Objects.requireNonNull(file.getOriginalFilename()), uuid);
        File saveFile = new File(filePath);
        if (!saveFile.getParentFile().exists() || !saveFile.getParentFile().isDirectory()) {
            saveFile.getParentFile().mkdirs();
        }


        file.transferTo(saveFile);

        String stlUnZipPath = filePath.replace(".zip", ".stl");


        ZipFileUtil.upZipFile(saveFile, saveFile.getParentFile().getAbsolutePath().replace("\\", "/"));
        //解压文件
        File stlUnZip = new File(stlUnZipPath);

        if (stlUnZip.exists() && stlUnZip.isFile()) {
            // 准备生成的gcode文件
            String stlGcode = stlUnZipPath.replace("stl", "gcode");
            boolean suc = slic3rUtil.exportGcode(stlUnZipPath, stlGcode, commandLineMap);
            if (suc) {
                ZipFileUtil.ZipFolder(stlGcode, stlGcode + ".zip");
                File gcodeZipFile = new File(stlGcode);
                if (gcodeZipFile.exists() && gcodeZipFile.isFile()) {
                    rsPath = stlGcode + ".zip";
                }
            }
        }
        return rsPath;
    }


    @Override
    public String saveStlImgIos(MultipartFile file, String uuid) throws IOException {

        String filePath = null;
        try {
            System.err.println("originalFilename:" + file.getOriginalFilename());
            filePath = slic3rUtil.getIosFilePath(Objects.requireNonNull(file.getOriginalFilename()), uuid);
            File saveFile = new File(filePath);
            if (!saveFile.getParentFile().exists() || !saveFile.getParentFile().isDirectory()) {
                saveFile.getParentFile().mkdirs();
            }
            file.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    private String saveImg(MultipartFile file) {
        String filePath = null;
        try {
            filePath = slic3rUtil.getAndroidFilePath(Objects.requireNonNull(file.getOriginalFilename()));
            File saveFile = new File(filePath);
            if (!saveFile.getParentFile().exists() || !saveFile.getParentFile().isDirectory()) {
                saveFile.getParentFile().mkdirs();
            }
            file.transferTo(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    @Override
    public Resource loadAndroidFileAsResource(String fileName) throws Exception {
        return slic3rUtil.loadAndroidFileAsResource(fileName);
    }


    @Override
    public Resource loadIosFileAsResource(String fileName, String uuid) throws Exception {
        return slic3rUtil.loadIosFileAsResource(fileName, uuid);
    }

}
