package com.raylew.algorithm.other;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.Random;
import java.util.Stack;

/**
 * 各种排序算法,升序
 * 
 * @author Administrator
 */
public class 排序算法集合 {

	public static int[] arr;
	public static int N;
	public static int size = 10;

	public static void main(String[] args) throws Exception {
		N = size;
		arr = new int[size];
		createStartFile(false);
		// selection();
		// bubble();
		// insert();
		quick(arr, 0, N - 1);
		createResFile(false);
	}

	public static void createStartFile(boolean createFile) throws Exception {
		if (createFile) {
			File file = new File("F:/随机数据.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			OutputStream out = new FileOutputStream(file);
			Random random = new Random();
			for (int i = 0; i < size; i++) {
				arr[i] = random.nextInt(10);
				out.write((arr[i] + "").getBytes());
				out.write(" ".getBytes());
			}
			out.close();
		} else {
			Random random = new Random();
			for (int i = 0; i < size; i++) {
				arr[i] = random.nextInt(10);
			}
			printArr(arr);
		}
	}

	public static void createResFile(boolean createFile) throws Exception {
		if (createFile) {
			FileWriter writer = new FileWriter("F:/排序结果.txt");
			for (int i = 0; i < size; i++) {
				writer.write(arr[i] + " ");
			}
			writer.close();
		} else {
			printArr(arr);
		}
	}

	/**
	 * 冒泡
	 */
	public static void bubble() {
		System.out.println("冒泡排序");
		int temp;
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < N - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {// 交换
					temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
			printArr(arr);
		}
	}

	/**
	 * 选择
	 */
	public static void selection() {
		System.out.println("选择排序");
		int t, temp;
		for (int i = 0; i < N - 1; i++) {
			t = i;
			for (int j = i + 1; j < N; j++) {// 每一次选择余下的最小的数
				if (arr[t] > arr[j]) {
					t = j;
				}
			}
			temp = arr[t];
			arr[t] = arr[i];
			arr[i] = temp;
		}
	}

	/**
	 * 插入
	 */
	public static void insert() {
		System.out.println("插入排序");
		if (arr != null) {
			for (int i = 1; i < arr.length; i++) {
				int temp = arr[i], j = i;
				if (arr[j - 1] > temp) {
					while (j >= 1 && arr[j - 1] > temp) {
						arr[j] = arr[j - 1];
						j--;
					}
				}
				arr[j] = temp;
				printArr(arr);
			}
		} else {
			System.out.print("array hasn't been initialized!");
		}
	}

	/**
	 * 快速排序
	 */
	public static void quick(int[] a, int low, int high) {// 递归快排
		if (low < high) {
			int index = partition(a, low, high);// 每执行一次partition函数数组就按照pivot分成两块了
			quick(a, low, index - 1);
			quick(a, index + 1, high);
		}
	}

	public static int partition(int[] a, int low, int high) {// 分块方法，在数组a中，对下标从low到high的数列进行划分
		int pivot = a[low];// 把比pivot(初始的pivot=a[low]小的数移动到pivot的左边
		while (low < high) {
			while (low < high && a[high] >= pivot) {// 把比pivot大的数移动到pivot的右边,先移动右边是因为左边的pivot位置空出来了
				high--;
			}
			a[low] = a[high];
			while (low < high && a[low] <= pivot) {// 把比pivot小的数移动到pivot的左边
				low++;
			}
			a[high] = a[low];
		}
		a[low] = pivot;// 此时low==high
		return low;// 返回划分后的pivot的位置
	}

	/**
	 * 非递归快排，两个栈
	 * 
	 * @param a
	 */
	public static void nonRecrutQuick(int[] a) {
		// 设置两个栈，一个用于保存
		if (a == null || a.length < 0) {
			return;
		}
		Stack<Integer> lowStack = new Stack<Integer>();// 保存当前划分的最高位
		Stack<Integer> highStack = new Stack<Integer>();// 保存当前划分的最低位
		int low = 0;
		int high = a.length - 1;
		int pivotPos;
		lowStack.push(low);
		highStack.push(high);
		while (!lowStack.isEmpty()) {// 先排好一边再排另一边
			low = lowStack.pop();
			high = highStack.pop();
			pivotPos = partition(a, low, high);
			if (low < pivotPos - 1) {
				lowStack.push(low);
				highStack.push(pivotPos - 1);
			}
			if (high > pivotPos + 1) {
				lowStack.push(pivotPos + 1);
				highStack.push(high);
			}
			printArr(arr);
			System.out.println("lowStack:" + lowStack + ",highStack:"
					+ highStack);
		}
	}

	/**
	 * 归并排序
	 */
	public static int[] mergeSort(int[] a, int s, int t, int[] b) {
		int m;
		int[] temp = new int[t + 1];
		if (s == t) {
			b[s] = a[s];
		} else {
			m = (s + t) / 2;
			mergeSort(a, s, m, temp);// 左半部分递归调用
			mergeSort(a, m + 1, t, temp);// 右半部分递归调用
			merge(temp, s, m, t, b);// 由temp去归并，返回的值放到b中,b赋新值，其实就是更新temp,然后让temp再去归并，返回新的b
		}
		return a;
	}

	/**
	 * 有序表的合并
	 * 
	 * @param a
	 *            原表
	 * @param l
	 *            low
	 * @param m
	 *            pivotIndex
	 * @param n
	 *            high
	 * @param b
	 *            新表
	 */
	private static void merge(int[] a, int l, int m, int n, int[] b) {
		int i, j, k;
		i = l;
		j = m + 1;
		k = l;
		while (i <= m && j <= n) {
			if (a[i] < a[j]) {
				b[k++] = a[i++];
			} else {
				b[k++] = a[j++];
			}
		}
		while (i <= m) {// 补上剩余的部分
			b[k++] = a[i++];
		}
		while (j <= n) {
			b[k++] = a[j++];
		}
	}

	/**
	 * 输出每一趟排序的结果
	 */
	public static void printArr(int[] arr) {
		for (int i = 0; i < N; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println("");
	}
}
