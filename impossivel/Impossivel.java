package com.example.impossivel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Impossivel extends SurfaceView implements Runnable{
	// classe para chegar se a surface view pode ser modificada;
	private SurfaceHolder holder;
	// classe responsavel por imprimir objetos, letras ,etc na tela
	private Paint paint;
	// variavel responsavel pelo loop game
	private boolean running = false;
	// thread responsavel pelas interaçoes do usuário
	Thread renderThread = null;
	
	private int playerX=300 , playerY = 300, playerRaio=50;
	
	private int enemyX=100,enemyY=100,enemyRaio = 100;

	private boolean GameOver;
	
	public Impossivel(Context context) {
		super(context);
		paint = new Paint();
		holder = getHolder();
	}
	
	public void resume() {
		running = true;
		GameOver = false;
		renderThread = new Thread(this);
		renderThread.start();
	}
	
	private void drawPlayer(Canvas canvas) {
		paint.setColor(Color.GREEN);
		canvas.drawCircle(playerX,playerY,playerRaio,paint);
	}
	
	private void drawEnemy(Canvas canvas) {
		paint.setColor(Color.RED);
		enemyRaio++;
		canvas.drawCircle(enemyX, enemyY, enemyRaio, paint);
	}
	
	public void moveDonw(int pixels) {
		playerY += pixels;
	}
	
	private void checkCollision(Canvas canvas) {
		double distancia = Math.pow(playerY-enemyY, 2) + Math.pow(playerX-enemyX, 2);
		distancia = Math.sqrt(distancia);
		
		if (distancia <= playerRaio + enemyRaio) {
			GameOver = true;
		}
	}
	
	private void stopGame(Canvas canvas) {
		paint.setStyle(Style.FILL);
		paint.setColor(Color.LTGRAY);
		paint.setTextSize(100);
		canvas.drawText("GAME OVER",50,150,paint);
	}

	@Override
	public void run() {
		while( running ) {
			if (!holder.getSurface().isValid()) {
				continue;
			}
			Canvas canvas = holder.lockCanvas();
		
			canvas.drawColor(Color.BLACK);
			
			drawEnemy(canvas);
			drawPlayer(canvas);
			
			checkCollision(canvas);
			
			if (GameOver) {
				stopGame(canvas);
				break;
			}
			
			holder.unlockCanvasAndPost(canvas);
		}
		
	}

}
