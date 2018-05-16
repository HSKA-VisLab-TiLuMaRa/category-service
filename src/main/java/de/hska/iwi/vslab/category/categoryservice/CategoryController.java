package de.hska.iwi.vslab.category.categoryservice;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CategoryController {

	@Autowired
	private CategoryRepo repo;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Category>> getCategorys(@RequestParam Optional<String> name) {
		// Iterable<Category> allPolls = repo.findAll();
		Iterable<Category> allPolls;
		if (name.isPresent()){
			allPolls = repo.findCategoriesByName(name.get());
		} else {
			allPolls = repo.findAll();
		}
		return new ResponseEntity<Iterable<Category>>(allPolls, HttpStatus.OK);
	}

	@RequestMapping(value = "/categories", method = RequestMethod.POST)
	public ResponseEntity<?> addCategory(@RequestBody Category category) {
		category = repo.save(category);
		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newCategoryUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();
		responseHeaders.setLocation(newCategoryUri);
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.GET)
	public ResponseEntity<Category> getCategory(@PathVariable Long categoryId) {
		Category category = repo.findById(categoryId).orElse(null);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}

	@RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCategory(@RequestBody Category category) {
		category = repo.save(category);
		// Set the location header for the newly created resource
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newCategoryUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();
		responseHeaders.setLocation(newCategoryUri);
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/categories/{categoryId}", method = RequestMethod.DELETE)
	public ResponseEntity<Category> deleteUser(@PathVariable Long categoryId) {
		repo.deleteById(categoryId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
