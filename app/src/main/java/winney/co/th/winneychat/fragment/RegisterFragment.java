package winney.co.th.winneychat.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import winney.co.th.winneychat.MainActivity;
import winney.co.th.winneychat.R;
import winney.co.th.winneychat.util.AlertUtil;

/**
 * Created by Dell on 1/27/2018.
 */

public class RegisterFragment extends Fragment {


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Toolbar Controller
        toolbarController();


    }//Main Method

    private void toolbarController() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar); //Cast to MainActivity

//        Setup Title
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.register_th));

//        Setup Navigator
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Set navigation back
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

//        Setup Menu
        setHasOptionsMenu(true);


    }// ToolBar

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_register, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {// ทำ menu ให้สามารถ Click ได้
        if (item.getItemId() == R.id.itemRegister) {
            uploadValueToFireBase();
        }
        return super.onOptionsItemSelected(item);
    }

    private void uploadValueToFireBase() {
//        Initial View
        EditText nameEditText = getView().findViewById(R.id.edtName);

        EditText emailEditText = getView().findViewById(R.id.edtMail);

        EditText passwordEditText = getView().findViewById(R.id.edtPassword);

        String nameString = nameEditText.getText().toString().trim();
        String emilString = emailEditText.getText().toString().trim();
        String passwordString = passwordEditText.getText().toString().trim();

        if (nameString.isEmpty() || emilString.isEmpty() || passwordString.isEmpty()) {
            // Have Space
            AlertUtil alertUtil = new AlertUtil(getActivity());
            alertUtil.normalDialog("Have Space", "Please filled All Every Blank");
        } else {
            // No Space
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

            firebaseAuth.createUserWithEmailAndPassword(emilString, passwordString)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Regeter Success",
                                        Toast.LENGTH_SHORT).show();
                                getActivity().getSupportFragmentManager().popBackStack();

                            } else {
                                AlertUtil alertUtil = new AlertUtil(getActivity());
                                alertUtil.normalDialog("Cannot Register", task.getException().getMessage());
                            }

                        }// onComplete
                    });




        } // End IF

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;

    }// Main method
}// Main Class
