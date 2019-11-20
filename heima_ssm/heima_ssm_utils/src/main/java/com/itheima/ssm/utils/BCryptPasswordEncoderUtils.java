/**
 * FileName: BCryptPasswordEncoderUtils
 * Author:   #include
 * Date:     2019/10/20 15:40
 * Description:
 */
package com.itheima.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 对密码进行加密的工具类
 */
public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }


    public static void main(String[] args) {
        String str = "123";

        String s = encodePassword(str);
        System.out.println(s);
        //$2a$10$jbIc2Zcz1WPGFPG8GO9Kk.sGZsaGVIIapat8eCPQcEervHvL1NWPe

    }

}
