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
public class ArticlePageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        if (request.getParameter("subjectOpen") != null){
            sessionUser.setAttribute("articles", Command.decodeParameter(request.getParameter("subjectOpen")));
        }
        if (!homePageCommand.isChangingLanguage()){
            sessionUser.setAttribute("articleName", Command.decodeParameter(request.getParameter("articleName")));
        }
        request.setAttribute("articleAuthorNameField", userService.getAuthorFullName((String) sessionUser.getAttribute("articleName")));
        request.setAttribute("articleDateField", articlesService.getArticleDate((String) sessionUser.getAttribute("articleName")));
        request.setAttribute("articleNameField", sessionUser.getAttribute("articleName"));
        request.setAttribute("articleReviewField", articlesService.getAboutArticleText((String) sessionUser.getAttribute("articleName")));
        request.setAttribute("articleTextField", articlesService.getArticleText((String) sessionUser.getAttribute("articleName")));
        homePageCommand.setChangingLanguage(false);
        return periodicalPageCommand.execute(request, response);
    }
}
