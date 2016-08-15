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
import model.entitys.Periodical;

/**
 *
 * @author Zakhar
 */
public class SetPeriodicalCommand implements Command {
    private Periodical periodical;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        periodical = new Periodical();
        periodical.setTheme(Command.decodeParameter(request.getParameter("titlePeriodical")));
        periodical.setMounthPrice(Double.parseDouble(request.getParameter("pricePeriodical")));
        periodicalsService.setPeriodical(periodical);
        return adminPageCommand.execute(request, response);
    }
}
