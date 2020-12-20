package it.freshfruits.ui;

import it.freshfruits.domain.vo.OrderItemImpl;
import it.freshfruits.ui.util.UiUtils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("addOrderItemController")
public class AddOrderItemController {

    @RequestMapping("/customer.add.page")
    public ModelAndView handle(@ModelAttribute("order") OrderItemImpl order, HttpServletRequest req) throws Exception {

        ModelAndView mav = new ModelAndView("redirect:items.htm");
        if (UiUtils.getOrder(req).addOrderItem(order)) {
            mav.addObject("msg", "success");
        } else {
            mav.addObject("msg", "failure");
        }
        return mav;
    }
}