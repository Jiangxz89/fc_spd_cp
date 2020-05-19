package com.thinkgem.jeesite.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验各种类型数据是否填写规范
 * @author zxh
 *
 */
public class CheckUtils {
	
    /**
     * 校验手机号格式是否正确
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone){
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if(phone.length() != 11){
        	return false;
        }else{
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if(isMatch){
                return true;
            } else {
                return false;
            }
        }
    }
    
    /**
     * 校验邮箱格式是否正确
     * @param email
     * @return
     */
    public static boolean isEmail(String email){
    	if(email!=null && !email.equals("")){
            String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";  
            Pattern p = Pattern.compile(regex);  
            Matcher mat = p.matcher(email);  
            boolean isMatch = mat.matches();
            if(isMatch){
                return true;
            } else {
                return false;
            }  
    	}
    	return true;
    }
    
    /**
     * 校验金额是否正确
     * @param purPrice
     * @return
     */
	public static boolean isMoney(Double purPrice) {
		if(purPrice!=null){
			 Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
		        Matcher match=pattern.matcher(purPrice.toString()); 
		        if(match.matches()==false) { 
		           return false; 
		        }else{ 
		           return true; 
		        }
		}else{
			return false;
		}
       
	}
	
	/**
	 * 校验长度是否合理
	 * @param description
	 * @return
	 */
	public static boolean checkRemark(String description) {
		if(description.length()>1000){
			return false;
		}else{
			return true;
		}
		
	}  
    
}
