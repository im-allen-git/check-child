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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: JiangXW
 * @version: v1.0
 * @description: com.kairong.util
 * @date:2020/6/8
 */
@Slf4j
@Component
public class Slic3rUtil {

    private static AtomicInteger aLong = new AtomicInteger(0);

    @Value("${file.upload-dir.android}")
    private String uploadDirAndroid;
    @Value("${file.upload-dir.ios}")
    private String uploadDirIos;

    @Value("${slic3r.console1}")
    private String slic3rConsole1;
    @Value("${slic3r.console2}")
    private String slic3rConsole2;
    @Value("${slic3r.console3}")
    private String slic3rConsole3;

    private static final String CONFIG_INT_KEY = "configInt";


    private Path fileAndroidPath;
    private Path fileIosPath;

    /**
     * 生成上传的文件名称全路径
     *
     * @param fileName
     * @return
     */
    public String getAndroidFilePath(String fileName) {

        String month = TimeUtil.getTimeWithYearAndMonth(LocalDateTime.now());
        // Random random = new Random();
        // int nextInt = random.nextInt(9999);

        // String endSuffix = fileName.substring(fileName.lastIndexOf("."));
        // return uploadDir.replace("\\", "/") + "/" + month + "/" + System.currentTimeMillis() + "_" + nextInt + endSuffix;
        if (fileName.contains("/")) {
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        }
        return uploadDirAndroid.replace("\\", "/") + "/" + month + "/" + fileName;
    }

    public String getIosFilePath(String fileName, String uuid) {

        if (fileName.contains("/")) {
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
        }
        return uploadDirIos.replace("\\", "/") + getIosUUid(uuid) + "/" + fileName;
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
        StringBuffer sb = new StringBuffer(getSlic3rConsole());
        sb.append(" " + filePath);
        // 判断是否精细打印
        int configInt = 0;


        if (null != commandLineMap && commandLineMap.size() > 0) {
            if (commandLineMap.containsKey(CONFIG_INT_KEY)) {
                configInt = Integer.parseInt(commandLineMap.get(CONFIG_INT_KEY));
                commandLineMap.remove(CONFIG_INT_KEY);
            }
            commandLineMap.forEach((k, v) -> sb.append(" " + k + " " + v));
        }
        if (configInt > 0) {
            sb.append(" --output " + outputPath + " --load config.ini");
        } else {
            sb.append(" --output " + outputPath + " --load config1.ini");
        }
        // sb.append(" --output " + outputPath);
        System.err.println(sb.toString());
        return exportGcodeByCommandLine(sb.toString(), outputPath);
    }


    private synchronized String getSlic3rConsole() {
        String tempStr;
        switch (aLong.get() % 3) {
            case 0:
                tempStr = slic3rConsole3;
                break;
            case 1:
                tempStr = slic3rConsole1;
                break;
            case 2:
                tempStr = slic3rConsole2;
                break;
            default:
                tempStr = slic3rConsole1;
                break;

        }
        int tempInt = aLong.addAndGet(1);
        System.err.println("tempInt:" + tempInt);
        if (aLong.get() > 100000000) {
            aLong.set(1);
        }
        return tempStr;
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

    public Resource loadAndroidFileAsResource(String fileName) throws Exception {
        try {
            if (null == fileAndroidPath) {
                fileAndroidPath = Paths.get(uploadDirAndroid).toAbsolutePath().normalize();
            }
            Path findPath = fileAndroidPath.resolve(fileName).normalize();
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

    public Resource loadIosFileAsResource(String fileName, String uuid) throws Exception {
        try {
            if (null == fileIosPath) {
                fileIosPath = Paths.get(uploadDirIos + "/" + getIosUUid(uuid)).toAbsolutePath().normalize();
            }
            Path findPath = fileIosPath.resolve(fileName).normalize();
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


    private String getIosUUid(String uuid) {
        return "/" + uuid.substring(0, 2) + "/" + uuid;
    }
}
