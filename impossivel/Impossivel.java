package com.example.impossivel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
	
	private int score=2000;
	
	private Bitmap imagem;
	
	public Impossivel(Context context) {
		super(context);
		paint = new Paint();
		holder = getHolder();
	}
	
	public void addPoints(int points) {
		score += points;
	}
	
	public void resume() {
		running = true;
		GameOver = false;
		renderThread = new Thread(this);
		renderThread.start();
	}
	
	private void drawScore(Canvas canvas) {
		paint.setStyle(Style.FILL);
		paint.setColor(Color.WHITE);
		paint.setTextSize(50);
		canvas.drawText(String.valueOf(score),50,200,paint);
	}
	
	private void drawPlayer(Canvas canvas) {
		paint.setColor(Color.GREEN);
		canvas.drawCircle(playerX,playerY,playerRaio,paint);
	}
	
	// desenhando bottons
	private void drawButtons(Canvas canvas) {
		paint.setStyle(Style.FILL);
		paint.setColor(Color.WHITE);
		paint.setTextSize(50);
		canvas.drawText("Restart",50,300,paint);
		
		paint.setStyle(Style.FILL);
		paint.setColor(Color.WHITE);
		paint.setTextSize(50);
		canvas.drawText("Exit",50,500,paint);
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
	
	public void init() {
		enemyX = enemyY = enemyRaio = 50;
		playerX = playerY = 300;
		playerRaio = 50;
		GameOver = false;
	}
	
	private void stopGame(Canvas canvas) {
		paint.setStyle(Style.FILL);
		paint.setColor(Color.BLUE);
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
			
			drawScore(canvas);
			
			drawButtons(canvas);
			
			imagem = BitmapFactory.decodeResource(getResources(),);
			canvas.drawBitmap(imagem, 0, 0, null);
			
			holder.unlockCanvasAndPost(canvas);
		}
		
	}

}
