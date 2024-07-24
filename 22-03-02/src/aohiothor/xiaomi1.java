package aohiothor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class xiaomi1 {
    static int findMaxCapacity(int[] array, int m) {
        int res = 0;
        int maxValue = 0;
        int[] canAdd = new int[array.length];
        maxValue = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
                canAdd[i] = 1;
            }
        }
        maxValue = -1;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] > maxValue) {
                maxValue = array[i];
                canAdd[i] = 1;
            }
        }

        for (int i = 0; i <= array.length; i++) {
            for (int j = i + 1; j < array.length; j++)
                if (canAdd[i] > 0 && canAdd[j] > 0) {
                    int x, y;
                    int tempx = array[i];
                    int tempy = array[j];
                    if ((array[i] + array[j] + m) / 2 >= maxValue) {
                        x = y = (array[i] + array[j] + m) / 2;
                        array[i] = x;
                        array[j] = y;
                        res = Math.max(res, getCapacity(array, false));
                        if ((tempx + tempy + m) % 2 > 0) {
                            res = Math.max(res, getCapacity(array, true));
                            if (x == maxValue) {
                                if (x > tempx) {
                                    array[i]--;
                                    array[j]++;
                                    res = Math.max(res, getCapacity(array, true));
                                    array[i]++;
                                    array[j]--;
                                }
                                if (y > tempy) {
                                    array[i]++;
                                    array[j]--;
                                    res = Math.max(res, getCapacity(array, true));
                                    array[i]--;
                                    array[j]++;
                                }
                            }

                        }

                    } else {
                        x = array[i] + Math.min(maxValue - array[i], m);
                        y = array[j] + (m - Math.min(maxValue - tempx, m));
                        array[i] = x;
                        array[j] = y;
                        res = Math.max(res, getCapacity(array, false));
                        array[i] = tempx;
                        array[j] = tempy;
                        y = array[j] + Math.min(maxValue - array[j], m);
                        x = array[i] + (m - Math.min(maxValue - tempy, m));
                        array[i] = x;
                        array[j] = y;
                        res = Math.max(res, getCapacity(array, false));
                    }
                    array[i] = tempx;
                    array[j] = tempy;
                }
        }
        return res;

    }

    private static int getCapacity(int[] array, boolean addInterval) {
        if (array == null || array.length <= 2) {
            return 0;
        }
        int capacity = 0; //初始容量
        int preMax = -1;
        int maxInteval = 0;
        int maxIndexLeft = 0;
        int maxIndexRight = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > preMax) {
                preMax = array[i];
                maxInteval = Math.max(maxInteval, i - maxIndexLeft - 1);
                maxIndexLeft = i;
            }
        }

        preMax = -1;
        for (int i = 0; i <= maxIndexLeft; i++) {
            if (array[i] > preMax) {
                preMax = array[i];
            } else {
                capacity += preMax - array[i];
            }
        }
        preMax = -1;
        for (int i = array.length - 1; i >= maxIndexLeft; i--) {
            if (array[i] > preMax) {
                preMax = array[i];
                maxInteval = Math.max(maxInteval, maxIndexRight - i - 1);
                maxIndexRight = i;
            } else {
                capacity += preMax - array[i];
            }
        }
        if (addInterval) {
            return capacity + maxInteval;
        } else {
            return capacity;
        }
    }

    /******************************结束写代码******************************/


    public static void mains(String[] args){
        Scanner in = new Scanner(System.in);
        int res;

        int _array_size = 0;
        _array_size = Integer.parseInt(in.nextLine().trim());
        int[] _array = new int[_array_size];
        int _array_item;
        for(int _array_i = 0; _array_i < _array_size; _array_i++) {
            _array_item = Integer.parseInt(in.nextLine().trim());
            _array[_array_i] = _array_item;
        }

        int _m;
        _m = Integer.parseInt(in.nextLine().trim());

        res = findMaxCapacity(_array, _m);
        System.out.println(String.valueOf(res));

    }




    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        int temp = Integer.MAX_VALUE;//用于记录相同的数值
        ArrayList<String> as = new ArrayList<String>();//存储输入的序列
        ArrayList<Integer> ai = new ArrayList<Integer>();//用于记录当前序列的十进制数
        ArrayList<Integer> ad = new ArrayList<Integer>();//用于记录异数
        while(!(str = br.readLine()).equals("END")){
            as.add(str);
            String[] nowstr = str.split("#");
            int n = Integer.parseInt(nowstr[0]);
            int num = xToten(n,nowstr[1]);
            ai.add(num);
            if(ad.contains(num)){
                temp = num;
                ad.remove(new Integer(num));
            }
            else
                ad.add(num);
        }
        ad.remove(new Integer(temp));//防止还有一个相同数被添加
        for(int i = 0;i<ad.size();i++){
            int index = ai.indexOf(ad.get(i));
            System.out.println(as.get(index));
        }



    }

    public static int xToten(int n,String str){
        if(n==10)return Integer.parseInt(str);
        char[] ch = str.toCharArray();
        int sum = 0;
        for(int i = ch.length - 1,j=0;i>=0;i--){
            if(ch[i]>='0'&&ch[i]<='9')
                sum+=(ch[i]-'0')*(int)Math.pow(n,j++);
            else
                sum+=(ch[i]-'A'+10)*(int)Math.pow(n,j++);
        }
        //System.out.println(sum);
        return sum;
    }




}
