package com.raylew.algorithm.book2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class DFS {
	static String[] str = { "r", "s", "t", "u", "v", "w", "x", "y" };
	static LinkedList[] adjacency_list;
	static Vertex1[] vs;
	static int time = 0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		adjacency_list = new LinkedList[8];
		vs = new Vertex1[8];
		for (int i = 0; i < adjacency_list.length; i++) {
			Vertex1 temp = new Vertex1(i);
			temp.setColor("WHITE");
			vs[i] = temp;
		}
		// 初始化邻接表
		LinkedList<Vertex1> list0 = new LinkedList<Vertex1>();
		list0.add(vs[1]);
		list0.add(vs[4]);
		adjacency_list[0] = list0;

		LinkedList<Vertex1> list1 = new LinkedList<Vertex1>();
		list1.add(vs[0]);
		list1.add(vs[5]);
		adjacency_list[1] = list1;

		LinkedList<Vertex1> list2 = new LinkedList<Vertex1>();
		list2.add(vs[3]);
		list2.add(vs[5]);
		list2.add(vs[6]);
		adjacency_list[2] = list2;

		LinkedList<Vertex1> list3 = new LinkedList<Vertex1>();
		list3.add(vs[2]);
		list3.add(vs[6]);
		list3.add(vs[7]);
		adjacency_list[3] = list3;

		LinkedList<Vertex1> list4 = new LinkedList<Vertex1>();
		list4.add(vs[0]);
		adjacency_list[4] = list4;

		LinkedList<Vertex1> list5 = new LinkedList<Vertex1>();
		list5.add(vs[1]);
		list5.add(vs[2]);
		list5.add(vs[6]);
		adjacency_list[5] = list5;

		LinkedList<Vertex1> list6 = new LinkedList<Vertex1>();
		list6.add(vs[2]);
		list6.add(vs[3]);
		list6.add(vs[5]);
		list6.add(vs[7]);
		adjacency_list[6] = list6;

		LinkedList<Vertex1> list7 = new LinkedList<Vertex1>();
		list7.add(vs[3]);
		list7.add(vs[6]);
		adjacency_list[7] = list7;
		// DFS
		System.out.println("DFS遍历序列");
		for (int i = 0; i < vs.length; i++) {
			Vertex1 u = vs[i];
			if (u.getColor().equals("WHITE")) {
				DFS_Visit(u);
			}
		}
		System.out.println();
		// 统计深度优先遍历边的分类
		summary_edge();
	}

	/**
	 * 遍历每一条边，通过比较端点的d和f的值对边进行分类
	 */
	private static void summary_edge() {
		Map edge = new HashMap();
		for (int i = 0; i < vs.length; i++) {
			LinkedList<Vertex1> u_adj = adjacency_list[i];
			Vertex1 u = vs[i];
			for (Iterator iterator = u_adj.iterator(); iterator.hasNext();) {
				Vertex1 v = (Vertex1) iterator.next();
				if (u.getD() < (v.getD() + 1) && u.getF() > (v.getF() + 1)) {
					if (edge.get("forward") == null) {
						LinkedList<String> list = new LinkedList<String>();
						list.add(str[u.getIndex()] + "-->" + str[v.getIndex()]);
						edge.put("tree", list);
					} else {
						LinkedList<Vertex1> list = (LinkedList<Vertex1>) edge
								.get("forward");
						list.add(vs[i]);
					}
				} else if (u.getD() == (v.getD() - 1)
						&& u.getF() == (v.getF() + 1)) {
					if (edge.get("tree") == null) {
						LinkedList<String> list = new LinkedList<String>();
						list.add(str[u.getIndex()] + "-->" + str[v.getIndex()]);
						edge.put("tree", list);
					} else {
						LinkedList<String> list = (LinkedList<String>) edge
								.get("tree");
						list.add(str[u.getIndex()] + "-->" + str[v.getIndex()]);
					}
				} else if (u.getD() > (v.getD() + 1)
						&& u.getF() < (v.getF() - 1)) {
					if (edge.get("back") == null) {
						LinkedList<String> list = new LinkedList<String>();
						list.add(str[u.getIndex()] + "-->" + str[v.getIndex()]);
						edge.put("back", list);
					} else {
						LinkedList<String> list = (LinkedList<String>) edge
								.get("back");
						list.add(str[u.getIndex()] + "-->" + str[v.getIndex()]);
					}
				} else if (u.getF() < v.getD() || u.getD() > v.getF()) {
					if (edge.get("cross") == null) {
						LinkedList<String> list = new LinkedList<String>();
						list.add(str[u.getIndex()] + "-->" + str[v.getIndex()]);
						edge.put("cross", list);
					} else {
						LinkedList<String> list = (LinkedList<String>) edge
								.get("cross");
						list.add(str[u.getIndex()] + "-->" + str[v.getIndex()]);
					}
				}
			}
		}
		LinkedList<String> forward_edge = (LinkedList<String>) edge
				.get("forward");
		System.out.println(forward_edge != null ? ("前向边:" + forward_edge
				.toString()) : "前向边空");
		LinkedList<String> tree_edge = (LinkedList<String>) edge.get("tree");
		System.out.println(tree_edge != null ? ("树边:" + tree_edge.toString())
				: "树边空");
		LinkedList<String> back_edge = (LinkedList<String>) edge.get("back");
		System.out.println(back_edge != null ? ("后向边:" + back_edge.toString())
				: "后向边空");
		LinkedList<String> cross_edge = (LinkedList<String>) edge.get("cross");
		System.out
				.println(cross_edge != null ? ("横向边:" + cross_edge.toString())
						: "横向边空");
	}

	public static void DFS_Visit(Vertex1 u) {
		System.out.print(str[u.getIndex()] + "-->");
		time++;
		u.setD(time);
		u.setColor("GRAY");
		LinkedList<Vertex1> u_adj = adjacency_list[u.getIndex()];
		for (Iterator iterator = u_adj.iterator(); iterator.hasNext();) {
			Vertex1 v = (Vertex1) iterator.next();
			if (v.getColor().equals("WHITE")) {
				v.setPre(u.getIndex());
				DFS_Visit(v);
			}
		}
		time++;
		u.setF(time);
		u.setColor("BLACK");
	}
}

class Vertex1 {
	private String color;// 颜色
	private Integer index;// 索引
	private Integer pre;// 前驱
	private Integer d;// 发现时间
	private Integer f;// 完成时间

	public Vertex1(Integer index) {
		this.index = index;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getPre() {
		return pre;
	}

	public void setPre(Integer pre) {
		this.pre = pre;
	}

	public Integer getD() {
		return d;
	}

	public void setD(Integer d) {
		this.d = d;
	}

	public Integer getF() {
		return f;
	}

	public void setF(Integer f) {
		this.f = f;
	}

}
