package kz.kdlolymp.springcallkomek.service;

import kz.kdlolymp.springcallkomek.entity.Article;
import kz.kdlolymp.springcallkomek.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ArticleService {
    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private ArticleRepository articleRepository;

    public Article getCityArticle(int cityId, int typeId, String language) {
        Article article = manager.createQuery("SELECT a FROM Article a WHERE a.city.id = :paramCity AND a.knowledgeType.id = :paramType " +
            "AND a.language = :paramLanguage", Article.class).setParameter("paramCity", cityId).setParameter("paramType", typeId)
            .setParameter("paramLanguage", language).getSingleResult();
        if(article.getText()!=null) {
            int l = article.getText().length();
            if (l > 200) {
                l = 200;
            }
            System.out.println("Article getCityArticle: " + article.getText().substring(0, l));
        } else {
            System.out.println("Article getCityArticle: have not article ");
        }

        return article;
    }


    public List<Article> getArticlesByText(String text) {
        String[] words = text.split(" ", 5);
        String addSelect = "";
        for (int i = 0; i < words.length; i++) {
            if(i>0) {
                addSelect += " AND";
            }
            addSelect += " a.text LIKE '%" + words[i] + "%'";
        }
        List<Article> articles = manager.createQuery("SELECT a FROM Article a WHERE" + addSelect, Article.class).getResultList();
        return articles;
    }
}
