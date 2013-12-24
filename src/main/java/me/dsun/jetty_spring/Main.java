package me.dsun.jetty_spring;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

public class Main {
	public static void main(String[] args) throws Exception {
		WebAppContext webContext = new WebAppContext();
		webContext.setClassLoader(Thread.currentThread()
				.getContextClassLoader());
		webContext.setContextPath("/");
		webContext.setBaseResource(Resource
				.newClassPathResource("META-INF/webapp"));
		webContext
				.setConfigurations(new Configuration[] { new WebXmlConfiguration() });
		webContext.getMetaData().addContainerResource(
				Resource.newClassPathResource("/"));
		List<Handler> _handlers = new ArrayList<Handler>();
		_handlers.add(webContext);

		HandlerList _contexts = new HandlerList();

		_contexts.setHandlers(_handlers.toArray(new Handler[0]));

		HandlerCollection _result = new HandlerCollection();
		_result.setHandlers(new Handler[] { _contexts });

		Server server = new Server(InetSocketAddress.createUnresolved(
				"0.0.0.0", 8808));
		server.setHandler(_result);
		server.setStopAtShutdown(true);
		server.start();
		server.join();
	}
}
