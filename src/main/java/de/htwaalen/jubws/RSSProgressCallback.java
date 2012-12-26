package de.htwaalen.jubws;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndFeedImpl;

/**
 * This callback constructs a RSS feed from the benchmark events.
 */
public class RSSProgressCallback extends ProgressCallback{

	private SyndFeed feed;
	
	public RSSProgressCallback() {
        feed = new SyndFeedImpl();
        feed.setFeedType("rss_2.0");
        feed.setTitle("Benchmark Progress");
        feed.setLink("");
        feed.setDescription("Tracks start and finish of benchmarks.");
        feed.setEntries(new LinkedList<>());
	}
	
	
	@Override
	protected void benchmarkStartedImpl(String path, String classname) {
        SyndEntry entry = new SyndEntryImpl();
        entry.setTitle("Benchmark Started: "+classname);
        entry.setPublishedDate(new Date());
        feed.getEntries().add(entry);
	}

	@Override
	protected void benchmarkFinishedInternal(BenchmarkResult result) {
        SyndEntry entry = new SyndEntryImpl();
        entry.setTitle("Benchmark Finished: "+result.getClassName());
        entry.setPublishedDate(new Date());
        SyndContent content = new SyndContentImpl();
        content.setValue(result.toXML());
        List<SyndContent> contents = new ArrayList<>();
        contents.add(content);
        entry.setContents(contents);
        feed.getEntries().add(entry);
	}

	public SyndFeed getFeed() {
		return feed;
	}

	
	
}
