package com.demo.acciona.manage.infraestructure.tweet.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TweetDto
{
    private Integer id;
    private Long user;
    private String text;
    private String locatization;
    private Boolean validation;
}
