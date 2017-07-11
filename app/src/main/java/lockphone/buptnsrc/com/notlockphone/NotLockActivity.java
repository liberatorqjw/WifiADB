package lockphone.buptnsrc.com.notlockphone;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;

public class NotLockActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_lock);
        Button button = (Button)findViewById(R.id.button);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataOutputStream os = null;
                try {
                    Process localProcess = Runtime.getRuntime().exec("su");

                    os = new DataOutputStream(localProcess.getOutputStream());
                    os.writeBytes("setprop service.adb.tcp.port 5555\n");
                    os.writeBytes("stop adbd\n");
                    os.writeBytes("start adbd\n");
                    os.flush();


                    WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
                    //判断wifi是否开启

                    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                    int ipAddress = wifiInfo.getIpAddress();
                    String ip = intToIp(ipAddress);
//                    String ip = String.valueOf(ipAddress);

                    Toast.makeText(NotLockActivity.this, ip, Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (os != null) {
                            os.close();
                        }
                    } catch (IOException e) {

                    }

                }
            }
        });
//
//        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
//        PowerManager.WakeLock wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "TAG");
//
//        wakeLock.acquire();

    }

    public static String intToIp(int ipInt) {
//        return new StringBuilder().append(((ipInt >> 24) & 0xff)).append('.')
//                .append((ipInt >> 16) & 0xff).append('.').append(
//                        (ipInt >> 8) & 0xff).append('.').append((ipInt & 0xff))
//                .reverse().toString();
        return   new StringBuilder().append((ipInt & 0xff)).append(".")
                .append((ipInt >> 8) & 0xff).append(".")
                .append((ipInt >> 16) & 0xff).append(".")
                .append((ipInt >> 24) & 0xff).toString();
    }
}
