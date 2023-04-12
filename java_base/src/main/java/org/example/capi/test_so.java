package org.example.capi;

public class test_so extends Myfunc{
    static{
        System.load("/opt/git_Pro/Daily_Code/java_base/src/main/java/org/example/capi/libmy_test.so");
    }

    public static void main(String[] args) {

        String s = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "\n" +
                "    <script type=\"text/javascript\" src=\"./jquery3.5.1.min.js\"></script>\n" +
                "    <script type=\"text/javascript\" src=\"./user.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "用户名:<input type=\"text\" id=\"uid\" name=\"personalAccount\" class=\"form-control\" placeholder=\"请输入账号\" maxlength=\"100\" autocomplete=\"off\">\n" +
                "<br/>\n" +
                "密码:<input type=\"password\" id=\"pwd\" name=\"personalPassword\" class=\"form-control\" placeholder=\"密码\" maxlength=\"20\" autocomplete=\"off\">\n" +
                "<br/>\n" +
                "<button type=\"button\" class=\"btn login-btn\" id=\"Lon_Btn\">登 录</button>\n" +
                "</body>\n" +
                "</html>";
        System.out.println(s.length());


//        test_so c = new test_so();
//        int a = c.add(4,7);
//        System.out.println(a);
    }


}
