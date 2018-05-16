package de.hska.iwi.vslab.category.categoryservice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CategoryRepo extends CrudRepository<Category, Long> {
  @Query("SELECT t FROM Category t where t.name = :name")
  Iterable<Category> findCategoriesByName(@Param("name") String name);

  // @Query("SELECT t FROM Category t where t.productId = :productId")
  // Iterable<Category> findCategoriesByProduct(@Param("productId") Integer productId);
}
