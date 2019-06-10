import java.util.Vector;

/**
 * 保存每一步的情况，包括每块拼图的位置、缺失块的位置和到当前步的解决路径
 */
public class Step {

    private int[] num;
    // 每块拼图的位置
    private int missingIndex;
    // 缺失块的位置
    private Vector<Integer> solution;
    // 到当前步的解决路径

    /**
     * 初始化的构造方法
     *
     * @param num          每块拼图的位置
     * @param missingIndex 缺失块的位置
     */
    public Step(int[] num, int missingIndex) {
        this.num = num;
        this.missingIndex = missingIndex;
        this.solution = new Vector<>();
        solution.add(missingIndex);
    }

    /**
     * 生成下一步的构造方法
     *
     * @param num          每块拼图的位置
     * @param missingIndex 缺失块的位置
     * @param solution     解决路径
     */
    private Step(int[] num, int missingIndex, Vector<Integer> solution) {
        this.num = new int[num.length];
        System.arraycopy(num, 0, this.num, 0, num.length);
        this.missingIndex = missingIndex;
        this.solution = new Vector<>();
        this.solution.addAll(solution);
    }

    /**
     * 生成下一步，如果不能移动拼图就返回null
     *
     * @param index 需要与缺失块交换的拼图
     * @return 返回交换后的Step或null
     */
    public Step getNext(int index) {
        boolean flag = false;
        if (missingIndex < 3) {
            if (missingIndex + 3 == index) {
                flag = true;
            }
            if (index < 3 && index != missingIndex) {
                flag = index - missingIndex != 2 && missingIndex - index != 2;
            }
        } else if (missingIndex < 6) {
            if (missingIndex + 3 == index) {
                flag = true;
            }
            if (missingIndex - 3 == index) {
                flag = true;
            }
            if (index >= 3 && index < 6 && index != missingIndex) {
                flag = index - missingIndex != 2 && missingIndex - index != 2;
            }
        } else {
            if (missingIndex - 3 == index) {
                flag = true;
            }
            if (index >= 6 && index < 9 && index != missingIndex) {
                flag = index - missingIndex != 2 && missingIndex - index != 2;
            }
        }
        if (flag) {
            Step step = new Step(num, missingIndex, solution);
            step.solution.add(index);
            int temp = step.num[step.missingIndex];
            step.num[step.missingIndex] = step.num[index];
            step.num[index] = temp;
            step.missingIndex = index;
            return step;
        }
        return null;
    }

    /**
     * 检查是否所有拼图已归位
     *
     * @return 所有拼图已归位则返回true，否则返回false
     */
    public boolean check() {
        for (int i = 0; i < 9; i++) {
            if (num[i] != i) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取解决路径
     *
     * @return 解决路径
     */
    public Vector<Integer> getSolution() {
        return solution;
    }
}
