package Hope.controller.home;

import Hope.model.Tool;
import Hope.model.ToolRepository;
import Hope.model.User;
import Hope.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    private final UserRepository userRepository;
    private final ToolRepository toolRepository;

    @Autowired
    public HomeService(UserRepository userRepository, ToolRepository toolRepository) {
        this.userRepository = userRepository;
        this.toolRepository = toolRepository;
    }

    public User getUser(String username) {
        return userRepository.findUserByUsername(username).orElse(null);
    }

    public List<Tool> getPreviewsData() {
        return toolRepository.findAll();
    }

    public List<Tool> searchData(String query) {
        return toolRepository.search(query);
    }
}
