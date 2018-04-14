package narayan.embed.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Objects;

/**
 * The Main Configuration of the App
 *
 * @author narayan-sambireddy
 */
@EnableWebMvc
@Configuration
@ComponentScan
public interface RunWebAppAsJarOnJetty {

    String CONTEXT_PATH = "/app";
    String URL_MAPPING = "/";

    static void main(String[] args) throws Exception {

        ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        servletContext.setContextPath(CONTEXT_PATH);
        servletContext.addServlet(new ServletHolder(new DispatcherServlet(webContext())),URL_MAPPING);

        Server server = new Server(getPort());
        server.setHandler(servletContext);
        server.start();
        server.join();

    }

    private static WebApplicationContext webContext() {
        return new AnnotationConfigWebApplicationContext() {{
           register(RunWebAppAsJarOnJetty.class);
        }};
    }

    private static Integer getPort() {
        return Integer.parseInt(Objects.toString(System.getProperty("PORT"), "8080"));
    }
}
