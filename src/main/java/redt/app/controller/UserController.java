package redt.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import redt.app.model.User;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class UserController {
	@GetMapping
	public ModelAndView showForm() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}

	@PostMapping
	public ModelAndView checkValidation(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
		if (bindingResult.hasFieldErrors()) {
			ModelAndView modelAndView = new ModelAndView("index");
			System.out.println(bindingResult);
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView("result");
		System.out.println(bindingResult);
		return modelAndView;
	}
}
