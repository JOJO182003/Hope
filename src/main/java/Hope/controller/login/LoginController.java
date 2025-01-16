package Hope.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/login")
    public String login() {
        return "loginPage";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "loginPage";
    }

    @PostMapping("/do-login")
    public String processLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model
    ) {
        System.out.println("processLogin Controller: " + username);
        if (loginService.login(username, password)) {
            System.out.println("Login successful.");
            return "redirect:/home";
        } else {
            System.out.println("Login failed.");
            model.addAttribute("loginError", true);
            return "loginPage";
        }

    }
}

/*
@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/login")
    public String login() {
        return "loginPage";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "loginPage";
    }

    @PostMapping("/do-login")
    public ResponseEntity<?> processLogin(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Model model
    ) {
        try {
            System.out.println("processLogin Controller: " + username);
            if (loginService.login(username, password)) {
                System.out.println("Login successful.");
                return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/home").build();
            } else {
                System.out.println("Login failed.");
                model.addAttribute("loginError", true);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nom d'utilisateur ou mot de passe incorrect.");
            }
        } catch (Exception ex) {
            System.out.println("Login error: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur inattendue s'est produite.");
        }
    }
}

*/
