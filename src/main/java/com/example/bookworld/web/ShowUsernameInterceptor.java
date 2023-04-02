package com.example.bookworld.web;

import com.example.bookworld.Models.Entities.User;
import com.example.bookworld.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.juli.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Component
public class ShowUsernameInterceptor implements HandlerInterceptor {
    private final Logger LOG = LoggerFactory.getLogger(ShowUsernameInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }


    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception {

        Principal principal = request.getUserPrincipal();

        if(principal == null) {
            String anonymousUser = "anonymous";
            LOG.info("Request {} invoked postHandle from: "+ anonymousUser, request.getRequestURL());
        } else {
            LOG.info("Request {} invoked postHandle from: "+ principal.getName(), request.getRequestURL());
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
