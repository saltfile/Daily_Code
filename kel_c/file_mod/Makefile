#kernel source
KERNEL_SRC=/lib/modules/$(shell uname -r)/build

#current directory
PWD = $(shell pwd)

#kernel module
obj-m += proc_write.o proc_read.o

all:
	make -C $(KERNEL_SRC) M=$(PWD) modules
clean:
	rm -f *.ko *.o *.symvers *.cmd *.cmd.o *.mod.* *.order

