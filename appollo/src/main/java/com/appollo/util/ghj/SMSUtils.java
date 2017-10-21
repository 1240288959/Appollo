package com.appollo.util.ghj;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 鐭俊鐩稿叧鐨勫伐鍏风被
 * 
 * @author 楂樼剷鏉�
 */
public class SMSUtils {

    private static final String NONCE = "123456";
	private static final String APP_SECRET = "c1a9a80fe292";
    private static final String APP_KEY = "336cc5a5412462ecca4ae63d99e0d45e";
    
    /**
     * 妫�娴嬫墜鏈哄彿鏈夋晥鎬�
     * 
     * @param mobile 鎵嬫満鍙风爜
     * @return 鏄惁鏈夋晥
     */
    public static final boolean isMobile(String mobile){
        Pattern pattern = Pattern.compile("^((1[3578][0-9])|(14[57]))\\d{8}$");
        Matcher matcher = pattern.matcher(mobile);
        return matcher.matches();
    }
    
    /**
     * 楠岃瘉鐮佺被鐭俊锛屾敞鎰忥細璇ョ煭淇′腑楠岃瘉鐮佷笉鑳借嚜宸辩敓鎴愶紝鐢辩綉鏄撲簯甯垜浠敓鎴�
     * 
     * @param mobile 鎵嬫満鍙风爜
     * @param templateId 楠岃瘉鐮佹ā鏉縄D
     * @return 鏄惁鍙戦�佹垚鍔�
     */
    public static final boolean sendCode(String mobile, String templateId) throws IOException {
        HttpPost httpPost = new HttpPost("https://api.netease.im/sms/sendcode.action");

        String currentTime = String.valueOf(new Date().getTime()/1000L);
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET,NONCE,currentTime);

        //set header
        httpPost.setHeader("AppKey",APP_KEY);
        httpPost.setHeader("CurTime",currentTime);
        httpPost.setHeader("Nonce",NONCE);
        httpPost.setHeader("CheckSum",checkSum);
        httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        //set data
        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        nameValuePairList.add(new BasicNameValuePair("mobile",mobile));
        nameValuePairList.add(new BasicNameValuePair("templateid", templateId));
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList,"utf-8"));

        //start request
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
        String responseResult = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
//        System.out.println("responseResult:"+responseResult);

        String stateCode = JSON.parseObject(responseResult).getString("code");
        if(stateCode.equals("200")){
            return true;
        }
        return false;
    }

    /**
     * 鍒ゆ柇鐢ㄦ埛杈撳叆楠岃瘉鐮佷笌缃戞槗浜戠敓鎴愮殑楠岃瘉鐮佹槸鍚︿竴鑷�
     * 
     * @param mobile 鐢佃瘽鍙风爜
     * @param code 鍙戦�佸埌mobile涓婄殑鐭俊
     */
    public static final boolean verifyCode(String mobile, String code) throws IOException {
        HttpPost httpPost = new HttpPost("https://api.netease.im/sms/verifycode.action");

        String currentTime = String.valueOf(new Date().getTime()/1000L);
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET,NONCE,currentTime);

        //set header
        httpPost.setHeader("AppKey",APP_KEY);
        httpPost.setHeader("CurTime",currentTime);
        httpPost.setHeader("Nonce",NONCE);
        httpPost.setHeader("CheckSum",checkSum);
        httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        //set data
        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        nameValuePairList.add(new BasicNameValuePair("code",code));
        nameValuePairList.add(new BasicNameValuePair("mobile",mobile));
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList,"utf-8"));

        //start request
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
        String responseResult = EntityUtils.toString(httpResponse.getEntity(),"utf-8");
        System.out.println("responseResult:"+responseResult);

        String stateCode = JSON.parseObject(responseResult).getString("code");
        if(stateCode.equals("200")){
            return true;
        }
        return false;
    }
    
    /**
     * 閫氱煡绫荤煭淇℃垨杩愯惀绫荤煭淇★紝娉ㄦ剰锛氣憼銆佽绫荤煭淇￠獙璇佺爜鍙互鑷繁鐢熸垚锛涒憽銆佽繍钀ョ被鐭俊鑳藉彧鎻愪緵浼佷笟浣跨敤
     * 
     * @param mobile 鎵嬫満鍙风爜
     * @param params 鍙戦�佺殑娑堟伅锛屽彲浠ユ槸鑷畾涔夐獙璇佺爜
     * @param templateId 閫氱煡绫绘ā鏉縤d鎴栬繍钀ョ被妯℃澘id
     * @return 鏄惁鍙戦�佹垚鍔�
     */
    public static final boolean sendSmg(String mobiles, String params, String templateId) throws IOException {
        HttpPost httpPost = new HttpPost("https://api.netease.im/sms/sendtemplate.action");

        String currentTime = String.valueOf(new Date().getTime()/1000L);
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET,NONCE,currentTime);

        //set header
        httpPost.setHeader("AppKey",APP_KEY);
        httpPost.setHeader("CurTime",currentTime);
        httpPost.setHeader("Nonce",NONCE);
        httpPost.setHeader("CheckSum",checkSum);
        httpPost.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        //set data
        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
        nameValuePairList.add(new BasicNameValuePair("templateid", templateId));
        nameValuePairList.add(new BasicNameValuePair("mobiles", mobiles));
        nameValuePairList.add(new BasicNameValuePair("params", params));
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList, "utf-8"));

        //start request
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
        String responseResult = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
        System.out.println("responseResult:"+responseResult);

        String stateCode = JSON.parseObject(responseResult).getString("code");
        if(stateCode.equals("200")){
            return true;
        }
        return false;
    }
    
    /**
     * 鐢熸垚鐭俊楠岃瘉鐮�
     * 
     * @param length 闀垮害
     * @param numberCode 楠岃瘉鐮佺被鍨�
     * @return 鎸囧畾闀垮害鐨勯殢鏈虹煭淇￠獙璇佺爜
     */
    public static final String getRandomSMSCode(int length, boolean numberCode) {
        String randomSMSCode = "";
        String codeTable = numberCode ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        boolean flag = true;
        do {
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * codeTable.length();
                int intR = (int) Math.floor(dblR);
                char c = codeTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                randomSMSCode += codeTable.charAt(intR);
            }
            if (count >= 2) {
            	flag = false;
            }
        } while (flag);
        return randomSMSCode;
    }
}