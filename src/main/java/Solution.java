import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * 使用BFS计算解决方案
 */
public class Solution {

    /**
     * 使用BFS计算解决方案
     *
     * @param num 所有拼图的位置
     * @param m   缺失块的数字
     * @return 代表解决方案路径的Vector
     */
    public static Vector<Integer> BFS(int[] num, int m) {
        Queue<Step> queue = new LinkedList<>();
        int missingIndex = -1;
        for (int i = 0; i < 9; i++) {
            if (num[i] == m) {
                missingIndex = i;
                break;
            }
        }
        queue.offer(new Step(num, missingIndex));
        while (!queue.isEmpty()) {
            Step step = queue.poll();
            Step next;
            for (int i = 0; i < 9; i++) {
                assert step != null;
                next = step.getNext(i);
                if (next != null) {
                    if (next.check()) {
                        return next.getSolution();
                    } else {
                        queue.offer(next);
                    }
                }
            }
        }
        return null;
    }
}

