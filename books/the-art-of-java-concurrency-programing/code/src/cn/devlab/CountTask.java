package cn.devlab;

import java.util.concurrent.RecursiveTask;

/**
 * Created by zhong on 2016/11/29.
 */
public class CountTask extends RecursiveTask {

    private static final int THRESHOLD = 2;

    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    protected Object compute() {
        int sum = 0;

        boolean canCompute = (end - start) <= THRESHOLD;

        if(canCompute){
            for(int i = start; i <= end; i++){
                sum += i;
            }
        }else {
            //int middle = (start + end)/2;

        }
        return null;
    }
}
