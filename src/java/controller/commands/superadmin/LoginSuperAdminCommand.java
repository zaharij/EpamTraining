/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.superadmin;

import controller.commands.Command;
import static controller.constants.ConstantsController.PASSWORD_SALT;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zakhar
 */
public class LoginSuperAdminCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionAdmin = request.getSession(false);
        if (adminService.checkLoginFields(request.getParameter("login"), request.getParameter("password"))){
            if (adminService.checkLogin(request.getParameter("login"), (request.getParameter("password") + PASSWORD_SALT).hashCode())){
                sessionAdmin.setAttribute("isLogined", true);
                sessionAdmin.setAttribute("login", request.getParameter("login"));               
                return adminPageCommand.execute(request, response);
            } else{
                request.setAttribute("fieldFailInfo", "Password or email is not correct, please try again!");
                return loginPageCommand.execute(request, response);
            }
        } else {
            request.setAttribute("fieldFailInfo", "Incorrect values, please try again!");
            return loginPageCommand.execute(request, response);
        }
    }
}
