package leecode;

public class ice_cream_low {
    public static int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        boolean[] got = new boolean[target + 1];//已达总成本值
        int overMin = 20000;//最小超目标成本
        for (int baseCost : baseCosts) {
            if (baseCost < target) got[baseCost] = true;
            else overMin = Math.min(overMin, baseCost);
        }
        for (int toppingCost : toppingCosts) {
            for (int base = target - 1; base > 0; base--) {//从大到小推导，避开新值
                if (got[base]) {
                    int value = toppingCost + base;
                    if (value < target) {
                        got[value] = true;
                        value += toppingCost;
                        if (value < target) got[value] = true;
                        else overMin = Math.min(overMin, value);
                    } else overMin = Math.min(overMin, value);
                }
            }
            if (overMin == target) return target;
        }
        for (int dif = 0; dif <= overMin - target; dif++) {
            if (target - dif >= 0 && got[target - dif]) return target - dif;
        }
        return overMin;
    }
    public static void main(String[] args) {
        int[] a = {2,3};
        int[] b = {4,5,100};
        System.out.println(closestCost(a,b,18));
    }
}
