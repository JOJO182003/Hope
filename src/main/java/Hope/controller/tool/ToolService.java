package Hope.controller.tool;

import Hope.model.Tool;
import Hope.model.ToolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToolService {

    private final ToolRepository toolRepository;

    @Autowired
    public ToolService(ToolRepository toolRepository){
        this.toolRepository = toolRepository;
    }

    public List<Tool> getAllMainTool(){
        return toolRepository.findAll();
    }

    public Optional<Tool> getTool(int id){ return toolRepository.findById(id); }

    public void deleteTool(int id){ toolRepository.deleteById(id); }

    public void updateTool(Tool data){ toolRepository.save(data); }
}


/*
@Service
public class ToolService {

    private final ToolRepository toolRepository;

    @Autowired
    public ToolService(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public List<Tool> getAllMainTool() {
        return toolRepository.findAll();
    }

    public Tool getTool(int id) {
        return toolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tool not found with ID: " + id));
    }

    public void deleteTool(int id) {
        if (!toolRepository.existsById(id)) {
            throw new ResourceNotFoundException("Tool not found with ID: " + id);
        }
        toolRepository.deleteById(id);
    }

    public void updateTool(Tool data) {
        if (!toolRepository.existsById(data.getId())) {
            throw new ResourceNotFoundException("Tool not found with ID: " + data.getId());
        }
        toolRepository.save(data);
    }
}

*/
