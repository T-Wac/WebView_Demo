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
		// ��ַ��ʾ��
		mTextView = (TextView) findViewById(R.id.textview);

		// ��ҳ���ؽ�����
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);

		// ��ַ�����
		mEditText = (EditText) findViewById(R.id.edittext);

		mWebView = (WebView) findViewById(R.id.webview);

		// ʹWebView��ý���
		mWebView.setFocusable(true);
		mWebView.setFocusableInTouchMode(true);
		mWebView.requestFocus();
		mWebView.requestFocusFromTouch();
		// WebView����web��Դ
		mWebView.loadUrl(httpurl);
		// ����WebViewĬ��ͨ����������ϵͳĬ�����������ҳ����Ϊ��ʹ��ҳ������WebView�д�
		mWebView.setWebViewClient(new WebViewClient());

		mImageButton = (ImageButton) findViewById(R.id.imagebutton);
		mImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String url = mEditText.getText().toString();
				mWebView.loadUrl(url);
			}
		});
		// ��ʾ��ǰ��ַ
		mTextView.setText(mWebView.getUrl());

		// WebView������ҳ����ʹ�û������
		mWebView.getSettings()
				.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

		// ���ý�������ʾ
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

	// ��д�����������������ص��߼�
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// ���������ؼ�
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// �������һҳ
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
