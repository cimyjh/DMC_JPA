package com.example.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter	@Setter
@Table(name = "event")
public class Event {
	
	@Id	@GeneratedValue
	@Column(name = "event_id")	
    private Long eventNum;
    
    
    private String name;
    private int price;
    private String evt;
    private String product1;
    private String store;
    private String product2;
    private String img;
    private Date registerDate;
    private Date expireDate;

}