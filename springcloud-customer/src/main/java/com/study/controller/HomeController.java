package com.study.controller;

import com.study.model.User;
import com.study.model.UserLog;
import com.study.service.UserLogService;
import com.study.service.UserService;
import com.study.service.impl.UserServiceImpl;
import com.study.util.DateFormate;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * Created by yangqj on 2017/4/21.
 */
@Controller
public class HomeController {

    static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private UserLogService userLogService;

    @Autowired
    UserService userService;

    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(){
        return "login";
    }

    /**
     * 用户登录
     * @param request
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(HttpServletRequest request, User user, Model model){
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            request.setAttribute("msg", "用户名或密码不能为空！");
            return "login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(user.getUsername(),user.getPassword());
        try {
            subject.login(token);
            insertLoginLog(request, DateFormate.date2String(new Date()), subject);
            logger.info(user.getUsername() + " 登录成功");
            return "redirect:usersPage";
        }catch (LockedAccountException lae) {
            token.clear();
            logger.error("用户已经被锁定不能登录，请与管理员联系！", lae);
            request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
            return "login";
        } catch (AuthenticationException e) {
            token.clear();
            logger.error("用户或密码不正确！", e);
            request.setAttribute("msg", "用户或密码不正确！");
            return "login";
        }
    }

    /**
     * 用户登出
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        UserLog userLog = new UserLog(user.getId());
        userLog.setLogoutTime(DateFormate.date2String(new Date()));
        String loginDate = (String) request.getSession().getAttribute("logintime");
        userLog.setLoginTime(loginDate);
        userLogService.updateLog(userLog);
        request.getSession().invalidate();
        return "login";
    }

    @RequestMapping(value={"/usersPage",""})
    public String usersPage(HttpServletRequest request){
        if(request.getSession().getAttribute("logintime") == null){
            insertLoginLog(request, DateFormate.date2String(new Date()), SecurityUtils.getSubject());
        }
        return "user/users";
    }

    /**
     * 用户登录时插入登录日志
     * @param request
     * @param loginTime
     * @param subject
     */
    private void insertLoginLog(HttpServletRequest request, String loginTime, Subject subject){
        User u = (User) subject.getPrincipal();
        UserLog userLog = new UserLog(u.getId());
        request.getSession().setAttribute("logintime", loginTime);
        userLog.setLoginTime(loginTime);
        userLogService.insertLog(userLog);
    }


    @RequestMapping("/rolesPage")
    public String rolesPage(){
        return "role/roles";
    }

    @RequestMapping("/resourcesPage")
    public String resourcesPage(){
        return "resources/resources";
    }

    @RequestMapping("/eureka")
    public String getEurekaUrl(){
        return "eureka/eureka";
    }

    @RequestMapping("/dataPage")
    public String dataPage(){
        return "data/importData";
    }

    @RequestMapping("/403")
    public String forbidden(){
        return "403";
    }

}
