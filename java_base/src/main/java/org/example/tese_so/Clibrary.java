package org.example.tese_so;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface Clibrary extends Library {

    // [Native.synchronizedLibrary] 阻止多线程同时访问本地代码
    Clibrary INSTANTCE = (Clibrary) Native.synchronizedLibrary(
            (Clibrary) Native.loadLibrary(
                    Clibrary.class.getResource("libmy_test.so")
                            .getPath()
//                            .substring(1)// substring(1)的原因是在Windows下获取到的路径前面会多一个斜杠，但在Linux下不会
                    , Clibrary.class
            )
    );

    // 此方法为so文件中的c语言函数1 -> int test_return_C(void);
    //  ##备注: 这里的void代表无参
    void hello();

    // 此方法为so库中的c语言函数2 -> char* Decrpyt( char * input);
    // ## 备注: 这里的char* 是c语言中的指针，与java中的String相对应
    int add_teest(int a,int b);
}