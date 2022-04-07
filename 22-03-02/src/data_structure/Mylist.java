package data_structure;

import sun.awt.image.ImageWatched;

import java.util.HashMap;

/**
 * @author saltfish
 * @date 2022-03-03
 * TODO:使用双向链表实现lru算法java版
 */

class LinkNode<T> {
    public String key;
    public T value;
    public LinkNode prev;//前驱
    public LinkNode next;//后继
}

class LinkCache<T>{
    //缓存对照表
    private HashMap<String,LinkNode<T>> map = new HashMap<>();
    private int count;//元素长度
    private int capacity;//缓存容量
    private LinkNode<T> head,tail;//头和尾

    public LinkCache(int capacity){
        this.capacity = capacity;
        this.count = 0;
        this.head = null;
        this.tail = null;
    }

    /**
     * TODO:向焕存中加入信息
     * @param key 节点对应的key
     * @param data 节点对应的信息
     */
    public void setNode(String key,T data){
        LinkNode<T> node = map.get(key);
        if(node != null){
            intoHead(node);
        }else if(count == capacity){
            String keyt = tail.key;
            this.map.remove(keyt);
            del(tail);
            LinkNode<T> newNode = new LinkNode<>();
            newNode.key = key;
            newNode.value = data;
            add(newNode);
            this.map.put(key,newNode);
        }else {
            LinkNode<T> newNode = new LinkNode<>();
            newNode.key = key;
            newNode.value = data;
            add(newNode);
            this.map.put(key,newNode);
            count++;
        }
    }

    /**
     * TODO:将对应的node移动到头结点（设计思路:先删除再添加）
     * @param node 要移动到head的节点
     */



    private void intoHead(LinkNode<T> node){
        if(head != node){
            LinkNode<T> link = new LinkNode<>();
            link.next = null;
            link.prev = null;
            link.key = node.key;
            link.value = node.value;
         del(node);
         add(link);
        }
    }

    /**
     * TODO: 移除节点
     * @param node 被移除的节点
     */


    private void del(LinkNode<T> node){
        LinkNode<T> prev = node.prev;
        LinkNode<T> next = node.next;
        if(prev == null){
            this.head = next;
            next.prev = null;
            System.gc();
        }else if(next == null){
            this.tail = prev;
            prev.next = null;
            System.gc();
        }else{
            prev.next = next;
            next.prev = prev;
            System.gc();
        }
    }

    /**
     * TODO:添加节点
     * @param node 被添加的节点
     */
    private void add(LinkNode node){
        if(this.head == null){
            this.head = node;
            this.tail = node;
        }else if(count == capacity){
            LinkNode<T> prev = this.tail.prev;
            this.tail = prev;
            System.gc();
            LinkNode<T> old = this.head;
            this.head = node;
            node.next = old;
            old.prev = node;
        }else {
            LinkNode<T> old = this.head;
            this.head = node;
            node.next = old;
            old.prev = node;
        }
    }

    /**
     * TODO:测试用:查看链表中的元素情况
     */
    public void display(){
        LinkNode<T> p = this.head;
        if(count == 0){
            System.out.println("缓存链表已空");
            return;
        }
        for (int i = 0;i < count;i++){
            System.out.print(p.value+"   ");
            p = p.next;
        }
    }

    /**
     * TODO:拿出缓存对应的信息并更新缓存链条
     * @param key 通过key拿到相应的值
     * @return 从缓存中拿出来的信息
     */
    public T get(String key){
        LinkNode<T> node = this.map.get(key);
        if(node == null){
            System.err.println("未找到相应节点信息");
            return null;
        }
        intoHead(node);
        return node.value;
    }

    /**
     * 清空缓存
     */
    public void clear(){
        this.map.clear();
        this.head = null;
        this.tail = null;
        this.count = 0;
        System.gc();
    }


}


public class Mylist {
    public static void main(String[] args) {
        LinkCache<String> cache = new LinkCache<>(2);
        cache.setNode("1","{message:1}");
        cache.setNode("2","{message,2}");
        cache.setNode("3","{message,3}");
        cache.display();
        System.out.println();
        System.out.println(cache.get("2"));
        cache.display();
        cache.clear();
        cache.display();

    }
}
