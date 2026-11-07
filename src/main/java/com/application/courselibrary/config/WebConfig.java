package com.application.courselibrary.config;

import com.application.courselibrary.entity.Author;
import com.application.courselibrary.entity.Category;
import com.application.courselibrary.entity.Publisher;
import com.application.courselibrary.repository.AuthorRepository;
import com.application.courselibrary.repository.CategoryRepository;
import com.application.courselibrary.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.util.Locale;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addFormatter(new Formatter<Category>() {
            @Override
            public Category parse(String text, Locale locale) throws ParseException {
                try {
                    return categoryRepository
                            .findById(Long.valueOf(text.trim()))
                            .orElse(null);
                } catch (NumberFormatException e) {
                    throw new ParseException("Invalid category ID: " + text, 0);
                }
            }

            @Override
            public String print(Category category, Locale locale) {
                return category.getId() != null
                        ? String.valueOf(category.getId())
                        : "";
            }
        });

        registry.addFormatter(new Formatter<Author>() {
            @Override
            public Author parse(String text, Locale locale) throws ParseException {
                try {
                    return authorRepository
                            .findById(Long.valueOf(text.trim()))
                            .orElse(null);
                } catch (NumberFormatException e) {
                    throw new ParseException("Invalid author ID: " + text, 0);
                }
            }

            @Override
            public String print(Author author, Locale locale) {
                return author.getId() != null
                        ? String.valueOf(author.getId())
                        : "";
            }
        });

        registry.addFormatter(new Formatter<Publisher>() {
            @Override
            public Publisher parse(String text, Locale locale) throws ParseException {
                try {
                    return publisherRepository
                            .findById(Long.valueOf(text.trim()))
                            .orElse(null);
                } catch (NumberFormatException e) {
                    throw new ParseException("Invalid publisher ID: " + text, 0);
                }
            }

            @Override
            public String print(Publisher publisher, Locale locale) {
                return publisher.getId() != null
                        ? String.valueOf(publisher.getId())
                        : "";
            }
        });
    }
}