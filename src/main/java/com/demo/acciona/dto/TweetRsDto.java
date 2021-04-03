package com.demo.acciona.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TweetRsDto
{

    private Integer id;

    private Long user;

    private String text;

    private String locatization;

    private Boolean validation;

}
