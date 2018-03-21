package com.blog.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_article", schema = "blog", catalog = "")
public class Article {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false,  length = 100)
    private String title;
    
    @Column(name = "content", nullable = false,  length = 100)
    private String content;
    
    @Column(name = "pub_date", nullable = false)
    private Date pub_date;
    
	@ManyToOne
	@JoinColumn(name="user_id", nullable = false)
	@Basic(fetch=FetchType.LAZY)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="articletype_id", nullable = false)
	@Basic(fetch=FetchType.LAZY)
	private ArticleType articletype;
	
    @OneToMany(mappedBy="article",cascade={CascadeType.ALL})   
    private Set<Reply> reply = new HashSet<Reply>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPub_date() {
		return pub_date;
	}

	public void setPub_date(Date pub_date) {
		this.pub_date = pub_date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArticleType getArticletype() {
		return articletype;
	}

	public void setArticletype(ArticleType articletype) {
		this.articletype = articletype;
	}

	public Set<Reply> getReply() {
		return reply;
	}

	public void setReply(Set<Reply> reply) {
		this.reply = reply;
	}
    
    
}
