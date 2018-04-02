package edu.shiyou.easy.xueba;

import android.accessibilityservice.AccessibilityService;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import edu.shiyou.easy.xueba.Data.Flag;

/**
 * Created by hp on 2018/3/28.
 */

public class XuebaService extends AccessibilityService {

    static String foregroundPackageName;

    private Flag f = new Flag();

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.i("XB-Service","start");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        int eventType = event.getEventType();
        PackageManager packageManager = getPackageManager();
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            /*
             * 如果 与 DetectionService 相同进程，直接比较 foregroundPackageName 的值即可
             * 如果在不同进程，可以利用 Intent 或 bind service 进行通信
             */
            foregroundPackageName = event.getPackageName().toString();
            Log.i("XB-Service",foregroundPackageName);
            Log.i("XB-Service",event.getPackageName().toString());
            if(f.getFlag()==1){
                if(event.getPackageName().toString().equals("edu.shiyou.easy.xueba")){
                    Log.i("XB-Service","study");
                }else{
                    Intent intent = new Intent(this, Study.class);
                    startActivity(intent);
                }
            }

            /*
             * 基于以下还可以做很多事情，比如判断当前界面是否是 Activity，是否系统应用等，
             * 与主题无关就不再展开。
             */
            ComponentName cName = new ComponentName(event.getPackageName().toString(),
                    event.getClassName().toString());
        }
        Log.i("XB-Service","eventType:"+eventType);
        Log.i("XB-Service","start1");
        Log.i("XB-Service","XB-Service: "+f.getFlag()+"");

    }

    public static boolean isForegroundPkgViaDetectionService(String packageName) {
        return packageName.equals(XuebaService.foregroundPackageName);
    }

    @Override
    public void onInterrupt() {
        Log.i("XB-Service","break");
    }
}
