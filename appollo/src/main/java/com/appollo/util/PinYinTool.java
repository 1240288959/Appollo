package com.appollo.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 
 * 拼音工具类
 * @author 谭洋
 *
 */
public class PinYinTool {
	
    /**
     * 将汉字转换为全拼的拼音。
     * @author 谭洋
     * @param isCapital true表示返回大写形式，false表示返回小写形式。
     */
    public static String getPinYinAllChar(String zn_str, boolean isCapital) {
        char[] strChar = zn_str.toCharArray();
        HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();
        // 输出设置，大小写，音标方式等
        if(isCapital) {
        	hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        } else {
        	hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        }
        hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
       
        StringBuffer pyStringBuffer = new StringBuffer();
        String[] strString = new String[strChar.length];
        try {
            for (int i = 0; i < strChar.length; i++) {
                if (Character.toString(strChar[i]).matches("[\\u4E00-\\u9FA5]+")) {//如果是汉字字符
                	strString = PinyinHelper.toHanyuPinyinStringArray(strChar[i], hanYuPinOutputFormat);//将汉字的几种全拼都存到strString数组中
                    pyStringBuffer.append(strString[0]);//取出该汉字全拼的第一种读音并连接到字符串pyStringBuffer后
                } else {//如果不是汉字字符，直接取出字符并连接到字符串pyStringBuffer后
                	pyStringBuffer.append(Character.toString(strChar[i]));
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return pyStringBuffer.toString();
    }

    /**
     * 输入中文名字用来获取默认用户名
     * @author 谭洋
     * @param real_name
     * @return	String
     */
	public static String getDefalutUserName(String real_name) {
		String str="";
		for(int i=0;i<real_name.length();i++) {
			String s=PinYinTool.getPinYinAllChar(real_name.substring(i, i+1), false);
			if(i==0) {
				str+=s;
			}else {
				str+=s.substring(0,1);
			}				
		}
		return str;
	}
}






