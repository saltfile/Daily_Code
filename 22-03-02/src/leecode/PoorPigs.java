package leecode;
class Solution458 {
    public int poorPigs(int buckets, int tdie, int ttest) {
        int test = ttest/tdie;
        int i=0;
        while(Math.pow(test+1,i)< buckets){
            i++;
        }return i;
    }
}
public class PoorPigs {
    public static void main(String[] args) {
        System.out.println(new Solution458().poorPigs(1000,15,60));
    }
}
