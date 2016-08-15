/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.command.factory;

import controller.commands.Command;
import controller.commands.user.*;
import controller.commands.superadmin.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Zakhar
 */
public class CommandFactory {
    private Map<String, Command> commandMap = new HashMap<String, Command>()
    {{
        put("registerUser", new RegisterFormCommand());
        put("startPage", new HomePageCommand());
        put("registerDo", new RegisterCommand());
        put("loginUser", new LoginCommand());
        put("logout", new LogoutCommand());
        put("moreAboutPeriodical", new PeriodicalUnsignedCommand());
        put("getAcquainted", new PeriodicalUnsignedCommand());
        put("openPeriodical", new PeriodicalPageCommand());
        put("openArticle", new ArticlePageCommand());
        put("backToUser", new UserPageCommand());
        put("backToPeriodicals", new PeriodicalPageCommand());
        put("replenishAccount", new ReplenishAccountPageCommand());
        put("replenish", new ReplenishCommand());
        put("signeUp", new SignedUpPeriodicalCommand());
        put("user", new HomePageCommand());
        put("choosePeriodicalToWriteArticle", new WriteArticlePageCommand());
        put("sendArticle", new WriteArticleCommand());
        put("viewUncheckedArticle", new UncheckedArticlePageCommand());
        put("permitArticleUser", new PermitArticleUserCommand());
        put("deleteUnpermitedArticleUser", new DeleteUnpermitedArticleUserCommand());
        put("loginAdmin", new LoginSuperAdminCommand());
        put("logoutAdmin", new LogoutSuperAdminCommand());
        put("createPeriodical", new SetPeriodicalCommand());
        put("superadmin", new LoginPageCommand());
        put("language", new LanguageCommand());
        put("deletePeriodical", new DeletePeriodicalCommand());
        put("unsetAdmin", new SetAdminFalseCommand());
        put("deleteUser", new DeleteUserCommand());
        put("setAdmin", new SetAdminTrueCommand());
        put("blockUser", new BlockUserCommand());
        put("unblockUser", new UnblockUserCommand());
        put("deletePeriodicalArticle", new DeletePeriodicalArticlePageCommand());
        put("deleteArticle", new DeleteArticleCommand());
        put("permitArticle", new PermitArticleCommand());
        put("deleteUnpermitedArticle", new DeleteUnpermitedArticleCommand());
        put("viewArticle", new ViewArticleCommand());
        put("payForArticles", new PayForArticlesCommand());
        put("userManagement", new UserManagementCommand());
        put("periodicalsManagement", new AdminPageCommand());
    }};

    /**
     * getCommand
     * get command from request
     * @param request
     * @param response
     * @return current command object
     * @throws ServletException
     * @throws IOException 
     */
    public Command getCommand (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{        
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            if (commandMap.containsKey(entry.getKey())){
                return commandMap.get(entry.getKey());
            } else if (entry.getKey().startsWith("language")){
                return commandMap.get("language");
            }        
        }
        return null;
    }

    /**
     * @return the login
     */
    public Command getLogin() {
        return commandMap.get("loginUser");
    }

    /**
     * @return the homePage
     */
    public Command getHomePage() {
        return commandMap.get("startPage");
    }

    /**
     * @return the language
     */
    public Command getLanguage() {
        return commandMap.get("language");
    }

    /**
     * @return the loginAdmin
     */
    public Command getLoginAdmin() {
        return commandMap.get("loginAdmin");
    }

    /**
     * @return the loginPage
     */
    public Command getLoginPage() {
        return commandMap.get("superadmin");
    }

    /**
     * @return the registerForm
     */
    public Command getRegisterForm() {
        return commandMap.get("registerUser");
    }

    /**
     * @return the register
     */
    public Command getRegister() {
        return commandMap.get("registerDo");
    }
    
}
