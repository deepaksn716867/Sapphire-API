package org.sapphire.appconsole.resource;

import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.*;

import io.swagger.models.auth.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import io.swagger.jaxrs.config.BeanConfig;

public class SwaggerConfig extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
    	Info info = new Info()
    		      .title("Swagger Sample App")
    		      .description("This is a A Level-2 Rest API(Java Apache Jersey) for the Appworks Sapphire project. The API handles the CRUD operations of the Angular UI")
    		      .termsOfService("https://github.com/deepaksn716867/Sapphire-API")
    		      .contact(new Contact()
    		        .email(""))
    		      .license(new License()
    		        .name("Apache 2.0")
    		        .url("http://www.apache.org/licenses/LICENSE-2.0.html"));

    		    ServletContext context = config.getServletContext();
    		    Swagger swagger = new Swagger().info(info);
    		    new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
    }

}
