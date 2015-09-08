package pl.sylwek.controler;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.sylwek.model.Cycki;
import pl.sylwek.servis.CyckiServis;

@Controller

public class BasicControler {

	@Autowired
	private CyckiServis serwis;

	@RequestMapping(value = "/", method = GET)
	public String homePage() {
		return "index";
	}

	@RequestMapping(value = "/hello", method = GET)
	public String displayHello() {
		return "hello";
	}

	@RequestMapping(value = "/save", method = POST)
	public ModelAndView saveCycki(@ModelAttribute("cycki") Cycki cycki) {

		serwis.save(cycki);

		return new ModelAndView("redirect:/listAll.html");
	}

	@RequestMapping(value = "/registerCycki", method = GET)
	public String registerCyckiPage(@ModelAttribute("cycki") Cycki cycki, ModelMap model) {
		model.addAttribute("cycki",new Cycki());
		return "registerCycki";
	}

	@RequestMapping(value = "/listAll", method = GET)
	public String listAll(ModelMap model) {
		List<Cycki> allCycki = serwis.findAll();
		model.put("lista", allCycki);
		return "list";
	}
}
