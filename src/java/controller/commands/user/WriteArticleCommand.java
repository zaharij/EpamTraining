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
import model.entitys.Article;
import model.service.constants.ConfigLog;
import model.service.constants.LogSettings;
import org.apache.log4j.Logger;

/**
 *
 * @author Zakhar
 */
public class WriteArticleCommand implements Command{
    private Article article = new Article();
    private static Logger logger = Logger.getLogger(WriteArticleCommand.class);
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionUser = request.getSession(false);        
        article.setPeriodical((String) sessionUser.getAttribute("subjectToWrite"));      
        article.setTitle(Command.decodeParameter(request.getParameter("articleName")).trim());
        article.setAuthorsEmail((String) sessionUser.getAttribute("email"));
        article.setArticleAnnotation(Command.decodeParameter(request.getParameter("annotation")).trim());
        article.setArticleText(Command.decodeParameter(request.getParameter("articleText")).trim());
        article.setPermisssion((boolean) sessionUser.getAttribute("isWriter")? 1: 0);
        articlesService.setArticleToDB(article);
        ConfigLog configLog = new ConfigLog(LogSettings.LOG_PROPERTIES_FILE);
        configLog.init();
        logger.info("new article has been created: " + article.getTitle());
        return userPageCommand.execute(request, response);
    }
}
