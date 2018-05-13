package Utils;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Class implementing a Thread Pool
 */
public class ThreadPool {

    /**
     * The default number of threads in the pool
     */
    private static final int THREAD_POOL_SIZE = 500;

    /**
     * Executor for executing the threads
     */
    private ScheduledThreadPoolExecutor executor;

    /**
     * ThreadPool constructor
     */
    public ThreadPool() {
        executor = new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE);
    }

    public ThreadPool(int numThreads) {
        executor = new ScheduledThreadPoolExecutor(numThreads);
    }


    //TODO: Methods to handle the threadPool - not doing nth with returned Futures for now

    public void run(Runnable runObject) {
        executor.submit(runObject);
    }

    public void run(Runnable runObject, int delay) {
        executor.schedule(runObject, delay, TimeUnit.MILLISECONDS);
    }

    public void run(Runnable runObject, int delay, int period) {
        executor.scheduleAtFixedRate(runObject, delay, period, TimeUnit.MILLISECONDS);
    }

    /**
     * Terminate the ThreadPool execution.
     * Waits for the threads to terminate.
     */
    public void shutDown() {
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
    }
}
