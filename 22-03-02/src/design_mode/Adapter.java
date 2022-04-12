package design_mode;
/**
 * @author saltfish
 * @date 22-04-12
 * TODO:设计模式------>适配器模式
 * 老生常谈的例子
 */
//电源正常插座220v电压
class PowerSupply{
    //充电电压
    public int Charge(){return 220;}
}
//手机充电电压10v
interface PhoneVoltage{
    public int Charge10v();
}
//手机充电插头
class PhoneTransformer extends PowerSupply implements PhoneVoltage{

    @Override
    public int Charge10v() {
        int Voltage = super.Charge();
        return Voltage/22;
    }
}



public class Adapter {
    public static void main(String[] args) {
        PhoneTransformer phone = new PhoneTransformer();
        System.out.println(phone.Charge10v());
    }
}
