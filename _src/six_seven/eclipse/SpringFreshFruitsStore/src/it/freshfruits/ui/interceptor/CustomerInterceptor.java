package it.freshfruits.ui.interceptor;

import it.freshfruits.domain.factory.CustomerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import it.freshfruits.util.Constants;

public class CustomerInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        req.setAttribute(Constants.CUSTOMER, customerFactory.getCurrentCustomer());
        return true;
    }

    @Autowired
    private CustomerFactory customerFactory;
}
