package ro.pub.cs.systems.eim.practicaltest01var04;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var04Service extends Service {
    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String name = intent.getStringExtra("SERVICE_SENT_NAME");
        String group = intent.getStringExtra("SERVICE_SENT_GROUP");
        processingThread = new ProcessingThread(this, name, group);
        Log.d("SERVICE STARTED", "On method onStartCommand");
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
