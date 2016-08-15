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
public class NameStatusCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        request.setAttribute("nameField", sessionUser.getAttribute("username"));
        if ((boolean) sessionUser.getAttribute("isWriter")){
            if (sessionUser.getAttribute("language").toString().toLowerCase().equals("eng")){
                request.setAttribute("isAuthorField", "[author]");
            } else if (sessionUser.getAttribute("language").toString().toLowerCase().equals("ukr")){
                request.setAttribute("isAuthorField", "[автор]");
            }
        }
        if ((boolean) sessionUser.getAttribute("isAdmin")){
            if (sessionUser.getAttribute("language").toString().toLowerCase().equals("eng")){
                request.setAttribute("isAdminField", "[admin]");
            } else if (sessionUser.getAttribute("language").toString().toLowerCase().equals("ukr")){
                request.setAttribute("isAdminField", "[адмін]");
            }
        }
        return null;
    }
    
}
