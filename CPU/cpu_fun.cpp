//
// Created by saltfish on 2022/7/15.
//
#include "pub_references.h"

//将命令转化成二进制字节(象征性的)
//add命令 == 10010110  加法
//sub命令 == 00010110  减法
//mul命令 == 10001001  乘法
//div命令 == 00001001  除法
//asg命令 == 10000000  赋值寄存器命令
byte *command_conver_byte(char *command){
    if (str_equal(command,"add")){
        byte *res = (byte *) "10010110";
        return res;
    }
    if (str_equal(command,"sub")){
        byte *res = (byte *) "00010110";
        return res;
    }
    if (str_equal(command,"mul")){
        byte *res = (byte *) "10001001";
        return res;
    }
    if (str_equal(command,"div")){
        byte *res = (byte *) "00001001";
        return res;
    }
    if (str_equal(command,"asg")){
        byte *res = (byte *) "10000000";
        return res;
    }
        return NULL;
};


































