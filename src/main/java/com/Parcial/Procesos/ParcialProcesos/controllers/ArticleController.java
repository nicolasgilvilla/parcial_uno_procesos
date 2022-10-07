package com.Parcial.Procesos.ParcialProcesos.controllers;


import com.Parcial.Procesos.ParcialProcesos.models.Article;
import com.Parcial.Procesos.ParcialProcesos.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(value = "/article/{code}")
    public ResponseEntity getByCode(@PathVariable String code) {
        List<Article> listArticleForCode = articleRepository.findOneByCode(code);
        if (!listArticleForCode.isEmpty()) {
            return new ResponseEntity<>(listArticleForCode, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/article")
    public ResponseEntity createArticle(@RequestBody Article article) {
        try {
            articleRepository.save(article);
            return new ResponseEntity(article, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/articles")
    public ResponseEntity listArticle() {
        List<Article> listArticle = articleRepository.findAll();
        if (!listArticle.isEmpty()) {
            return new ResponseEntity<>(listArticle, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }



    @PutMapping(value = "/article/{code}")
    public ResponseEntity updateArticle(@PathVariable String code, @RequestBody Article article) {
        List<Article> articleFind = articleRepository.findOneByCode(code);
        try {
            if (articleFind.isEmpty()) {
                Article articlePresent = articleFind.get();
                articlePresent.setCode(article.getCode());
                articlePresent.setName(article.getName());
                articlePresent.setDescription(article.getDescription());
                articlePresent.setDateOfRegister(article.getDateOfRegister());
                articlePresent.setCategory(article.getCategory());
                articlePresent.setStock(article.getStock());
                articlePresent.setPurchasePrice(article.getPurchasePrice());
                articlePresent.setSalePrice(article.getSalePrice());
                articleRepository.save(articlePresent);
                return new ResponseEntity(articlePresent, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/article/{code}")
    public ResponseEntity deleteArticle(@PathVariable String code) {
        Optional<Article> article = articleRepository.findOneByCode(code);
        if (article.isPresent()) {
            articleRepository.delete(article.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}