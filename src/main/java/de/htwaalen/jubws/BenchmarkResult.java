package de.htwaalen.jubws;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


/**
 * Class for holding the result of a benchmark.
 * 
 */
public class BenchmarkResult {

	/**
	 * Name of the benchmark class.
	 */
	private String className;

	/**
	 * Timestamp of the time the benchmark request gets processed.
	 */
	private Calendar date;

	/**
	 * List of the results for each benchmark method in the class.
	 */
	private List<BenchmarkMethod> methods;

	/**
	 * 
	 * Class for holding the result of one benchmark method.
	 * Since this class is holding values from com.carrotsearch.junitbenchmarks.Result
	 * most of the documentation is copied from there.
	 * 
	 */
	public static class BenchmarkMethod {

		/**
		 * Name of the benchmark method.
		 */
		private String methodName;
		
		/**
		 * Concurrency level (number of used threads).
		 */
		private int concurrency;
		
		/**
		 * Average from benchmark rounds. (in milliseconds)
		 */
		private double roundAvg;
		
		/**
		 * Standard deviation from benchmark rounds. (in milliseconds)
		 */
		private double roundStddev;

		
		/**
		 * @see com.carrotsearch.junitbenchmarks.GCSnapshot#accumulatedInvocations()
		 */
		private int gcInvocations;
		
		/**
		 * @see com.carrotsearch.junitbenchmarks.GCSnapshot#accumulatedTime()
		 */
		private double gcTime;

		
		/**
		 * Average from GC cleanups. (in milliseconds)
		 */
		private double gcAverage;
		
		/**
		 * Standard deviation from GC cleanups. (in milliseconds)
		 */
		private double gcStddev;
		
		
		/**
		 * Number of executed benchmark rounds.
		 * 
		 */
		private int benchmarkRounds;
		
		/**
		 * Number of warmup rounds.
		 */
		private int warmupRounds;
		
		/**
		 * Total warmup time, includes benchmarking and GC overhead.
		 */
		private long warmupTime;
		
		/**
		 * Total benchmark time, includes benchmarking and GC overhead.
		 */
		private long benchmarkTime;

		public BenchmarkMethod() {
		}

		public String getMethodName() {
			return methodName;
		}

		public void setMethodName(String methodName) {
			this.methodName = methodName;
		}
		
		public int getConcurrency() {
			return concurrency;
		}
		
		public void setConcurrency(int concurrency) {
			this.concurrency = concurrency;
		}

		public double getRoundAvg() {
			return roundAvg;
		}

		public void setRoundAvg(double roundAvg) {
			this.roundAvg = roundAvg;
		}

		public double getRoundStddev() {
			return roundStddev;
		}

		public void setRoundStddev(double roundStddev) {
			this.roundStddev = roundStddev;
		}

		public int getGcInvocations() {
			return gcInvocations;
		}

		public void setGcInvocations(int gcInvocations) {
			this.gcInvocations = gcInvocations;
		}

		public double getGcTime() {
			return gcTime;
		}

		public void setGcTime(double gcTime) {
			this.gcTime = gcTime;
		}

		public double getGcAverage() {
			return gcAverage;
		}
		
		public void setGcAverage(double gcAverage) {
			this.gcAverage = gcAverage;
		}
		
		public double getGcStddev() {
			return gcStddev;
		}
		
		public void setGcStddev(double gcStddev) {
			this.gcStddev = gcStddev;
		}
		
		public int getBenchmarkRounds() {
			return benchmarkRounds;
		}

		public void setBenchmarkRounds(int benchmarkRounds) {
			this.benchmarkRounds = benchmarkRounds;
		}

		public int getWarmupRounds() {
			return warmupRounds;
		}

		public void setWarmupRounds(int warmupRounds) {
			this.warmupRounds = warmupRounds;
		}

		public long getWarmupTime() {
			return warmupTime;
		}

		public void setWarmupTime(long warmupTime) {
			this.warmupTime = warmupTime;
		}

		public long getBenchmarkTime() {
			return benchmarkTime;
		}

		public void setBenchmarkTime(long benchmarkTime) {
			this.benchmarkTime = benchmarkTime;
		}

		/**
		 * Converts this BenchmarkMethod to a xml string.
		 * 
		 * @return a string representing this object in xml.
		 */
		public String toXML() {
			StringBuilder sb = new StringBuilder();

			sb.append("<BenchmarkMethod");
			sb.append(" methodName=\"" + methodName + "\"");
			sb.append(" concurrency=\"" + concurrency + "\"");
			sb.append(" roundAvg=\"" + roundAvg + "\"");
			sb.append(" roundStddev=\"" + roundStddev + "\"");
			sb.append(" gcInvocations=\"" + gcInvocations + "\"");
			sb.append(" gcTime=\"" + gcTime + "\"");
			sb.append(" gcAverage=\"" + gcAverage + "\"");
			sb.append(" gcStddev=\"" + gcStddev + "\"");
			sb.append(" benchmarkRounds=\"" + benchmarkRounds + "\"");
			sb.append(" warmupRounds=\"" + warmupRounds + "\"");
			sb.append(" warmupTime=\"" + warmupTime + "\"");
			sb.append(" benchmarkTime=\"" + benchmarkTime + "\"");
			sb.append("/>");

			return sb.toString();
		}

	}

	public BenchmarkResult() {
		methods = new LinkedList<>();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public List<BenchmarkMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<BenchmarkMethod> methods) {
		this.methods = methods;
	}

	/**
	 * Converts this BenchmarkResult to a xml string.
	 * 
	 * @return a string representing this object in xml.
	 */
	public String toXML() {
		StringBuilder sb = new StringBuilder();

		sb.append("<BenchmarkResult classname=\"" + className + "\" date=\""
				+ date.getTime() + "\">");
		sb.append("<Methods>");
		for (BenchmarkMethod m : getMethods()) {
			sb.append(m.toXML());
		}
		sb.append("</Methods>");
		sb.append("</BenchmarkResult>");

		return sb.toString();
	}

}
