#kernel source
KERNEL_SRC=/lib/modules/$(shell uname -r)/build

#current directory
PWD = $(shell pwd)

#kernel module
obj-m += my_kth_demo.o

all:
	make -C $(KERNEL_SRC) M=$(PWD) modules
clean:
	rm -f *.ko *.o *.symvers *.cmd *.cmd.o *.mod.* *.order

