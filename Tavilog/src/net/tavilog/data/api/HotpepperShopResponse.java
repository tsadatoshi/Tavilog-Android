package net.tavilog.data.api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class HotpepperShopResponse {

	private String shopName;

	private String genreName;

	private String access;

	private String mbAccess;

	private String photoUrl;

	private String couponUrl;

	private String latitude;

	private String longitude;

	private String address;

	private String foodName;

	private String catchcopy;

	private String budgetAve;

	private String shopUrl;

	private String openTime;

	private String closeDay;

	private String wifi;

	private String card;

	private String smoke;

	private String parking;

	private String midnight;

	private String detail;

	HotpepperShopResponse(JSONObject jsonLine) throws JSONException{

		JSONObject genre = jsonLine.getJSONObject("genre");
		JSONObject photo = jsonLine.getJSONObject("photo");
		JSONObject photoPc = photo.getJSONObject("pc");
		JSONObject coupon_urls = jsonLine.getJSONObject("coupon_urls");
		JSONObject food = jsonLine.getJSONObject("food");
		JSONObject budget = jsonLine.getJSONObject("budget");
		JSONObject url = jsonLine.getJSONObject("urls");

		access = jsonLine.getString("access");
		mbAccess = jsonLine.getString("mobile_access");
		genreName = genre.getString("name");
		photoUrl = photoPc.getString("l");
		couponUrl = coupon_urls.getString("pc");
		latitude = jsonLine.getString("lat");
		longitude = jsonLine.getString("lng");
		address = jsonLine.getString("address");
		foodName = food.getString("name");
		catchcopy = jsonLine.getString("catch");
		budgetAve = budget.getString("average");
		shopUrl = url.getString("pc");
		openTime = jsonLine.getString("open");
		closeDay = jsonLine.getString("close");
		smoke = jsonLine.getString("non_smoking");
		parking = jsonLine.getString("parking");
		midnight = jsonLine.getString("midnight");
		detail = jsonLine.getString("shop_detail_memo");
	}

	public static List<HotpepperShopResponse> createResultList(JSONObject callBackJson) throws JSONException{
		List<HotpepperShopResponse> result = new ArrayList<HotpepperShopResponse>();
		JSONObject jsonDataObject = callBackJson.getJSONObject("results");
		JSONArray jsonDataArray = jsonDataObject.getJSONArray("shop");
		for (int i = 0; i < jsonDataArray.length(); i++) {
			result.add(new HotpepperShopResponse((JSONObject)jsonDataArray.get(i)));
		}
		return result;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
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

	public String getCouponUrl() {
		return couponUrl;
	}

	public void setCouponUrl(String couponUrl) {
		this.couponUrl = couponUrl;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getCatchcopy() {
		return catchcopy;
	}

	public void setCatchcopy(String catchcopy) {
		this.catchcopy = catchcopy;
	}

	public String getBudgetAve() {
		return budgetAve;
	}

	public void setBudgetAve(String budgetAve) {
		this.budgetAve = budgetAve;
	}

	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
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

	public String getWifi() {
		return wifi;
	}

	public void setWifi(String wifi) {
		this.wifi = wifi;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getSmoke() {
		return smoke;
	}

	public void setSmoke(String smoke) {
		this.smoke = smoke;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
	}

	public String getMidnight() {
		return midnight;
	}

	public void setMidnight(String midnight) {
		this.midnight = midnight;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
}
