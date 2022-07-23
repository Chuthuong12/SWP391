/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import model.Role.Type;
import model.Account;
import org.reflections.Reflections;
import static utils.UrlHelper.minimizeUrl;

public class RoleAndRequestMapper {

    public static HashMap<String, List<Type>> currentMapping = new HashMap();

    public RoleAndRequestMapper() {
        Reflections reflections = new Reflections();

        Set<Class<?>> servletClazzes = reflections.getTypesAnnotatedWith(AccessRole.class);

        for (Class<?> servletClazz : servletClazzes) {
            WebServlet webServlet = servletClazz.getAnnotation(WebServlet.class);
            AccessRole accessRole = servletClazz.getAnnotation(AccessRole.class);

            if (webServlet == null) {
                throw new RuntimeException(String.format("AccessRole annotation can only use for servlet {%s}", servletClazz.getSimpleName()));
            }

            for (String s : webServlet.urlPatterns()) {
                currentMapping.put(minimizeUrl(s), Arrays.asList(accessRole.roles()));
                for (Type role : accessRole.roles()) {
                    Logger.getLogger(this.getClass().getSimpleName()).info("Mapper: " + minimizeUrl(s) + " , " + role.name());
                }
            }
        }
    }

    public void register(String url, List<Type> roles) {
        Logger.getLogger(this.getClass().getSimpleName()).info("Register: " + url);
        currentMapping.put(url, roles);
    }

    public boolean isAllowAnyOneToAccess(String url) {
        List<Type> allowedType = currentMapping.get(minimizeUrl(url));
        String key = "";
        if (allowedType == null || allowedType.isEmpty()) {
            
            //http://localhost:8080/onlinelearn/about.html
            
             key = url.replace("http://localhost:8084/SWP-home/", "http://localhost:8084/SWP-home/home");
            
            if (key == null || key.isEmpty()) return true;
            
            if (key.contains("?")) key = key.substring(0, key.indexOf("?"));
            
            Logger.getLogger(this.getClass().getSimpleName()).info("Checking: " + key + ": " + currentMapping.get(key));
            allowedType = currentMapping.get(key);
        }
        
        boolean result = allowedType == null  || allowedType.equals("null")|| allowedType.isEmpty();
        Logger.getLogger(this.getClass().getSimpleName()).info("Checking aaa: " + " key: " + key + "Url :" +url+ " :" + result);
        return result;
    }

    public boolean isAllowToAccess(Account account, String url) {
        if (isAllowAnyOneToAccess(url)) {
            List<Type> allowedType = currentMapping.get(minimizeUrl(url));
            if (allowedType == null || allowedType.isEmpty()) {
                String key = url.replace("http://localhost:8084/SWP-home/home", "");
            if (key == null || key.isEmpty()) return true;
                Logger.getLogger(this.getClass().getSimpleName()).info("Checking: " + key);
                if (key.indexOf("?") >= 0) {
                    key = key.substring(0, key.indexOf("?"));
                }
                allowedType = currentMapping.get(key);
            }
        }
        List<Type> allowedType = currentMapping.get(minimizeUrl(url));
        for (Type type : allowedType) {
            if (type == getRoleTypeById(account.getRole().getRole_id())) {
                return true;
            }
        }

        return false;
    }

    public Type getRoleTypeById(int roleId) {
        switch (roleId) {
            case 1:
                return Type.admin;
            case 2:
                return Type.customer;
            case 3:
                return Type.expert;
            case 4:
                return Type.sale;
            case 5:
                return Type.marketing;
            default:
                throw new RuntimeException("Invalid role id, roleId = " + roleId);
        }
    }
}
