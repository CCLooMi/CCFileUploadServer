package com.ccloomi.core.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：StringUtil
 * 类 描 述：字符串工具类
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:20:33
 */
public class StringUtil {

	/**
	 * 方法描述：字符串格式化
	 * 作者：Chenxj
	 * 日期：2015年6月18日 - 下午4:50:07
	 * @param f
	 * @param obj
	 * @return StringBuilder
	 */
	public static StringBuilder format(StringBuilder f,Object...objs){
		StringBuilder sb=new StringBuilder();
		int count=0;
		int flag=0;
		char a="?".charAt(0);
		String b="";
		for(int i=0;i<f.length();i++){
			if(a==f.charAt(i)){
				String s=objs[count].toString();
				f.replace(i,i+1,b);
				sb.append(f.substring(flag, i)).append(s);
				flag=i;
				count++;
			}
		}
		sb.append(f.substring(flag, f.length()));
		return sb;
	}
	/**
	 * 方法描述：字符串格式化
	 * 作者：Chenxj
	 * 日期：2015年6月18日 - 下午4:58:56
	 * @param string
	 * @param objs
	 * @return String
	 */
	public static String format(String string,Object...objs){
		return format(new StringBuilder(string), objs).toString();
	}
	/**
	 * 方法描述：字符串连接
	 * 作者：Chenxj
	 * 日期：2015年6月18日 - 下午5:20:32
	 * @param s
	 * @param objs
	 * @return StringBuilder
	 */
	@SafeVarargs
	public static <T>StringBuilder join(String s,T...tt){
		StringBuilder sb=new StringBuilder();
		int l=tt.length;
		if(l>0){
			Object obj0=tt[0];
			if(obj0 instanceof Object[]){
				sb.append("[").append(join(s,(Object[])obj0)).append("]");
			}else{
				sb.append(tt[0]);
			}
			for(int i=1;i<l;i++){
				Object obji=tt[i];
				if(obji instanceof Object[]){
					sb.append(s).append("[").append(join(s, (Object[])obji)).append("]");
				}else{
					sb.append(s).append(tt[i]);
				}
			}
			return sb;
		}else{
			return sb;
		}
	}
	/**
	 * 方法描述：连接字符串数组
	 * 作者：Chenxj
	 * 日期：2015年6月18日 - 下午5:22:01
	 * @param s
	 * @param objs
	 * @return String
	 */
	@SafeVarargs
	public static <T>String joinString(String s,T...tt){
		return join(s, tt).toString();
	}
	
	/**
	 * 获取32位字符串  生成数据库ID
	 * 
	 * @return
	 */
	public static final String buildUUID() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}
	/**
	 * 方法描述：判断字符串是否为邮箱
	 * 作        者：Chenxj
	 * 日        期：2015年7月16日-下午1:03:50
	 * @param string
	 * @return
	 */
	public static boolean isEmail(String string){
		boolean is=false;
		Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		Matcher mat=pattern.matcher(string);
		if(mat.find()){
			is=true;
		}
		return is;
	}
	/**
	 * 方法描述：判断字符串是否为数字
	 * 作        者：Chenxj
	 * 日        期：2015年7月16日-下午1:03:55
	 * @param string
	 * @return
	 */
	public static boolean isNumeric(String string){
		int l=string.length();
		for(int i=0;i<l;i++){
			if(!Character.isDigit(string.charAt(i))){
				return false;
			}
		}
		return true;
	}
	/**
	 * 方法描述：首字母大写
	 * 作        者：Chenxj
	 * 日        期：2015年11月20日-上午10:43:04
	 * @param str
	 * @return
	 */
	public static String upperCaseFirstLatter(String str){
		char[] strChar=str.toCharArray();
		strChar[0]-=32;
		return String.valueOf(strChar);
	}
	
	/**  
     * 使用java正则表达式去掉多余的.与0  
     * @param s  
     * @return   
     */    
    public static String  subZeroAndDot(Object s1){  
    	String s=s1.toString();
        if(s.indexOf(".") > 0){    
            s = s.replaceAll("0+?$", "");//去掉多余的0    
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉    
        }    
        return s;    
    } 	
    
    /**
	 * 方法描述：对数据四舍五入，并且保留decimal（如：2，3，4）位小数，并且对数据后面的0去掉
	 * 作        者：MaoSF
	 * 日        期：2015年11月16日-上午10:44:45
	 * @param o 处理的对象
	 * @param decimal 保留的小数位（后面是0的去掉）
	 * @return
	 */
	public static String formateNumber(Object o,int decimal){
		BigDecimal d= new BigDecimal(o.toString()).setScale(decimal, BigDecimal.ROUND_HALF_UP); 
		return subZeroAndDot(d);
	}
	
	/**
	 * 方法描述：正则匹配
	 * 作        者：Chenxj
	 * 日        期：2016年5月24日-上午9:52:37
	 * @param regex
	 * @param str
	 * @return
	 */
    public static boolean match(String regex, String str) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        return matcher.lookingAt();
    }
	public static long strtol(String str,int base){
		return strtol((str+"\0").toCharArray(),base);
	}
	public static long strtoul(String str,int base){
		return strtoul((str+"\0").toCharArray(),base);
	}
	
	//#####################################################################################
	//#
	//##
	//###                                一下所有为私有方法
	//##
	//#
	//#####################################################################################
	
	/**
	 * 描述：此方法只返回非负数
	 * 作者：Chenxj
	 * 日期：2016年7月20日 - 下午10:35:28
	 * @param cp
	 * @param base
	 * @return
	 */
	private static long strtoul(char[] cp,int base){
		long result=0,value;
		int i=0;
		if(base==0){
			base=10;
			if(cp[i]=='0'){
				base=8;
				i++;
				if(Character.toLowerCase(cp[i])=='x'&&isxdigit(cp[1])){
					i++;
					base=16;
				}
			}
		}else if(base==16){
			if(cp[0]=='0'&&Character.toLowerCase(cp[1])=='x')
				i+=2;
		}
		while(isxdigit(cp[i])&&(value = isdigit(cp[i]) ? cp[i]-'0' : Character.toLowerCase(cp[i])-'a'+10) < base){
			result=result*base+value;
			i++;
		}
		return result;
	}
	/**
	 * 描述：此会返回有符号数
	 * 作者：Chenxj
	 * 日期：2016年7月20日 - 下午10:36:08
	 * @param cp
	 * @param base
	 * @return
	 */
	private static long strtol(char[]cp,int base){
		if(cp[0]=='-'){
			return -strtoul(subChars(cp, 1),base);
		}
		return strtoul(cp, base);
	}
	/**
	 * 判断char是否是16进制以内的数
	 * @param c
	 * @return
	 */
	private static boolean isxdigit(char c){
		return ('0' <= c && c <= '9')||('a' <= c && c <= 'f')||('A' <= c && c <= 'F');
	}
	/**
	 * 判断char是否是10进制的数
	 * @param c
	 * @return
	 */
	private static boolean isdigit(char c){
		return '0' <= c && c <= '9';
	}
	/**
	 * 描述：char数组切分
	 * 作者：Chenxj
	 * 日期：2016年7月20日 - 下午10:37:59
	 * @param cp
	 * @param indexs
	 * @return
	 */
	private static char[] subChars(char[]cp,int...indexs){
		if(indexs.length==1){
			return Arrays.copyOfRange(cp, indexs[0], cp.length);
		}else if(indexs.length>1){
			return Arrays.copyOfRange(cp, indexs[0], indexs[0]+indexs[1]);
		}
		return cp;
	}
	public static void main(String[] args) {
		String md5="b1c5553067e312ff7f9fe0c456d1752b";
		System.out.println(strtol(md5.substring(0, 3),16)/4);
		System.out.println(strtol(md5.substring(3, 3+3),16)/4);
	}
}
