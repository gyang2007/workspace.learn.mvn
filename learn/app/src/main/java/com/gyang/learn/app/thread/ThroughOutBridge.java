package com.gyang.learn.app.thread;

/**
 * 多线程练习
 * 
 * @author gyang
 *
 */
public class ThroughOutBridge {

	public static void main(String[] args) {
		Bridge b = Bridge.getInstance();// 实例化桥
		// 实例化左端9个人，此时所有人都不能过桥，桥的可以状态标志为不可以用
		for (int i = 1; i <= 9; i++) {
			Thread t = new Thread(new Person(false, i, b));
			t.start();
		}
		// 实例化右端12个人，此时所有人都不能过桥，桥的可以状态标志为不可以用
		for (int i = 1; i <= 12; i++) {
			Thread t = new Thread(new Person(true, i, b));
			t.start();
		}
		// 桥的可用状态给左端第10个人，可以自定义给谁
		b.state = true;
		Thread t = new Thread(new Person(false, 10, b));
	}

}

class Person implements Runnable {

	public Bridge bridge;// 桥
	private String hand;// 在桥哪一端
	int No;// 序号

	public Person(boolean side, int i, Bridge bridge) {

		this.No = i;
		this.bridge = bridge;
		if (bridge.state) {
			System.out.println(i + "已经过桥。");
		}
		if (side) {
			this.hand = new String("右");
		} else {
			this.hand = new String("左");
		}
	}

	// 过桥方法
	public synchronized void through() throws InterruptedException {

		if (bridge.state) {
			System.out.println(hand + "边第" + No + "在过桥");
			bridge.open(No);
		} else {
			bridge.lock(No);

		}
	}

	public void run() {
		try {
			Thread.sleep(1000);
			// System.out.println(No+hand+" 边已经过桥!");
			through();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Bridge {
	// 可用状态判断true：可用
	public boolean state = false;

	// 自行实例化
	public static Bridge getInstance() {
		return new Bridge();
	}

	public synchronized void open(int i) throws InterruptedException {
		if (state) {
			Thread.sleep(1000);
			this.state = true;
			notify();
		}
	}

	public synchronized void lock(int i) throws InterruptedException {
		if (!state) {
			this.state = false;
			System.out.println(i + " 在等待.");
			wait();
		}
	}
}
