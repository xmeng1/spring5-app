package science.mengxin.spring5app.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import science.mengxin.spring5app.domain.Category;

/**
 * <p>Date:    26/04/18
 *
 * @author mengxin
 * @version 1.0
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription (String description);
}
