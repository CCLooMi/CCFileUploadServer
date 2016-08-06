package com.ccloomi.core.constant;

import java.util.Locale;
/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：Constant
 * 类 描 述：核心常量类
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:20:21
 */
public class Constant {
	public static final int EXCEL_MAX_ROW=65535;
	public static final Locale DEFAULT_LOCALE = Locale.getDefault();
	public static final String FILE_SEPARATOR = System.getProperty( "file.separator" );
	public static final String FTP_FILE_SEPARATOR = "/";
	public static final String UTF8="UTF-8";
	public static final int DEFAULT_BUFFER=1024;
	/**验证码时效（2分钟）*/
	public static final long SECURITY_CODE_MAX_LIVE_TIME=180000;
	/**令牌时效（一星期）*/
	public static final long TOKEN_MAX_LIVE_TIME=604800000;
	public static final int blobSize=1048576;
}
