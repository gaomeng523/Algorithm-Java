package Demo1;

import java.util.*;

public class pile {

    /**
     * 最后⼀块⽯头的重量
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        // 大顶堆
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        // 全部入堆
        for (int s : stones) {
            maxHeap.offer(s);
        }

        while (maxHeap.size() >= 2) {
            int y = maxHeap.poll(); // 最重
            int x = maxHeap.poll(); // 次重
            if (y > x) {
                maxHeap.offer(y - x);
            }
        }
        // 堆为空返回0，否则返回剩余石头
        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }

    // 小根堆，堆顶 = 第 K 大

    /**
     * 数据流中的第 K ⼤元素  https://leetcode.cn/problems/kth-largest-element-in-a-stream/
     * @param k
     * @param nums
     */
    public void KthLargest(int k, int[] nums) {
        PriorityQueue<Integer>  minHeap = new PriorityQueue<>();
        for (int num : nums) add(num); // 复用 add 逻辑，保持不变量
    }

    public int add(int val) {
        int k = 0;
        PriorityQueue<Integer>  minHeap = new PriorityQueue<>();
        minHeap.offer(val);
        if (minHeap.size() > k) minHeap.poll(); // 超容量，踢掉最小的
        return minHeap.peek();
    }

    /**
     * 前 K 个⾼频单词  https://leetcode.cn/problems/top-k-frequent-words/
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        // 1. 统计词频
        Map<String, Integer> freq = new HashMap<>();
        for (String w : words) freq.merge(w, 1, Integer::sum);

        // 2. 小根堆容量 K
        //    比较器设计：让「最想淘汰的」排在堆顶
        //      - 频率小的排前
        //      - 频率一样时，字典序大的排前（相同频率要保留字典序小的）
        PriorityQueue<String> heap = new PriorityQueue<>((a, b) -> {
            int fa = freq.get(a), fb = freq.get(b);
            return fa != fb ? fa - fb : b.compareTo(a);
        });

        // 3. 遍历所有不重复的词
        for (String w : freq.keySet()) {
            heap.offer(w);
            if (heap.size() > k) heap.poll();   // 超容量淘汰最差的
        }

        // 4. 堆弹出来是「最差→最好」，反转得到「最好→最差」
        List<String> res = new ArrayList<>();
        while (!heap.isEmpty()) res.add(heap.poll());
        Collections.reverse(res);
        return res;
    }

    PriorityQueue<Integer> maxHeap; // 较小一半，大根堆
     PriorityQueue<Integer> minHeap; // 较大一半，小根堆

    public void MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // 1) 先放进「该去」的堆：<= 大根堆顶就进大根堆，否则进小根堆
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) maxHeap.offer(num);
        else minHeap.offer(num);

        // 2) 平衡：保证 maxHeap 不小于 minHeap，且差 ≤ 1
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());   // 大根堆顶(较大的一半里的最大) 跑到小根堆
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());   // 小根堆顶(较小一半里的最小) 跑到大根堆
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) return (maxHeap.peek() + minHeap.peek()) / 2.0;
        return maxHeap.peek();  // 奇数个时 maxHeap 必多一个
    }
}
