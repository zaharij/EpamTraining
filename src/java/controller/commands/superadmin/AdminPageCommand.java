/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.superadmin;

import controller.commands.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zakhar
 */
public class AdminPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionAdmin = request.getSession(false);
        request.setAttribute("loginField", sessionAdmin.getAttribute("login"));
        request.setAttribute("periodicals", periodicalsService.getAllPeriodicalsNames());
        request.setAttribute("articlesNotPermitted", articlesService.getNotPermittedArticles());
        request.setAttribute("objectList", articlesService.getNotPermittedArticles());
        return "superadmin_" + sessionAdmin.getAttribute("language").toString().toLowerCase() + ".jsp";
    }
}
