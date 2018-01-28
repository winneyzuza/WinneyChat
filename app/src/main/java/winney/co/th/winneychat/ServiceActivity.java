package winney.co.th.winneychat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import winney.co.th.winneychat.fragment.ChatFragment;
import winney.co.th.winneychat.fragment.RegisterFragment;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentServiceFragment, new ChatFragment())
                    .commit();
        }
    }// Main Method


}// Main Class
