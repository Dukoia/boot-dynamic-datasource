package com.dukoia.boot.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fanshk@yonyou.com
 * @date 2019/11/11 19:32
 */
@Slf4j
public class IpUtil {
    public final static String ERROR_IP = "127.0.0.1";

    public final static Pattern PATTERN = Pattern.
            compile("(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\.(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})");

    private IpUtil() {
    }

    /**
     * 获取用户的真实ip
     *
     * @param request
     * @return
     */
    public static String getUserIp(HttpServletRequest request) {
        return getIpAddr(request);
    }

    /**
     * 判断我们获取的ip是否是一个符合规则ip
     *
     * @param ip
     * @return
     */
    public static boolean isValidIP(String ip) {
        if (StringUtils.isEmpty(ip)) {
            log.debug("ip is null. valid result is false");
            return false;
        }

        Matcher matcher = PATTERN.matcher(ip);
        boolean isValid = matcher.matches();
        log.debug("valid ip:" + ip + " result is: " + isValid);
        return isValid;
    }

    public static String getLocalIp() {
        try {
            return getIpAdd();
        } catch (Exception e) {
            log.error("获取本机ip地址异常：", e);
        }
        return ERROR_IP;
    }

    /**
     * 根据网卡获得IP地址
     *
     * @return
     * @throws Exception
     */
    public static String getIpAdd() throws Exception {
        String ip = "";
        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
            NetworkInterface intf = en.nextElement();
            String name = intf.getName();
            if (!name.contains("docker") && !name.contains("lo")) {
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    //获得IP
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        String ipaddress = inetAddress.getHostAddress().toString();
                        if (!ipaddress.contains("::") && !ipaddress.contains("0:0:") && !ipaddress.contains("fe80")) {
                            if (!ERROR_IP.equals(ip)) {
                                ip = ipaddress;
                            }
                        }
                    }
                }
            }
        }
        return ip;
    }


    /**
     * 从HttpServletRequest中获取IP地址
     *
     * @param request HttpServletRequest
     * @return 返回值
     */
    public static String getIpAddr(HttpServletRequest request) {
        String unknown = "unknown";
        //未适用任何代理服务
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            //适用于nginx转发
            ip = request.getHeader("X-Real-IP");
        }
        if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            //使用客户端代理
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            //本机访问获取ip
            if (ip.equals(ERROR_IP)) {
                //根据网卡取本机配置的IP
                try {
                    ip = getIpAdd();
                } catch (Exception e) {
                    log.error("request获取ip，获取本机ip地址异常：", e);
                }
            }
        }
        //多次反向代理后会有多个ip值，第一个ip才是真实ip
        int index = ip.indexOf(",");
        if (index != -1) {
            return ip.substring(0, index);
        } else {
            return ip;
        }
    }

}
