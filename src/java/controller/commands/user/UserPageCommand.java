/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.user;

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
public class UserPageCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        nameStatusCommand.execute(request, response);
        request.setAttribute("periodicals", periodicalsService.getAllPeriodicalsNames());
        request.setAttribute("periodicalsPayed", periodicalsService.getAllPeriodicalsNames((String) sessionUser.getAttribute("email")));
        request.setAttribute("authNum", userService.getAuthorsNumber());       
        if (request.getParameter("backToUser") != null){
            sessionUser.setAttribute("articlesNotPermittedName", null);
        }
        if (homePageCommand.isChangingLanguage() && sessionUser.getAttribute("articlesNotPermittedName") != null){
            uncheckedArticlePageCommand.execute(request, response);
        }
        if ((boolean) sessionUser.getAttribute("isAdmin")){
            request.setAttribute("articlesNotPermitted", articlesService.getNotPermittedArticles());;
        }
        homePageCommand.setChangingLanguage(false);
        return "user_" + sessionUser.getAttribute("language").toString().toLowerCase() + ".jsp";
    }
}
