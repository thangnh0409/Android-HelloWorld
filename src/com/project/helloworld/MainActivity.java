package com.project.helloworld;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	// array save menu item
	static final String[] menuItem = new String[]{"Fragment Test", "Save data Test", "Dialog Test",
		"AsyncTask Test", "Tabview Test", "Take Photo"};
	// no comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveValueKey("number", 99);
        
        ListView listView = (ListView)findViewById(R.id.listView1);
        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.list_view_cell, menuItem)); 
        
        listView.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			    
			    switch(position){
			    case 0:
			    {
			    	Intent intent = new Intent(getApplicationContext(), FragmentTestActivity.class);
			    	startActivity(intent);	
			    	break;
			    }
			    case 1:
			    	int value = getValueForKey("number");
			    	Toast.makeText(getApplicationContext(), String.valueOf(value), Toast.LENGTH_SHORT).show();
			    	break;
			    case 2:
			    	break;
			    case 3:
			    {
			    	Intent intent = new Intent(getApplicationContext(), AsyncTaskActivity.class);
			    	startActivity(intent);
			    	break;
			    }
			    case 4:
			    {
			    	Intent intent = new Intent(getApplicationContext(), TabbedTestActivity.class);
			    	startActivity(intent);
			    	break;
			    }
			    case 5:
			    {
			    	Intent intent = new Intent(getApplicationContext(), CameraTestActivity.class);
			    	startActivity(intent);
			    	break;
			    }
			    default:
			    	break;
			    
			    }
        	}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    
    @Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			Toast.makeText(getApplicationContext(), "Setting click", Toast.LENGTH_SHORT).show();
			break;
		case 2:
			Toast.makeText(getApplicationContext(), "Camera click1", Toast.LENGTH_SHORT).show();
			break;
		default:
			break;
		}
		return true;
	}


	public void saveValueKey(String key, int value){
    	SharedPreferences sharedPref = getSharedPreferences("data", MODE_PRIVATE);
    	SharedPreferences.Editor editor = sharedPref.edit();
    	editor.putInt(key, value);
    	editor.commit();
    }
    public int getValueForKey(String key){
    	SharedPreferences sharedPref = getSharedPreferences("data", MODE_PRIVATE);
    	return sharedPref.getInt(key, 100);
    }
    
}
