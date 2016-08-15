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
public class PeriodicalPageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        nameStatusCommand.execute(request, response);
        if (request.getParameter("subjectOpen") != null){
            sessionUser.setAttribute("subjectOpen", Command.decodeParameter(request.getParameter("subjectOpen")));
        }
        request.setAttribute("titlePeriodical", sessionUser.getAttribute("subjectOpen"));
        request.setAttribute("subscriptionDate", periodicalsService.getDateEndSubscription((String) sessionUser.getAttribute("email"), (String) sessionUser.getAttribute("subjectOpen")));
        request.setAttribute("articles", articlesService.getArticleNamesByPeriodical((String) sessionUser.getAttribute("subjectOpen")));
        
        return "periodical_" + sessionUser.getAttribute("language").toString().toLowerCase() + ".jsp";
    }
}
