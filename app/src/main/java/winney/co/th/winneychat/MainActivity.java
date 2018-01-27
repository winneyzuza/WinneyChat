package winney.co.th.winneychat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import winney.co.th.winneychat.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { // method หลัก ที่เริ่มต้นการทำงาน ทำงานที่แรกที่ method นี้  savedInstanceState
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Add Fragment to Activity
        if(savedInstanceState == null){ //กรณีเปิดแอพครั้งแรก

            // ต้องจำเอาตรงนี้
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentMainFragment, new MainFragment()).commit();
        }

    }//Main Methid


}// Main Class
