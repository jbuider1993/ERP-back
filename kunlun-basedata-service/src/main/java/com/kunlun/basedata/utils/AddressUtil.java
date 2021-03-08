package com.kunlun.basedata.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.ObjectUtils;

public class AddressUtil {

    private static Logger log = LogManager.getLogger();

    public static final String AMAPKEY = "0d78256ea89beeb8c25d1cd047549d1f";

    public static final String BAIDUKEY = "F454f8a5efe5e577997931cc01de3974";

    public static final String AMAPURL = "http://restapi.amap.com/v3/geocode/geo?key=%s&s=rsv3&address=%s";

    public static final String BAIDUURL = "http://api.map.baidu.com/location/ip?ak=%s&ip=%s";

    public static final String TAOBOURL = "http://ip.taobao.com/service/getIpInfo.php";

    /**
     * 高德地图API获取位置信息
     *
     * @param address       登录地址
     * @return
     */
    public static Map<String, String> getAddressInfoByAmap(String address) {
        String str = null;
        try {
//            BASE64Encoder encoder = new BASE64Encoder();
//            String encode = encoder.encode(address.getBytes());

            String url = String.format(AMAPURL, AMAPKEY, address);
            String res = sendPost(url, null);
            JSONObject geCodes = JSONObject.parseObject(res).getJSONObject("gecodes");
            if (ObjectUtils.isEmpty(geCodes)) {
                return new HashMap<>();
            }
            Map<String, String> results = new HashMap<>();
            // 格式化地址
            results.put("address", geCodes.getString("formatted_address"));

            // 邮政编码
            results.put("adcode", geCodes.getString("adcode"));

            // 区号
            results.put("citycode", geCodes.getString("citycode"));

            // 经纬度坐标
            results.put("location", geCodes.getString("location"));
            return results;
        } catch (Exception e) {
            log.error("AddressUtil getAddressInfo Error: ", e);
        }
        return new HashMap<>();
    }

    /**
     * 百度地图API获取经纬度坐标
     *
     * @param address       登录地址
     * @return
     */
    public static Map<String, String> getLatitude(String address) {
        try {
            address = URLEncoder.encode(address, "UTF-8");
            URL resjson = new URL("http://api.map.baidu.com/geocoder?address="
                    + address + "&output=json&key=" + "0d78256ea89beeb8c25d1cd047549d1f");
            BufferedReader in = new BufferedReader(new InputStreamReader(resjson.openStream()));
            String res;
            StringBuilder sb = new StringBuilder("");
            while ((res = in.readLine()) != null) {
                sb.append(res.trim());
            }
            in.close();
            String str = sb.toString();
            if (str != null && !str.equals("")) {
                Map<String, String> map = null;
                int lngStart = str.indexOf("lng\":");
                int lngEnd = str.indexOf(",\"lat");
                int latEnd = str.indexOf("},\"precise");
                if (lngStart > 0 && lngEnd > 0 && latEnd > 0) {
                    String longitude = str.substring(lngStart + 5, lngEnd);
                    String latitude = str.substring(lngEnd + 7, latEnd);
                    map = new HashMap<String, String>();
                    map.put("longitude", longitude);
                    map.put("latitude", latitude);
                    return map;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * unicode 转换成 中文
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
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
                            value = (value << 4) + aChar - '0';
                            break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                            value = (value << 4) + 10 + aChar - 'a';
                            break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                            value = (value << 4) + 10 + aChar - 'A';
                            break;
                            default:
                            throw new IllegalArgumentException("Malformed  encoding.");
                        }
                    }
                outBuffer.append((char) value);
            } else {
                if (aChar == 't') {
                    aChar = '\t';
                } else if (aChar == 'r') {
                    aChar = '\r';
                } else if (aChar == 'n') {
                    aChar = '\n';
                } else if (aChar == 'f') {
                    aChar = '\f';
                }
                outBuffer.append(aChar);
            }
        } else {
            outBuffer.append(aChar);
        }
    }
    return outBuffer.toString();
    }

    /**
     * 获取登录IP地址
     *
     * @param request       请求
     * @return
     */
    public static  String getIp(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 百度地图API获取位置信息
     *
     * @param ip       登录IP
     * @return
     */
    public static Map<String, String> getAddressInfoByBaidu(String ip) {
        try {
            String mapUrl = String.format(BAIDUURL, BAIDUKEY, ip);
            URL url = new URL(mapUrl);
            InputStream inputStream = url.openStream();
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputReader);
            StringBuffer sb = new StringBuffer();
            String str;
            do {
                str = reader.readLine();
                sb.append(str);
            } while (null != str);

            inputStream.close();

            str = sb.toString().substring(0, sb.toString().lastIndexOf("null"));

            JSONObject jsonObject = JSONObject.parseObject(str);
            JSONObject parseJson = jsonObject.getJSONObject("content");
            Map<String, String> results = new HashMap<>();
            // 格式化地址
            results.put("address", parseJson.getString("address"));

            // 区号
            results.put("citycode", parseJson.getJSONObject("address_detail").getString("city_code"));

            // 经纬度坐标
            JSONObject location = parseJson.getJSONObject("point");
            String point = location.getString("x") + "," + location.getString("y");
            results.put("location", point);
            return results;
        } catch (Exception e) {
            log.error("AddressUtil getAddressInfoByBaidu Error: ", e);
        }
        return null;
    }

    /**
     * 获取登录IP地址
     *
     * @param request       请求
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return "unknown";
        }

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    /**
     * 百度地图API获取位置信息
     *
     * @param ip       登录IP地址
     * @return
     */
    public static String getRealAddressByIP(String ip) {
        String address = "XX XX";
        // 内网不查询
        if (IpUtil.internalIp(ip)) {
            return "内网IP";
        }

        if (true) {
            String rspStr = sendPost(TAOBOURL, "ip=" + ip);
            if (ObjectUtils.isEmpty(rspStr)) {
                log.error("获取地理位置异常 {}", ip);
                return address;
            }

            JSONObject obj;
            try {
                obj = unmarshal(rspStr, JSONObject.class);
                JSONObject data = obj.getJSONObject("data");
                String region = data.getString("region");
                String city = data.getString("city");
                address = region + " " + city;
            } catch (Exception e) {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return address;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            String urlNameString = url;
            if (!ObjectUtils.isEmpty(param)) {
                urlNameString = url + "?" + param;
            }
            log.info("sendPost - {}", urlNameString);
            URL realUrl = new URL(urlNameString);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(param);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            log.info("recv - {}", result);
        } catch (ConnectException e) {
            log.error("调用HttpUtils.sendPost ConnectException, url=" + url + ",param=" + param, e);
        } catch (SocketTimeoutException e) {
            log.error("调用HttpUtils.sendPost SocketTimeoutException, url=" + url + ",param=" + param, e);
        } catch (IOException e) {
            log.error("调用HttpUtils.sendPost IOException, url=" + url + ",param=" + param, e);
        } catch (Exception e) {
            log.error("调用HttpsUtil.sendPost Exception, url=" + url + ",param=" + param, e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                log.error("调用in.close Exception, url=" + url + ",param=" + param, ex);
            }
        }
        return result.toString();
    }

    public static <T> T unmarshal(String str, Class<T> valueType) throws Exception {
        try {
            return new ObjectMapper().readValue(str, valueType);
        } catch (JsonParseException e) {
            throw new Exception(e);
        } catch (JsonMappingException e) {
            throw new Exception(e);
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    public static boolean verifyIP(String ipAddr) {
        // ip的正则表达式
        String ipReg = "^(([1-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))\\.)(([1-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))\\.){2}([1-9]|([1-9]\\d)|(1\\d\\d)|(2([0-4]\\d|5[0-5])))$";
        Pattern ipPattern = Pattern.compile(ipReg);
        return ipPattern.matcher(ipAddr).matches();
    }

    public static boolean verifyAddress(String address) {
        String adressReg = "([\\u4e00-\\u9fa5]+)";
        Pattern ipPattern = Pattern.compile(adressReg);
        return ipPattern.matcher(address).matches();
    }
}
