package com.blog.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_articletype", schema = "blog", catalog = "")
public class ArticleType {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "type", nullable = false,  length = 30)
    private String type;
    
    @OneToMany(mappedBy="articletype",cascade={CascadeType.ALL})   
    private Set<Article> article = new HashSet<Article>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Article> getArticle() {
		return article;
	}

	public void setArticle(Set<Article> article) {
		this.article = article;
	}
    
    
}
