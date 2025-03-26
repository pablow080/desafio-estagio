package org.desafioestagio.backend.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.apache.wicket.protocol.http.WicketFilter;

import java.io.IOException;

public class CustomWicketFilter implements Filter {

    private WicketFilter wicketFilter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        wicketFilter = new WicketFilter();
        try {
            wicketFilter.init((javax.servlet.FilterConfig) filterConfig); // Ajuste para compatibilidade
        } catch (javax.servlet.ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            wicketFilter.doFilter((javax.servlet.ServletRequest) request, (javax.servlet.ServletResponse) response, (javax.servlet.FilterChain) chain); // Passando as requisições de forma direta
        } catch (javax.servlet.ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        wicketFilter.destroy();
    }
}
