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
@Table(name = "t_user", schema = "blog", catalog = "")
public class User extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5713968420282750235L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;


    @Column(name = "name", nullable = false, unique = true, length = 45)
    private String name;
    
    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "email")
    private String email;
    
    @Column(name = "phone")
    private String phone;
    
    @Column(name = "qq")
    private String qq;
    
    @Column(name = "info")
    private String info;
    
    @OneToMany(mappedBy="user",cascade={CascadeType.ALL})   
    private Set<Picture> picture = new HashSet<Picture>();
    
    @OneToMany(mappedBy="user",cascade={CascadeType.ALL})   
    private Set<Article> article = new HashSet<Article>();
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Set<Picture> getPicture() {
		return picture;
	}

	public void setPicture(Set<Picture> picture) {
		this.picture = picture;
	}

	public Set<Article> getArticle() {
		return article;
	}

	public void setArticle(Set<Article> article) {
		this.article = article;
	}
    

	
    
}
