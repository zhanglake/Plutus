package com.plutus.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/20.
 */
public class CommonUtils {

    public static final String ORDER_CODE_HEAD_A = "XH";

    public static String genOrderCode(String codeHead) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddhhmmss");
        String code = codeHead + sdf.format(date);
        return code;
    }
}
