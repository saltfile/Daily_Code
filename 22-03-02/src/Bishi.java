public class Bishi {
    public static void main(String[] args) {
        String str = " aaa ";
        String[] s = str.split(" ");
        System.out.println(s.length);

        StringBuilder df = new StringBuilder();

        df.append("ass");
        df.append(" ");
        df.deleteCharAt(df.length()-1);
        System.out.println(new String(df));

        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
    }
}
