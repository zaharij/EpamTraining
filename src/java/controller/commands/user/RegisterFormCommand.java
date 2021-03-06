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
public class RegisterFormCommand implements Command {
    private static String imgNameId;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        imgNameId = userService.getRandomLogImgName();
        request.setAttribute("logimg", getImgNameId() + ".jpg");
        return "register_" + sessionUser.getAttribute("language").toString().toLowerCase() + ".jsp";
    }

    /**
     * @return the imgNameId
     */
    public String getImgNameId() {        
        return imgNameId;
    }
}
