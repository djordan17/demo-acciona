package com.demo.acciona.manage.infraestructure.tweet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Tweet")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TweetEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Long user;

    @Column(nullable = false, length = 8000)
    private String text;

    @Column(nullable = true)
    private String locatization;

    @Column(nullable = false)
    private Boolean validation;

}
