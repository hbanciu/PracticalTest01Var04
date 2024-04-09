package ro.pub.cs.systems.eim.practicaltest01var04;

public interface Constants {

    public final String PROCESSING_THREAD_TAG = "thread";

    final public static String[] actionTypes = {
            "ro.pub.cs.systems.eim.practicaltest01.firstAction",
            "ro.pub.cs.systems.eim.practicaltest01.secondAction"
    };

    final public static String BROADCAST_RECEIVER_EXTRA = "message";
    final public static String BROADCAST_RECEIVER_TAG = "[Message]";

    final public int SERVICE_STOPPED = 0;

    final public int SERVICE_STARTED = 1;
}
