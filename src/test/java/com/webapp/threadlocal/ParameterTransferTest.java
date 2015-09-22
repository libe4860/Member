package com.webapp.threadlocal;

//결과를 담고 있는 객체
class Result {
	int sum;
}

//내부객체
class Calcurator{
	
	public Calcurator() {
//		GlobalVariable.sum = 0;
	}
	
	void summerize(Result result , int start, int end){
		for(int i=start; i<end; i++){
//			GlobalVariable.sum += i;
			result.sum += i;
		}
	}
	
	void multiplay(Result result, int mul){
//		GlobalVariable.sum *= mul;
		result.sum *= mul;
	}
}

class MyThread extends Thread{
	
	
	@Override
	public void run() {
		Result r = new Result();
		Calcurator c = new Calcurator();
		c.summerize(r, 1, 11);
		
		try {
			Thread.sleep((int)(Math.random()*1000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		c.multiplay(r, 10);
		
//		System.out.println("sum = " + GlobalVariable.sum);
		System.out.println("sum = " + r.sum);
	}
}

public class ParameterTransferTest {
	
	public static void main(String[] args) {
		
		for(int i=0; i<5; i++){
			new MyThread().start();
		}
	}
}
