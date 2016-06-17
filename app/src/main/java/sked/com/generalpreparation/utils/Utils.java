package sked.com.generalpreparation.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class Utils {
    public static final String USER_CREDENTIAL_PREFS = "user_credential_prefs";
    public static final String IS_LOGGED_IN = "is_logged_in";
    public static final String USER_ID = "user_id";
    public static final String USER_ROLE = "user_role";
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";


    private static ProgressDialog progressDialog;

    public static ProgressDialog show(Context context) {
        progressDialog = ProgressDialog.show(context, null, "Please wait...", false, false, null);
        return progressDialog;
    }

    public static void dismiss() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
    public static boolean validateField(EditText editText) {
        if (editText.getText().toString().length() > 0) {
            return true;
        } else {
            editText.setError("Filed is required.");
            return false;
        }
    }


    public static boolean validatePassword(EditText editText, boolean password) {
        if (editText.getText().toString().length() > 0) {
            if (password) {
                if (editText.getText().toString().length() >= 6)
                    return true;
                else {
                    editText.setError("Password should be minimum of 6 characters!");
                    return false;
                }
            }
            return true;
        } else {
            editText.setError("This filed is required.");
            return false;
        }
    }

    public static boolean isUserLoggedIn(Context context) {

        SharedPreferences userCredentials = context.getSharedPreferences(
                USER_CREDENTIAL_PREFS, Activity.MODE_PRIVATE);
        return userCredentials.getBoolean(IS_LOGGED_IN, false);
    }


    public static String getUserName(Context context) {

        SharedPreferences userCredentials = context.getSharedPreferences(
                USER_CREDENTIAL_PREFS, Activity.MODE_PRIVATE);
        return userCredentials.getString(USER_NAME, "");
    }

    public static String getUserId(Context context) {

        SharedPreferences userCredentials = context.getSharedPreferences(
                USER_CREDENTIAL_PREFS, Activity.MODE_PRIVATE);
        return userCredentials.getString(USER_ID, "");
    }

    public static String getUserRole(Context context) {

        SharedPreferences userCredentials = context.getSharedPreferences(
                USER_CREDENTIAL_PREFS, Activity.MODE_PRIVATE);
        return userCredentials.getString(USER_ROLE, "");
    }

    public static String getUserEmail(Context context) {

        SharedPreferences userCredentials = context.getSharedPreferences(
                USER_CREDENTIAL_PREFS, Activity.MODE_PRIVATE);
        return userCredentials.getString(USER_EMAIL, "");
    }

    public static void setUserCredential(Context context, String user_Id, String user_role, String user_name, boolean loggedIn) {

        SharedPreferences userCredentials = context.getSharedPreferences(
                USER_CREDENTIAL_PREFS, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = userCredentials.edit();
        editor.putBoolean(IS_LOGGED_IN, loggedIn);
        editor.putString(USER_ID, user_Id);
        editor.putString(USER_NAME, user_name);
        editor.putString(USER_ROLE, user_role);
        editor.apply();
    }

    public static boolean isNetworkAvailable(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        if (!(haveConnectedWifi || haveConnectedMobile)) {
            Toast.makeText(context, "No Network Connectivity", Toast.LENGTH_SHORT).show();
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

  /*  public static void setImage(Context context, ImageView image, String url) {
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                 .error(R.drawable.default_logo).into(image);
    }*/

    public static boolean validateSpinnerFields(TextView textView, Context context) {

        String textViewData = "Select Course";
        if (!textViewData.equals(textView.getText().toString()))
            return true;
        else {
            Toast.makeText(context, "Select Hospitals.", Toast.LENGTH_LONG).show();
            return false;
        }
    }
    public static boolean hasImage(@NonNull ImageView view, Context context) {
        Drawable drawable = view.getDrawable();
        boolean hasImage = (drawable != null);

        if (hasImage && (drawable instanceof BitmapDrawable)) {
            hasImage = ((BitmapDrawable)drawable).getBitmap() != null;
        }else {
            Toast.makeText(context,"Please Select Image",Toast.LENGTH_LONG).show();

        }

        return hasImage;
    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
