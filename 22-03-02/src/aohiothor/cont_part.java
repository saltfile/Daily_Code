package aohiothor;
// 散

//在一段字符串中找到最长的且不重复的字符串 都是小写字母

import java.util.ArrayList;
import java.util.List;

class soult1{
    public ArrayList<String> MaxBu(String str){
        int left = 0;
        int max = 0;
        ArrayList<String> arr = new ArrayList<>();
        for (int right = 1; right < str.length(); right++) {
            String st = str.substring(left,right);
            //最起码要大于前面的max
            if (st.length() > max){
                //如果是最长
                if (isMString(st)){
                    arr.clear();
                    arr.add(st);
                    max = st.length();
                }else {
                    left = right-1;
                }
            }else if(st.length() == max){
                if (isMString(st)){
                    arr.add(st);
                }
            }
        }
        return arr;

    }
    public boolean isMString(String s){
        int[] _tab = new int[26];

        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i)-'a';
            if (_tab[idx] == 0){
                _tab[idx] = 1;
            }else {
                return false;
            }
        }
        return true;
    }
}


/**
 * 2. 两数相加
 *
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
class AddTwoNumbers{
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode node = res;

        int car = 0;
        while (l1 != null || l2 != null||car != 0){
            int var1 = l1 != null?l1.val:0;
            int var2 = l2 != null?l2.val:0;

            int num = var1+var2+car;

            ListNode news = new ListNode(num%10);
            node.next = news;
            node = node.next;
            car = num/10;

            if (l1!=null)l1 = l1.next;
            if (l2 != null)l2 = l2.next;
        }
        return res.next;
    }



}

/**
 * 258. 各位相加
 *
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 */

class AddDigits{
    public int addDigits(int num) {
        while (num >= 10){
            num = number_sum(num);
        }
        return num;
    }


    public int number_sum(int num){
        int res = 0;
        while (num > 0){
            res += num%10;
            num/=10;
        }
        return res;
    }

}




class MeiTuan{


/**
 * 小美有一个由数字字符组成的字符串。现在她想对这个字符串进行一些修改。
 * 具体地，她可以将这个字符串中任意位置字符修改为任意的数字字符。
 * 她想知道，至少进行多少次修改，可以使得修改后的字符串不包含两个连续相同的字符？
 * 例如，对于字符串”111222333”，她可以进行3次修改将其变为”121212313”。
 * 注意返回的是次数
 */

public int XiaoMei(String str){
    int left = 0;
    int res = 0;
    char a = str.charAt(left);
    for (int i = 1; i < str.length(); i++) {
        if (str.charAt(i) == a) {
            res++;
            a = '&';
            left = i;
            continue;
        }
        left = i;
        a = str.charAt(left);

    }


return res;

}



//0平 1赢 [0,0] 2赢[15,15]
    public int Tank(String com1,String com2){
        int[][] map = new int[16][16];
        int num1 = 1;//坦克占地号
        int num2 = 2;//坦克占地号
        char d1 = ' ';
        char d2 = ' ';
        int x1 = 0;//初始
        int y1 = 0;

        map[x1][y1] = num1;

        int x = 15;
        int y = 15;

        map[x][y] = num2;




        //开始
        for (int i = 0; i < 256; i++) {
            //1先走
            char c1 = com1.charAt(i);
            switch (c1){
                case 'U':
                    y1 = U(map,x1,y1,num1);
                    map[x1][y1] = num1;
                    d1 = 'U';
                    break;
                case 'D':
                    y1 = D(map,x1,y1,num1);
                    map[x1][y1] = num1;
                    d1 = 'D';
                    break;
                case 'L':
                    x1 = L(map,x1,y1,num1);
                    map[x1][y1] = num1;
                    d1 = 'L';
                    break;
                case 'R':
                    x1 = R(map,x1,y1,num1);
                    map[x1][y1] = num1;
                    d1 = 'R';
                    break;
                case 'F':
                    if (F(map,x1,y1,x,y,d1))return 1;
            }

            char c2 = com2.charAt(i);

            switch (c2){
                case 'U':
                    y = U(map,x,y,num2);
                    map[x][y] = num2;
                    d2 = 'U';
                    break;
                case 'D':
                    y = D(map,x,y,num2);
                    map[x][y] = num2;
                    d2 = 'D';
                    break;
                case 'L':
                    x = L(map,x,y,num2);
                    map[x][y] = num2;
                    d2 = 'L';
                    break;
                case 'R':
                    x = R(map,x,y,num2);
                    map[x][y] = num2;
                    d2 = 'R';
                    break;
                case 'F':
                    if (F(map,x,y,x1,y1,d2))return 2;
            }
            if (Ping(x1,y1,x,y))return 0;
        }
        return last(map);
    }



    public int U(int[][] map,int x,int y,int num){
    //首先判定是否在地图外
        if (y-1 < 0)return y;
        if (map[x][y-1] == 0||map[x][y-1] == num){
            return y-1;
        }
        return y;
    }


    public int D(int[][] map,int x,int y,int num){
        //首先判定是否在地图外
        if (y+1 > 15)return y;
        if (map[x][y+1] == 0||map[x][y+1] == num){
            return y+1;
        }
        return y;
    }


    public int L(int[][] map,int x,int y,int num){
    //首先判断是否在地图外
        if (x-1 < 0)return x;
        if (map[x-1][y] == 0||map[x-1][y] == num){
            return x-1;
        }
        return x;
    }

    public int R(int[][] map,int x,int y,int num){
        //首先判断是否在地图外
        if (x+1 > 15)return x;
        if (map[x+1][y] == 0||map[x+1][y] == num){
            return x+1;
        }
        return x;
    }

    public boolean Ping(int x1,int y1,int x,int y){
    if (x1 == x&&y == y1)return true;
    else return false;
    }

    //1是本坦克 无1是对方坦克
    public boolean F(int[][] map,int x1,int y1,int x,int y,char direction){
    switch (direction){
        case 'U':
            if (x1 == x&&y1 > y)return true;//打中了
            break;
        case 'D':
            if (x1 == x&&y > y1)return true;//打中了
            break;
        case 'L':
            if (y == y1&&x < x1)return true;//打中了
            break;
        case 'R':
            if (y == y1&&x1 < x)return true;//打中了
    }
    return false;
    }



    public int last(int[][] map){
        int i1 = 0;
        int i2 = 0;


        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                if (map[i][j] == 1)i1++;
                if (map[i][j] == 2) i2++;
            }
        }
        if (i1 == i2)return 0;

        return i1 > i2?1:2;
    }





}
















public class cont_part {

    private static Integer a = new Integer(4);



    public static void main(String[] args) {

        MeiTuan meiTuan = new MeiTuan();
        System.out.println(meiTuan.XiaoMei("111222333"));

//        System.out.println(new soult1().MaxBu("stringsabcvdb"));
    }
}
