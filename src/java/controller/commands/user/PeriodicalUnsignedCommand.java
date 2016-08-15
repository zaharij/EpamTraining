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
public class PeriodicalUnsignedCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        nameStatusCommand.execute(request, response);
        if (request.getParameter("subject") != null){
            sessionUser.setAttribute("subject", Command.decodeParameter(request.getParameter("subject")));
        }
        if (!homePageCommand.isChangingLanguage()){
            sessionUser.setAttribute("articleName", Command.decodeParameter(request.getParameter("articleName")));
        }
        request.setAttribute("titlePeriodical", sessionUser.getAttribute("subject"));
        request.setAttribute("currentArticleTitle", sessionUser.getAttribute("articleName"));
        request.setAttribute("review", articlesService.getAboutArticleText((String) sessionUser.getAttribute("articleName")));
        request.setAttribute("pricePeriodical", periodicalsService.getPeriodicalPrice((String) sessionUser.getAttribute("subject")));
        request.setAttribute("authNum", periodicalsService.getAuthorsNumberBySubject((String) sessionUser.getAttribute("subject")));
        request.setAttribute("articlesNum", periodicalsService.getArticlesNumberBySubject((String) sessionUser.getAttribute("subject")));
        request.setAttribute("followersNum", periodicalsService.getFollowersNumberBySubject((String) sessionUser.getAttribute("subject")));
        request.setAttribute("articles", articlesService.getArticleNamesByPeriodical((String) sessionUser.getAttribute("subject")));
        request.setAttribute("monthesCost", periodicalsService.getPeriodicalsMonthesCost((String) sessionUser.getAttribute("subject")));
        homePageCommand.setChangingLanguage(false);
        return "periodicalunsigned_" + sessionUser.getAttribute("language").toString().toLowerCase() + ".jsp";
    }
}
