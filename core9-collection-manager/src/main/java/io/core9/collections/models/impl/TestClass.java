package io.core9.collections.models.impl;

import org.hibernate.search.annotations.Field;




public class TestClass {

@Field
private String id;
@Field
private String name;
@Field
private String surName;


public String getId(){
 return this.id;
}

public String getName(){
 return this.name;
}

public String getSurName(){
 return this.surName;
}

public void setId(String id){
     this.id = id;
}

public void setName(String name){
     this.name = name;
}

public void setSurName(String surName){
     this.surName = surName;
}

}