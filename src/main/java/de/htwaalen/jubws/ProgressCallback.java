package de.htwaalen.jubws;


/**
 * 
 * This class is used to implement callbacks for the BenchmarkTask class.
 * It provides callback methods for several events.
 * 
 */
public abstract class ProgressCallback {
	
	protected abstract void benchmarkStartedImpl(String path, String classname);
	protected abstract void benchmarkFinishedInternal(BenchmarkResult result);

	/**
	 * This is called when the Benchmark starts.
	 * Since BenchmarkTasks can run in multiple threads and call the callbacks we need to synchronize it.
	 * 
	 * @param path Path to a directory or JAR containing the benchmark class.
	 * @param classname Fully qualified name of the benchmark class.
	 */
	public synchronized void benchmarkStarted(String path, String classname){
		benchmarkStartedImpl(path, classname);
	}
	/**
	 * Since BenchmarkTasks can run in multiple threads and call the callbacks we need to synchronize it.
	 * 
	 * @param result The result of the finished benchmark.
	 */
	public synchronized void benchmarkFinished(BenchmarkResult result){
		benchmarkFinishedInternal(result);
	}
}
