package com.example.demo.entiry;

import lombok.Data;

/**
 * @author saltfish
 * @date 2022-04-29
 * 属性意义:
 * id           仅为账号存在（通过手机号生成）
 * password     密码
 * username     用户名（产品名+手机末尾4个）
 * tel          用来注册的手机号
 * emil         电子邮箱（可以不填用来补全用户信息）
 * address      家庭住址地区
 * gender       性别
 * dentcard     用来绑定的身份证
 * jurs         权限值（0:普通用户,1:银卡用户,2:黑卡用户,3:白金会员,4:后台管理员（不可被注册））
 * age          年龄，可以不加
 */






@Data
public class user_Tab {
    public String id;
    public String password;
    public String username;
    public String tel;
    public String emil;
    public String address;
    public String gender;
    public String dentcard;
    public int jurs;
    public int age;
}
