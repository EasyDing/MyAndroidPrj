package edu.shiyou.easy.xueba;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.List;

import edu.shiyou.easy.xueba.Data.Flag;

public class Study extends AppCompatActivity implements View.OnClickListener{

    private Flag f;
    private Button set;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study);


        set = (Button)findViewById(R.id.switch1);

        f = new Flag();
        set.setOnClickListener(this);

        f.setFlag(0);


        if (serviceIsRunning()) {
            Log.i("XB-Service","服务已经在运行！");
        } else {

            Log.i("XB-Service","服务没在运行！");
        }
    }





    private boolean serviceIsRunning() {
        ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(Short.MAX_VALUE);
        for (ActivityManager.RunningServiceInfo info : services) {
            if (info.service.getClassName().equals(getPackageName() + ".MyService")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.switch1:
                if(f.getFlag()==1){
                    f.setFlag(0);
                    Log.i("XB-Service",f.getFlag()+"");
                }else{
                    f.setFlag(1);
                    Log.i("XB-Service",f.getFlag()+"");
                }

        }
    }
}
