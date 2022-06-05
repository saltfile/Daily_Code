import junit.framework.TestCase;

public class TalkServerTest extends TestCase {

    public void testInit() {
       new Thread(new TalkServer()).start();
    }

    public void testTestRun() {
    }
}