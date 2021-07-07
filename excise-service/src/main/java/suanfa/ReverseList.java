package suanfa;

import com.alibaba.fastjson.JSON;

/**
 * @author maguoqiang
 * @date 2021-06-07 16:52
 */
public class ReverseList {
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }


    public static ListNode iterate(ListNode head){
        ListNode curr = head,currSnashot=null,next;
        while (curr!=null){
            next = curr.next;

            curr.next = currSnashot;
            currSnashot = curr;

            curr = next;
        }

        return currSnashot;

    }

    public static ListNode recursion(ListNode node){
        //System.out.println("====="+JSON.toJSONString(recursion(node)));
        if (node.next == null){
            return node;
        }
        node.next.next=node;
        return recursion(node);
    }



    public static void main(String[] args) {
        //链表反转
        ListNode node5 = new ListNode(5,null);
        ListNode node4 = new ListNode(4,node5);
        ListNode node3 = new ListNode(3,node4);
        ListNode node2 = new ListNode(2,node3);
        ListNode node1 = new ListNode(1,node2);

        /*ListNode iterate = ReverseList.iterate(node1);
        System.out.println(JSON.toJSONString(iterate,true));*/
        System.out.println(JSON.toJSONString(recursion(node1)));
    }
}
