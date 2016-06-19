package sked.com.generalpreparation.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import sked.com.generalpreparation.LoginActivity;
import sked.com.generalpreparation.R;
import sked.com.generalpreparation.Synchronize;
import sked.com.generalpreparation.utils.Utils;

/**
 * A simple {@link Fragment} subclass of LoginActivity.
 */
public class RegisterFragment extends Fragment {

    final String QUERY_PARAM = "name";
    final String EMAIL_PARAM = "email";
    final String PASSWORD_PARAM = "password";
    URL url;
    /*find id of all views and instantiate them*/
    @BindView(R.id.etName)
    EditText nameField;
    @BindView(R.id.etEmail)
    EditText emailField;
    @BindView(R.id.etPwd)
    EditText passwordField;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @OnClick(R.id.registerBtn)
    void registerUser() {
        final String userName = nameField.getText().toString().toLowerCase().trim();
        final String userEmail = emailField.getText().toString().toLowerCase().trim();
        final String userPassword = passwordField.getText().toString().toLowerCase().trim();

/*append parameter in the url*/

        Uri builtUri = Uri.parse(Synchronize.URL_REGISTER)
                .buildUpon()
                .appendQueryParameter(QUERY_PARAM, userName)
                .appendQueryParameter(EMAIL_PARAM, userEmail)
                .appendQueryParameter(PASSWORD_PARAM, "" + Integer.parseInt(userPassword))//check how to append integer
                .build();
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
/*make a stringRequest request to server*/
        StringRequest request = new StringRequest(Request.Method.POST, url.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(),"Success",Toast.LENGTH_LONG).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    if (jsonObject.optBoolean("error")) {
                       Toast.makeText(getActivity(),"You have already registered",Toast.LENGTH_LONG).show();
                    } else{
                        Toast.makeText(getActivity(),"Success",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, v);


        return v;
    }

}
