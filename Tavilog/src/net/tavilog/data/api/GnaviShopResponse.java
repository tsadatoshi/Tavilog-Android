package net.tavilog.data.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;


public class GnaviShopResponse {

	private String shopName;

	private String category;

	private String shopUrl;

	private String shopUrlMb;

	private String shopImageUrl;

	private String address;

	private String tel;

	private String openTime;

	private String closeDay;

	private String access;

	private String mbAccess;

	private String photoUrl;

	private String latitude;

	private String longitude;

	private String detail;

	public void setParam(XmlPullParser xpp) throws IOException, XmlPullParserException{

		String tag = xpp.getName();
		if("name".equals(tag)){
			shopName = xpp.nextText();
		} else if("category".equals(tag)){
			category = xpp.nextText();
		} else if("latitude".equals(tag)){
			latitude = xpp.nextText();
		} else if("longitude".equals(tag)){
			longitude = xpp.nextText();
		} else if("url".equals(tag)){
			shopUrl = xpp.nextText();
		} else if("url_mobile".equals(tag)){
			shopUrlMb = xpp.nextText();
		} else if("image_url".equals(tag)){
			shopImageUrl = xpp.nextText();
		} else if("url".equals(tag)){
			shopUrl = xpp.nextText();
		} else if("address".equals(tag)){
			address = xpp.nextText();
		}
	}

	public static List<GnaviShopResponse> createResultList(XmlPullParser xpp) throws Exception{
		List<GnaviShopResponse> result = new ArrayList<GnaviShopResponse>();
		int eventType = xpp.getEventType();
		GnaviShopResponse response = null;
		while(eventType != XmlPullParser.END_DOCUMENT) {
			if(eventType == XmlPullParser.START_TAG){
				String tag = xpp.getName();
				if("rest".equals(tag)){
					response = new GnaviShopResponse();
				} else {
					if(response != null){
						try {
							response.setParam(xpp);
						} catch (Exception e) {
							Log.w("GNAVI-RESPONSEERROR", tag + "の解析に失敗しました");
						}

					}
				}
			} else if(eventType == XmlPullParser.END_TAG){
				String tag = xpp.getName();
				if("rest".equals(tag)){
					result.add(response);
					response = null;
				}
			}
			eventType = xpp.next();
		}
		return result;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}

	public String getShopUrlMb() {
		return shopUrlMb;
	}

	public void setShopUrlMb(String shopUrlMb) {
		this.shopUrlMb = shopUrlMb;
	}

	public String getShopImageUrl() {
		return shopImageUrl;
	}

	public void setShopImageUrl(String shopImageUrl) {
		this.shopImageUrl = shopImageUrl;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getCloseDay() {
		return closeDay;
	}

	public void setCloseDay(String closeDay) {
		this.closeDay = closeDay;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public String getMbAccess() {
		return mbAccess;
	}

	public void setMbAccess(String mbAccess) {
		this.mbAccess = mbAccess;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
