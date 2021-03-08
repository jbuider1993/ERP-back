package com.kunlun.basedata.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommonUtil {

    private static Logger log = LogManager.getLogger();

    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static Map<String, Object> packageQueryMap(Object model, int currentPage, int pageSize) {
        Map<String, Object> queryMap = new HashMap<>();
        Class clz = model.getClass();
        try {
            Field[] fields = clz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                queryMap.put(field.getName(), field.get(model));
            }
            queryMap.put("currentPage", currentPage);
            queryMap.put("pageSize", pageSize);
        } catch (Exception e) {
            log.error("CommonUtil packageQueryMap Error: ", e);
        }
        return queryMap;
    }

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !"".equals(ip)  && !"unknown".equalsIgnoreCase(ip) && !"0:0:0:0:0:0:0:1".equals(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    public static String getAddress(String params, String encoding) throws Exception {
        String path = "http://ip.taobao.com/service/getIpInfo.php";
        String returnStr = getRs(path, params, encoding);
        JSONObject json = null;
        if(returnStr != null){
            json = JSONObject.parseObject(returnStr);
            if("0".equals(json.get("code").toString())){
                StringBuffer buffer = new StringBuffer();
                //buffer.append(decodeUnicode(json.optJSONObject("data").getString("country")));//国家
                //buffer.append(decodeUnicode(json.optJSONObject("data").getString("area")));//地区
                buffer.append(decodeUnicode(json.getJSONObject("data").getString("region")));//省份
                buffer.append(decodeUnicode(json.getJSONObject("data").getString("city")));//市区
                buffer.append(decodeUnicode(json.getJSONObject("data").getString("county")));//地区
                buffer.append(decodeUnicode(json.getJSONObject("data").getString("isp")));//ISP公司
                System.out.println(buffer.toString());
                return buffer.toString();
            }else{
                return "获取地址失败!";
            }
        }
        return null;
    }

    /**
     * 从url获取结果
     * @param path
     * @param params
     * @param encoding
     * @return
     */
    private static String getRs(String path, String params, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(path);
            connection = (HttpURLConnection)url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间
            connection.setReadTimeout(2000);// 设置读取数据超时时间
            connection.setDoInput(true);// 是否打开输出
            connection.setDoOutput(true);// 是否打开输入流
            connection.setRequestMethod("POST");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(params);
            out.flush();
            out.close();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),encoding));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine())!= null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            connection.disconnect();// 关闭连接
        }
        return null;
    }

    /**
     * 字符转码
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer buffer = new StringBuffer(len);
        for (int i = 0; i < len;) {
            aChar = theString.charAt(i++);
            if(aChar == '\\'){
                aChar = theString.charAt(i++);
                if(aChar == 'u'){
                    int val = 0;
                    for(int j = 0; j < 4; j++){
                        aChar = theString.charAt(i++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                val = (val << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                val = (val << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                val = (val << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    buffer.append((char) val);
                }else{
                    if(aChar == 't'){
                        aChar = '\t';
                    }
                    if(aChar == 'r'){
                        aChar = '\r';
                    }
                    if(aChar == 'n'){
                        aChar = '\n';
                    }
                    if(aChar == 'f'){
                        aChar = '\f';
                    }
                    buffer.append(aChar);
                }
            }else{
                buffer.append(aChar);
            }
        }
        return buffer.toString();
    }

    public static String getRequestSystemInfo(HttpServletRequest request){
        String systenInfo = null;
        String header = request.getHeader("user-agent");
        if(header == null || header.equals("")){
            return "";
        }
        //得到用户的操作系统
        if (header.indexOf("NT 6.0") > 0){
            systenInfo = "Windows Vista/Server 2008";
        } else if (header.indexOf("NT 5.2") > 0){
            systenInfo = "Windows Server 2003";
        } else if (header.indexOf("NT 5.1") > 0){
            systenInfo = "Windows XP";
        } else if (header.indexOf("NT 6.0") > 0){
            systenInfo = "Windows Vista";
        } else if (header.indexOf("NT 6.1") > 0){
            systenInfo = "Windows 7";
        } else if (header.indexOf("NT 6.2") > 0){
            systenInfo = "Windows Slate";
        } else if (header.indexOf("NT 6.3") > 0){
            systenInfo = "Windows 9";
        } else if (header.indexOf("NT 10.0") > 0){
            systenInfo = "Windows 10";
        } else if (header.indexOf("NT 5") > 0){
            systenInfo = "Windows 2000";
        } else if (header.indexOf("NT 4") > 0){
            systenInfo = "Windows NT4";
        } else if (header.indexOf("Me") > 0){
            systenInfo = "Windows Me";
        } else if (header.indexOf("98") > 0){
            systenInfo = "Windows 98";
        } else if (header.indexOf("95") > 0){
            systenInfo = "Windows 95";
        } else if (header.indexOf("Mac") > 0){
            systenInfo = "Mac";
        } else if (header.indexOf("Unix") > 0){
            systenInfo = "UNIX";
        } else if (header.indexOf("Linux") > 0){
            systenInfo = "Linux";
        } else if (header.indexOf("SunOS") > 0){
            systenInfo = "SunOS";
        }
        return systenInfo;
    }

    public static String getRequestBrowserInfo(HttpServletRequest request){
        String browserVersion = null;
        String header = request.getHeader("user-agent");
        if(header == null || header.equals("")){
            return "";
        }
        if(header.indexOf("MSIE")>0){
            browserVersion = "IE";
        }else if(header.indexOf("Firefox")>0){
            browserVersion = "Firefox";
        }else if(header.indexOf("Chrome")>0){
            browserVersion = "Chrome";
        }else if(header.indexOf("Safari")>0){
            browserVersion = "Safari";
        }else if(header.indexOf("Camino")>0){
            browserVersion = "Camino";
        }else if(header.indexOf("Konqueror")>0){
            browserVersion = "Konqueror";
        }
        return browserVersion;
    }
}
