package prashushi.travelamen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.victor.loading.newton.NewtonCradleLoading;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    public ProgressBar progress;
    WebView web;
//   compile 'com.victor:lib:1.0.4'
    NewtonCradleLoading newtonCradleLoading;
    long time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        web = (WebView) findViewById(R.id.web);
        newtonCradleLoading = (NewtonCradleLoading) findViewById(R.id.newton_cradle_loading);
        newtonCradleLoading.setLoadingColor(getResources().getColor(R.color.colorPrimary));

        time=System.currentTimeMillis();
        String url = "http://www.haldwanisales.com";
        openBrowser(url);
        buildbut();
    }
    private void openBrowser(String url) {
        web.setWebViewClient(new ourViewClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setBuiltInZoomControls(false);
        web.getSettings().setLoadsImagesAutomatically(true);
        web.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        System.out.println("11");
        web.loadUrl(url);
        System.out.println("22");

    }

    @Override
    public void onClick(View v) {

        switch (Integer.parseInt(v.getTag().toString())) {
            //call, fb, email
            case 1:
                Intent ii = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + "+918006502295"));
                startActivity(ii);
                break;
            case 2:
                Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/travelamen1/"));
                startActivity(i);
                break;
            case 3:
                String[] TO = {"travelamen123@gmail.com"};
                String[] CC = {"ullas.pandey16@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Query/Feedback");
                try {					// lets u choose intent
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                }
                catch (Exception ex)
                { Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show(); }
        break;
        }
    }

    public class ourViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            //newtonCradleLoading.setVisibility(View.VISIBLE);
            System.out.println("44");
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            newtonCradleLoading.setVisibility(View.INVISIBLE);
        }
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();       //ignore certification

        }
    }

    private void buildbut() {
        //add dependency:     compile 'com.oguzdev:CircularFloatingActionMenu:1.0.2'

        //Main icon
        ImageView iconActionButton = new ImageView(this);
        iconActionButton.setImageResource(R.drawable.ic_account);
        //setOn click here


        ImageView call = new ImageView(this);
        call.setImageResource(R.drawable.ic_phone_in_talk);
        ImageView fb = new ImageView(this);
        fb.setImageResource(R.drawable.ic_facebook_box);
        ImageView email = new ImageView(this);
        email.setImageResource(R.drawable.ic_gmail);


    }


    @Override
    public void onBackPressed() {
        long recent=System.currentTimeMillis();
        if(recent-time<=2000)
            finish();
        else {
            Toast.makeText(this, "Double tap to exit!", Toast.LENGTH_SHORT).show();
            time = System.currentTimeMillis();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
