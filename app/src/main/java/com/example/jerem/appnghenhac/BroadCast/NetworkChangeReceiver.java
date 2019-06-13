package com.example.jerem.appnghenhac.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class NetworkChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {

        int status = NetworkUtil.getConnectivityStatusString(context);
        Log.e("Sulod sa network reciever", "Sulod sa network reciever");
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            if (status == NetworkUtil.NETWORK_STATUS_NOT_CONNECTED) {
                Toast.makeText(context,"Không có kết nối Internet Vui lòng kiểm tra kết nối mạng !!",Toast.LENGTH_LONG).show();
            } else {
              //  Toast.makeText(context," co internet",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
