package com.saltfish.cloud.Service;

import com.saltfish.cloud.entities.payment;

public interface PayMentService {
    int AddPayMent(String numbers,String content);

    payment SelPayMentByid(Long id);
}
