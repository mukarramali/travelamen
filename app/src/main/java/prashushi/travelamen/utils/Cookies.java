package prashushi.travelamen.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

/**
 * Created by Dell User on 12/31/2016.
 */
public class Cookies {

    Context context;
    Cookies(Context context){
        this.context=context;
    }
    public void clearCookies() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            Log.d("XXX", "Using clearCookies code for API >=" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
            CookieManager.getInstance().removeAllCookies(null);
            CookieManager.getInstance().flush();
        } else
        {
            Log.d("XXX", "Using clearCookies code for API <" + String.valueOf(Build.VERSION_CODES.LOLLIPOP_MR1));
            CookieSyncManager cookieSyncMngr=CookieSyncManager.createInstance(context);
            cookieSyncMngr.startSync();
            CookieManager cookieManager=CookieManager.getInstance();
            cookieManager.removeAllCookie();
            cookieManager.removeSessionCookie();
            cookieSyncMngr.stopSync();
            cookieSyncMngr.sync();
        }
    }
    private void setCookies(String url,String cookies) {
        if(cookies.length()<6)
            return;
        CookieManager cookieManager=CookieManager.getInstance();
        cookieManager.setCookie(url, cookies);
    }

    private void showCookies() {
        String fb_cookie= CookieManager.getInstance().getCookie("https://www.facebook.com");
        System.out.println("Cookies:"+fb_cookie);
    }

    void updateCookies(){
        String fb_cookie= CookieManager.getInstance().getCookie("https://www.facebook.com");
        fb_cookie=fb_cookie.substring(fb_cookie.indexOf("m_user"));
        fb_cookie=fb_cookie.substring(0,fb_cookie.indexOf(";"));
        SharedPreferences sPrefs;
//        SharedPreferences.Editor editor=sPrefs.edit();
  //      editor.putString("fb_cookie", fb_cookie);
    //    editor.commit();
        System.out.println("updated Cookies:"+fb_cookie);

    }
}
