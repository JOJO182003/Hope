package Hope.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepository extends JpaRepository<Feedback,Long> {


    /*@Query(value = "SELECT * FROM feedback WHERE tool_id = ?", nativeQuery = true)*/
    List<Feedback> findAllByToolId(int id);

    @Modifying
    @Query(value = "INSERT INTO feedback (user_id, tool_id, commentaire) VALUES (?, ?, ?)", nativeQuery = true)
    void addComment(long userId, int toolId, String comment);
}
