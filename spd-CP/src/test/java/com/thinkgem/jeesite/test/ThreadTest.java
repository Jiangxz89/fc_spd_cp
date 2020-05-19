package com.thinkgem.jeesite.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadTest {
	
	public static void main(String[] args) {
//		int [] arrs = {1,3,6,9,8,7,5,2,4};
//		maoPao(arrs);
//		for(int i = 0 ;i<arrs.length;i++){
//			System.out.print(arrs[i]);
//		}
//		
//		int [] arr = {'A','B','C','D','E','F','G','H','I','J'};
//		System.out.println(erFen(arr,'G'));
		
//		initA();
//		initB();
//		initC();
//		initD();
		initF();
	}
	
	
	
	/**1丶对数组进行排序 int [] arrs = {1,3,6,9,8,7,5,2,4}
	 * 
	 * 	int [] arrs = {1,3,6,9,8,7,5,2,4};
		maoPao(arrs);
		for(int i = 0 ;i<arrs.length;i++){
			System.out.print(arrs[i]);
		}
	 */
	public static  void maoPao(int [] arrs){
		int temp = 0;
		int size = arrs.length;
		for(int i=0;i<size-1;i++){
			for(int j=0;j<size-1-i;j++){
				if(arrs[j+1]<arrs[j]){
					temp = arrs[j];
					arrs[j] = arrs[j+1];
					arrs[j+1] = temp;
				}
			}
		}
	}
	
	/**
	 * 	2丶 A B C D E F G H I J 使用算法查询G在第几个位置
	 * 	int [] arr = {'A','B','C','D','E','F','G','H','I','J'};
		System.out.println(erFen(arr,'G'));
	 */
	public static int erFen(int []arrs,int key){
		int start = 0;
		int end = arrs.length;
		while(start<=end){
			int moddle = (start + end)/2;
			if(arrs[moddle]<key){
				start = moddle +1;
			}else if(arrs[moddle] > key){
				end = moddle -1;
			}else{
				return moddle +1;
			}
		}
		
		return -1;
	}
	
	//3丶写个单例模式
	public ThreadTest (){};
	private static ThreadTest threadTest = null;
	public static  synchronized ThreadTest getInstance(){
		if(threadTest==null){
			threadTest = new ThreadTest();
		}
		return threadTest;
	}
	
	//4丶主线程打印100次,子线程打印10次,往复循环50次
	static class ThreadA{
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		boolean flag = true;
		public void main(int size){
			lock.lock();
			try{
				while(!flag){
					try {
						condition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for(int i=1;i<=100;i++){
					System.out.println(Thread.currentThread().getName()+"主线程次数:"+i+"合计次数:"+size);
				}
				flag = false;
				condition.signal();
			}finally{
				lock.unlock();
			}
		}
		
		public void child(int size){
			lock.lock();
			try{
				while(flag){
					try {
						condition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for(int i=1;i<=10;i++){
					System.out.println(Thread.currentThread().getName()+"子线程次数:"+i+"合计次数:"+size);
				}
				flag = true;
				condition.signal();
			}finally{
				lock.unlock();
			}

		}
	}
	
	public static void initA(){
		final ThreadA threadA = new ThreadA();
		new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i=1;i<=50;i++){
					threadA.child(i);
				}
			}
			
		}).start();
		
		for(int j=1;j<=50;j++){
			threadA.main(j);
		}
	}
	
	
	//5丶利用线程实现缓存机制
	private static Map<String,Object> map = new HashMap<String,Object>();
	static class MethodB{
		ReadWriteLock rwl = new ReentrantReadWriteLock();
		public Object get(String key){
			rwl.readLock().lock();
			Object value = null;
			try{
				value = map.get(key);
				if(value==null){
					rwl.readLock().unlock();
					rwl.writeLock().lock();
					try{
						if(value==null){
							value = "hellow world";
						}
					}finally{
						rwl.writeLock().unlock();
					}
					rwl.readLock().lock();
				}
			}finally{
				rwl.readLock().unlock();
			}
			return value;
		}
	}
	
	public static void initB(){
		final MethodB methodB = new MethodB();
		new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println(methodB.get(""));
			}
			
		}).start();
	}
	
	//6丶有3个线程A，B， C， 请用多线程编程实现在屏幕上循环打印10次ABCABC...， 其中A线程打印“A”， B线程打印“B”， C线程打印“C”
	static class MethodC{
		Lock lock = new ReentrantLock();
		Condition conditionA = lock.newCondition();
		Condition conditionB = lock.newCondition();
		Condition conditionC = lock.newCondition();
		int flag = 1;
		public void printA(){
			lock.lock();
			try{
				while(flag !=1){
					try {
						conditionA.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.print("A");
				conditionB.signal();
				flag = 2;
			}finally{
				lock.unlock();
			}
		}
		
		public void printB(){
			lock.lock();
			try{
				while(flag !=2){
					try {
						conditionB.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.print("B");
				conditionC.signal();
				flag = 3;
			}finally{
				lock.unlock();
			}
		}
		
		public void printC(){
			lock.lock();
			try{
				while(flag !=3){
					try {
						conditionC.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.print("C");
				conditionA.signal();
				flag = 1;
			}finally{
				lock.unlock();
			}
		}
	}
	
	public static void initC(){
		final MethodC methodC = new MethodC();
		new Thread(new Runnable(){
			@Override
			public void run(){
				for(int i=1;i<10;i++){
					methodC.printA();
				}
			}
		}).start();
		new Thread(new Runnable(){
			@Override
			public void run(){
				for(int i=1;i<10;i++){
					methodC.printB();
				}
			}
		}).start();
		new Thread(new Runnable(){
			@Override
			public void run(){
				for(int i=1;i<10;i++){
					methodC.printC();
				}
			}
		}).start();
	}
	
	
	//7丶设计2个线程1个线程每次对i加1，另一个线程每次对i减去1
	
	private int i = 0;
	class MethodD implements Runnable{
		@Override
		public void run() {
			for(int i=1;i<=10;i++){
				add();
			}
		}
	}
	
	class MethodE implements Runnable{
		@Override
		public void run() {
			for(int i=1;i<=10;i++){
				sub();
			}
		}
	}
	
	public synchronized void add(){
		i++;
		System.out.println(Thread.currentThread()+"正在增加"+i);
	}
	public synchronized void sub(){
		i--;
		System.out.println(Thread.currentThread()+"正在减少"+i);
	}
	
	public static void initD(){
		ThreadTest threadTest = getInstance();
		MethodD methodD = threadTest.new MethodD();
		MethodE methodE = threadTest.new MethodE();
		new Thread(methodD).start();
		new Thread(methodE).start();
	}
	
	/**
	 * 模拟处理16行日志，下面的代码产生了16个日志对象，当前代码需要运行16秒才能打印完这些日志。 修改程序代码，开四个线程让这16个对象在4秒钟打完。
	 * 
	 * public static void main(String[] args){
			System.out.println("begin:"+(System.currentTimeMillis()/1000));
			for(int i=0;i<16;i++){
				//这行代码不能改动
				final String log = ""+(i+1);//这行代码不能改动
				{
					Test.parseLog(log);
				}
			}
		}
		//parseLog方法内部的代码不能改动
		public static void parseLog(String log){
			System.out.println(log+":"+(System.currentTimeMillis()/1000));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	 */
	
	public static void initF(){
		final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(16);
		for(int i=0;i<4;i++){
			new Thread(new Runnable(){
				@Override
				public void run() {
					try {
						while(true){
							parseLog(queue.take());
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		System.out.println("begin:"+(System.currentTimeMillis()/1000));
		for(int i=0;i<16;i++){
			//这行代码不能改动
			final String log = ""+(i+1);//这行代码不能改动
			{
				try {
					queue.put(log);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void parseLog(String log){
		System.out.println(log+":"+(System.currentTimeMillis()/1000));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
