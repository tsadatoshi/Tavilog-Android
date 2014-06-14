package net.tavilog.fragment;

import net.tavilog.R;
import net.tavilog.exception.MapInitException;

import com.androidquery.AQuery;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class StationFragment extends Fragment {

	/** AQuery */
	protected AQuery aQuery;
	/** GoogleMaps */
	protected GoogleMap gMap;
	/** LayoutInflater for callback */
	protected LayoutInflater mInflater;
	/** Station Name */
	protected String staName;
	/** Station Latitude */
	protected String staLatitude;
	/** Station Longitude */
	protected String staLongitude;
	/** Station Location */
	protected LatLng staLatLng;
	/** Station Marker Options */
	protected MarkerOptions staMarkerOptions;
	/** Station Marker Icon */
	BitmapDescriptor staIcon;

	public void initStationFragment() throws MapInitException {
		aQuery  = new AQuery(getActivity());
		// メインスレッドからHTTPリクエスト実行許可
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
		// 駅の位置情報を登録
		staLatitude = "41.773746";
		staLongitude = "140.726399";
		staName = "函館駅";
		staLatLng = new LatLng(Double.parseDouble(staLatitude) , Double.parseDouble(staLongitude));
		try {
			gMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.mapStationInfo)).getMap();
			staIcon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN);
			// 駅マーカーの作成
			staMarkerOptions = new MarkerOptions();
			staMarkerOptions.icon(staIcon);
			staMarkerOptions.position(staLatLng);
			staMarkerOptions.title(staName);
			// 駅情報を地図へ反映
			gMap.addMarker(staMarkerOptions);
			// 地図の縮尺を決定
	        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(staLatLng, 15));
		} catch (Exception e) {
			throw new MapInitException(e.toString());
		}

	}



}
