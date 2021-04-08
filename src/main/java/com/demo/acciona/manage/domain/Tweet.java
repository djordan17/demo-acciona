package com.demo.acciona.manage.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Tweet
{
    private Integer id;
    private Long user;
    private String text;
    private String locatization;
    private Boolean validation;
}
