package de.htwaalen.jubws;

import java.util.Calendar;

public class BenchmarkResult {

	private Calendar date;
	
	private String classname;
	private String testname;
	
	private double roundAvg;
	private double roundStddev;
	
	private int gcInvocations;
	private double gcTime;
	
	private int benchmarkRounds;
	private int warmupRounds;
    private long warmupTime;
    private long benchmarkTime;
    
    public BenchmarkResult() {
	}
    
    
    
    public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
    
    public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getTestname() {
		return testname;
	}

	public void setTestname(String testname) {
		this.testname = testname;
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

	@Override
    public String toString() {
    	StringBuffer sb = new StringBuffer();
    	sb.append("date: ");
    	sb.append(date);
    	sb.append(" classname: ");
    	sb.append(classname);
    	sb.append(" testname: ");
    	sb.append(testname);
    	sb.append(" benchmarkRounds: ");
    	sb.append(benchmarkRounds);
    	sb.append(" warmupRounds: ");
    	sb.append(warmupRounds);
    	sb.append(" warmupTime: ");
    	sb.append(warmupTime);
    	sb.append(" benchmarkTime: ");
    	sb.append(benchmarkTime);
    	sb.append(" roundAvg: ");
    	sb.append(roundAvg);
    	sb.append(" roundStddev: ");
    	sb.append(roundStddev);
    	sb.append(" gcInvocations: ");
    	sb.append(gcInvocations);
    	sb.append(" gcTime: ");
    	sb.append(gcTime);
    	
    	return sb.toString();
    }
	
}
