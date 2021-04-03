package com.demo.acciona.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Tweet
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Long user;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String locatization;

    @Column(nullable = false)
    private Boolean validation;

}
