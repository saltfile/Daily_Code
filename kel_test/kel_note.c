#include<stdio.h>



super_block


struct super_block {
	struct list_head	s_list;		/* Keep this first */
	dev_t			s_dev;		/* search index; _not_ kdev_t */
	unsigned char		s_blocksize_bits;
	unsigned long		s_blocksize;
	loff_t			s_maxbytes;	/* Max file size */
	struct file_system_type	*s_type;
	const struct super_operations	*s_op;
	const struct dquot_operations	*dq_op;
	const struct quotactl_ops	*s_qcop;
	const struct export_operations *s_export_op;
	unsigned long		s_flags;
	unsigned long		s_iflags;	/* internal SB_I_* flags */
	unsigned long		s_magic;
	struct dentry		*s_root;
	struct rw_semaphore	s_umount;
	}










dentry 目录项
管理文件系统的目录树
struct dentry {
...
	struct qstr d_name;
	struct inode *d_inode;		/* Where the name belongs to - NULL is
...
	/* Ref lookup also touches following */
	struct lockref d_lockref;	/* per-dentry lock and refcount */
	const struct dentry_operations *d_op;
	struct super_block *d_sb;	/* The root of the dentry tree */
	unsigned long d_time;		/* used by d_revalidate */
	void *d_fsdata;			/* fs-specific data */
...
}



目录项和super_block是 相互依赖的struct dentry; 在 struct super_block 的定义之前


inode 文件的元数据

* 文件的字节数

* 文件拥有者的User ID

* 文件的Group ID

* 文件的读、写、执行权限

* 文件的时间戳，共有三个：ctime指inode上一次变动的时间，mtime指文件内容上一次变动的时间，atime指文件上一次打开的时间。

* 链接数，即有多少文件名指向这个inode

* 文件数据block的位置



struct inode {
loff_t			i_size
	kuid_t			i_uid;
	kgid_t			i_gid;
	umode_t			i_mode //执行权限
	
	...
	
	struct super_block	*i_sb;
	
	...

	struct timespec64	i_atime;
	struct timespec64	i_mtime;
	struct timespec64	i_ctime;
	
	...
	
	union {
		struct hlist_head	i_dentry;
		struct rcu_head		i_rcu;
	};
	

}










struct file {
	union {
		struct llist_node	fu_llist;
		struct rcu_head 	fu_rcuhead;
	} f_u;
	struct path		f_path;
	struct inode		*f_inode;	/* cached value */
	const struct file_operations	*f_op;

	/*
	 * Protects f_ep_links, f_flags.
	 * Must not be taken from IRQ context.
	 */
	spinlock_t		f_lock;
	enum rw_hint		f_write_hint;
	atomic_long_t		f_count;
	unsigned int 		f_flags;
	fmode_t			f_mode;
	struct mutex		f_pos_lock;
	loff_t			f_pos;
	struct fown_struct	f_owner;
	const struct cred	*f_cred;
	struct file_ra_state	f_ra;

	u64			f_version;
#ifdef CONFIG_SECURITY
	void			*f_security;
#endif
	/* needed for tty driver, and maybe others */
	void			*private_data;

#ifdef CONFIG_EPOLL
	/* Used by fs/eventpoll.c to link all the hooks to this file */
	struct list_head	f_ep_links;
	struct list_head	f_tfile_llink;
#endif /* #ifdef CONFIG_EPOLL */
	struct address_space	*f_mapping;
	errseq_t		f_wb_err;
	errseq_t		f_sb_err; /* for syncfs */
}





file里面有inode指针
通过file--找到-->inode--->可以找到dentry、super_block

dentry可以反着找到inode指针

superblock可以找到dentry 也可以找到inode


如何从inode获取dentry???
注意inode结构体中有共用体hlist_head 
可以使用liunx内核给的hlist_for_each //for循环遍历内核链表的宏
然后用hlist_entry()将其从内核链表中拿出




















































int main(){}
