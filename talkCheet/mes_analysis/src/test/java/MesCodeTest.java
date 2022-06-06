import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class MesCodeTest {

    @Test
    public void encoder() {
        Mes mes = new Mes();
        mes.setMes("dsaasd");
        mes.setUser("xxx");
        mes.setStatu(status.LOGIN);
        System.out.println(MesCode.Encoder(mes));
    }

    @Test
    public void decoder() {
    }
}