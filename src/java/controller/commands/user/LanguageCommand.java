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
public class LanguageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String page = null;
        session.setAttribute("language", request.getParameter(request.getParameterNames().nextElement()));
        if (request.getParameter("languageUser") != null){
            homePageCommand.setChangingLanguage(true);
            page = userPageCommand.execute(request, response);   
        }else if (request.getParameter("languageRegister") != null){
            page = registerFormCommand.execute(request, response); 
        } else if (request.getParameter("languagePeriodicalunsigned") != null){
            homePageCommand.setChangingLanguage(true);
            page = periodicalUnsignedCommand.execute(request, response);
        }else if (request.getParameter("languagePeriodical") != null){
            homePageCommand.setChangingLanguage(true);
            page = articlePageCommand.execute(request, response);
        } else if (request.getParameter("languageMain") != null){
            page = homePageCommand.execute(request, response);
        } else if (request.getParameter("languageReplenish") != null){
            page = replenishAccountPageCommand.execute(request, response);
        } else if (request.getParameter("languageWriteArticle") != null){
            page = writeArticlePageCommand.execute(request, response);
        } else if (request.getParameter("languageAdmin") != null){
            homePageCommand.setChangingLanguage(true);
            session.setAttribute("language", request.getParameter("languageAdmin"));
            page = adminPageCommand.execute(request, response);
        } else if (request.getParameter("languageAdminUserManagement") != null){
            session.setAttribute("language", request.getParameter("languageAdminUserManagement"));
            page = userManagementCommand.execute(request, response);
        } else if (request.getParameter("languageAdminlogin") != null) {
            session.setAttribute("language", request.getParameter("languageAdminlogin"));
            page = loginPageCommand.execute(request, response);
        } 
        return page;
    }  
}
