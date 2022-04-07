package data_structure;


import javax.management.openmbean.TabularData;

public class skip_list {
    private static final float SKIPLIST_P = 0.5f;
    private static final int MAX_LEVEL = 16;
    public static void main(String[] args) {
        MySkipList list = new MySkipList();
//        SkipList list = new SkipList();
        list.insert(1);
        list.insert(2);
        list.insert(-1);
        list.insert(4);
        list.insert(-3);

        System.out.println(list.find(1).data);

    }
}
