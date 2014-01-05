package com.project.helloworld;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class CameraTestActivity extends Activity {

	ImageView imageView;
	public final String TAG = "CameraLog";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_test);
		imageView = (ImageView) findViewById(R.id.imageView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.camera_test, menu);
		return true;
	}
	public void onTakePhotoClick(View v){
		Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		deletePhoto();
		//System.out.println(getPhotoUri().toString());
		Uri uriSavedImage=Uri.fromFile(new File("/sdcard/tmp.jpg"));
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
		if (intent.resolveActivity(getPackageManager())!= null){
			startActivityForResult(intent, 0);
		}
		
	}
	public Uri getPhotoUri(){
		
		File rootFolder = Environment.getExternalStorageDirectory();
		File tempPhoto = new File(rootFolder.getAbsolutePath()+ "tmp.jpg");
		Log.i("Photo URL", tempPhoto.getAbsolutePath());
		
		try{
			if(!tempPhoto.exists()){
				tempPhoto.createNewFile();
			}
		
			Uri temPhotoUri = Uri.fromFile(tempPhoto);
		
			
			return temPhotoUri;
		}
		catch (IOException e){
		e.printStackTrace();
		return Uri.EMPTY;
		}
	}
	public void deletePhoto(){
		File rootFolder = Environment.getExternalStorageDirectory();
		File tempPhoto = new File(rootFolder.getAbsolutePath()+ "tmp.jpg");
		if(tempPhoto.exists()){
			tempPhoto.delete();
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	      if(resultCode == RESULT_CANCELED) return;
	    /*  
	      super.onActivityResult(requestCode, resultCode, data);
	      Bitmap bp = (Bitmap) data.getExtras().get("data");
	      imageView.setImageBitmap(bp);
	      */
	      String imgPath = Environment.getExternalStorageDirectory() + File.separator + "tmp.jpg";
	      Log.i(TAG, imgPath);
	      Bitmap bmp = BitmapFactory.decodeFile(imgPath);
	      imageView.setImageBitmap(bmp);
	      
	   }
	
}
