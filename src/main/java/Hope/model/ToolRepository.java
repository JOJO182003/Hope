package Hope.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ToolRepository extends JpaRepository<Tool,Integer> {

/*
    @Query(value = "SELECT * FROM tool d WHERE " +
            "LOWER(Titre) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(Domaine) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(Description_simple) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)*/
    List<Tool> search(@Param("query") String query);

}
