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
public class ViewArticleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionAdmin = request.getSession(false);
        sessionAdmin.setAttribute("articlesNotPermittedName", Command.decodeParameter(request.getParameter("articlesNotPermitted")));
        request.setAttribute("articleAuthorNameField", userService.getAuthorFullName((String) sessionAdmin.getAttribute("articlesNotPermittedName")));
        request.setAttribute("articleDateField", articlesService.getArticleDate((String) sessionAdmin.getAttribute("articlesNotPermittedName")));
        request.setAttribute("articleNameField", sessionAdmin.getAttribute("articlesNotPermittedName"));
        request.setAttribute("articleReviewField", articlesService.getAboutArticleText((String) sessionAdmin.getAttribute("articleName")));
        request.setAttribute("articleTextField", articlesService.getArticleText((String) sessionAdmin.getAttribute("articlesNotPermittedName")));
        if (sessionAdmin.getAttribute("language").toString().toLowerCase().equals("eng")){
            request.setAttribute("permitUnchecked", "Permit");
            request.setAttribute("deleteUnchecked", "Delete");
        } else if (sessionAdmin.getAttribute("language").toString().toLowerCase().equals("ukr")){
            request.setAttribute("permitUnchecked", "Дозволити");
            request.setAttribute("deleteUnchecked", "Видалити");
        }
        return adminPageCommand.execute(request, response);
    }
}
