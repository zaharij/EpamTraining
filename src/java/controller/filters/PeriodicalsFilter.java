/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filters;

import controller.PeriodicalsServlet;
import controller.command.factory.CommandFactory;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Zakhar
 */
public class PeriodicalsFilter implements Filter {
    private CommandFactory commandFactory = new CommandFactory();
    private PeriodicalsServlet periodicalsServlet = new PeriodicalsServlet();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = null;
        session = httpRequest.getSession(false);
        if(session.getAttribute("language") == null){
            session.setAttribute("language", "ENG".toLowerCase());
        }
        if (session != null){
            if (session.getAttribute("isLogined") != null && (boolean)session.getAttribute("isLogined")){
                chain.doFilter(request, response);
                return;
            } else {
                if (httpRequest.getParameterNames().nextElement().startsWith("language")){
                    periodicalsServlet.forward(commandFactory.getLanguage().execute(httpRequest, httpResponse), httpRequest, httpResponse);
                } else if (httpRequest.getParameter("registerUser") != null){
                    periodicalsServlet.forward(commandFactory.getRegisterForm().execute(httpRequest, httpResponse), httpRequest, httpResponse);
                } else if (httpRequest.getParameter("registerDo") != null){
                    periodicalsServlet.forward(commandFactory.getRegister().execute(httpRequest, httpResponse), httpRequest, httpResponse);
                } else if (httpRequest.getParameterMap().containsKey("email") && httpRequest.getParameterMap().containsKey("password")){
                    periodicalsServlet.forward(commandFactory.getLogin().execute(httpRequest, httpResponse), httpRequest, httpResponse);
                } else {
                    periodicalsServlet.forward(commandFactory.getHomePage().execute(httpRequest, httpResponse), httpRequest, httpResponse);
                }
            }
        } else {
            periodicalsServlet.forward("/error_" + session.getAttribute("language").toString().toLowerCase() + ".jsp", httpRequest, httpResponse);
        }
    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
