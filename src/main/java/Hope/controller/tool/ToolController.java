package Hope.controller.tool;

import Hope.controller.feedback.FeedbackService;
import Hope.controller.home.HomeService;
import Hope.model.Tool;
import Hope.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ToolController {

    private final ToolService toolService;
    private final FeedbackService feedbackService;
    private final HomeService homeService;

    @Autowired
    public ToolController(ToolService toolService, FeedbackService feedbackService, HomeService homeService) {
        this.toolService = toolService;
        this.feedbackService = feedbackService;
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/mainData")
    public String showAllMainData(Model model) {
        List<Tool> dataList = toolService.getAllMainTool();
        model.addAttribute("dataList", dataList);
        return "home";
    }

    @GetMapping("/details/{id}")
    public String getDataById(@PathVariable int id, Model model, Principal principal) {
        Optional<Tool> data = toolService.getTool(id);
        User user = homeService.getUser(principal.getName());
        if (user == null) {
            return "redirect:/login";
        }
        if (data.isPresent()) {
            Tool dataObj = data.get();
            model.addAttribute("data", dataObj);
            model.addAttribute("comments", feedbackService.getComments(id));
            model.addAttribute("isAdmin", user.getRole().equals("admin"));
        }

        return "details";
    }

    @GetMapping("/update/{id}")
    public String updateDataById(@PathVariable int id, Model model) {
        Optional<Tool> data = toolService.getTool(id);
        if (data.isPresent()) {
            Tool dataObj = data.get();
            model.addAttribute("data", dataObj);
        }

        return "update";
    }

    @PostMapping("/updateData/{id}")
    public String updateData(@PathVariable int id, @ModelAttribute Tool dataObj) {
        dataObj.setId(id);
        toolService.updateTool(dataObj);

        return "redirect:/details/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteDataById(@PathVariable int id) {
        toolService.deleteTool(id);

        return "redirect:/mainData";
    }

}


/*
@Controller
@RequestMapping("/")
public class ToolController {

    private final ToolService toolService;
    private final FeedbackService feedbackService;
    private final HomeService homeService;

    @Autowired
    public ToolController(ToolService toolService, FeedbackService feedbackService, HomeService homeService) {
        this.toolService = toolService;
        this.feedbackService = feedbackService;
        this.homeService = homeService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/mainData")
    public ResponseEntity<?> showAllMainData() {
        try {
            List<Tool> dataList = toolService.getAllMainTool();
            return ResponseEntity.ok(dataList);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération des outils principaux.");
        }
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<?> getDataById(@PathVariable int id, Principal principal) {
        try {
            if (principal == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Veuillez vous connecter.");
            }
            User user = homeService.getUser(principal.getName());
            Optional<Tool> data = toolService.getTool(id);

            if (data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Outil introuvable.");
            }

            Tool dataObj = data.get();
            return ResponseEntity.ok(new Object() {
                public final Tool tool = dataObj;
                public final List<?> comments = feedbackService.getComments(id);
                public final boolean isAdmin = user.getRole().equals("admin");
            });
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération des détails de l'outil.");
        }
    }

    @GetMapping("/update/{id}")
    public ResponseEntity<?> updateDataById(@PathVariable int id) {
        try {
            Optional<Tool> data = toolService.getTool(id);

            if (data.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Outil introuvable pour mise à jour.");
            }

            return ResponseEntity.ok(data.get());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la récupération des données pour mise à jour.");
        }
    }

    @PostMapping("/updateData/{id}")
    public ResponseEntity<?> updateData(@PathVariable int id, @RequestBody Tool dataObj) {
        try {
            dataObj.setId(id);
            toolService.updateTool(dataObj);
            return ResponseEntity.ok("Outil mis à jour avec succès.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour de l'outil.");
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteDataById(@PathVariable int id) {
        try {
            toolService.deleteTool(id);
            return ResponseEntity.ok("Outil supprimé avec succès.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression de l'outil.");
        }
    }
}

*/
