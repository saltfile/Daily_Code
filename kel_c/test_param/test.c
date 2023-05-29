#include <linux/init.h>
#include <linux/module.h>

static char* param="aaa";


static int param_start(void)
{
        printk("this is my param = %s\n",param);
        return 0;
}


static void param_end(void)
{
        printk("Bye! param=%s\n",param);
}

module_init(param_start);
module_exit(param_end);
module_param(param,charp,00700);

MODULE_LICENSE("GPL");
MODULE_DESCRIPTION("My_param Module");
