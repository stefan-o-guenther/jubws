package de.htwaalen.jubws;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


import com.carrotsearch.junitbenchmarks.IResultsConsumer;
import com.carrotsearch.junitbenchmarks.Result;


public class DemoConsumer implements IResultsConsumer{



	@Override
	public void accept(Result result) throws IOException {
		BenchmarkResult r =  new BenchmarkResult();
		r.setDate(Calendar.getInstance());
		r.setBenchmarkRounds(result.benchmarkRounds);
		r.setBenchmarkTime(result.benchmarkTime);
		r.setWarmupRounds(result.warmupRounds);
		r.setWarmupTime(result.warmupTime);
		r.setTestname(result.getTestMethodName());
		r.setClassname(result.getTestClassName());
		r.setRoundAvg(result.roundAverage.avg);
		r.setRoundStddev(result.roundAverage.stddev);
		r.setGcTime(result.gcInfo.accumulatedTime());
		r.setGcInvocations((int)result.gcInfo.accumulatedInvocations());
		Benchmarks.addResult(r);
	}

}
