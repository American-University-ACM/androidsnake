package com.example.snake;

import java.util.Random;

import android.os.Bundle;
import android.os.Looper;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity
{
	MyGame myprogram;
	public Snake mysnake;
	public int MOVE;
	EndGame myEnd;
	boolean lose = false;
	int gamediff;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		gamediff = 250;
		mysnake = new Snake(new Coords(10, 10), 5);
		myEnd = new EndGame(this, mysnake.size - 5);
		myprogram = new MyGame(this);
		myprogram.setOnTouchListener(new SnakeListener(this));
		LinearLayout layout=new LinearLayout(this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		layout.addView(myprogram);
		super.onCreate(savedInstanceState);
		//setContentView(myprogram);
		setContentView(layout);
		
		//setContentView(R.layout.activity_main);
		Thread gameThread = new Thread()
		{
			public void run()
			{
				MOVE = 1;
				Looper.prepare();
				while (true)
				{
					try
					{
						if (mysnake.first.coord.x == 0
								|| mysnake.first.coord.x == 46
								|| mysnake.first.coord.y == 0
								|| mysnake.first.coord.y == 70)
						{
							lose = true;
							endGame();
							break;
						} else
						{
							updateSnake();
							generatePoint();
							myprogram.postInvalidate();
							Thread.sleep(gamediff);
						}
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
		};
		gameThread.start();
		
			if(!lose)
			{setContentView(myprogram);}
			if(lose)
			{setContentView(myEnd);}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	protected void generatePoint()
	{
		Random generator = new Random();
		int x = generator.nextInt(46) + 1;
		int y = generator.nextInt(70) + 1;
		if (myprogram.value[x][y] == 0)
		{
			myprogram.value[x][y] = 2;
		}
	}

	protected void updateSnake()
	{
		switch (MOVE)
		{
		case 1:
			// Going up
			if (myprogram.value[mysnake.first.coord.x][mysnake.first.coord.y - 1] == 2)
			{
				mysnake.eatflag = true;
				gamediff--;
			}
			mysnake.update(new Coords(mysnake.first.coord.x,
					mysnake.first.coord.y - 1));
			break;
		case 2:
			// Going right
			if (myprogram.value[mysnake.first.coord.x + 1][mysnake.first.coord.y] == 2)
			{
				mysnake.eatflag = true;
				gamediff--;
			}
			mysnake.update(new Coords(mysnake.first.coord.x + 1,
					mysnake.first.coord.y));
			break;
		case 3:
			// Going left
			if (myprogram.value[mysnake.first.coord.x - 1][mysnake.first.coord.y] == 2)
			{
				mysnake.eatflag = true;
				gamediff--;
			}
			mysnake.update(new Coords(mysnake.first.coord.x - 1,
					mysnake.first.coord.y));
			break;
		case 4:
			// Going down
			if (myprogram.value[mysnake.first.coord.x][mysnake.first.coord.y + 1] == 2)
			{
				mysnake.eatflag = true;
				gamediff--;
			}
			mysnake.update(new Coords(mysnake.first.coord.x,
					mysnake.first.coord.y + 1));
			break;
		}
		resetSnake();
		reIntegrateSnake();
	}

	protected void resetSnake()
	{
		for (int x = 0; x < 47; x++)
		{
			for (int y = 0; y < 71; y++)
			{
				if (myprogram.value[x][y] == 1)
				{
					myprogram.value[x][y] = 0;
				}
			}
		}
	}

	protected void reIntegrateSnake()
	{
		Snake.Node pointer = mysnake.first;
		while (pointer.next != null)
		{
			myprogram.value[pointer.coord.x][pointer.coord.y] = 1;
			pointer = pointer.next;
		}
	}

	protected void endGame()
	{
		CharSequence text = "Final Score: " + String.valueOf(mysnake.size - 5);
		Toast toast = Toast.makeText(getApplicationContext(), text,
				Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 20, 20);
		toast.show();
		mysnake.print();
		System.out.println("Toast Shown!");
		setContentView(myEnd);
	}
}
