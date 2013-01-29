package com.example.snake;

public class Snake
{

    public Node first, last;
    int size;
	boolean eatflag = false;

	public Snake(Coords begincoord, int size)
	{
		Node firstnode = new Node(begincoord);
		this.first = firstnode;
		this.last = firstnode;

		for (this.size = 1; this.size < size; this.size=this.size)
		{
			Coords newcoord = new Coords(begincoord.x, begincoord.y + this.size);
			this.append(newcoord);
		}
	}
	public void extend()
	{
		Node toextend = new Node(last.coord);
		last.next = toextend;
		last = toextend;
		size++;
	}
	public void append(Coords appendcoord)
	{
		Node toappend = new Node(appendcoord);
		last.next = toappend;
		last = toappend;
		size++;
	}
	public void update(Coords updatecoord)
	{
		Node pointer = first;
		Node newtail = new Node(last.coord);
		Coords tempcoord = pointer.coord;
		pointer.coord = updatecoord;
		updatecoord = tempcoord;
		while(pointer.next != null)
		{
			pointer = pointer.next;
			tempcoord = pointer.coord;
			pointer.coord = updatecoord;
			updatecoord = tempcoord;
		}
		if (eatflag)
		{
			extend();
			eatflag = !eatflag;
		}
	}
	
	public void print()
	{
		Node pointer = first;
		while(pointer.next != null)
		{
			System.out.println(String.valueOf(pointer.coord.x + " " + String.valueOf(pointer.coord.y)));
			pointer = pointer.next;
		}
	}
	public Coords[] getCoords()
	{
		Node pointer = first;
		Coords[] mylist = new Coords[size];
		int z = 0;
		while(pointer.next != null)
		{
			mylist[z] = new Coords(pointer.coord.x, pointer.coord.y);
			pointer = pointer.next;
			z++;
		}
		return mylist;
	}
	public class Node
	{
		public Coords coord;
		Node next;

		public Node(Coords coord)
		{
			this.coord = coord;
		}
	}	
}
