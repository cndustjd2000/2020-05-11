package com.human.ex;

public abstract class class1{
	int num;
	String str;
	
	public class1() {
		System.out.println("AbstractClassEX constructor");
	}
	
	public class1(int i, String s) {
		System.out.println("AbstractClassEX constructor");
		
		this.num=i;
		this.str=s;
	}
	
	public void fun1() {
		System.out.println(" -- fun1() Start -- ");
	}
	
	public abstract void fun2();


	public static void main(String[] args) {
	
		class1 ex=new ClassEx(10,"Java");
		ex.fun1();
		ex.fun2();
		
	}
}
