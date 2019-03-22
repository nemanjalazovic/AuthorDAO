package Servlet;

import io.swagger.jaxrs.config.BeanConfig;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class SwaggerConfigurationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setBasePath("/demoREST/webapi/");
		beanConfig.setHost("localhost:8080");
		beanConfig.setTitle("Authors/Books app Swagger Docs");
		beanConfig.setResourcePackage("com.telusko.demoREST");
		beanConfig.setPrettyPrint(true);
		beanConfig.setScan(true);
		beanConfig.setSchemes(new String[] { "http" });
		beanConfig.setVersion("1.0");
	}

}
