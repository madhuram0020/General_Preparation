package sked.com.generalpreparation.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sked.com.generalpreparation.R;

/**
 * A simple {@link Fragment} subclass of LoginActivity.
 */
public class RegisterFragment extends Fragment {
    /*find id of all views and instantiate them*/
    @BindView(R.id.etName)
    EditText nameField;
    @BindView(R.id.etEmail)
    EditText emailField;
    @BindView(R.id.etPhone)
    EditText mobileField;
    @BindView(R.id.etPwd)
    EditText passwordField;
   @OnClick(R.id.registerBtn) void registerUser(){
       Toast.makeText(getActivity(),"success",Toast.LENGTH_LONG).show();
   }

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this,v);

        return v;
    }

}
