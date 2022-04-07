package data_structure;

import java.util.Random;

public class demomian {
    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        Random random = new Random();
        skipList.insert(-3);
        for(int i = 0;i < 30;i++) {
            int a = random.nextInt(100);
            skipList.insert(i);
        }
        skipList.find(20);
        skipList.printAll();
    }
}
