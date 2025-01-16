package Hope.controller.feedback;

import Hope.controller.home.HomeService;
import Hope.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class FeedbackController {

    private final FeedbackService feedbackService;
    private final HomeService homeService;

    public FeedbackController(FeedbackService feedbackService, HomeService homeService) {
        this.feedbackService = feedbackService;
        this.homeService = homeService;
    }

    @PostMapping("/addComment/{id}")
    public String addComment(@PathVariable int id, String comment, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        User user = homeService.getUser(principal.getName());
        feedbackService.addComment(id, user, comment);
        return "redirect:/details/" + id;
    }
}


/*
    @PostMapping("/addComment/{id}")
    public ResponseEntity<?> addComment(@PathVariable int id, @RequestBody String comment, Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Veuillez vous connecter pour ajouter un commentaire.");
        }
        try {
            User user = homeService.getUser(principal.getName());
            feedbackService.addComment(id, user, comment);
            return ResponseEntity.ok("Commentaire ajouté avec succès.");
        } catch (FeedbackNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (UnauthorizedException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur inattendue s'est produite.");
        }
    }

*/
