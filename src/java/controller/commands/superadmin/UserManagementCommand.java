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
public class UserManagementCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionAdmin = request.getSession(false);
        request.setAttribute("loginField", sessionAdmin.getAttribute("login"));
        request.setAttribute("admins", userService.getAdminUsers());
        request.setAttribute("usersSimple", userService.getSimpleUsers());
        request.setAttribute("usersAll", userService.getAllUsers());
        request.setAttribute("usersUnblocked", userService.getUnblockedUsers());
        request.setAttribute("usersBlocked", userService.getBlockedUsers());
        return "superadminuser_" + sessionAdmin.getAttribute("language").toString().toLowerCase() + ".jsp";
    }
}
