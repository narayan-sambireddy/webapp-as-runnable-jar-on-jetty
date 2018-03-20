package narayan.embed.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * The Main Configuration of the App
 *
 * @author narayana
 */
@EnableWebMvc
@Configuration
@ComponentScan
public interface RunWebAppAsJar {

    String CONTEXT_PATH = "/app";
    String URL_MAPPING = "/";
    Integer SERVER_PORT = 8080;


    static void main(String[] args) throws Exception {

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(RunWebAppAsJar.class);

        ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContext.setContextPath(CONTEXT_PATH);
        servletContext.addServlet(new ServletHolder(new DispatcherServlet(context)),URL_MAPPING);

        Server server = new Server(SERVER_PORT);
        server.setHandler(servletContext);
        server.start();
        server.join();

    }
}
