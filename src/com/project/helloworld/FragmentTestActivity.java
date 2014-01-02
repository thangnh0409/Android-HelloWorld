package com.project.helloworld;

import com.project.helloworld.Fragment1.ToolbarListener;

import android.os.Bundle;
import android.view.Menu;
import android.support.v4.app.FragmentActivity;

public class FragmentTestActivity extends FragmentActivity implements ToolbarListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment_test);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fragment_test, menu);
		return true;
	}
	
	public void onButtonClick(String text){
		Fragment2 fragment2 = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.fragment2);
		fragment2.onSetTextView(text);
	}

}
