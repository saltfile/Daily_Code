
#include "basic_func.h"
//#include <stdio.h>




bool check(int n,int fgh){
    int arr[10] = {'a','a','a','a','a','a','a','a','a','a'};
    int temp = 0;
    for (int i = 0; i < 5; ++i) {
        temp = n%10;
        arr[temp] = temp;
        n=n/10;
    }
    for (int i = 0; i < 5; ++i) {
        temp = fgh%10;
        arr[temp] = temp;
        fgh = fgh/10;
    }
    for (int i = 0; i < 10; ++i) {
        if (arr[i] != i)return false;
    }
    return true;


}


void aa(int &a,int &b){
    a = 2;
    b = 3;
}







int main() {
//    design_mode_main();
    int a = 0;
    int *c = &a;
    int *d = &a;
    cout<<&c<<endl;
    cout<<&d<<endl;

//    if (check(79546,1283)){
//        cout<<"aaaa"<<endl;
//    }

















// expampe1_main();
//expampe2_main();
//expampe3_main();
//expampe4_main();
//expampe5_main();
//expampe6_main();
//expampe7_main();
//expampe8_main();
//    expampe9_main();
//expampe10_main();

























//    int a;
//    scanf("%d",&a);
//    int g = a%10;
//    int s = a/10%10;
//    int b = a/100;
//
//    printf("%d%d%d",g,s,b);


//int f1 = 0,f2 = 1,fn;
//for (int i = 1;i <= 40;i++)
//{
//    fn = f1+f2;
//    f1 = f2;
//    f2 = fn;
//    cout<<f1<<endl;
//}
//
//exit(6);

    return 0;
}
