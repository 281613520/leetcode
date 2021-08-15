package stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 一个数组的 最小乘积 定义为这个数组中 最小值 乘以 数组的 和 。
 *
 * 比方说，数组 [3,2,5] （最小值是 2）的最小乘积为 2 * (3+2+5) = 2 * 10 = 20 。
 * 给你一个正整数数组 nums ，请你返回 nums 任意 非空子数组 的最小乘积 的 最大值 。由于答案可能很大，请你返回答案对  109 + 7 取余 的结果。
 *
 * 请注意，最小乘积的最大值考虑的是取余操作 之前 的结果。题目保证最小乘积的最大值在 不取余 的情况下可以用 64 位有符号整数 保存。
 *
 * 子数组 定义为一个数组的 连续 部分。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3,2]
 * 输出：14
 * 解释：最小乘积的最大值由子数组 [2,3,2] （最小值是 2）得到。
 * 2 * (2+3+2) = 2 * 7 = 14 。
 * 示例 2：
 *
 * 输入：nums = [2,3,3,1,2]
 * 输出：18
 * 解释：最小乘积的最大值由子数组 [3,3] （最小值是 3）得到。
 * 3 * (3+3) = 3 * 6 = 18 。
 * 示例 3：
 *
 * 输入：nums = [3,1,5,6,4,2]
 * 输出：60
 * 解释：最小乘积的最大值由子数组 [5,6,4] （最小值是 4）得到。
 * 4 * (5+6+4) = 4 * 15 = 60 。
 */
public class MaxSumMinProduct {
    public static int maxSumMinProduct(int[] nums) {
        long mod = 1000000000+7;
        long res = 0;

        //单调递增的栈
        Stack<Integer> stack = new Stack<>();
        int [] left = new int[nums.length];
        int [] right = new int[nums.length];
        //前缀和sums[i]为nums[0...i]的和
        long [] sums = new long[nums.length];
        sums[0] = nums[0];
        for (int i=1;i<nums.length;i++){
            sums[i] = sums[i-1]+nums[i];
        }
        // 初始化边界值
        Arrays.fill(left,-1);
        Arrays.fill(right,nums.length);
        for (int i=0;i<nums.length;i++){
            // 对于nums[i],如果栈不为空且栈顶下标对应元素大于或等于nums[i],更新栈顶元素的right
            while (!stack.isEmpty()&&nums[stack.peek()]>=nums[i]){
                right[stack.peek()] = i;
                stack.pop();
            }
            // 如果栈不为空，说明栈顶元素比nums[i]小，更新i对应的left
            if (!stack.isEmpty()){
                left[i] = stack.peek();
                stack.push(i);
            }else {
                // 如果栈为空，直接压栈
                stack.push(i);
            }
        }
        // 求最值
        for (int i=0;i<nums.length;i++){
            res = Math.max(nums[i]*(sums[right[i]-1]-(left[i]==-1?0:sums[left[i]])),res);
        }

        return (int) (res%mod);

    }

    public static void main(String[] args) {
        maxSumMinProduct(new int[]{3,1,5,6,4,2});
    }
}
