//
// Created by saltfish on 2022/5/4.
//

#include "Myany.h"


int sql_connect(){

    MYSQL *conn_ptr=mysql_init(NULL);

    if(!conn_ptr)

    {
        printf("init error/n");

        return EXIT_FAILURE;

    }

    conn_ptr=mysql_real_connect(conn_ptr,"192.168.1.1","i409","409root409","iTest",0,NULL,0);

    if(conn_ptr)

    {
        printf("connect success/n");

    }

    else

    {
        printf("connect error");

    }
}













