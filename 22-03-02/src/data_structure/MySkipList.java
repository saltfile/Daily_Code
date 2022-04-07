package data_structure;




public class MySkipList {
    private static final float RADOM_P = 0.5f;//决定是否加一层的数字（随机用）
    private static final int MAX_LEVEL = 16;//最大跳表层数
    private int levelCount = 1;//目前跳表层数

    private Node head = new Node();



    public class Node{
        public int data = -1;
        public Node LevelCount[] = new Node[MAX_LEVEL];
        public int level = 0;
    }

    public int randomLevel(){
        int level = 1;

        while (Math.random() < RADOM_P && level < MAX_LEVEL)
            level++;
        return level;
    }




    public void insert(int val){
       int level = randomLevel();//选取随机层数
        Node node = new Node();
        node.data = val;
        node.level = level;//此层数固定
        Node update[] = new Node[level];//新的层数
        for (int i = 0; i < level; i++) {
            update[i] = head;
        }

        Node p = head;
        for(int i = level - 1;i >= 0;--i){
            while (p.LevelCount[i] != null&&p.data < val){
                p = p.LevelCount[i];
            }
            update[i] = p;
        }

        for(int i = 0;i < level;i++){//更新每层节点信息
            node.LevelCount[i] = update[i].LevelCount[i];
            update[i].LevelCount[i] = node;
        }
        if(this.levelCount < level)this.levelCount = level;
    }

    public Node find(int val){
        Node p = head;
        for(int i = levelCount-1; i >= 0;i--){
            while (p.LevelCount[i] != null && p.LevelCount[i].data  < val){
                p=p.LevelCount[i];
            }
        }
        if (p.LevelCount[0] != null && p.LevelCount[0].data == val) {
            return p.LevelCount[0];
        } else {
            return null;
        }
    }









}
