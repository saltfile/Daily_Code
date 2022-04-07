#include "my_home.h"

int main() {
    //home1
//    init_cache();
//    set_tea(455,'W');
//    set_stu(1454,"sadasf");
//    set_tea(445,'N');
//    show_all();
char *cache = (char *)malloc(sizeof(char)*8);
memset(cache,0,sizeof(cache));
char *name="123";
unsigned long a=(long )name;
printf("0x%x\n",a);
for(int i=0;i<8;i++)
{
    cache[i]=a;
    a=a>>8;
    printf("0x%x\t",cache[i]);
}
    printf("\n");
long a2=0;
    for(int i=7;i>=0;i--)
    {
        a2=a2<<8;
        a2+=cache[i];
        printf("0x%x\t",a2);
    }
    printf("\n");

    printf("0x%x\t",a2);





//hmoe2
//demo_explme();








    return 0;
}
