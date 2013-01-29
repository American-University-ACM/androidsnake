package com.example.snake;

import com.example.snake.MainActivity;

import android.location.Location;
import android.util.Log;
import android.location.Location;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SnakeListener implements OnTouchListener
{
    MainActivity past;
    Location myloc = new Location("Snake");
	public SnakeListener(MainActivity past)
	{
		this.past = past;
	}
	public boolean onTouch(View v, MotionEvent event) 
	{
		float xtouch = event.getX();
		float ytouch = event.getY();
        float xsnake = past.mysnake.first.coord.x * 5 + 5;
        float ysnake = past.mysnake.first.coord.y * 10 + 10;
        float xdiff = xtouch - xsnake;
        float ydiff = ytouch - ysnake;
        float slope = ydiff/xdiff;

        if (slope < -1 || slope > 1)
        {
        	if (ydiff < 0 && past.MOVE != 4)
        	{	 
        		past.MOVE = 1; // North
        	}
        	else if (ydiff > 0 && past.MOVE != 1)
        	{
        		past.MOVE = 4; // South
        	}
        }
        else
        {
        	if (xdiff > 0 && past.MOVE != 3)
        	{
        		past.MOVE = 2; // East
        	}
        	else if (xdiff < 0 && past.MOVE != 2)
        	{
        		past.MOVE = 3; // West
        	}
        }
        
		Log.i("touchtest","I touched here: " + event.getX() + " " + event.getY());
		Log.i("difftest", "Differences: " + xdiff + " " + ydiff);
		
		return true;
	}

}