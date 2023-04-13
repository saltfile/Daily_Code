package org.example;

import java.util.Random;
import java.util.TreeMap;

public class ConsistentHash {

    private static String[] serverIpArray = new String[]{"192.168.10.106", "172.16.116.1", "172.87.2.10", "172.17.0.1", "10.16.0.201"};
    private static TreeMap<Integer,String> Map_Ip;
    static {
        Map_Ip = new TreeMap<>();
        for (String ip: serverIpArray) {
            for (int i = 0; i < 3; i++) {
                String virtualIp = new Random().nextInt(10000000) + "#" + ip;
                Map_Ip.put(Myhashcode(virtualIp),ip);
            }
        }

    }





    private static Integer Myhashcode(String ServerIp){
        int hashCode = Math.abs(ServerIp.hashCode());
        System.out.println(ServerIp + ":" + hashCode);
        return hashCode;
    }

    private static String getRealServerIp(String client) {
        int clientHash = Myhashcode(client);
        Integer hKey = Map_Ip.higherKey(clientHash);
        if (hKey == null){
            return Map_Ip.get(Map_Ip.firstKey());
        }
        return Map_Ip.get(hKey);

    }



    public static void main(String[] args) {
        String[] task = {"上传图片1","电影2","手机客户端链接","电脑客户端连接"};
        for (String t:task) {
            String realIp = getRealServerIp(t);
            System.out.println("服务器:  "+realIp+"执行");
        }









    }
}
