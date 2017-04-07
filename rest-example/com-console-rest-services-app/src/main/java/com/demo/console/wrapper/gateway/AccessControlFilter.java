package com.demo.console.wrapper.gateway;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Implements Server-Side Access Control for Cross-Origin Resource Sharing.
 */
public class AccessControlFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // Nothing to initialize.
  }

  @Override
  public void destroy() {
    // Nothing to destroy.
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    httpResponse.setHeader("Access-Control-Allow-Origin", "*");
    httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
    httpResponse.setHeader("Access-Control-Allow-Headers", "Authorization,Content-Type,Accept");
    httpResponse.setHeader("Content-Type", "application/json");
    chain.doFilter(request, response);
  }
}
