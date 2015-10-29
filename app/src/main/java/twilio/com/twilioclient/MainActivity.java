package twilio.com.twilioclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Chronometer;
import android.os.SystemClock;
import android.app.ProgressDialog;


public class MainActivity extends Activity implements View.OnClickListener {


    //These are our UI elements
    private CallRoulette phone;
    private Button callsomeoneButton;
    private Chronometer callTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Here we start our listeners and initialize our elements
        phone = new CallRoulette(this, this);
        callsomeoneButton = (Button)  findViewById(R.id.callsomeoneButton);
        callsomeoneButton.setOnClickListener(this);
         callTimer = (Chronometer) findViewById(R.id.chronometer);

    }



    @Override
    public void onClick(View view)
    {
        //whenever a user taps the call button, connect/disconnect a phone line
        if (view.getId() == R.id.callsomeoneButton && callsomeoneButton.getText().toString().equals("Call Someone")) {
            phone.connect();
            callsomeoneButton.setBackgroundResource(R.drawable.endcallgradient);
            callsomeoneButton.setText("End Call");
            callTimer.setVisibility(view.VISIBLE);
            callTimer.setBase(SystemClock.elapsedRealtime());
            callTimer.start();
        } else if(view.getId() == R.id.callsomeoneButton && callsomeoneButton.getText().toString().equals("End Call")) {
            phone.disconnect();
            callsomeoneButton.setText("Call Someone");
            callsomeoneButton.setBackgroundResource(R.drawable.callgradient);
            callTimer.setVisibility(view.INVISIBLE);
            callTimer.stop();
            callTimer.setBase(SystemClock.elapsedRealtime());

        }
    }
}

