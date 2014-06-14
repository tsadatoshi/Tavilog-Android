package net.tavilog.activity;

import net.tavilog.R;
import android.os.Bundle;

import com.androidquery.AQuery;

public class StationActivity extends BaseActivity {

	private static final String TAG = StationActivity.class.toString();
	private AQuery aQuery;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_station);
		aQuery = new AQuery(this);
		
		//asyncJson();
		
		
	}

}
