package com.example.impossivel;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Paint;
import android.view.Menu;
import android.view.SurfaceHolder;

public class Game extends Activity {
	// classe que guarda a logica do jogo
	Impossivel veiw;
	
	// metodo construtor

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		veiw = new Impossivel(this);
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

}
