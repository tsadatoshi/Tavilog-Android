package net.tavilog.async;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;

public class DownloadImageAsyncTaskLoaderHelper extends AsyncTaskLoader<Bitmap> {

	private String imageUrl = "";
	private Context context = null;

	public DownloadImageAsyncTaskLoaderHelper(Context context, String url) {
		super(context);

		this.imageUrl = url;
		this.context = context;
	}

	@Override
	public Bitmap loadInBackground() {
		return null;//HttpUtil.getBitmapHttpService(context, imageUrl);
	}

	@Override
	protected void onStartLoading() {
		forceLoad();
	}
}
