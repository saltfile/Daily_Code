//
// Created by saltfish on 2022/7/15.
//
#include "pub_references.h"
#define MEM_SIZE 5     //多少mb

static char *memery;
static char *sp;
//初始化内存
void mem_inits()
{
    memery = (char *)malloc(sizeof(char)*1024*1024*MEM_SIZE);
    memset(memery,0,sizeof(memery));
    sp = memery;
}

/**
 * 将数据添加在内存
 * @param data 被添加的数据
 * @return 数据存在的首地址
 */

char* mem_in_data(char *data,int len)
{
    char *res = sp;
    for(int i = 0;i < len;i++){
        memery[i] = data[i];
        sp++;
    }
    return res;
}
/**
 * 将数据从内存中取出
 * @param fir 数据首地址
 * @param len 数据长度
 * @return 取出的数据
 */
char* mem_out_data(char *fir,int len)
{
    char *p = fir;
    char* data = (char *)malloc(sizeof(len));
    for(int i = 0;i < len;i++)
    {
        char c = (char)*p;
        data[i] = c;
        p =p+1;
    }

    return data;
}




/**
 * 将内存中的一块数据放进某一个指定的寄存器
 * @param fir 内存数据首地址
 * @param len 数据长度
 * @param cpu 放入的cpu
 * @param md 放入哪个寄存器里面
 * @return 寄存器数据地址
 */
char *mem_moveto_mrd(char* fir,int len,CPU* cpu,MDRs md)
{
    char* res = NULL;
    char* data = mem_out_data(fir,len);
    switch (md)
    {
        case ax:res = mem_info_mdr(cpu->ax,data,len);break;
        case bx:res = mem_info_mdr(cpu->bx,data,len);break;
        case cx:res = mem_info_mdr(cpu->cx,data,len);break;
        case dx:res = mem_info_mdr(cpu->dx,data,len);break;
        default:exit(-145);
    }
    return res;
}

char * mrd_move_mem(MDR* m)
{
    char* res = mem_in_data(m->mem,strlen(m->mem));
    memset(m->mem,0,sizeof(m->mem));
    return res;
}

/**
 * 将数据填写进寄存器
 * @param m 填写的寄存器
 * @param mem 填写的内容
 * @param len 内容的长度
 * @return 寄存器数据首地址
 */
char *mem_info_mdr(MDR* m,char *mem,int len)
{
    char *res = NULL;
    if(len < MEM_SIZE)
    {
        res = m->ptr;
   for(int i = 0;i < len;i++)
   {
       m->mem[i] = mem[i];
       m->ptr++;
   }
   }
    return res;
}
















