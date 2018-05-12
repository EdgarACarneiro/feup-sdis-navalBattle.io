package Utils;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Class implementing a Thread Pool
 */
public class ThreadPool {

    /**
     * The number of threads in the pool
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

    //TODO: Methods to handle the threadPool

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
