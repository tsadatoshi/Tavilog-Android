package net.tavilog.util;

import java.io.InputStream;
import java.net.URL;

import net.tavilog.R;
import net.tavilog.constants.MassageConstants;

import org.apache.commons.lang3.StringUtils;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.Toast;

public class HtmlUtil {

	public static void callWebSite(Context con, String url) {
		try {
			Uri uriUrl = Uri.parse(url);
			Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
			con.startActivity(launchBrowser);
		} catch (Exception e) {
			Toast.makeText(con, MassageConstants.E0005, Toast.LENGTH_SHORT)
					.show();
		}
	}

	public static Drawable getUrlDrawable(Context con, String url) {
		if (StringUtils.isBlank(url)) {
			return new BitmapDrawable(con.getResources(), BitmapFactory.decodeResource(con.getResources(), R.drawable.noimage));
		}
		Drawable draw = null;
		URL shopUrl;
		try {
			shopUrl = new URL(url);
			InputStream istream = shopUrl.openStream();
			draw = Drawable.createFromStream(istream, "webimg");
			istream.close();
		} catch (Exception e) {
			return new BitmapDrawable(con.getResources(), BitmapFactory.decodeResource(con.getResources(), R.drawable.noimage));
		}
		return draw;
	}
}