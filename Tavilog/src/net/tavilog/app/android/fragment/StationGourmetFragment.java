package net.tavilog.app.android.fragment;

import java.util.List;

import net.tavilog.R;
import net.tavilog.app.android.data.api.GNaviShopRequest;
import net.tavilog.app.android.data.api.GnaviShopResponse;
import net.tavilog.app.android.data.api.HotpepperShopRequest;
import net.tavilog.app.android.data.api.HotpepperShopResponse;
import net.tavilog.app.android.exception.MapInitException;
import net.tavilog.app.android.util.HtmlUtil;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StationGourmetFragment extends StationFragment{
	/** レイアウト情報 */
	private View mLayout;

	String couponUrl;

	FrameLayout frameLayoutHP;
	HorizontalScrollView scrollViewHP;

	FrameLayout frameLayoutGN;
	HorizontalScrollView scrollViewGN;

	@Override
	public View onCreateView(
		LayoutInflater inflater,
		ViewGroup container,
		Bundle savedInstanceState) {
		// Viewのグローバル化
		mLayout = inflater.inflate(R.layout.fragment_station_gourmet, container, false);
		// LayoutInflaterのグローバル化
		mInflater = inflater;
		// StationFragment定義初期化
		try {
			initStationFragment();
		} catch (MapInitException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			Toast.makeText(getActivity(), "map err", Toast.LENGTH_LONG);
		}

		frameLayoutHP = (FrameLayout) mLayout.findViewById(R.id.frameLayoutHP);
		scrollViewHP = (HorizontalScrollView) mLayout.findViewById(R.id.scrollViewHP);
		// 非同期でHotPepper取得
		asyncHotpepper();

		frameLayoutGN = (FrameLayout) mLayout.findViewById(R.id.frameLayoutGN);
		scrollViewGN = (HorizontalScrollView) mLayout.findViewById(R.id.scrollViewGN);
		asyncGnavi();
		return mLayout;
	}

	private void asyncHotpepper() {

		HotpepperShopRequest hpRequest = new HotpepperShopRequest();
		hpRequest.setLatitude(staLatitude);
		hpRequest.setLongitude(staLongitude);
		hpRequest.setOrder("4");
		hpRequest.setFormat("json");
	    final LinearLayout linearLayoutHPArea = (LinearLayout) mLayout.findViewById(R.id.linearLayoutHPArea);
	    aQuery.ajax(hpRequest.getRequestUrl(), JSONObject.class, new AjaxCallback<JSONObject>() {
	        @Override
	        public void callback(String url, JSONObject json, AjaxStatus status) {
        	    frameLayoutHP.setVisibility(View.GONE);
        	    scrollViewHP.setVisibility(View.VISIBLE);
        	    if (json != null) {
	            	try {
	            		List<HotpepperShopResponse> hpList = HotpepperShopResponse.createResultList(json);
	            		View hpLayout;
						TextView textViewListHPShop;
						TextView textViewListHPAccess;
						TextView textViewListHPCategory;
						ImageView imageViewListHPImage;
						Button btnHP;
	            		for (HotpepperShopResponse hotpepperShop : hpList) {
	            			hpLayout = mInflater.inflate(R.layout.parts_list_hotpepper, null);
	            			textViewListHPShop = (TextView) hpLayout.findViewById(R.id.textViewListHPShop);
	            			textViewListHPShop.setText(hotpepperShop.getShopName());
	            			textViewListHPAccess = (TextView) hpLayout.findViewById(R.id.textViewListHPAccess);
	            			textViewListHPAccess.setText(hotpepperShop.getMbAccess());
	            			textViewListHPCategory = (TextView) hpLayout.findViewById(R.id.textViewListHPCategory);
	            			textViewListHPCategory.setText(hotpepperShop.getGenreName());
	            			imageViewListHPImage = (ImageView) hpLayout.findViewById(R.id.imageViewListHPImage);
	            			imageViewListHPImage.setImageDrawable(HtmlUtil.getUrlDrawable(getActivity() ,hotpepperShop.getPhotoUrl()));
	            			couponUrl = hotpepperShop.getCouponUrl();
	            			btnHP = (Button) hpLayout.findViewById(R.id.buttonHP);
	            			btnHP.setOnClickListener(new OnClickListener() {
	            				String url = couponUrl;
								@Override
								public void onClick(View v) {
									// クーポンページを開く
									HtmlUtil.callWebSite(getActivity(), url);
								}
							});
	            			if(gMap != null){
		            			MarkerOptions markerOptions = new MarkerOptions();
		            			BitmapDescriptor icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE);
		            			markerOptions.icon(icon);
		            			markerOptions.position(new LatLng(Double.parseDouble(hotpepperShop.getLatitude()) , Double.parseDouble(hotpepperShop.getLongitude())));
		            			markerOptions.title(hotpepperShop.getShopName());
		            			gMap.addMarker(markerOptions);
	            			}
	            			linearLayoutHPArea.addView(hpLayout);
	            		}
	            	} catch (Exception e) {
						e.printStackTrace();
					}
	            } else {
	                // ajax error, show error code
	                Toast.makeText(aQuery.getContext(), "Error:" + status.getCode(), Toast.LENGTH_LONG)
	                        .show();
	            }
	        }
	    });
	}

	private void asyncGnavi() {

		GNaviShopRequest gnRequest = new GNaviShopRequest();
		gnRequest.setLatitude(staLatitude);
		gnRequest.setLongitude(staLongitude);
		gnRequest.setRange("3"); // 1000m
		gnRequest.setSort("1"); // 名称順
	    final LinearLayout linearLayoutGNArea = (LinearLayout) mLayout.findViewById(R.id.linearLayoutGNArea);
	    aQuery.ajax(gnRequest.getRequestUrl(), XmlPullParser.class, new AjaxCallback<XmlPullParser>() {
	        @Override
	        public void callback(String url, XmlPullParser xpp, AjaxStatus status) {
        	    frameLayoutGN.setVisibility(View.GONE);
        	    scrollViewGN.setVisibility(View.VISIBLE);
        	    if (xpp != null) {
	            	try {
	            		List<GnaviShopResponse> gnList = GnaviShopResponse.createResultList(xpp);

	            		View gnLayout;
						TextView textViewListGNShop;
						TextView textViewListGNAccess;
						TextView textViewListGNCategory;
						ImageView imageViewListGNImage;
						Button btnGN;
	            		for (GnaviShopResponse gnaviShop : gnList) {
	            			gnLayout = mInflater.inflate(R.layout.parts_list_gnavi, null);
	            			textViewListGNShop = (TextView) gnLayout.findViewById(R.id.textViewListGNShop);
	            			textViewListGNShop.setText(gnaviShop.getShopName());
	            			textViewListGNAccess = (TextView) gnLayout.findViewById(R.id.textViewListGNAccess);
	            			textViewListGNAccess.setText(gnaviShop.getMbAccess());
	            			textViewListGNCategory = (TextView) gnLayout.findViewById(R.id.textViewListGNCategory);
	            			textViewListGNCategory.setText(gnaviShop.getCategory());
	            			imageViewListGNImage = (ImageView) gnLayout.findViewById(R.id.imageViewListGNImage);
	            			imageViewListGNImage.setImageDrawable(HtmlUtil.getUrlDrawable(getActivity() ,gnaviShop.getPhotoUrl()));
	            			couponUrl = gnaviShop.getShopUrl();
	            			btnGN = (Button) gnLayout.findViewById(R.id.buttonGN);
	            			btnGN.setOnClickListener(new OnClickListener() {
	            				String url = couponUrl;
								@Override
								public void onClick(View v) {
									// クーポンページを開く
									HtmlUtil.callWebSite(getActivity(), url);
								}
							});
	            			if(gMap != null){
		            			MarkerOptions markerOptions = new MarkerOptions();
		            			BitmapDescriptor icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE);
		            			markerOptions.icon(icon);
		            			markerOptions.position(new LatLng(Double.parseDouble(gnaviShop.getLatitude()) , Double.parseDouble(gnaviShop.getLongitude())));
		            			markerOptions.title(gnaviShop.getShopName());
		            			gMap.addMarker(markerOptions);
	            			}
	            			linearLayoutGNArea.addView(gnLayout);
	            		}
	            	} catch (Exception e) {
						e.printStackTrace();
					}

        	    	//Map<String, String> images = new LinkedHashMap<String, String>();
        	        String currentTitle = null;

        	        try{

        	        int eventType = xpp.getEventType();
        	        while(eventType != XmlPullParser.END_DOCUMENT) {

        	                if(eventType == XmlPullParser.START_TAG){

        	                        String tag = xpp.getName();

        	                        if("name".equals(tag)){
        	                                currentTitle = xpp.nextText();
        	                        }else if("content".equals(tag)){
        	                                String imageUrl = xpp.getAttributeValue(0);
        	                                //images.put(currentTitle, imageUrl);
        	                        }
        	                }
        	                eventType = xpp.next();
        	        }

        	        }catch(Exception e){
        	                AQUtility.report(e);
        	        }
	            } else {
	                // ajax error, show error code
	                Toast.makeText(aQuery.getContext(), "Error:" + status.getCode(), Toast.LENGTH_LONG)
	                        .show();
	            }
	        }
	    });
	}

}
