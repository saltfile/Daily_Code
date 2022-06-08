public class expemla {
    public static void main(String[] args) {
        System.out.println("服务端开启");
        new Thread(new TalkServer()).start();
    }
}
