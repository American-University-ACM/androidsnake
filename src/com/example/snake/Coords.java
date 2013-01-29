package com.example.snake;

public class Coords
{
    public int x;
	public int y;
	
	public Coords(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	public void print()
	{
		System.out.println("X: " + String.valueOf(x) + " Y: " + String.valueOf(y));
	}
}
