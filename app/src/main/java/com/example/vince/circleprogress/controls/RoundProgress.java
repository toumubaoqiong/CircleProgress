package com.example.vince.circleprogress.controls;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.example.vince.circleprogress.R;

public class RoundProgress extends View{

	/**
	 * 画笔
	 */
	private Paint paint;
	/**
	 * 背景色
	 */
	private int backgroundColor;
	/**
	 * 前景色
	 */
	private int foregroundColor;
	/**
	 * 圆环大小
	 */
	private float roundWidth;
	/**
	 * 最大值
	 */
	private int max;
	/**
	 * 进度
	 */
	private int progress;
	
	
	public RoundProgress(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public RoundProgress(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		paint = new Paint();

		TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
				R.styleable.RoundProgress);
		
		//获取属性值
		backgroundColor = mTypedArray.getColor(R.styleable.RoundProgress_backgroundColor, Color.WHITE);
		foregroundColor = mTypedArray.getColor(R.styleable.RoundProgress_foregroundColor, Color.BLACK);
		roundWidth = mTypedArray.getDimension(R.styleable.RoundProgress_d_value, 5);
		max = mTypedArray.getInteger(R.styleable.RoundProgress_maximum, 100);
		
		mTypedArray.recycle();
	}

	public RoundProgress(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		int centre = getWidth()/2;
////		int radius = (int) (centre - roundWidth/2);
		paint.setColor(backgroundColor);
		paint.setAntiAlias(true);
		canvas.drawCircle(centre, centre, centre, paint);
		paint.setColor(foregroundColor);
		canvas.drawCircle(centre, centre, centre - roundWidth, paint);
		
		paint.setColor(backgroundColor);
		RectF oval = new RectF(roundWidth, roundWidth, centre + (centre - roundWidth), centre + (centre - roundWidth));
		
		if(progress !=0)
			canvas.drawArc(oval, -90,450 * progress / max, true, paint);
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public int getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public int getForegroundColor() {
		return foregroundColor;
	}

	public void setForegroundColor(int foregroundColor) {
		this.foregroundColor = foregroundColor;
	}

	public float getRoundWidth() {
		return roundWidth;
	}

	public void setRoundWidth(float roundWidth) {
		this.roundWidth = roundWidth;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public synchronized int getProgress() {
		return progress;
	}

	public synchronized void setProgress(int progress) {
		if(progress < 0){
			throw new IllegalArgumentException("progress not less than 0");
		}
		if(progress > max){
			progress = max;
		}
		if(progress <= max){
			this.progress = progress;
			postInvalidate();
		}
	}
}