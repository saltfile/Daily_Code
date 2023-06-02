#include <linux/init.h>
#include <linux/module.h>
#include <linux/proc_fs.h>
#include <linux/uaccess.h>
#include <linux/slab.h>
#include <linux/vmalloc.h>
#include <linux/mm.h>
#include <linux/highmem.h>

extern char* my_param;
static char *kbuf;
static int proc_file_open(struct inode *inode, struct file *file)
{
	printk("Open Operation!\n");
	return 0;
}

static ssize_t proc_file_read(struct file *file,
        char __user* buf,size_t size, loff_t * pos){
    printk("fileR read success:%s!\n",my_param);
    size_t len = strlen(kbuf);
    if (*pos >= len) {
        return 0;/**/
    }
    if (size > len - *pos) {
        size = len - *pos;
    }
    if (copy_to_user(buf, kbuf + *pos, size)) {
        return -EFAULT;/*复制失败，返回这个错误码。*/
    }
    printk("read ok!!");
    *pos += size;//
    return size;
}


//write opertion
static ssize_t proc_file_write(struct file *file, const  char __user *buf,
	size_t count, loff_t *ppos)
{
	printk("my_fileR write success!%d\n",count);
	copy_from_user(kbuf , buf , count);//strlen(buf)
	printk("write ok!!");
	return count;
}
//release operation
int proc_file_release(struct inode *inode, struct file *file)
{
	printk("Release Operation!\n");
	return 0;
}
static const struct proc_ops proc_file_ops = {
	.proc_open 	 = proc_file_open,
	.proc_read 	 = proc_file_read,
	.proc_write 	 = proc_file_write,
	.proc_release = proc_file_release,
};


static int my_read_init(void)
{
        proc_create_data("my_fileR", 0700, NULL,
                &proc_file_ops, NULL);
        kbuf=(char *)kmalloc(128,GFP_KERNEL);
        printk("my_fileW init success!\n");
        return 0;
}


static void my_read_exit(void)
{
        remove_proc_entry("my_fileR", NULL);
        kfree(kbuf);
        printk("my_filW exit success\n");
}
module_init(my_read_init);
module_exit(my_read_exit);

MODULE_LICENSE("GPL");
MODULE_DESCRIPTION("hello my read module");

