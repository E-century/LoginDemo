package com.mydemo.utils;

/**
 * @ClassName FormatUtil
 * @Description TODO
 * @Author Administrator
 * @Date 2021-08-05 11:23
 */
public class FormatUtil {

    /**
     * 功能：验证密码
     */

    //数字
    public static final String REG_NUMBER = ".*\\d+.*";
    //小写字母
    public static final String REG_UPPERCASE = ".*[A-Z]+.*";
    //大写字母
    public static final String REG_LOWERCASE = ".*[a-z]+.*";
    //特殊符号
    public static final String REG_SYMBOL = ".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*";

    public static boolean isPassWord(String password) {
        //密码为空或者长度小于8位则返回false
       if (password == null) return false;
        int i = 0;
        if (password.matches(REG_NUMBER)) i++;
        if (password.matches(REG_LOWERCASE)) i++;
        if (password.matches(REG_UPPERCASE)) i++;
        if (password.matches(REG_SYMBOL)) i++;

        if (i < 3) return false;
        return true;
    }

    public static  boolean isUser(String user){
        if (user ==null)
            return false;
        if (user.matches(REG_SYMBOL)){
            return false;
        }else{
            return true;
        }
    }

}
