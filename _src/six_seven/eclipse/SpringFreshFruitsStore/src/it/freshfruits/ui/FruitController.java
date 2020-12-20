package it.freshfruits.ui;

import it.freshfruits.application.repository.FruitTypeRepository;
import it.freshfruits.domain.vo.FruitMap;
import it.freshfruits.ui.validator.FruitValidator;
import it.freshfruits.util.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller("fruitController")
@RequestMapping("/fruit.edit.admin")
@SessionAttributes("fruit")
public class FruitController {

    @RequestMapping(method = RequestMethod.POST)
    public String processSubmit(@ModelAttribute("fruit") FruitMap fruit, BindingResult result, SessionStatus status) {
        validator.validate(fruit, result);
        if (result.hasErrors()) {
            return "userForm";
        } else {
            fruit.save();
            status.setComplete();
            return "redirect:role.list.admin";
        }
    }

    @InitBinder()
    public void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(@RequestParam(required = false, value = "id") Integer id, ModelMap model) {
        model.addAttribute(Constants.FRUIT, id == null ? new FruitMap() : fruitRepository.getFruitType(id));
        return "role/form";
    }

    @Autowired @Qualifier("fruitRepository")
    private FruitTypeRepository fruitRepository;
    
    @Autowired @Qualifier("fruitValidator")
    private FruitValidator validator;
}
