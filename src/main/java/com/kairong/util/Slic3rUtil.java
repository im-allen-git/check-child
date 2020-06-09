package com.kairong.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.util
 * @date:2020/6/8
 */
@Slf4j
@Component
public class Slic3rUtil {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${slic3r.console}")
    private String slic3rConsole;


    private Path filePath;

    /**
     * 生成上传的文件名称全路径
     *
     * @param fileName
     * @return
     */
    public String getFilePath(String fileName) {

        String month = TimeUtil.getTimeWithYearAndMonth(LocalDateTime.now());
        Random random = new Random();
        int nextInt = random.nextInt(9999);

        String endSuffix = fileName.substring(fileName.lastIndexOf("."));
        return uploadDir.replace("\\", "/") + "/" + month + "/" + System.currentTimeMillis() + "_" + nextInt + endSuffix;
    }

    /**
     * 生成gcode文件
     *
     * @param filePath
     * @param outputPath
     * @param commandLineMap
     * @return
     */
    public boolean exportGcode(String filePath, String outputPath, Map<String, String> commandLineMap) {
        // 组合commandLine命令

        // F:\WorkSoft\Slic3r-1.3.0.64bit\Slic3r-console F:\3d\123.stl --layer-height 0.2 --output F:\456.gcode
        StringBuffer sb = new StringBuffer(slic3rConsole);
        sb.append(" " + filePath);
        if (null != commandLineMap && commandLineMap.size() > 0) {
            commandLineMap.forEach((k, v) -> sb.append(" " + k + " " + v));
        }
        sb.append(" --output " + outputPath);
        System.err.println(sb.toString());
        return exportGcodeByCommandLine(sb.toString(), outputPath);
    }


    /**
     * 使用commandLine生成gcode文件
     *
     * @param commandLine
     * @param outputPath
     * @return
     */
    private boolean exportGcodeByCommandLine(String commandLine, String outputPath) {

        File file = new File(outputPath);
        if (file.exists() && !file.isDirectory()) {
            file.delete();
        }

        Runtime rn = Runtime.getRuntime();
        Process process = null;
        InputStreamReader inputStr = null;
        BufferedReader br = null;
        try {
            // process = rn.exec("F:\\WorkSoft\\Slic3r-1.3.0.64bit\\Slic3r.exe F:\\3d\\123.stl");
            process = rn.exec(commandLine);
            inputStr = new InputStreamReader(process.getInputStream());
            br = new BufferedReader(inputStr);
            String temp = "";
            log.info(outputPath + " export gcode begin!!");
            System.out.println(outputPath + " export gcode begin!!");
            while ((temp = br.readLine()) != null) {
                log.info(temp);
                System.out.println(temp);
            }
            log.info(outputPath + " export gcode end!!");
            System.out.println(outputPath + " export gcode end!!");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("exportGcode commandLine[{}] error:", commandLine, e);
            return false;
        } finally {
            if (null != process) {
                process.destroy();
            }
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != inputStr) {
                try {
                    inputStr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        file = new File(outputPath);
        return file.exists() && !file.isDirectory();
    }

    public Resource loadFileAsResource(String fileName) throws Exception {
        try {
            if (null == filePath) {
                filePath = Paths.get(uploadDir).toAbsolutePath().normalize();
            }
            Path findPath = filePath.resolve(fileName).normalize();
            Resource resource = new UrlResource(findPath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new Exception("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new Exception("File not found " + fileName, ex);
        }
    }
}
