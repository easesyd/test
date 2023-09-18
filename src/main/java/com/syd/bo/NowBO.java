package com.syd.bo;

import lombok.Data;

@Data
public class NowBO {
    private String text;
    private String temp;
    private String feels_like;
    private String rh;
    private String wind_class;
    private String wind_dir;
    private String uptime;
}
