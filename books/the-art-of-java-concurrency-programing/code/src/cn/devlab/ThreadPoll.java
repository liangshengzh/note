package cn.devlab;

/**
 * Created by zhong on 2016/11/29.
 */
public interface ThreadPoll<T extends Runnable> {
    void execute(T job);

    void shutdown();

    void addWorkers(int num);

    void removeWorkers(int num);

    int getJobSize();

}
