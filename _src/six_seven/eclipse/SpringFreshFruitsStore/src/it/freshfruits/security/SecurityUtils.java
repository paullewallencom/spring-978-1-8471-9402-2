package it.freshfruits.security;

import it.freshfruits.util.Constants;

import java.util.Map;

import org.springframework.security.context.SecurityContextHolder;

public class SecurityUtils {

    @SuppressWarnings("unchecked")
    public static String getIdCustomer() {
        FreshFruitUser user = (FreshFruitUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map userInfo = (Map) user.getUserInfo();
        return userInfo.get(Constants.ID_CUSTOMER).toString();

    }

    public static String getCustomerName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
