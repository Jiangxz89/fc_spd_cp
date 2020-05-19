package com.thinkgem.jeesite.test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestOne {
	
	private TestOne testOne = null;
	
	public synchronized TestOne getInstance(){
		if(testOne ==null ){
			testOne = new TestOne();
		}
		return testOne;
	}
	public TestOne(){
		
	}
	
	public static void main(String[] args) {
		TestOne  testObe = new TestOne();
		MethodD methodD = testObe.new MethodD();
		MethodE methodE = testObe.new MethodE();
		for(int i=1;i<=2;i++){
			new Thread(methodD,"线程A").start();
		}
		for(int j=1;j<=2;j++){
			new Thread(methodE,"线程B").start();
		}
	}
	
	public static void maoPao(int [] arrs){
		int temp = 0;
		int size = arrs.length;
		for(int i=0;i<size-1;i++){
			for(int j=0;j<size-i-1;j++){
				if(arrs[j]>arrs[j+1]){
					temp = arrs[j];
					arrs[j] = arrs[j+1];
					arrs[j+1] = temp;
				}
			}
		}
	}
	
	public static int erFen(int []arrs,int key){
		int start = 0;
		int end = arrs.length;
		while(start<=end){
			int moddle = (start+end) /2;
			if(arrs[moddle]<key){
				start = moddle +1;
			}else if(arrs[moddle] >key){
				end = moddle -1;
			}else{
				return moddle+1;
			}
		}
		return -1;
	}
	
	
	//主线程循环10次  子线程循环20次  重复5次
	
	public static void initA(){
		final Method method = new Method();
		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i=1;i<=5;i++){
					method.Child(i);
				}
			}
		}).start();
		for(int i=1;i<=5;i++){
			method.MainA(i);
		}
	}
	
	static class Method{
		boolean bl = true;
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		public  void MainA(int size){
			lock.lock();
			try{
				while(!bl){
					try {
						condition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for(int i=1;i<=10;i++){
					System.out.println("主线程循环次数:"+i+"次数统计:"+size);
				}
				bl = false;
				condition.signal();
			}finally{
				lock.unlock();
			}
		}
		
		public  void Child(int size){
			lock.lock();
			try{
				while(bl){
					try {
						condition.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				for(int i=1;i<=20;i++){
					System.out.println("子线程循环次数:"+i+"次数统计:"+size);
				}
				condition.signal();
				bl = true;
			}finally{
				lock.unlock();
			}
			
		}
	}
	
	
	//打印  AA  BB  CC 连续打印5次
	
	static class MethodA{
		Lock lock = new ReentrantLock();
		Condition conditionA = lock.newCondition();
		Condition conditionB = lock.newCondition();
		Condition conditionC = lock.newCondition();
		int i = 1;
		public void methodA(){
			lock.lock();
			try{
				if(i!=1){
					try {
						conditionA.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.print("A");
				i = 2;
				conditionB.signal();
			}finally{
				lock.unlock();
			}
		}
		
		public void methodB(){
			lock.lock();
			try{
				if(i!=2){
					try {
						conditionB.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.print("B");
				i = 3;
				conditionC.signal();
			}finally{
				lock.unlock();
			}
		}
		
		public void methodC(){
			lock.lock();
			try{
				if(i!=3){
					try {
						conditionC.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.print("C");
				i = 1;
				conditionA.signal();
			}finally{
				lock.unlock();
			}
		}
	}
	
	public static void initB(){
		final MethodA mothod = new MethodA();
		new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i = 1;i<=5;i++){
					mothod.methodA();
				}
			}
			
		}).start();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i = 1;i<=5;i++){
					mothod.methodB();
				}
			}
			
		}).start();
		
		new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i = 1;i<=5;i++){
					mothod.methodC();
				}
			}
		}).start();
	}
	
	//利用线程实现一个缓存
	public static void initC(){
		final MethodC methodC = new MethodC();
		new Thread(new Runnable(){

			@Override
			public void run() {
				System.out.println(methodC.get("1"));
			}
			
		}).start();
	}
	
	private static Map<String,Object> map = new HashMap<String,Object>();
	
	static class MethodC{
		ReadWriteLock lock = new ReentrantReadWriteLock();
		public Object get(String key){
			lock.readLock().lock();
			Object value = null;
			try{
				value = map.get(key);
				if(value==null){
					lock.readLock().unlock();
					lock.writeLock().lock();
					try{
						if(value ==null){
							value = "12321312";
						}
					}finally{
						lock.writeLock().unlock();
					}
					lock.readLock().lock();
				}
			}finally{
				lock.readLock().unlock();
			}
			return value;
		}
	}
	
	//设计四个线程,其中两个线程每次对变量i加1,另外两个线程每次对i减1.
	
	private int i = 0;
	public synchronized void add(){
		i ++;
		System.out.println(Thread.currentThread()+"正在增加"+i);
	}
	public synchronized void sub(){
		i--;
		System.out.println(Thread.currentThread()+"正在减少"+i);
	}
	
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
}
