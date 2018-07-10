package com.anysoftkeyboard.api;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import static android.content.Intent.CATEGORY_INFO;

public class DummyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent findASKIntent = new Intent("android.intent.action.MAIN");
        findASKIntent.setPackage("com.menny.android.anysoftkeyboard");
        findASKIntent.addCategory(CATEGORY_INFO);
        List<ResolveInfo> listResolveInfo = getPackageManager().queryIntentActivities(findASKIntent, 0);
        if (listResolveInfo.size() != 0) {
            // start the main ASK prefs screen
        } else {
            createAlert("AnySoftKeyboard not installed!", "Install AnySoftKeyboard first, and then select the desired layout from AnySoftKeyboard's Settings->Keyboards menu.", true);
        }

    }

    public void createAlert(String title, String message, Boolean button) {
        // http://androidideasblog.blogspot.com/2010/02/how-to-add-messagebox-in-android.html
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        if ((button == true)) {
            alertDialog.setButton("Download Now",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent browserIntent = new Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("market://search?q=pname:com.menny.android.anysoftkeyboard"));
                            startActivity(browserIntent);
                        }
                    });
        }
        alertDialog.show();
    }
}
