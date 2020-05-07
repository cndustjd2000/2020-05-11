package com.human.ex;

interface Toy{
	
	public void walk();
	public void run();
	public void alarm();
	public void light();		
}

public class Interface{
	
	public static void main(String[] args) {
	
		Toy robot=new ToyRobot();
		Toy airplane=new ToyAirplane();

		Toy toys[] = {robot, airplane};

		for(int i=0; i<toys.length;i++) {
		toys[i].walk();
		toys[i].run();
		toys[i].alarm();
		toys[i].light();

		System.out.println();
		}
		
		
	}
}
