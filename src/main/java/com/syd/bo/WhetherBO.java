package com.syd.bo;

import lombok.Data;

import java.util.List;

@Data
public class WhetherBO {
    private LocationBO location;
    private NowBO now;
    private List<Forecast> forecasts;
}
