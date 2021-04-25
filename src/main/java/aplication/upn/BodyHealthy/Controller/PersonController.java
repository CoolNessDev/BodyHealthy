package aplication.upn.BodyHealthy.Controller;

import aplication.upn.BodyHealthy.Model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class PersonController {
    @GetMapping
    String getPeople(Model model){
        model.addAttribute("something", "this is coming from the controller");
        model.addAttribute("people", Arrays.asList(
                new Person("Diego",20),
                new Person("John",19),
                new Person("Monica",22),
                new Person("Sara",27)
        ));
        return "people";
    }
}
