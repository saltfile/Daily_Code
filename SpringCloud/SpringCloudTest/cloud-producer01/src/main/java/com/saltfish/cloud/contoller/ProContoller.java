package com.saltfish.cloud.contoller;

import com.saltfish.cloud.Service.PayMentService;
import com.saltfish.cloud.entities.CommResult;
import com.saltfish.cloud.entities.payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ProContoller {
    @Autowired
    private PayMentService paymentService;

    @GetMapping(value = "/payment/{id}")
    public CommResult getPaymentById(@PathVariable("id") Long id){
        payment result = paymentService.SelPayMentByid(id);
        log.info("****查询结果：" + result);
        if(result != null){
            return new CommResult(200, "查询成功", result);
        }
        return new CommResult(444, "没有对应id的记录", null);
    }

    @PostMapping(value = "/payment/create")
    public CommResult creates(@RequestBody payment payment){
//        int result = 1;
        int result = paymentService.AddPayMent(payment.getNumbers(),payment.getContent());
        log.info("====插入结果：  "+result);
        if (result > 0){
            return new CommResult(200,"数据库插入成功",result);
        }else {
            return new CommResult(444,"数据库插入失败",null);
        }
    }

}
