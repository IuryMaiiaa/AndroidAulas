package com.example.impossivel;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Paint;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnTouchListener;

public class Game extends Activity implements OnTouchListener{
	// classe que guarda a logica do jogo
	Impossivel veiw;
	
	// metodo construtor

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		veiw = new Impossivel(this);
		
		veiw.setOnTouchListener(this);
		setContentView(veiw);
	}
	
	// iniciando o loop game
	protected void onResume() {
		super.onResume();
		veiw.resume();
	}

	
	// default
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// restart
		if (event.getX()<100 && event.getY() > 290 && event.getY() < 310) {
			veiw.init();
		} else if (event.getX()<100 && event.getY() > 490 && event.getY() < 510) { // exit
			System.exit(0);
		} else { // default
			veiw.moveDonw(10);
			veiw.addPoints(100);	
		}
		return true;
	}

}
