package sg.edu.np.w6_practical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {
    ArrayList<User> myList = new ArrayList<>();
    private final static String TAG = "ListActivity";
    private UserAdapter.RecyclerViewClickListener listener;
    DBHandler dbHandler = new DBHandler(
            this, null, null, 1
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        User tempUser = new User();
        for (int i = 1; i <21; i++){
            tempUser.setName("Name " + String.valueOf(randomNum()));
            tempUser.setDescription("Desc " + String.valueOf(randomNum()));
            tempUser.setId(i);
            if (i % 2 == 0) {
                tempUser.setFollowed(true);

            }
            else {
                tempUser.setFollowed(false);
            }
            dbHandler.addUser(tempUser);
        }
        myList = dbHandler.getUsers();

        setOnClickListener();
        RecyclerView recyclerView = findViewById(R.id.ListRecyclerView);
        UserAdapter myAdapter = new UserAdapter(myList, listener);
        LinearLayoutManager myLayoutManager = new LinearLayoutManager(
                this
        );
        recyclerView.setLayoutManager(myLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
        Log.v(TAG, "On Create List Activity");
    }
    private void setOnClickListener() {
        listener = new UserAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int pos) {
                Log.v(TAG, "Clicked Profile Picture!");
                ClickedButton(pos);
            }
        };
    }

    private void ClickedButton(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(myList.get(position).getName()).setCancelable(false);
        builder.setTitle("Profile");
        builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG, "View Button Clicked!");
                Bundle extras = new Bundle();
                extras.putString("name", myList.get(position).getName());
                extras.putString("desc", myList.get(position).getDescription());
                extras.putBoolean("followed", myList.get(position).isFollowed());

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v(TAG, "Close Button Clicked");
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public int randomNum() {
        Random ran = new Random();
        int value = ran.nextInt(1000000000);
        return value;
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