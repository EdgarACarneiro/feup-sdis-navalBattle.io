package Utils;

import java.util.concurrent.*;


/**
 * Class implementing a Thread Pool.
 */
public class ThreadPool {

    /** The default number of threads in the pool. */
    private static final int THREAD_POOL_SIZE = 500;

    /** Executor for executing the threads. */
    private ScheduledThreadPoolExecutor executor;

    /**
     * ThreadPool constructor.
     */
    public ThreadPool() {
        executor = new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE);
    }

    /**
     * Instantiates a new thread pool.
     *
     * @param numThreads the number of threads
     */
    public ThreadPool(int numThreads) {
        executor = new ScheduledThreadPoolExecutor(numThreads);
    }


    //TODO: Methods to handle the threadPool - not doing nth with returned Futures for now

    /**
     * Run.
     *
     * @param runObject the run object
     * @return the future
     */
    public Future run(Callable runObject) {
        return executor.submit(runObject);
    }

    /**
     * Run.
     *
     * @param runObject the run object
     * @param delay the delay
     * @return the future
     */
    public Future run(Callable runObject, int delay) {
        return executor.schedule(runObject, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * Run.
     *
     * @param runObject the run object
     */
    public void run(Runnable runObject) {
        executor.submit(runObject);
    }

    /**
     * Run.
     *
     * @param runObject the run object
     * @param delay the delay
     */
    public void run(Runnable runObject, int delay) {
        executor.schedule(runObject, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * Run.
     *
     * @param runObject the run object
     * @param delay the delay
     * @param period the period
     */
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
