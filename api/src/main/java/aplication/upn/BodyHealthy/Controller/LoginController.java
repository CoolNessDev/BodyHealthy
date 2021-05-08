package aplication.upn.BodyHealthy.Controller;

import aplication.upn.BodyHealthy.Model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Arrays;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(
            @RequestParam(value="eror", required = false) String error,
            Model model, Principal principal, RedirectAttributes retorno){
        if(principal!=null){
            System.out.println("ya ha iniciado sesion");
            return "redirect:/";
        }
        if(error!=null){
            System.out.println("error");
            return "error";
        }
        return "login";
    }
    @GetMapping("/login-error")
    public String loginError(){
        return "error";
    }
}
