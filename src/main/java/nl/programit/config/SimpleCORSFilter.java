package nl.programit.config;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
/**
 * 
 * This class is a so called J2EE (Java EE) filter
 * It means that before or after every request this filte is inserted
 * between the request and the response
 * Since the last statement (chain.doFilter) is at the end it is a so called
 * request filter since before the chain.doFilter something is done
 * Might explain more here: http://www.journaldev.com/1933/java-servlet-filter-example-tutorial
 *
 * Since SpringBoot is a lot simpler than Java EE the part regarding the web.xml
 * can be skipped in SpringBoot.
 */


@Component
public class SimpleCORSFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "36000");
        response.setHeader("Access-Control-Allow-Headers", "origin, content-type, accept");
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {
    }

    public void destroy() {
    }

}