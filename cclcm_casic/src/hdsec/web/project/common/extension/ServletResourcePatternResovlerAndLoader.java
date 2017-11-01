package hdsec.web.project.common.extension;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.context.support.ServletContextResource;
import org.springframework.web.context.support.ServletContextResourcePatternResolver;

/**
 * Servlet资源搜索器, 模仿Spring的XmlWebApplicationContext写的ResourceResolver.
 * 该类不能实例化, 应该由ContextListener调用init方法进行初始化.
 */
public class ServletResourcePatternResovlerAndLoader extends DefaultResourceLoader implements ResourcePatternResolver {
	
	private static ServletResourcePatternResovlerAndLoader instance;
	protected static Logger log = Logger.getLogger(ServletResourcePatternResovlerAndLoader.class);
	
	public static void init(ServletContext servletContext) {
		instance = new ServletResourcePatternResovlerAndLoader(servletContext);
		if (instance != null)
			log.info("Servlet资源搜索器初始化成功.");
	}
	
	public static Resource[] getResourceArray(String locationPattern) throws IOException {
		if (instance == null || instance.servletContext == null)
			throw new RuntimeException("Servlet资源搜索器没有被正确的初始化, 请检查web.xml中的listener配置");
		return instance.getResources(locationPattern);
	}
	
	private ServletContext servletContext;
	private ResourcePatternResolver resourcePatternResolver = new ServletContextResourcePatternResolver(this);
	
	private ServletResourcePatternResovlerAndLoader(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@Override
	public Resource[] getResources(String locationPattern) throws IOException {
		return this.resourcePatternResolver.getResources(locationPattern);
	}
	
	@Override
	protected Resource getResourceByPath(String path) {
		return new ServletContextResource(this.servletContext, path);
	}
	
	/*
	private final ServletContext context;

	public ServletResourcePatternResovlerAndLoader(ServletContext context) {
	this.context = context;
	}

	public Resource getResource(String path) {
	return new ServletContextResource(this.context, path);
	}
	*/
	
}
