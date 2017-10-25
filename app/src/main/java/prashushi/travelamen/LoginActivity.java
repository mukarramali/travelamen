package prashushi.travelamen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Dell User on 3/30/2017.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphRequestAsyncTask;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;

import prashushi.travelamen.model.User;
import prashushi.travelamen.utils.BackgroundTask;
import prashushi.travelamen.utils.Params;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    private static final int RC_SIGN_IN = 333;
    String number;
    ProgressDialog pDialog;
    LoginButton facebookButton;
    CallbackManager callbackManager;
    Params params;
    SignInButton googleSignInButton;
    GoogleApiClient mGoogleApiClient;
    GoogleSignInOptions gso;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_login);
        params=new Params(this);
        AppEventsLogger.activateApp(this);
        initGoogle();
        init();
        //parse();

        findViewById(R.id.con_fb).setOnClickListener(this);
        findViewById(R.id.con_google).setOnClickListener(this);
        //      findViewById(R.id.unset).setOnClickListener(this);
        findViewById(R.id.tv_next).setOnClickListener(this);
    }

    private void parse() {
        try {
            URL url= new URL("https://www.google.com");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void initGoogle() {
// Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }


    private void init() {
        facebookButton = (LoginButton) findViewById(R.id.fb_button);
        facebookButton.setReadPermissions("email");
        facebookButton.setReadPermissions("name");
        facebookButton.setReadPermissions("username");
        callbackManager= CallbackManager.Factory.create();
        facebookButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String userid_fb=loginResult.getAccessToken().getUserId();
                System.out.println("success");
                final AccessToken accessToken = loginResult.getAccessToken();
                final User fbUser = new User();
                GraphRequestAsyncTask request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject user, GraphResponse graphResponse) {
                        fbUser.setEmail(user.optString("email"));
                        fbUser.setName(user.optString("name"));
                        fbUser.setId(user.optString("id"));
                        fbUser.setPhone(user.optString("phone"));
                        register("facebook", fbUser);
                    }
                }).executeAsync();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
// Set the dimensions of the sign-in button.
        googleSignInButton = (SignInButton) findViewById(R.id.google_button);
        googleSignInButton.setSize(SignInButton.SIZE_STANDARD);
        googleSignInButton.setOnClickListener(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("XXX", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            User user=new User();
            user.setId(acct.getId());
            user.setEmail(acct.getEmail());
            user.setName(acct.getDisplayName());
            register("google", user);

        } else {
            // Signed out, show unauthenticated UI.
        }
    }

    private void register(String method, User user) {

        params.setName(user.getName());
        params.setEmail(user.getEmail());
        params.setPhone(user.getPhone());
        startActivity(new Intent(this, MainActivity.class));
        finish();

        /*
        String url=getString(R.string.local_host)+"register.php";
        ArrayList<String> params=new ArrayList<>();
        params.add("method");
        params.add("method_id");
        ArrayList<String> values=new ArrayList<>();
        values.add(method);
        values.add(method_id);
        new BackgroundTask(url, params, values, new BackgroundTask.AsyncResponse() {
            @Override
            public void processFinish(String output) {

            }

            @Override
            public void processFinish(String output, int code) {

            }
        }).execute();
*/
    }

    private void setParameters(JSONObject object) {

        params.clear();
        JSONObject user=object.optJSONObject("data");
        String userid=user.optString("userid");
        params.setUserid(userid);
        String name=user.optString("name");
        params.setName(name);
        String email=user.optString("email");
        params.setEmail(email);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_next:
                EditText etNumber=(EditText)findViewById(R.id.et_phone);
                number=etNumber.getText().toString();
                if(number.length()!=10) {
                    TextInputLayout til= (TextInputLayout) findViewById(R.id.input_layout);
                    til.setError("Please enter a valid number!");
                    return;
                }
                register("phone", new User());
                break;
            case R.id.google_button:
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
                break;
            case R.id.con_fb:
                facebookButton.performClick();
                break;
            case R.id.con_google:
                googleSignInButton.performClick();
                break;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

/*
<form id="entrada_register_form" action="#">
							<div class="form-holder">
								<div class="wrap">
									<div class="hold">
										<label for="reg_fname">First Name</label>
										<input type="text" id="reg_fname" name="reg_fname" class="form-control">
									</div>
									<div class="hold">
										<label for="reg_lname">Last Name</label>
										<input type="text" id="reg_lname" name="reg_lname" class="form-control">
									</div>
									<div class="hold">
										<label for="reg_username">Username</label>
										<input type="text" id="reg_username" name="reg_username" class="form-control">
									</div>
									<div class="hold">
										<label for="reg_email">Email</label>
										<input type="email" id="reg_email" name="reg_email" class="form-control">
									</div>
									<div class="hold">
										<label for="reg_password">Password</label>
										<input type="password" id="reg_password" name="reg_password" class="form-control">
									</div>
									<div class="btn-hold">
										<button type="submit" class="btn btn-default">Register</button>
									</div>
								</div>
							</div>
							</form>
 */