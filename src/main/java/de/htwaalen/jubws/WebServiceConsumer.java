package de.htwaalen.jubws;
import java.io.IOException;

import com.carrotsearch.junitbenchmarks.IResultsConsumer;
import com.carrotsearch.junitbenchmarks.Result;

import de.htwaalen.jubws.BenchmarkResult.BenchmarkMethod;

/**
 * JUnitBenchmarks consumer used to add the results to the corresponding BenchmarkTask.
 */
public class WebServiceConsumer implements IResultsConsumer{



	@Override
	public void accept(Result result) throws IOException {
		BenchmarkResult bench = BenchmarkTask.getContext().getResult();
		BenchmarkMethod r = new BenchmarkMethod();
		

		r.setBenchmarkRounds(result.benchmarkRounds);
		r.setBenchmarkTime(result.benchmarkTime);
		r.setWarmupRounds(result.warmupRounds);
		r.setWarmupTime(result.warmupTime);
		r.setMethodName(result.getTestMethodName());

		r.setConcurrency(result.getThreadCount());
		r.setGcAverage(result.gcAverage.avg);
		r.setGcStddev(result.gcAverage.stddev);
		
		r.setRoundAvg(result.roundAverage.avg);
		r.setRoundStddev(result.roundAverage.stddev);
		r.setGcTime(result.gcInfo.accumulatedTime());
		r.setGcInvocations((int)result.gcInfo.accumulatedInvocations());
		BenchmarkTask.getContext().getResult().getMethods().add(r);
	}

}
