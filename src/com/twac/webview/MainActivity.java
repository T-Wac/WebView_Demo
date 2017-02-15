package com.twac.webview;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText mEditText;
	private WebView mWebView;
	private ImageButton mImageButton;
	private TextView mTextView;
	private ProgressBar mProgressBar;
	private String httpurl = "http://www.site.baidu.com/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 网址显示框
		mTextView = (TextView) findViewById(R.id.textview);

		// 网页加载进度条
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);

		// 网址输入框
		mEditText = (EditText) findViewById(R.id.edittext);

		mWebView = (WebView) findViewById(R.id.webview);

		// 使WebView获得焦点
		mWebView.setFocusable(true);
		mWebView.setFocusableInTouchMode(true);
		mWebView.requestFocus();
		mWebView.requestFocusFromTouch();
		// WebView加载web资源
		mWebView.loadUrl(httpurl);
		// 覆盖WebView默认通过第三方或系统默认浏览器打开网页的行为，使网页可以在WebView中打开
		mWebView.setWebViewClient(new WebViewClient());

		mImageButton = (ImageButton) findViewById(R.id.imagebutton);
		mImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String url = mEditText.getText().toString();
				mWebView.loadUrl(url);
			}
		});
		// 显示当前网址
		mTextView.setText(mWebView.getUrl());

		// WebView加载网页优先使用缓存加载
		mWebView.getSettings()
				.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

		// 设置进度条显示
		mWebView.setWebChromeClient(new WebChromeClient() {
			@Override
			public void onProgressChanged(WebView view, int newProgress) {
				if (newProgress < 100) {
					mProgressBar.setProgress(newProgress);
				} else {
					mProgressBar.setProgress(0);
				}
				super.onProgressChanged(view, newProgress);
			}
		});
	}

	// 改写物理按键————返回的逻辑
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 如果点击返回键
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 如果有上一页
			if (mWebView.canGoBack()) {
				mWebView.goBack();
				return true;
			} else {
				System.exit(0);
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
