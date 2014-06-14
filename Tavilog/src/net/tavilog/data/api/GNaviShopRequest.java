package net.tavilog.data.api;

import net.tavilog.constants.ApiConstants;

import org.apache.commons.lang3.StringUtils;

public class GNaviShopRequest {

	private String key;

	private String latitude;

	private String longitude;

	private String range;

	private String sort;

	public String getRequestUrl(){
		StringBuffer sbf = new StringBuffer();
		sbf.append(ApiConstants.API_URL_GNAVI);
		sbf.append("keyid=");
		if(StringUtils.isBlank(key)){
			sbf.append(ApiConstants.API_KEY_GNAVI);
		} else {
			sbf.append(key);
		}
		sbf.append("&latitude=");
		sbf.append(latitude);
		sbf.append("&longitude=");
		sbf.append(longitude);
		sbf.append("&range=");
		sbf.append(range);
		sbf.append("&sort=");
		sbf.append(sort);
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

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

}
