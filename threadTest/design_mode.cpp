//
// Created by saltfish on 2022/10/27.
//
#include <assert.h>
#include "basic_func.h"



/**
 * 单例模式：
 * 保证永远都创造一个实例，获得的实例永远否是只有一个
 * 只提供一个访问该实例的接口
 * @return 获得创建的实例
 */
single_utils* Singleton_mode(){
static single_utils *p = NULL;
if (p!=NULL)
    return p;
p = (single_utils*)malloc(sizeof(single_utils));
memset(p,0,sizeof(single_utils));
assert(p);
    return p;
}









typedef enum
{
    COTTON,
    LEATHER,
    FABRIC_MAX,
};

typedef struct _Clothing
{
    int fabric; /*面料*/
    void (*generate_clothing)(void);
}Clothing;


void make_cotton_clothes(void)
{
    printf("Make cotton clothes\r\n");
}

void make_leather_clothes(void)
{
    printf("Make leather clothes\r\n");
}


Clothing* manufacture_clothing(int fabric)
{
    assert(fabric < FABRIC_MAX);

    Clothing* pClothing = (Clothing*)malloc(sizeof(Clothing));
    assert(NULL != pClothing);

    memset(pClothing, 0, sizeof(Clothing));

    pClothing->fabric = fabric;

    switch(fabric)
    {
        case COTTON:
            pClothing->generate_clothing = make_cotton_clothes;
            break;

        case LEATHER:
            pClothing->generate_clothing = make_leather_clothes;
            break;
    }
    return pClothing;
}


int design_mode_main(){

    single_utils *utils = Singleton_mode();
    utils->id = 124;
    single_utils *p = Singleton_mode();
    cout<<p->id;






    return 0;
}