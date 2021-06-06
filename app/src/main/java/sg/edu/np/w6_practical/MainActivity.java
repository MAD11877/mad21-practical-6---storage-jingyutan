package sg.edu.np.w6_practical;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "Main Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "On Create Main Activity");

        Button button = findViewById(R.id.MainFollowButton);
        TextView mainName = findViewById(R.id.mainName);
        TextView mainDesc = findViewById(R.id.mainDesc);
        Bundle recieveData = getIntent().getExtras();

        String recievedName = recieveData.getString("name");
        String recievedDesc = recieveData.getString("desc");
        if (recieveData.getBoolean("followed")){
            button.setText("Unfollow");
        }
        else
        {
            button.setText("Follow");
        }
        mainName.setText(recievedName);
        mainDesc.setText(recievedDesc);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recieveData.getBoolean("followed")) {
                    button.setText("Follow");
                    Toast.makeText(MainActivity.this,
                            "Unfollowed!", Toast.LENGTH_SHORT).show();
                }
                else {
                    button.setText("Unfollow");
                    Toast.makeText(MainActivity.this,
                            "Followed!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "On Start!");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "On Resume!");
    }
    @Override
    protected void onPause (){
        super.onPause();
        Log.v(TAG,"On Pause!");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "On Stop!");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "On Destroy!");
    }

}