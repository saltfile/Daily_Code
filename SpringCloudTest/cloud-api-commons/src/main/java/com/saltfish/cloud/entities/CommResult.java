package com.saltfish.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommResult<E> {
    //响应码
    private Integer code;
    //对应消息
    private String message;
    //发送来的数据
    private E data;

    public CommResult(Integer code,String message){
        this(code,message,null);
    }
}
