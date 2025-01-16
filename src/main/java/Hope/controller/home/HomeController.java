package Hope.controller.home;

import Hope.model.Tool;
import Hope.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @RequestMapping("/home")
    public String home(Principal principal, Model model) {
        if (principal != null) {
            User user = homeService.getUser(principal.getName());
            model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        } else {
            return "redirect:/login";
        }

        List<Tool> dataList = homeService.getPreviewsData();
        model.addAttribute("dataList", dataList);

        return "home";
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("query") String query,
            Principal principal,
            Model model
    ) {
        if (principal != null) {
            User user = homeService.getUser(principal.getName());
            model.addAttribute("username", user.getFirstName() + " " + user.getLastName());
        } else {
            return "redirect:/login";
        }

        if (query == null || query.isEmpty()) {
            return "redirect:/home";
        }

        List<Tool> searchResults = homeService.searchData(query);
        model.addAttribute("dataList", searchResults);
        model.addAttribute("query", query);

        return "home";
    }
}



/*
@Controller
@RequestMapping("/home")
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService) {
        this.homeService = homeService;
    }

    @GetMapping
    public ResponseEntity<?> home(Principal principal, Model model) {
        try {
            if (principal == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Veuillez vous connecter.");
            }
            User user = homeService.getUser(principal.getName());
            model.addAttribute("username", user.getFirstName() + " " + user.getLastName());

            List<Tool> dataList = homeService.getPreviewsData();
            model.addAttribute("dataList", dataList);

            return ResponseEntity.ok().body("Page chargée avec succès.");
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (DataNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur.");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("query") String query, Principal principal, Model model) {
        try {
            if (principal == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Veuillez vous connecter.");
            }
            if (query == null || query.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La requête de recherche ne peut pas être vide.");
            }

            User user = homeService.getUser(principal.getName());
            model.addAttribute("username", user.getFirstName() + " " + user.getLastName());

            List<Tool> searchResults = homeService.searchData(query);
            model.addAttribute("dataList", searchResults);
            model.addAttribute("query", query);

            return ResponseEntity.ok().body("Résultats de recherche chargés avec succès.");
        } catch (UserNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (DataNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne du serveur.");
        }
    }
}


*/
