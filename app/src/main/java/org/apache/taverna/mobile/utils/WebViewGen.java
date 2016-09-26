package org.apache.taverna.mobile.utils;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.apache.taverna.mobile.R;

public class WebViewGen extends Fragment {
    private static final String PARAM1 = "url";
    private int flag;
    private ProgressBar progressBar;
    private WebView web;
    private String url;

    public static WebViewGen newInstance(String URL) {

        Bundle args = new Bundle();
        args.putString(PARAM1, URL);
        WebViewGen fragment = new WebViewGen();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString(PARAM1);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View layout = inflater.inflate(R.layout.webviewgen, container, false);

        web = (WebView) layout.findViewById(R.id.webView);

        progressBar = (ProgressBar) layout.findViewById(R.id.progressBar);

        web.setWebViewClient(new WebClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.loadUrl(url);
        web.canGoBack();
        return layout;

    }

    private void getServer() {

    }


    public class WebClient extends WebViewClient {


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);

            progressBar.setVisibility(View.GONE);
        }
    }


}