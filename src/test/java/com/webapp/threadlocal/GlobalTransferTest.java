package com.webapp.threadlocal;

//결과를 담고 있는 객체
class Result2 {
	int sum;
}

//내부객체
class Calcurator2{
	
	public Calcurator2() {
//		GlobalVariable.sum = 0;
	}
	
	void summerize(int start, int end){
		for(int i=start; i<end; i++){
//			GlobalVariable.sum += i;
			GlobalVariable.result.get().sum += i;
			try {
				Thread.sleep((int)(Math.random()*100));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	void multiplay(int mul){
//		GlobalVariable.sum *= mul;
		GlobalVariable.result.get().sum *= mul;
	}
}

class MyThread2 extends Thread{
	
	
	@Override
	public void run() {
		//전달 시 set으로 전달
		GlobalVariable.result.set(new Result2());
		Calcurator2 c = new Calcurator2();
		c.summerize(1, 11);
		
		try {
			Thread.sleep((int)(Math.random()*1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		c.multiplay(10);
		
//		System.out.println("sum = " + GlobalVariable.sum);
		System.out.println("sum = " + GlobalVariable.result.get().sum);
	}
}

public class GlobalTransferTest {
	
	public static void main(String[] args) {
		
		for(int i=0; i<100; i++){
			new MyThread2().start();
		}
	}
}
