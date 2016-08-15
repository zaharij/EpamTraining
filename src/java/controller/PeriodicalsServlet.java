/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.command.factory.CommandFactory;
import controller.commands.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *PeriodicalsServlet
 * @author Zakhar
 */
public class PeriodicalsServlet extends HttpServlet {
    private CommandFactory userCommandFactory = new CommandFactory();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        if(sessionUser.getAttribute("language") == null){
            sessionUser.setAttribute("language", "ENG".toLowerCase());
        }
        Command command = userCommandFactory.getCommand(request, response);
        if (command != null){
            forward(command.execute(request, response), request, response);
        } else {
            forward("/error_" + sessionUser.getAttribute("language").toString().toLowerCase() + ".jsp", request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
    
    public void forward (String redirectTo, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(redirectTo).forward(request, response);
    }
}
