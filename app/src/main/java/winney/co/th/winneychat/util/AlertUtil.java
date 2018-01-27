package winney.co.th.winneychat.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import winney.co.th.winneychat.R;

/**
 * Created by Dell on 1/27/2018.
 */

public class AlertUtil {
    private Context context; // ท่อที่เชื่อมต่อเพื่อการสื่อสารกันระหว่าง Object

    public AlertUtil(Context context) {
        this.context = context;
    }

    public void normalDialog(String titleString, String msgString){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false); // ต้อง กด Ok เท่านั้น Dialog ถึงจะหายไป
        builder.setIcon(R.drawable.ic_action_alert);
        builder.setTitle(titleString);
        builder.setMessage(msgString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();

    }


}//Main Class
