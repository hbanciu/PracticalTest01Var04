package ro.pub.cs.systems.eim.practicaltest01var04;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

public class ProcessingThread extends Thread {
    private Context context = null;
    private boolean isRunning = true;

    private String name_student_thread;
    private String group_student_thread;

    private Random random = new Random();

    public ProcessingThread(Context context, String name, String group) {
        this.context = context;
        name_student_thread = name;
        group_student_thread = group;
    }

    @Override
    public void run() {
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has started! PID: " + getId());
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actionTypes[random.nextInt(Constants.actionTypes.length)]);
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA,
                name_student_thread + " " + group_student_thread);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
