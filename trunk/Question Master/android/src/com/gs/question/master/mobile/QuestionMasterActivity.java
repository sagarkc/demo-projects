package com.gs.question.master.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class QuestionMasterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionmaster);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.question_master, menu);
		return true;
	}

}
