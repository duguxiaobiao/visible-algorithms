package com.lonely;

/**
 * @author ztkj-hzb
 * @Date 2019/8/20 11:43
 * @Description
 */
public class Test {

    public static void main(String[] args) {

        int[] nums = {2, 4, 5, 1, 6};

        for (int i = 0; i < nums.length; i++) {
            int compareIndex = i;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[compareIndex] < nums[j]) {
                    int temp = nums[compareIndex];
                    nums[compareIndex] = nums[j];
                    nums[j] = temp;

                    compareIndex = j;
                }
            }

            for (int z = 0; z < nums.length; z++) {
                System.out.print(nums[z] + ",");
            }
            System.out.println();
        }


    }


}
