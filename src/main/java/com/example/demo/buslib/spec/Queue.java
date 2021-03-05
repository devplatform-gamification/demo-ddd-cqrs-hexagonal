package com.example.demo.buslib.spec;

public interface Queue {
	public void enqueue(String message);
	
	public String dequeue();
}
