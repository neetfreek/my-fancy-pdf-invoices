/*
 * Jonathan Widdowson
 * Neetfreek, 2021
 */

/*
 * Launch application by running main()
 *      dispatcherServlet serves as Spring WebMVC entry point, acting as servlet accepting all HTTP requests to,
 *          and sending responses from, @Controllers
 *      dispatcherServlet is registered with tomcat
 *      dispatcherServlet knows about application context as passed via MyFancyPdfInvoicesApplicationConfiguration.class
 *      webAppCtx knows about @Controllers and @...Mappings because of @ComponentScanning
 */

package com.neetfreek.myfancypdfinvoices;

import com.neetfreek.myfancypdfinvoices.context.MyFancyPdfInvoicesApplicationConfiguration;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;

public class ApplicationLauncher {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();

        Context tomcatCtx = tomcat.addContext("", null);

        WebApplicationContext webAppCtx = createWebApplicationContext(tomcatCtx.getServletContext());
        DispatcherServlet dispatcherServlet = new DispatcherServlet(webAppCtx);
        Wrapper servlet = Tomcat.addServlet(tomcatCtx, "dispatcherServlet", dispatcherServlet);
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");

        tomcat.start();
    }

    public static WebApplicationContext createWebApplicationContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MyFancyPdfInvoicesApplicationConfiguration.class);
        ctx.setServletContext(servletContext);
        ctx.refresh();
        ctx.registerShutdownHook();
        return ctx;
    }
}
