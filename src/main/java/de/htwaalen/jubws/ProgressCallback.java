package de.htwaalen.jubws;

public abstract class ProgressCallback {
	
	protected abstract void benchmarkStartedImpl(String path, String classname);
	protected abstract void benchmarkFinishedInternal(BenchmarkResult result);

	public synchronized void benchmarkStarted(String path, String classname){
		benchmarkStartedImpl(path, classname);
	}
	
	public synchronized void benchmarkFinished(BenchmarkResult result){
		benchmarkFinishedInternal(result);
	}
}
