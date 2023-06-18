#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/fs.h>
#include <linux/errno.h>
#include <linux/types.h>
#include <linux/fcntl.h>
#include <linux/cdev.h>
#include <linux/version.h>
#include <linux/vmalloc.h>
#include <linux/ctype.h>
#include <linux/pagemap.h>
#include <linux/delay.h>
#include <linux/sched.h>
#include <linux/kthread.h> 


static struct task_struct *my_kthread;
int my_run(void *data)
{
 int i = 0;
 
	while(i <= 50)
	{
	set_current_state(TASK_UNINTERRUPTIBLE);
		
		if(kthread_should_stop()){
		//当i = 10的时候这里会置为true线程会退出
		//printk("should_stop\n");
		break;
		}
		//set_current_state(TASK_STOPPED);

		printk("threadfunc\n");
		
		//这里是从调度树里拿到rtc表中等4s再拿出来
		schedule_timeout(4*HZ);
		
		i++;
		//我这里是让i等于10的时候主动退出
		if(i == 10){
		printk("should_stop\n");
		kthread_stop(my_kthread);//这里使用stop之后kthread_should_stop()会判断为true
		}
		
	}
	printk("thread dead\n");
	//kthread_should_stop();
	return 0;
}

void simple_cleanup_module(void)
{
	if(my_kthread)
	{
		kthread_stop(my_kthread);
		my_kthread = NULL;
	}
}

int simple_init_module(void)
{
	int err;
   my_kthread = kthread_run(my_run, NULL, "my_kthread");

    if(IS_ERR(my_kthread))
	{
		printk("kthread_create failed.\n");
		err = PTR_ERR(my_kthread);
		my_kthread = NULL;
		return err;
    }
	return 0;
}

module_init(simple_init_module);
module_exit(simple_cleanup_module);

MODULE_LICENSE("Dual BSD/GPL");
