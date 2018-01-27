package winney.co.th.winneychat.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import winney.co.th.winneychat.R;

/**
 * Created by Dell on 1/27/2018.
 */

public class MainFragment extends Fragment {
    // ตัวจัดการ fragment
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Controller
        registerController();

    }// Main Method

    private void registerController() {
        TextView textView = getView().findViewById(R.id.txtNewRegister);
        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                Replcae Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null) // ไม่สลาย Fragement ก่อนหน้า
                        .commit();
            }
        });
    }

    //1 ต้องการสร้าง view หน้ากากขึ้นมา
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, // layout เสมือน
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false); // false = ไม่ต้องมี user password

        return view;
    }
}// Main Class
