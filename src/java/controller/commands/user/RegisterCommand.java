/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.user;

import controller.commands.Command;
import static controller.constants.ConstantsController.PASSWORD_SALT;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.entitys.User;

/**
 *
 * @author Zakhar
 */
public class RegisterCommand implements Command {
    User user = new User();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userService.checkRegisterImg(registerFormCommand.getImgNameId(), request.getParameter("logimg"))
                    && (request.getParameter("password").equals(request.getParameter("passwordRep")))
                    && (userService.checkRegFields(Command.decodeParameter(request.getParameter("firstName"))
                        , Command.decodeParameter(request.getParameter("lastName"))
                            , Command.decodeParameter(request.getParameter("fatherName"))
                                , request.getParameter("email"), request.getParameter("password")))){
            if (userService.loginIsFree(request.getParameter("email"))){
                return registerFormCommand.execute(request, response);
            } else {
                user = new User(Command.decodeParameter(request.getParameter("firstName")), Command.decodeParameter(request.getParameter("fatherName"))
                            , Command.decodeParameter(request.getParameter("lastName"))
                            , request.getParameter("email"), (request.getParameter("password") + PASSWORD_SALT).hashCode());
                userService.createUser(user);
                request.setAttribute("fieldSucInfo", "User " + Command.decodeParameter(request.getParameter("firstName")) 
                            + " have registered successfully, login, please!");
                return homePageCommand.execute(request, response);
            }
        } else {
            request.setAttribute("fieldFailInfo", "Incorrect values, please try again!");
            return registerFormCommand.execute(request, response);
        }
    }
}
