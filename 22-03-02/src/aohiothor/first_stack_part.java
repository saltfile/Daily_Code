package aohiothor;


/**
 * 42. 接雨水
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 */











public class first_stack_part {
    public static void main(String[] args) {
        Long longIp = 3232238233L;
        StringBuffer sb = new StringBuffer();
        System.out.println(longIp >>> 24);
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        sb.append(String.valueOf((longIp  & 0x00FFFFFF)>>> 16));
        sb.append(".");
        sb.append(String.valueOf((longIp & 0x0000FFFF)>>>8));
        sb.append(".");
        sb.append(String.valueOf((longIp & 	0x000000FF)));
        System.out.println(sb.toString());
    }
}
