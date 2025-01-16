package Hope.controller.signup;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignUpController {

    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @RequestMapping("/signUp")
    public String signUp() {
        return "signUpPage";
    }

    @RequestMapping("/signUp-error")
    public String signUpError(Model model) {
        model.addAttribute("signUpError", true);
        return "signUpPage";
    }

    @PostMapping("/signUp")
    public String processSignUp(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("first_name") String firstName,
            @RequestParam("last_name") String lastName,
            Model model
    ) {
        if (signUpService.signUp(username, password, firstName, lastName)) {
            return "redirect:/login";
        } else {
            model.addAttribute("signUpError", true);
            return "signUpPage";
        }

    }
}
/*
@Controller
public class SignUpController {

    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @RequestMapping("/signUp")
    public String signUp() {
        return "signUpPage";
    }

    @RequestMapping("/signUp-error")
    public String signUpError(Model model) {
        model.addAttribute("signUpError", true);
        return "signUpPage";
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> processSignUp(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("first_name") String firstName,
            @RequestParam("last_name") String lastName,
            Model model
    ) {
        try {
            if (signUpService.signUp(username, password, firstName, lastName)) {
                return ResponseEntity.status(HttpStatus.FOUND).header("Location", "/login").build();
            } else {
                model.addAttribute("signUpError", true);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de l'inscription. Veuillez vérifier vos informations.");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur inattendue s'est produite lors de l'inscription.");
        }
    }
}
*/
