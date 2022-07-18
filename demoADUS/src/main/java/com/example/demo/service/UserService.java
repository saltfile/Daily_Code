package com.example.demo.service;

import com.example.demo.dao.MyBatisUserDao;
import com.example.demo.dao.userDao;
import com.example.demo.entiry.user_Tab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
public class UserService implements userservicempl{
    @Autowired
    private userDao userDao;

    /**
     * TODO:登录业务
     * @param id 用户名
     * @param password 密码
     * @return 找到得用户
     */
    @Override
    public user_Tab Login(String id, String password) {
        return userDao.Sel(id,password);
    }

    /**
     * TODO:注册业务
     * @param user 要注册的用户
     * @return 是否成功
     */
    @Override
    public boolean Register(user_Tab user) {
        return userDao.Add(user) > 0;
    }

    /**
     * TODO:展示所有用户
     * @return
     */
    @Override
    public ArrayList<user_Tab> Show_All() {

        return userDao.ShowAll();
    }

    /**
     * TODO:删除某个用户
     * @param id 要删除的用户id
     * @return 是否成功
     */
    @Override
    public boolean Delete(String id) {
        return userDao.Del(id) > 0;
    }

    /**
     * TODO：个人用户修改信息
     * @param user 被修改的用户
     * @return 是否成功
     */
    @Override
    public boolean Update(user_Tab user) {
        return userDao.Update(user) > 0;
    }
}
