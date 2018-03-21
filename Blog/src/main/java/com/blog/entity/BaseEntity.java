package com.blog.entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

public class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3376508141408478603L;

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
