#include "pub_references.h"
/**
 * 一个虚拟的cpu　要求指令有读取内存到寄存器和将寄存器信息读取到内存
 * @return
 */


void func_cpu(){
    loading_code("/opt/git_Pro/Daily_Code/CPU/test.txt");
    compile();
    CPU *cpu = new CPU();
    cpu->loading_binary();
    cpu->run();
}
int main() {
    //这个cpu要debug着看
//    func_cpu();
//    cout<<to_number("10000000");

//    commod_binary("mov R1");



//cpu_fun f1 = (cpu_fun) (copys);
//string s1 = "000000";
//
//string s2 = "111111";
//
//    f1(&s1,&s2);











//string *s2 = static_cast<std::string*>(f1(&s1,&s1));



//    CPU cpu = CPU();
//    cpu.loading_binary();
    return 0;
}
