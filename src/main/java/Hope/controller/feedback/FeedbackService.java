package Hope.controller.feedback;

import Hope.model.Feedback;
import Hope.model.FeedbackRepository;
import Hope.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Transactional
    public void addComment(int id, User user, String comment) {
        /*
        if (user == null) {
            throw new UnauthorizedException("Utilisateur non authentifié.");
        }
        Optional<Feedback> feedback = feedbackRepository.findById(id);
        if (feedback.isEmpty()) {
            throw new FeedbackNotFoundException("Commentaire introuvable pour l'ID : " + id);
        }
        */
        feedbackRepository.addComment(user.getId(), id, comment);
    }

    public List<Feedback> getComments(int id) {
        return feedbackRepository.findAllByToolId(id);
    }
}



