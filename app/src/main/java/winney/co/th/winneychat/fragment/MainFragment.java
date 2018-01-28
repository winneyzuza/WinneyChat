package winney.co.th.winneychat.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import winney.co.th.winneychat.R;
import winney.co.th.winneychat.ServiceActivity;
import winney.co.th.winneychat.util.AlertUtil;

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

//         Login Controller  // ctrl + Alt +M
        loginController();


    }// Main Method

    private void loginController() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setTitle("Please wait few minutes...");
                progressDialog.setMessage("Continue Connected FireBase");
                progressDialog.show();

//                Initial View
                EditText email = getView().findViewById(R.id.edtEmail);
                EditText password = getView().findViewById(R.id.edtPassword);

//                Get Value From EditText
                String emailString = email.getText().toString().trim();
                String passwordString = password.getText().toString().trim();

//                Check Space
                if (emailString.isEmpty() || passwordString.isEmpty()) {
                    AlertUtil alertUtil = new AlertUtil(getActivity());
                    alertUtil.normalDialog("Have Space", "Please Filled Email and Password");
                } else {
//                    No Space
                    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                    firebaseAuth.signInWithEmailAndPassword(emailString, passwordString)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    Toast.makeText(getActivity(),"Welcome", Toast.LENGTH_SHORT).show();

                                    progressDialog.dismiss();
                                    Intent intent = new Intent(getActivity(),
                                            ServiceActivity.class); //ย้าย Activity
                                    startActivity(intent);
                                    getActivity().finish();// ทำลายหน้าหลัก

                                } else {
                                    progressDialog.dismiss();
                                    AlertUtil alertUtil = new AlertUtil(getActivity());
                                    alertUtil.normalDialog("Cannot Login",
                                            task.getException().getMessage());
                                }
                            }   // onComplete
                        });

                }// End if


            }    // onClick
        });
    }

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
