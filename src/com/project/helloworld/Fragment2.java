package com.project.helloworld;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment2 extends Fragment {
	public TextView txtView;
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
          View view = inflater.inflate(R.layout.fragment2, 
              container, false);
        txtView = (TextView) view.findViewById(R.id.textView1);
        
        return view;
    }
	public void onSetTextView(String text){
		txtView.setText(text);
	}
}
