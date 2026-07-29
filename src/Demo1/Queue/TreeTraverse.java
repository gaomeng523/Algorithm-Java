package Demo1.Queue;

import java.util.*;

public class TreeTraverse {

    /**
     * N 叉树的层序遍历
     * https://leetcode.cn/problems/n-ary-tree-level-order-traversal/
     * @param node
     * @return
     */
    public List<List<Integer>> levelOrder(Node node) {
        Queue<Node> q = new LinkedList<>();
        List<List<Integer>> ret = new ArrayList<>();
        if(node == null){
            return ret;
        }
        q.add(node);
        while(!q.isEmpty()){
            int count = q.size();
            List<Integer> tmp = new ArrayList<>();
            while(count-- > 0){
                Node t = q.poll();
                tmp.add(t.val);
            }
            ret.add(tmp);
        }
        return ret;
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();

        if(root == null) return ret;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int n = 1;
        while(!q.isEmpty()){
            int count = q.size();
            List<Integer> tmp = new ArrayList<>();
            while(count-- > 0){
                TreeNode t = q.poll();
                tmp.add(t.val);
                if(t.left != null) q.add(t.left);
                if(t.right != null) q.add(t.right);
            }
            if(n % 2 == 0) Collections.reverse(tmp);
            n++;
            ret.add(tmp);
        }
        return ret;
    }


    /**
     * ⼆叉树的最⼤宽度 https://leetcode.cn/problems/maximum-width-of-binary-tree/description/
     * @param root
     * @return
     */
    public int widthOfBinaryTree(TreeNode root) {
        List<Pair<TreeNode , Integer>> q = new ArrayList<>();
        q.add(new Pair<TreeNode , Integer>(root , 1));
        int ret = 0;
        while(!q.isEmpty()){
            Pair<TreeNode , Integer> t1 = q.get(0);
            Pair<TreeNode , Integer> t2 = q.get(q.size() - 1);
            ret = Math.max(ret , t2.getValue() - t1.getValue() + 1);
            List<Pair<TreeNode , Integer>> tmp = new ArrayList<>();
            for(Pair<TreeNode , Integer> t : q){
                TreeNode node = t.getKey();
                if(node.left != null) {
                    tmp.add(new Pair<TreeNode , Integer>(node.left , 2*t.getValue()));
                }
                if(node.right != null){
                    tmp.add(new Pair<TreeNode , Integer>(node.right , 2 * t.getValue() + 1));
                }
            }
            q = tmp;
        }
        return ret;
    }


    /**
     * 在每个树⾏中找最⼤值
     * https://leetcode.cn/problems/find-largest-value-in-each-tree-row/
     * @param root
     * @return
     */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> ret = new ArrayList<>();
        if(root == null) return ret;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            int sz = q.size();
            int tmp = Integer.MIN_VALUE;
            // 遍历当前一层所有节点
            for(int i = 0; i < sz; i++){
                TreeNode t = q.poll();
                tmp = Math.max(tmp, t.val);
                if(t.left != null) q.add(t.left);
                if(t.right != null) q.add(t.right);
            }
            ret.add(tmp);
        }
        return ret;
    }
}
