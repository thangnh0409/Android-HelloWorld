package com.project.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragment1 extends Fragment {

	private EditText editText;
	
	 ToolbarListener activityCallback;
		
	  public interface ToolbarListener {
	        public void onButtonClick(String text);
	  }
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		try {
            activityCallback = (ToolbarListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ToolbarListener");
        }
	}
	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState){
		
		// Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment1, 
                                 container, false);
        editText = (EditText) view.findViewById(R.id.editText1);
        final Button button = 
                (Button) view.findViewById(R.id.button1);
	        button.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                onButtonClick(v);
	            }
	        });
        
        return view;
	}
	public void onButtonClick(View v){
		Log.v("Button",	"Button click");
		activityCallback.onButtonClick(editText.getText().toString());
	}

}
