package org.javaboy.vhr.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.UUID;

public class MD5 {
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String md5(String key) {
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
