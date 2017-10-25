package prashushi.travelamen.utils;

import android.content.Context;
import android.content.SharedPreferences;

import prashushi.travelamen.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Dell User on 12/31/2016.
 */

public class Params {
    SharedPreferences sPrefs;
    SharedPreferences.Editor editor;

    public Params(Context context){
        this.sPrefs=context.getSharedPreferences(context.getString(R.string.sPrefs), MODE_PRIVATE);
        editor=sPrefs.edit();
        editor.putBoolean("flag", false);
    }
    public void clear(){
        editor.clear();
        editor.putBoolean("flag", false);
        editor.commit();
    }
    public void setName(String name){
        editor.putString("name", name);
        editor.commit();
    }
    public String getName(){
        return sPrefs.getString("name", "N/A");
    }

    public void setPhone(String phone){
        editor.putString("phone", phone);
        editor.commit();
    }
    public String getPhone(){
        return sPrefs.getString("phone", "");
    }
    public void setUserid(String userid){
        editor.putString("userid", userid);
        editor.putBoolean("flag", true);
        editor.commit();
    }
    public String getUserid(){
        return sPrefs.getString("userid", "");
    }
    public void setEmail(String email){
        editor.putString("email", email);
        editor.commit();
    }
    public String getEmail(){
        return sPrefs.getString("email", "N/A");
    }
}
