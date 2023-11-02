package com.laptrinhjavaweb.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.lang.StringUtils;

@Component
public class UploadFileUtils {

    public void writeOrUpdate(String path, byte[] bytes) {
        path = "C://home/office" + path;
        File file = new File(StringUtils.substringBeforeLast(path, "/"));
        if (!file.exists()) {
            file.mkdir();
        }
        try (FileOutputStream fop = new FileOutputStream(path)) {
            fop.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
