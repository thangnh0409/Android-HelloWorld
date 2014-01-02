package com.project.helloworld;

import java.text.MessageFormat;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AsyncTaskActivity extends Activity {
	
	private Button btnRestart;
    private Button btnCancel = null;
    private TextView txtMessage =  null;
    private ProgressBar mProgressBar =  null;
    private Progress task = null;
	private static final int MAX_PROGRESS = 10;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_async_task);
		
		btnRestart = (Button) findViewById(R.id.btnRestart);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        txtMessage = (TextView) findViewById(R.id.txtMessage);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        // set an arbitrary max value for the progress bar
        mProgressBar.setMax(MAX_PROGRESS);
        
        start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.async_task, menu);
		return true;
	}
	
	public void onRestartClick(View v){
		start();
	}
	public void onCancelClick(View v) {
        task.cancel(true);
        btnCancel.setEnabled(false);
        btnRestart.setEnabled(true);
    }
	private void executeHardWork() {
	        try {
	            Thread.sleep(1000);
	        }
	         catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	}
	private void start() {
        // instantiate a new async task
        task = new Progress();
        // start async task setting the progress to zero
        task.execute(0);
        // reset progress
        mProgressBar.setProgress(0);
        // handle buttons
        btnCancel.setEnabled(true);
        btnRestart.setEnabled(false);
    }
	
	public class Progress extends AsyncTask<Integer, Integer, Integer>{
		
		@Override
		protected void onPreExecute(){
			txtMessage.setText("Executing async task...");
            super.onPreExecute();
		}
		@Override
		protected Integer doInBackground(Integer ...integers ){
			int progress = ((Integer[])integers)[0];
			 do {

	                // only keep going in case the task was not cancelled
	                if (!this.isCancelled()) {
	                    // execute hard work - sleep
	                    executeHardWork();
	                }
	                else {
	                    // in case the task was cancelled, break the loop
	                    // and finish this task
	                    break;
	                }

	                // upgrade progress
	                progress++;
	                publishProgress(progress);
	            } while (progress <= MAX_PROGRESS);
			
			return progress;
		}
		
		@Override
		protected void onProgressUpdate(Integer ... values){
			int progress = ((Integer[])values)[0];
            mProgressBar.setProgress(progress);
            super.onProgressUpdate(values);
		}
		@Override
        protected void onCancelled(Integer result) {
            txtMessage.setText(MessageFormat.format
            ("Async task has been cancelled at {0} seconds.", result - 1));
            super.cancel(true);
        }
		@Override
		protected void onPostExecute(Integer result){
			txtMessage.setText(MessageFormat.format
		            ("Async task execution finished in {0} seconds.", result - 1));
		            btnCancel.setEnabled(false);
		            btnRestart.setEnabled(true);
		            super.onPostExecute(result);
		}
	}

}
