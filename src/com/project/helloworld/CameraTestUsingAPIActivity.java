package com.project.helloworld;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CameraTestUsingAPIActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_test_using_api);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera_test_using_api, menu);
		return true;
	}

}
