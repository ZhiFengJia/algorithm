package com.jzf;

import java.util.Arrays;

/**
 * 计数排序的基本思想: 为一组数在排序之前先统计这组数中其他数小于这个数的个数,则可以确定这个数的位置.
 *
 * @author Jia ZhiFeng <312290710@qq.com>
 * @date 2019/5/30 18:00:20
 */
public class CountSortTest {
    /**
     * 计数排序
     *
     * @param arr 排序前的数组
     * @return 排序后的数组
     */
    public static int[] sort(int[] arr) {
        int[] result = new int[arr.length];
        int[] count = new int[max(arr) + 1];

        // 将数据出现的次数存储在count[]中
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        System.out.println("统计次数: " + Arrays.toString(count));

        // 目的是让更改后的k[i]的值,是该数据在result[]中的位置.
        for (int i = 1; i < count.length; i++) {
            count[i] = count[i] + count[i - 1];
        }
        System.out.println("数据位置: " + Arrays.toString(count));

        for (int i = 0; i < arr.length; i++) {
            result[--count[arr[i]]] = arr[i];
        }
        return result;
    }

    private static int max(int[] arr) {
        return Arrays.stream(arr).max().getAsInt();
    }


    public static void main(String[] args) {
        int[] src = {12, 23, 34, 65, 20, 31, 13, 23, 46};
        System.out.println("排序前的数组: " + Arrays.toString(src));

        int[] result = sort(src);
        System.out.println("排序后的数组: " + Arrays.toString(result));
    }
}
