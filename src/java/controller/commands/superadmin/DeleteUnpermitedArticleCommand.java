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
public class DeleteUnpermitedArticleCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        articlesService.deleteArticle(Command.decodeParameter(request.getParameter("articlesNotPermitted")));
        session.setAttribute("articlesNotPermittedName", null);
        return adminPageCommand.execute(request, response);
    }
}
