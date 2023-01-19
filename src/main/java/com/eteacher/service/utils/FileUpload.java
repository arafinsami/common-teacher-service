package com.eteacher.service.utils;

import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class FileUpload {

    protected String upload(MultipartFile file, String uploadPath) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");
        String date = dateFormat.format(new Date());
        String name = date + file.getOriginalFilename().replace(' ', '-');
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File dir = new File(uploadPath + "/");
                if (!dir.exists())
                    dir.mkdirs();
                File serverFile = new File(dir + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                return name;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    protected String upload(String filePath, String encodedStr, String fileName, String fileType) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-");
        String date = dateFormat.format(new Date());
        String name = date + fileName;
        if (Objects.nonNull(encodedStr)) {
            try {
                byte[] fileBytes = Base64Utils.decodeFromString(encodedStr);
                File dir = new File(filePath + "/");
                if (!dir.exists())
                    dir.mkdirs();
                File serverFile = new File(
                        dir + File.separator + name + "." + fileType
                );
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(fileBytes);
                stream.close();
                //return serverFile.getAbsolutePath();
                return name;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }
}
