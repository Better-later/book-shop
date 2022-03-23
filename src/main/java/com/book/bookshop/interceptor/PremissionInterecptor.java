package com.book.bookshop.interceptor;

import com.book.bookshop.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**使用拦截器防止用户在未登录的状态下就进入到订单后者购物车界面
 * @author qianjin
 * @create 2022-03-09 22:53
 */
public class PremissionInterecptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null && user.getUsername() != null ){
            return true;
        }else{
            //未登录的状态下就返回到首页
            response.sendRedirect(request.getContextPath()+"/home/index");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
