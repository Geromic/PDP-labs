package lab3.threading;


import lab3.workers.*;

public class ThreadingMethod {
    protected final BaseWorkSplitter workSplitter;
    protected final Integer SIZE = 3;
    //THREAD_COUNT can't be greater than SIZE*SIZE;
    protected final Integer THREAD_COUNT = 4;

    public ThreadingMethod(TaskSplittingMode mode) {
        switch (mode) {
            case FIRST:
                this.workSplitter = new FirstSplitter();
                break;
            case SECOND:
                this.workSplitter = new SecondSplitter();
                break;
            default:
                this.workSplitter = new ThirdSplitter();
        }
    }

    void compute() {}
}
