package org.example.Web;

//import cn.hutool.core.net.NetUtil;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class mac {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        InetAddress inetAddress = InetAddress.getByName("192.168.10.106");
        System.out.println(inetAddress);
        //第二种方式：利用hutool工具类中的封装方法获取本机mac地址
//        System.out.println("localMacAddress2 = " + NetUtil.getMacAddress(inetAddress));

    }
    private static String getLocalMac(InetAddress inetAddress) {
        try {
            //获取网卡，获取地址
            byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < mac.length; i++) {
                if (i != 0) {
                    sb.append("-");
                }
                //字节转换为整数
                int temp = mac[i] & 0xff;
                String str = Integer.toHexString(temp);
                if (str.length() == 1) {
                    sb.append("0" + str);
                } else {
                    sb.append(str);
                }
            }
            return sb.toString();
        } catch (Exception exception) {
        }
        return null;
    }
}
