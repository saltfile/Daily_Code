package leecode;

/*
 * 在8×8格的国际象棋上摆放8个皇后，使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 * 回溯法模板
void backtracking(参数) {
    if (终止条件) {
        存放结果;
        return;
    }

    for (选择：本层集合中元素（树中节点孩子的数量就是集合的大小）) {
        处理节点;
        backtracking(参数); // 递归
        回溯，撤销处理结果
    }
 */

public class EightQueen {
    public static void main(String[] args) {
        EightQueen solution=new EightQueen();
        solution.eightQueen(0);
    }
    int []Queen=new int[8];
    int count=0;
    public void eightQueen(int row){
        if (row==8){
            count++;
            printQueens(Queen);
            return;
        }
        for (int column = 0; column < 8; column++) {
            if (isOk(row,column)){
                Queen[row]=column;
                eightQueen(row+1);
            }
        }
    }
    public boolean isOk(int row,int column){
        int leftup=column-1,rightup=column+1;
        for (int i = row-1; i >=0 ; --i) {
            if (Queen[i]==column) return false;//看垂直方向是否有皇后
            if (leftup>=0){
                if (Queen[i]==leftup) return false;//看左上角斜线是否有皇后
            }
            if (rightup<8){
                if (Queen[i]==rightup) return false;//看右上角斜线是否有皇后
            }
            --leftup;
            ++rightup;
        }
        return true;
    }
    private void printQueens(int []Queen){
        for (int row = 0; row < 8; row++) {
            for (int column = 0; column < 8; column++) {
                if (Queen[row]==column) System.out.print("   Q   ");
                else System.out.print("   *   ");
            }
            System.out.println();
        }
        System.out.print(count);
        System.out.println();
    }
}
