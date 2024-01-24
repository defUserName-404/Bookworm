package com.defusername.bookworm.service.implementation;

import com.defusername.bookworm.entity.Author;
import com.defusername.bookworm.repository.AuthorRepository;
import com.defusername.bookworm.service.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorServiceImplementation implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	public List<Author> getAllAuthors() {
		return authorRepository.findAll();
	}

	public Optional<Author> getAuthorById(Long id) {
		return authorRepository.findById(id);
	}

	public Author addNewAuthor(Author author) {
		return authorRepository.save(author);
	}

	public Author updateAuthor(Author updatedAuthor, Long id) {
		Author existingAuthor = authorRepository.findById(id)
												.orElseThrow();
		existingAuthor.setName(updatedAuthor.getName());
		existingAuthor.setDescription(updatedAuthor.getDescription());
		existingAuthor.getBooks()
					  .clear();
		existingAuthor.getBooks()
					  .addAll(updatedAuthor.getBooks());

		return existingAuthor;
	}

	public boolean deleteAuthor(Long id) {
		if (authorRepository.existsById(id)) {
			authorRepository.deleteById(id);
			return true;
		}
		return false;
	}

}
