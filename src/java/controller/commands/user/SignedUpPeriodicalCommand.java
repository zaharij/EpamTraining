/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.commands.user;

import controller.commands.Command;
import static controller.constants.ConstantsController.*;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zakhar
 */
public class SignedUpPeriodicalCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);
        Pattern priceReg = Pattern.compile(GET_PRICE_REG);
        Pattern monthNumberReg = Pattern.compile(GET_MONTH_NUMBER_REG);
        Matcher monthNumberMatcher = monthNumberReg.matcher(request.getParameter("monthCost"));
        Matcher priceMatcher = priceReg.matcher(request.getParameter("monthCost"));
        monthNumberMatcher.find();
        priceMatcher.find();
        double price = Double.parseDouble(priceMatcher.group());
        int monthNum = Integer.parseInt(monthNumberMatcher.group());
        double monthPrice = price / monthNum;
        periodicalsService.signedUpPeriodical((String) sessionUser.getAttribute("email")
                , (String) sessionUser.getAttribute("subject"), monthNum, price, monthPrice);
        return userPageCommand.execute(request, response);
    }
}
