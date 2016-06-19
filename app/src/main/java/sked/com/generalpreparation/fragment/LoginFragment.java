package sked.com.generalpreparation.fragment;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import sked.com.generalpreparation.R;
import sked.com.generalpreparation.Synchronize;

/**
 * A simple {@link Fragment} subclass of LoginActivity.
 */
public class LoginFragment extends Fragment {
    final String EMAIL_PARAM = "email";
    final String PASSWORD_PARAM = "password";
    URL url;
    /*find id of all views and instantiate them*/
    @BindView(R.id.etEmail)
    EditText emailField;
    @BindView(R.id.etPwd)
    EditText password;
    /*perform click operation on register button click*/
    @OnClick(R.id.loginBtn) void signInOnClick() {
        Log.d("test", "success");

        final String userEmail = emailField.getText().toString().toLowerCase().trim();
        final String userPassword = password.getText().toString().toLowerCase().trim();

/*append parameter in the url*/
        Uri builtUri = Uri.parse(Synchronize.URL_LOGIN)
                .buildUpon()
                .appendQueryParameter(EMAIL_PARAM, userEmail)
                .appendQueryParameter(PASSWORD_PARAM, "" + Integer.parseInt(userPassword))//check how to append integer
                .build();
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

 /*make a stringRequest request to server*/
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url.toString(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    if(! jsonObject.optBoolean(Synchronize.ERROR)){

                        String userID=jsonObject.getString(Synchronize.USER_ID);

/*Getting the details of User from nested json object */

                        JSONObject userDetail=jsonObject.getJSONObject(Synchronize.USER_DETAIL);

                        String nameValue=userDetail.getString("name");
                        String emailValue=userDetail.getString("email");
                        String dateValue=userDetail.getString("created_at");
                        String updatedValue=userDetail.getString("updated_at");

                        Toast.makeText(getActivity(),"welcome"+nameValue+""+emailValue+""+dateValue
                                +""+updatedValue ,Toast.LENGTH_LONG).show();

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
        requestQueue.add(stringRequest);
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
