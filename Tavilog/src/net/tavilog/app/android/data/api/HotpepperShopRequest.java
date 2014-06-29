package net.tavilog.app.android.data.api;

import net.tavilog.app.android.constants.ApiConstants;

import org.apache.commons.lang3.StringUtils;

public class HotpepperShopRequest {
	
	private String key;
	
	private String latitude;
	
	private String longitude;
	
	private String order;
	
	private String format;
	
	public String getRequestUrl(){
		StringBuffer sbf = new StringBuffer();
		sbf.append(ApiConstants.API_URL_HOTPEPPER);
		sbf.append("key=");
		if(StringUtils.isBlank(key)){
			sbf.append(ApiConstants.API_KEY_RECRUIT);
		} else {
			sbf.append(key);
		}
		sbf.append("&lat=");
		sbf.append(latitude);
		sbf.append("&lng=");
		sbf.append(longitude);	
		sbf.append("&order=");
		sbf.append(order);
		sbf.append("&format=");
		sbf.append(format);		
		return sbf.toString();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
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

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

}
