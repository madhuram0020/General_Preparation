package sked.com.generalpreparation.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
public class LoginFragment extends Fragment {
    /*find id of all views and instantiate them*/
    @BindView(R.id.etEmail)
    EditText emailField;
    @BindView(R.id.etPwd)
    EditText password;
    /*perform click operation on register button click*/
    @OnClick(R.id.registerBtn) void signInOnClick() {
        Log.d("test", "success");
        Toast.makeText(getActivity(),"success",Toast.LENGTH_LONG).show();
    }
    @OnClick(R.id.facebook) void  signInFb(){Toast.makeText(getActivity(),"success fb",Toast.LENGTH_LONG).show();}
    @OnClick(R.id.google) void  signInGoogle(){Toast.makeText(getActivity(),"success G+",Toast.LENGTH_LONG).show();}

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_login, container, false);
        // Inflate the layout for this fragment
        ButterKnife.bind(this,v);
        return v;
    }

}
