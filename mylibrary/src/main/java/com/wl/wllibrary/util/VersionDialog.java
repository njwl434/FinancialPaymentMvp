package com.wl.wllibrary.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.wl.wllibrary.R;
import com.wl.wllibrary.bean.VersionBean;
import com.wl.wllibrary.view.NumberProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class VersionDialog extends CenterDialog {

	private TextView mTvTitle;
	private LinearLayout mLayoutForceUpdate;
	private TextView mTvVersionContent;
	private Button mBtnCancel;
	private Button mBtnUpdate;
	private LinearLayout mLayoutProgress;
	private NumberProgressBar mPbBar;
	private boolean mIsDownloading = false;
	private boolean mIsForceUpdate = false;
	private boolean mIsCancled = false;
	public static final String DOWNLOAD_APK_PATH = Environment
			.getExternalStorageDirectory().getPath()
			+ "/com.redsoft.zhipaiwu/download/apk/";

	public VersionDialog(Context context, final VersionBean version) {
		super(context,false);
		setCancelable(false);
		setCanceledOnTouchOutside(false);
		mIsForceUpdate = version.getIsForceUpdate() == 1;
		View v = LayoutInflater.from(context).inflate(R.layout.dialog_version,
				null);
//		addView(v,false);
		setContentView(v);
		setTitle("发现新版本");
		mTvTitle = (TextView) findViewById(R.id.dialog_version_tv_title);
		mLayoutForceUpdate = (LinearLayout) findViewById(R.id.dialog_version_layout_forceupdate);
		mLayoutForceUpdate.setVisibility(mIsForceUpdate ? View.VISIBLE
				: View.GONE);
		mTvVersionContent = (TextView) findViewById(R.id.dialog_version_tv_content);
		mBtnCancel = (Button) findViewById(R.id.dialog_version_btn_cancel);
		mBtnUpdate = (Button) findViewById(R.id.dialog_version_btn_update);
		mLayoutProgress = (LinearLayout) findViewById(R.id.dialog_version_layout_progress);
		mLayoutProgress.setVisibility(View.INVISIBLE);
		mPbBar = (NumberProgressBar) findViewById(R.id.dialog_progress_pb_progressbar);
		mPbBar.setProgressTextVisibility(NumberProgressBar.ProgressTextVisibility.Invisible);
		mTvTitle.setText("版本号 : " + version.getVersion());
		String content = version.getDesc();
		content = TextUtils.isEmpty(content) ? content : content.replace("|",
				"\n");
		mTvVersionContent.setText(content);
		mBtnCancel.setText(mIsForceUpdate ? "退出" : "取消");
		mBtnCancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mIsDownloading) {
					final String path = DOWNLOAD_APK_PATH;
					final String name = "epindustry_"
//							+ Double.toString(version.getVersion())
							+version.getVersion()
									.replace(".", "_") + ".apk";
					FileUtil.deleteFile(path + name);
				}
				if (mIsForceUpdate) {
//					MobclickAgent.onKillProcess(getContext());
//					DataManager.getInstance().clearAllActivity();
					System.exit(0);
				} else {
					mIsCancled = true;
				}
				dismiss();
			}
		});
		mBtnUpdate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				mBtnUpdate.setEnabled(false);
				mBtnUpdate.setText("更新中");
				mLayoutProgress.setVisibility(View.VISIBLE);
				mPbBar.setProgressTextVisibility(NumberProgressBar.ProgressTextVisibility.Visible);
				mPbBar.setProgress(0);
				final String path = DOWNLOAD_APK_PATH;
				final String name = "zhipaiwu_"
//						+ Double.toString(version.getVersionCode())
						+version.getVersion().replace(
								".", "_") + ".apk";
				final File file = new File(path + name);
				if (file.exists()) {
					if (same(path + name, version.getVersion() + "")) {
						OpenApkFile(getContext(), file);
						mBtnUpdate.setEnabled(true);
						mBtnUpdate.setText("更新");
						mLayoutProgress.setVisibility(View.INVISIBLE);
						return;
					} else {
						FileUtil.deleteFile(path + name);
					}
				}
				new Thread(new Runnable() {

					@Override
					public void run() {
						mIsDownloading = true;
						boolean result = downloadFile(getContext(),
								version.getDownLoadUrl(), path,
								name, mHandler);
						if (result) {
							Message msg = mHandler.obtainMessage(1, file);
							msg.sendToTarget();
						} else {
							Message msg = mHandler
									.obtainMessage(2, path + name);
							msg.sendToTarget();
						}
					}
				}).start();
			}
		});
	}
	public static boolean OpenApkFile(Context context, File file) {
		if (!checkFileEndings(file, ".apk")) {
			return false;
		}
		Intent it = new Intent();
		it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		it.setAction(Intent.ACTION_VIEW);
		Uri u = Uri.fromFile(file);
		it.setDataAndType(u, "application/vnd.android.package-archive");
		context.startActivity(it);
		return true;
	}
	/**
	 * 检查文件后缀
	 *
	 * @param file
	 * @param fileEndings
	 * @return
	 */
	private static boolean checkFileEndings(File file, String fileEndings) {
		if (file == null || TextUtils.isEmpty(fileEndings) || !file.exists()) {
			return false;
		}

        return file.getPath().toLowerCase().endsWith(fileEndings);
    }
	private boolean same(String path, String version) {
		PackageManager packageManager = getContext().getPackageManager();
		PackageInfo packageInfo = packageManager.getPackageArchiveInfo(path,
				PackageManager.GET_ACTIVITIES);
		if (packageInfo != null) {
			if (version.equals(packageInfo.versionName)) {
				return true;
			}
		}
		return false;
	}

	private boolean downloadFile(Context context, String url, String filePath,
			String fileName, Handler handler) {
		if (TextUtils.isEmpty(url) || !FileUtil.isSDCardExist()
				|| !FileUtil.isHasSDCardPermission(context)
				|| TextUtils.isEmpty(filePath) || TextUtils.isEmpty(fileName)) {
			return false;
		}
		try {
			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(20000);
			conn.connect();
			int length = conn.getContentLength();
			InputStream is = conn.getInputStream();
			File downloadFile = new File(FileUtil.createDirFile(context,
					filePath), fileName);
			if (downloadFile.exists()) {
				downloadFile.delete();
			}
			FileOutputStream fos = new FileOutputStream(downloadFile);
			int count = 0;
			int progress = 0;
			byte buf[] = new byte[1024];
			while (!mIsCancled) {
				int numread = is.read(buf);
				if (numread <= 0) {
					return true;
				}
				count += numread;
				int curProgress = (int) (((float) count / length) * 100);
				fos.write(buf, 0, numread);
				if (handler != null) {
					if (curProgress > progress) {
						progress = curProgress;
						Message msg = handler.obtainMessage(0, progress);
						handler.sendMessage(msg);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				int progress = (Integer) msg.obj;
				mPbBar.setProgress(progress);
				break;

			case 1:
				mIsDownloading = false;
				OpenApkFile(getContext(), (File) msg.obj);
				mBtnUpdate.setEnabled(true);
				mBtnUpdate.setText("更新");
				mLayoutProgress.setVisibility(View.INVISIBLE);
				break;

			case 2:
				mIsDownloading = false;
				if (mIsCancled) {
					return;
				}
				FileUtil.deleteFile(msg.obj.toString());
				Toast.makeText(getContext(), "更新失败", Toast.LENGTH_SHORT).show();
				break;
			}
		}

	};

}
