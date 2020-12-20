package it.freshfruits.ui;

import it.freshfruits.domain.entity.Order;
import it.freshfruits.ui.util.UiUtils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("customerController")
public class CustomerController {

    @RequestMapping("/customer.create.page")
    public ModelAndView create(HttpServletRequest req) {
        return new ModelAndView("customer/create", "result", UiUtils.getCustomer(req).createOrder());
    }

    @RequestMapping("/customer.save.page")
    public ModelAndView save(HttpServletRequest req) {
        return new ModelAndView("customer/save", "result", UiUtils.getCustomer(req).saveOrder());
    }

    @RequestMapping("/customer.show.page")
    public ModelAndView show(HttpServletRequest req) {
        return new ModelAndView("customer/show", "customer", UiUtils.getCustomer(req));
    }

    @RequestMapping("/customer.order.page")
    public ModelAndView order(HttpServletRequest req) {
        return new ModelAndView("customer/order", "order", UiUtils.getOrder(req));
    }

    @RequestMapping("/customer.items.page")
    public ModelAndView items(HttpServletRequest req) {
        return new ModelAndView("customer/items", "items", UiUtils.getOrder(req).getOrderItems());
    }

    @RequestMapping("/customer.remove.page")
    public ModelAndView remove(@RequestParam("id") String id, HttpServletRequest req) throws Exception {

        Order order = UiUtils.getOrder(req);
        return order.removeOrderItem(order.getId().toString(), id) ? new ModelAndView("customer/items", "items", order.getOrderItems()) : new ModelAndView("customer/remove", "result", false);
    }
}
