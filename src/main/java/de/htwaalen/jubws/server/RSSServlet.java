package de.htwaalen.jubws.server;

import java.io.IOException;
import java.io.Writer;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;


import com.sun.syndication.io.SyndFeedOutput;

import de.htwaalen.jubws.RSSProgressCallback;


public class RSSServlet extends HttpServlet{
	



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
        ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();
        
        RSSProgressCallback rss = (RSSProgressCallback)ctx.getBean("RSSProgressCallback");
        

        
        Writer writer = resp.getWriter();
        resp.setContentType("application/xml; charset=UTF-8");
        SyndFeedOutput output = new SyndFeedOutput();
        try{
        	output.output(rss.getFeed(), writer);
        }catch (Exception e){
            e.printStackTrace();
        }
        writer.flush();
        writer.close();
	}
	
}
