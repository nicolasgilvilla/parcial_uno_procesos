package com.Parcial.Procesos.ParcialProcesos.repository;

import com.Parcial.Procesos.ParcialProcesos.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findOneByCode(String code);

}