package com.example.impossivel;

import android.content.Context;
import android.graphics.Paint;
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

	public Impossivel(Context context) {
		super(context);
		paint = new Paint();
		holder = getHolder();
	}
	
	public void resume() {
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}

	@Override
	public void run() {
		while( running ) {
			System.out.println("impossivel running");
		}
		
	}

}
