package com.lonely;

/**
 * @author ztkj-hzb
 * @Date 2019/8/20 10:23
 * @Description
 */
public class SelectSortData {


    /**
     * 数据
     */
    private int[] nums;

    /**
     * 指定索引范围[0,ordserIndex) 范围为已排好序的
     */
    private int orderedIndex = -1;
    /**
     * 当前正在比较的索引index
     */
    private int currCompareIndex = -1;
    /**
     * 当前最小值的index
     */
    private int currMinIndex = -1;

    public SelectSortData(int randomNum, int size) {
        this.nums = new int[size];
        for (int i = 0; i < size; i++) {
            int random = (int) ((Math.random() * randomNum) + 1);
            this.nums[i] = random;
        }
    }

    /**
     * 交换位置
     *
     * @param index    带交换的位置
     * @param minIndex 最小值的索引位置
     */
    public void swap(int index, int minIndex) {
        int temp = this.nums[index];
        this.nums[index] = this.nums[minIndex];
        this.nums[minIndex] = temp;
    }

    public int[] getNums() {
        return nums;
    }

    public int getSize() {
        return this.nums.length;
    }

    public int getOrderedIndex() {
        return orderedIndex;
    }

    public void setOrderedIndex(int orderedIndex) {
        this.orderedIndex = orderedIndex;
    }

    public int getCurrCompareIndex() {
        return currCompareIndex;
    }

    public void setCurrCompareIndex(int currCompareIndex) {
        this.currCompareIndex = currCompareIndex;
    }

    public int getCurrMinIndex() {
        return currMinIndex;
    }

    public void setCurrMinIndex(int currMinIndex) {
        this.currMinIndex = currMinIndex;
    }


}
