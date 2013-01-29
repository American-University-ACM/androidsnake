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
public class EndGame extends View
{
	Context mycontext;
	Paint artist;
	int[][] value;
	String myscore;
	
	public EndGame(Context context,int score)
	{
		super(context);
		myscore = "Final Score" + String.valueOf(score);
		mycontext = context;
		artist = new Paint();
		value = new int[47][71];
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
		artist.setTextSize(40);
		g.drawText(myscore,0,myscore.length() - 1,100,200,artist);
	}
}
