package it.freshfruits.ui.util;

import it.freshfruits.domain.entity.Customer;
import it.freshfruits.domain.entity.Order;
import it.freshfruits.util.Constants;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestUtils;

public class UiUtils {

    public static Customer getCustomer(HttpServletRequest req) {
        return (Customer) req.getAttribute(Constants.CUSTOMER);
    }

    public static Order getOrder(HttpServletRequest req) {
        return getCustomer(req).getOrder();
    }

    public static int getNumPage(HttpServletRequest req) {

        return ServletRequestUtils.getIntParameter(req, Constants.PAGE_NUMBER, 0);
    }

}
