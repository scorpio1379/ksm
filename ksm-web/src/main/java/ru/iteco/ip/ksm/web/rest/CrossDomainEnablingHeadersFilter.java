package ru.iteco.ip.ksm.web.rest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by administrator on 29.06.2017.
 */
@WebFilter(filterName = "CrossDomainEnablingHeadersFilter")
public class CrossDomainEnablingHeadersFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        ((HttpServletResponse) resp).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) resp).addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("CrossDomainEnablingHeadersFilter initialized");
    }

}
