package com.saltfish.cloud.Service;

import com.saltfish.cloud.dao.PaymentDao;
import com.saltfish.cloud.entities.payment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PayMentServicempl implements PayMentService{
    @Resource
    private PaymentDao paymentDao;
    @Override
    public int AddPayMent(String numbers, String content) {
        return paymentDao.Add(numbers,content);
    }

    @Override
    public payment SelPayMentByid(Long id) {
        return paymentDao.Sel(id);
    }
}
