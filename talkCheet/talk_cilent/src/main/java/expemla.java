public class expemla {
    public static void main(String[] args) {
        System.out.println("客户端开启");
        new Thread(new TalkCilent("xxx")).start();
    }
}
