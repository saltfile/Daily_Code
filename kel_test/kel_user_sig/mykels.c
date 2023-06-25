#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>

#include <linux/fs.h>
#include <linux/errno.h>
#include <linux/types.h>
#include <linux/fcntl.h>
#include <linux/cdev.h>
#include <linux/version.h>
#include <linux/ctype.h>
#include <linux/pagemap.h>
#include <linux/delay.h>
#include <linux/kthread.h>


#include <linux/proc_fs.h>
#include <linux/uaccess.h>
#include <linux/slab.h>
#include <linux/vmalloc.h>
#include <linux/mm.h>
#include <linux/highmem.h>

#include <linux/interrupt.h>
#include <linux/timer.h>
#include <linux/sched.h>
#include <linux/sched/signal.h>

static struct task_struct *reg_kthread;

static struct task_struct *msg_kthread;

static int param = 0;

static int t_param = 0;



static int m_flag = 0;

static int t_flag = 0;


static int p_list[128];

static int p_lens = 0;





int irq;
char *interface;



// 下面这2个参数通过插入内核模块的时候赋值的...
module_param(irq, int, 0700);
module_param(interface, charp, 0700);



//需要发送的处理函数
int send_msg(int msg){
	int i = 0;
	for(i = 0;i < p_lens;i++)
	{
	
	
	//获取pid发送数字
	int pid = p_list[i];
	struct task_struct *cuxxx = pid_task((pid_t)pid,PIDTYPE_PID);
	 send_sig(SIGUSR1, cuxxx, msg);
	 
	}
}


int reg_run(void *data)
{
 int i = 0;
 
	while(1)
	{
	set_current_state(TASK_UNINTERRUPTIBLE);
		
		if(kthread_should_stop()){
		break;
		}
		
		schedule_timeout(4*HZ);
		i++;
		//如果变了就说明注册
		if(param != m_flag){
		
		p_list[p_lens] = param;
		
		m_flag = param;
		 
		p_lens++;
		
		
		printk("reg success:%d  .\n",m_flag);
		}
		
		
		
		if(i == 50){
		printk("should_stop\n");
		kthread_stop(reg_kthread);//这里使用stop之后kthread_should_stop()会判断为true
		}
		
	}
	printk("thread dead\n");
	//kthread_should_stop();
	return 0;
}





int mes_motion(void *data)
{
 int i = 0;
 
	while(1)
	{
	set_current_state(TASK_UNINTERRUPTIBLE);
		
		if(kthread_should_stop()){
		break;
		}
		
		schedule_timeout(4*HZ);
		i++;
		//如果消息参数被改变
		if(t_param != t_flag){
		
		send_msg(t_flag);
		
		printk("msg send :%d  .\n",t_flag);
		}
		
		
		
		if(i == 50){
		printk("should_stop\n");
		kthread_stop(msg_kthread);//这里使用stop之后kthread_should_stop()会判断为true
		}
		
	}
	printk("thread dead\n");
	//kthread_should_stop();
	return 0;
}





int simple_init_module(void)
{
//检测进程号的注册
	int err;
   reg_kthread = kthread_run(reg_run, NULL, "reg_kthread");

    if(IS_ERR(reg_kthread))
	{
		printk("reg_kthread_create failed.\n");
		err = PTR_ERR(reg_kthread);
		reg_kthread = NULL;
		return err;
    }
    
    printk("reg_kthread_create success.\n");
    
    
    
    //检测信息变化并转发的线程
    int err1;
   msg_kthread = kthread_run(mes_motion, NULL, "msgs_kthread");

    if(IS_ERR(msg_kthread))
	{
		printk("msg_kthread_create failed.\n");
		err1 = PTR_ERR(msg_kthread);
		msg_kthread = NULL;
		return err1;
    }
    
    printk("msg_kthread_create success.\n");
    
    
	return 0;
}

void simple_exit_module(void)
{
	if(reg_kthread)
	{
		kthread_stop(reg_kthread);
		reg_kthread = NULL;
	}
	
	
}

module_init(simple_init_module);
module_exit(simple_exit_module);
module_param(param,int,0700);
module_param(t_param,int,0700);


MODULE_LICENSE("GPL");
MODULE_DESCRIPTION("Hello Module");
