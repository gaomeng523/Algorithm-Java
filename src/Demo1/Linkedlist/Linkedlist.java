package Demo1.Linkedlist;


import java.util.PriorityQueue;

public class Linkedlist {

    /**
     * 两数相加  https://leetcode.cn/problems/add-two-numbers/
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(0);
        ListNode prev = newHead;
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        int t = 0;
        while(cur1 != null || cur2 != null || t != 0){
            if(cur1 != null){
                t += cur1.val;
                cur1 = cur1.next;
            }
            if(cur2 != null){
                t += cur2.val;
                cur2 = cur2.next;
            }
            prev.next = new ListNode(t % 10);
            prev = prev.next;
            t /= 10;
        }
        return newHead.next;
    }

    /**
     * 两两交换链表中的节点 https://leetcode.cn/problems/swap-nodes-in-pairs/
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode newHead = new ListNode(0);
        if(head == null || head.next == null) return head;
        newHead.next = head;
        ListNode prev = newHead , cur = prev.next , next = cur.next , nnext = next.next;

        while(cur != null && next != null){
            prev.next = next;
            next.next = cur;
            cur.next = nnext;

            prev = cur;
            cur = nnext;
            if(cur != null) next = cur.next;
            if(next != null) nnext = next.next;
        }
        return newHead.next;
    }

    /**
     * 重排链表 https://leetcode.cn/problems/reorder-list/
     * @param head
     */
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        // 1.快慢指针找中点
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 分割前后两段链表
        ListNode second = slow.next;
        slow.next = null; // 切断前半段！重点
        // 2.反转后半段链表
        second = reverseList(second);
        // 3.交替合并两个链表
        ListNode p1 = head;
        ListNode p2 = second;
        while (p2 != null) {
            ListNode next1 = p1.next;
            ListNode next2 = p2.next;
            p1.next = p2;
            p2.next = next1;
            p1 = next1;
            p2 = next2;
        }
    }
    // 反转链表辅助函数
    private ListNode reverseList(ListNode head) {
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    /**
     * 合并 K 个升序链表 https://leetcode.cn/problems/merge-k-sorted-lists/
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap = new PriorityQueue<>((v1 , v2) -> v1.val - v2.val);
        for(ListNode head : lists)
            if(head != null) heap.offer(head);
        ListNode ret = new ListNode(0);
        ListNode prev = ret;
        while(!heap.isEmpty()){
            ListNode t = heap.poll();
            prev.next = t;
            if(t.next != null)
                heap.offer(t.next);
            prev = prev.next;
        }
        return ret.next;
    }

    public ListNode mergeKLists2(ListNode[] lists) {
        return mergeSort(lists , 0 , lists.length - 1);
    }

    public ListNode mergeSort(ListNode[] lists , int left , int right){
        if(left > right) return null;
        if(left == right) return lists[left];

        int mid = left + (right - left)/2;

        ListNode l1 = mergeSort(lists , left , mid);
        ListNode l2 = mergeSort(lists , mid + 1 , right);
        return mergeTwoList(l1 , l2);
    }
    public ListNode mergeTwoList(ListNode l1 , ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        ListNode head = new ListNode(0);
        ListNode prev = head;

        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                prev.next = l1;
                l1 = l1.next;
                prev = prev.next;
            }else{
                prev.next = l2;
                l2 = l2.next;
                prev = prev.next;
            }
        }
        while(l1 != null){
            prev.next = l1;
            l1 = l1.next;
            prev = prev.next;
        }
        while(l2 != null){
            prev.next = l2;
            l2 = l2.next;
            prev = prev.next;
        }
        return head.next;
    }

    /**
     * k 个一组翻转链表
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 虚拟头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // pre：每组区间的前驱节点
        ListNode pre = dummy;

        while (true) {
            // 定位当前组末尾节点
            ListNode tail = pre;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                // 剩余节点不足k个，直接退出循环
                if (tail == null) {
                    return dummy.next;
                }
            }
            // 保存下一组的起点
            ListNode nextStart = tail.next;
            // 翻转 \[pre.next, tail\] 区间
            ListNode newHead = reverse(pre.next, tail);
            // 重新连接链表
            ListNode oldHead = pre.next;
            pre.next = newHead;
            oldHead.next = nextStart;
            // pre移动到本组原来的头，作为下一组前驱
            pre = oldHead;
        }
    }

    private ListNode reverse(ListNode start, ListNode end) {
        ListNode prev = null;
        ListNode cur = start;
        // 翻转到end为止
        while (prev != end) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }
}
