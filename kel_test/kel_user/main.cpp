#include<stdio.h>
#include<unistd.h>
#include<signal.h>
#include <string.h>

void SigRoutine(int iSignNum)
{
    printf("%d\n",iSignNum);
    switch(iSignNum)
    {
        case 1:
            printf("capture SIGHUP signal, signal number is %d\n",iSignNum);
            break;
        case 2:
            printf("capture SIGINT signal, signal number is %d\n",iSignNum);
            break;
        case 3:
            printf("capture SIGINT signal, signal number is %d\n",iSignNum);
            break;
        case 78:
            printf("capture SIGINT signal, signal number is %d\n",iSignNum);
            break;
    }

}



int main()
{
    FILE *fd = fopen("/sys/module/mykels/parameters/param","w+");
    char buffer[6] = "";
    sprintf(buffer,"%d",getpid());
    if (fd != NULL){
    fwrite(buffer, sizeof(char), sizeof(buffer), fd);
    fclose(fd);
    }
//

    printf("process ID is %s\n",buffer);
    if(signal(SIGHUP,SigRoutine) == SIG_ERR)
    {
        printf("coundn't register signal handler for SIGHUP");
    }

    if(signal(SIGQUIT,SigRoutine) == SIG_ERR)
    {
        printf("coundn't register signal handler for SIGQUIT");
    }
    if(signal(SIGINT,SigRoutine) == SIG_ERR)
    {
        printf("coundn't register signal handler for SIGINT");
    }

    if(signal(SIGILL,SigRoutine) == SIG_ERR)
    {
        printf("coundn't register signal handler for SIGILL");
    }


    while(1)
        sleep(1);
    return 0;
}














