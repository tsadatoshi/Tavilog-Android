package net.tavilog.fragment;

import net.tavilog.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class StationBasicFragment extends Fragment {

	@Override
	public View onCreateView(
		LayoutInflater inflater, 
		ViewGroup container, 
		Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_station_basic, container, false);
	}

}