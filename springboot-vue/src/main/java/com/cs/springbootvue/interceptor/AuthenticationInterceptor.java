package com.cs.springbootvue.interceptor;



import com.cs.springbootvue.annotation.PassToken;
import com.cs.springbootvue.entity.User;
import com.cs.springbootvue.exception.BaseException;
import com.cs.springbootvue.service.UserService;
import com.cs.springbootvue.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author yantao
 */
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = request.getHeader("token");
        System.out.println("token:       "+token);
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        //得到当前请求Handler中的方法对象
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //默认全部检查
        else {
            System.out.println("被jwt拦截需要验证");
            // 执行认证
            if (token == null) {
                throw new BaseException("无效请求,请重新登录");
            }

            // 获取 token 中的 user id
            String userId = JwtUtils.getAudience(token);

            System.out.println("JWT " + userId);

            //找找看是否有这个user   因为我们需要检查用户是否存在，读者可以自行修改逻辑
            User user = userService.getUserById(Long.parseLong(userId));
            if (user == null) {
                throw new BaseException("该用户不存在,请重新登录");
            }
            System.out.println("JWT User " + user);

            // 验证 token
            JwtUtils.verifyToken(token, userId);

            //获取载荷内容
            String userName = JwtUtils.getClaimByName(token, "userName").asString();
            System.out.println("JWT UserName " + userName);

            return true;

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

}
