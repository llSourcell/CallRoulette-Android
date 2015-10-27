package twilio.com.twilioclient;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import com.twilio.client.Connection;
import com.twilio.client.Device;
import com.twilio.client.DeviceListener;
import com.twilio.client.Twilio;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import java.util.HashMap;
import java.util.Map;
import android.view.View;
import android.app.Activity;

import android.widget.ProgressBar;

public class CallRoulette implements Twilio.InitListener
 {
    private Device mDevice;
    private String TAG = "CallRoulette";
    private Connection mConnection;
    private Context mContext;
     private ProgressBar pBar;
     public Activity activity;


     public CallRoulette(Context context, Activity _activity)
    {
        this.mContext = context;
        Twilio.initialize(context, this);
        this.activity = _activity;
        pBar = (ProgressBar)this.activity.findViewById(R.id.progressBar);

    }

    @Override
    public void onInitialized(){
        Log.d(TAG, "Twilio SDK is ready");
        new HttpHandler(){
            @Override
            public HttpUriRequest getHttpRequestMethod(){
                Log.d(TAG, mContext.getString(R.string.app_capability_url));

                return new HttpGet(mContext.getString(R.string.app_capability_url));
            }

            @Override
            public void onResponse(String token) {
                mDevice = Twilio.createDevice(token, null);
                Log.d(TAG, "Capability token: " + token);
                pBar.setVisibility(View.INVISIBLE);

            }
        }.execute();
    }

    /* Twilio.InitListener method */
    @Override
    public void onError(Exception e) {
        Log.e(TAG, "Twilio SDK couldn't start: " + e.getLocalizedMessage());
    }

    public void connect(String phoneNumber)
    {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("To", phoneNumber);
        mConnection = mDevice.connect(parameters, null);
        if (mConnection == null)
            Log.w(TAG, "Failed to create new connection");
    }


    public void disconnect()
    {
        if (mConnection != null) {
            mConnection.disconnect();
            mConnection = null;
        }
        new HttpHandler(){
            @Override
            public HttpUriRequest getHttpRequestMethod(){
                Log.d(TAG, mContext.getString(R.string.app_hangup_url));

                return new HttpGet(mContext.getString(R.string.app_hangup_url));
            }

            @Override
            public void onResponse(String token) {
                Log.d(TAG, token);
            }
        }.execute();

    }

    @Override
    protected void finalize()
    {
        if (mDevice != null)
            mDevice.release();
    }
}