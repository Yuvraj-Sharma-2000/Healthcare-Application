package com.example.had.entity;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.UUID;

@Entity(name = "articles")
@Table(
        name = "articles"
)
public class articles {
    @Id
    @SequenceGenerator(
            name = "article_sequence",
            sequenceName = "article_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = UUID,
            generator = "article_sequence"
    )
    @Column(
            name = "article_id"
    )
    private String id;


    @Column(
            name = "article_type",
            nullable = true
    )
    private String articleType;


    @Column(
            name = "article_week",
            nullable = false
    )
    private String articleWeek;


    @Column(
            name = "article_session",
            nullable = false
    )
    private String articleSession;


    @Column(
            name = "article_link",
            nullable = false
    )
    private String articleLink;


    @Column(
            name = "article_data",
            nullable = false
    )
    private String articleData;

    public articles() {
    }

    public articles(String articleType, String articleWeek, String articleSession, String articleLink, String articleData) {
        this.articleType = articleType;
        this.articleWeek = articleWeek;
        this.articleSession = articleSession;
        this.articleLink = articleLink;
        this.articleData = articleData;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticleWeek() {
        return articleWeek;
    }

    public void setArticleWeek(String articleWeek) {
        this.articleWeek = articleWeek;
    }

    public String getArticleSession() {
        return articleSession;
    }

    public void setArticleSession(String articleSession) {
        this.articleSession = articleSession;
    }

    public String getArticleLink() {
        return articleLink;
    }

    public void setArticleLink(String articleLink) {
        this.articleLink = articleLink;
    }

    public String getArticleData() {
        return articleData;
    }

    public void setArticleData(String articleData) {
        this.articleData = articleData;
    }

    @Override
    public String toString() {
        return "articles{" +
                "id='" + id + '\'' +
                ", articleType='" + articleType + '\'' +
                ", articleWeek='" + articleWeek + '\'' +
                ", articleSession='" + articleSession + '\'' +
                ", articleLink='" + articleLink + '\'' +
                ", articleData='" + articleData + '\'' +
                '}';
    }
}