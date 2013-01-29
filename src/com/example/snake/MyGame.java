package com.example.snake;

import java.util.Random;

import android.R.color;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.RectShape;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class MyGame extends View
{
	Context mycontext;
	Paint artist;
	int[][] value;
	
	public MyGame(Context context)
	{
		super(context);
		mycontext = context;
		artist = new Paint();
		value = new int[47][71];
		for (int x = 0; x < 47; x++)
		{
			for (int y = 0; y < 71; y++)
			{
				value[x][y] = 0;
			}
		}
	}

	protected void sync()
	{

	}

	protected void onDraw(Canvas g)
	{
		float SCALE = Math.min(g.getWidth(), g.getHeight()) / (float) 1000;
		g.scale(SCALE*2, SCALE*2);
		RectShape mybackground = new RectShape();
		artist.setColor(new Color().BLACK);
		mybackground.resize((float) 480.0, (float) 725.0);
		mybackground.draw(g, artist);
		artist.setColor(new Color().WHITE);
		drawBorders(g, artist);
		
		for (int x = 1; x < 47; x++)
		{
			for (int y = 1; y < 71; y++)
			{
				float tempx = x * 10 + 5;
				float tempy = y * 10 + 10;
				if (value[x][y] == 1)
				{
					artist.setColor(new Color().WHITE);
				}
				else if (value[x][y] == 2)
				{
					artist.setColor(new Color().GREEN);
				}
				else
				{
					artist.setColor(new Color().BLACK);
				}
				g.drawRect(tempx, tempy, tempx + 5, tempy + 5, artist);
			}
		}
	}
	protected void drawBorders(Canvas g, Paint artist)
	{
		// Screen pixels for width: 0-479 inclusive
		// Screen pixels for height:1-723 inclusive
		for (int z = 0; z < 4; z++)
		{
			// Bottom
			g.drawLine(0, 723 - z, 479, 723 - z, artist);
		}
		for (int z = 1; z < 10; z++)
		{
			// Top
			g.drawLine(0, z, 479, z, artist);
		}
		for (int z = 0; z < 5; z++)
		{
			// Left and right
			g.drawLine(z, 1, z, 723, artist);
			g.drawLine(479 - z, 1, 479 - z, 723, artist);
		}

	}

	

	
}
