package com.ming.slowly;

/**
 * @author: Lemon-XQ
 * @date: 2017/11/11
 */

public class Consts {
    private static String URL = "http://ldy.geomon.top/";
    public static String URL_Register = URL + "userdata/register";
    public static String URL_Login = URL + "userdata/login";
    public static String URL_Absorb=URL+"abs/addAbsdata";
    public static String URL_breath=URL+"bre/addBredata";
    public static String URL_sleep=URL+"sleepdata/addsleepdata";
    public static String URL_getAbsorb=URL+"abs/selectAbsDate";
    public static String URL_getBreath=URL+"bre/selectBredata";
    public static String URL_ModifyPwd = URL + "ModifyPwdServlet";

    public static final int IMAGE_REQUEST_CODE = 0;
    public static final int CAMERA_REQUEST_CODE = 1;
    public static final int RESIZE_REQUEST_CODE = 2;
    public static final String IMAGE_FILE_NAME="tmp_avatar.jpg";

    // 服务器代码
    public static String ERRORCODE_NULL = "200";
    public static String ERRORCODE_PWD = "201";
    public static String ERRORCODE_ACCOUNTNOTEXIST = "202";
    public static String ERRORCODE_ACCOUNTEXIST = "203";
    public static String ERRORCODE_INSERT = "204";
    public static String ERRORCODE_FORMAT = "205";
    public static String ERRORCODE_AGE_INVALID = "206";
    public static String ERRORCODE_HEIGHT_INVALID = "207";
    public static String ERRORCODE_WEIGHT_FORMAT = "208";
    public static String ERRORCODE_OGTT_FORMAT = "209";

    public static String SUCCESSCODE_LOGIN = "200";
    public static String SUCCESSCODE_REGISTER = "1";

    // 代码对应信息
    public static String ERRORMSG_NULL = "不能为空";
    public static String ERRORMSG_PWD = "密码错误";
    public static String ERRORMSG_ACCOUNTNOTEXIST = "账号不存在，请注册";
    public static String ERRORMSG_ACCOUNTEXIST = "账号已存在，请登录";
    public static String ERRORMSG_INSERT = "插入信息失败";

    public static String SUCCESSMSG_LOGIN = "登录成功";
    public static String SUCCESSMSG_REGISTER = "注册成功";


    // 客户端提示信息
    public static String ERROR_FORMAT = "输入数据格式错误";
}
