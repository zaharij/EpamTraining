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
public class UncheckedArticlePageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        if (!homePageCommand.isChangingLanguage()){
            sessionUser.setAttribute("articlesNotPermittedName", Command.decodeParameter(request.getParameter("articlesNotPermitted")));
        }
        request.setAttribute("articleAuthorNameField", userService.getAuthorFullName((String) sessionUser.getAttribute("articlesNotPermittedName")));
        request.setAttribute("articleDateField", articlesService.getArticleDate((String) sessionUser.getAttribute("articlesNotPermittedName")));
        request.setAttribute("articleNameField", sessionUser.getAttribute("articlesNotPermittedName"));
        request.setAttribute("articleReviewField", articlesService.getAboutArticleText((String) sessionUser.getAttribute("articlesNotPermittedName")));
        request.setAttribute("articleTextField", articlesService.getArticleText((String) sessionUser.getAttribute("articlesNotPermittedName")));
        if (sessionUser.getAttribute("language").toString().toLowerCase().equals("eng")){
            request.setAttribute("permitUnchecked", "Permit");
            request.setAttribute("deleteUnchecked", "Delete");
            request.setAttribute("closeUnchecked", "Close");
        } else if (sessionUser.getAttribute("language").toString().toLowerCase().equals("ukr")){
            request.setAttribute("permitUnchecked", "Дозволити");
            request.setAttribute("deleteUnchecked", "Видалити");
            request.setAttribute("closeUnchecked", "Згорнути");
        }
        homePageCommand.setChangingLanguage(false);
        return userPageCommand.execute(request, response);
    }
    
}
