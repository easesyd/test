package com.syd.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.syd.bo.Forecast;
import com.syd.bo.NowBO;
import com.syd.bo.WhetherBO;
import com.syd.service.TestService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author syd
 * @Description：
 * @created 2023/9/18
 */
@Service
public class TestServiceImpl implements TestService {
    @Resource
    private RestTemplate restTemplate;

    @Override
    public void test() {
        String accessToken = JSON.parseObject(getAccessToken()).getString("access_token");
        System.out.println(accessToken);
        sendMessage(accessToken);
    }

    public void sendMessage(String accessToken) {

        String BIRTHDAY = System.getenv("BIRTHDAY") == null ? LocalDate.now().toString() : System.getenv("BIRTHDAY");
        String TO_USER_ID = System.getenv("USER_ID") == null ? "oaM8s6xPcWrT-npsnga5UVuQHx-s" : System.getenv("USER_ID");

        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + accessToken;
        String TEMPLATE_ID = System.getenv("TEMPLATE_ID");
//        String TEMPLATE_ID = "SHoZA6A6tY6ACdqH5DJbG2fbbBvoUcZWjEDO1wm_vNo";
        JSONObject object = new JSONObject();
        object.put("touser", TO_USER_ID);
        object.put("template_id", TEMPLATE_ID);
        object.put("topcolor", "#FF0000");
        JSONObject data = new JSONObject();

        WhetherBO whether = getWhether();
        System.out.println(whether);
        Forecast todayWhether = whether.getForecasts().get(0);
        NowBO now = whether.getNow();

        JSONObject dateObject = new JSONObject();
        dateObject.put("value", todayWhether.getDate());
        dateObject.put("color", "#51c332");
        data.put("date", dateObject);

        JSONObject week_day = new JSONObject();
        week_day.put("value", todayWhether.getWeek());
        week_day.put("color", "#51c332");
        data.put("week_day", week_day);

        JSONObject weather = new JSONObject();
        weather.put("value", todayWhether.getText_day());
        weather.put("color", "#51c332");
        data.put("weather", weather);

        JSONObject wind = new JSONObject();
        wind.put("value", todayWhether.getWd_day() + "  "+ todayWhether.getWc_day());
        wind.put("color", "#51c332");
        data.put("wind", wind);

        JSONObject temperature = new JSONObject();
        temperature.put("value", now.getTemp());
        temperature.put("color", "#51c332");
        data.put("temperature", temperature);


        JSONObject low = new JSONObject();
        low.put("value", todayWhether.getLow());
        low.put("color", "#51c332");
        data.put("lowest", low);

        JSONObject high = new JSONObject();
        high.put("value", todayWhether.getHigh());
        high.put("color", "#51c332");
        data.put("highest", high);

        LocalDateTime birthday = LocalDateTime.parse(BIRTHDAY + " 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime nowTime = LocalDateTime.now();
        Duration duration = Duration.between(nowTime, birthday);
        long toDays = duration.toDays();
        JSONObject birthday_left = new JSONObject();
        birthday_left.put("value", toDays);
        birthday_left.put("color", "#51c332");
        data.put("birthday_left", birthday_left);


        object.put("data", data);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = object.toString();
        System.out.println("requestJson: " + requestJson);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        String result = restTemplate.postForObject(url, entity, String.class);

        System.out.println(result);
    }

    private WhetherBO getWhether() {
        String url = "https://api.map.baidu.com/weather/v1/?district_id=330106&data_type=all&ak=RhGKBCdXIrETDTtnDdzQyvIztCjIw2Qb";
        String result = restTemplate.getForObject(url, String.class);
        return JSONObject.parseObject(result).getObject("result", new TypeReference<WhetherBO>() {
        });
    }

    public String getAccessToken() {
        String APP_ID = System.getenv("APP_ID");
        String APP_SECRET = System.getenv("APP_SECRET");
//        String APP_ID = "wx72e3b32d6ed5ee77";
//        String APP_SECRET = "1ad43532df49989264e3f722c59fd847";
        Map<String, String> param = new HashMap<>();
        param.put("appid", APP_ID);
        param.put("secret", APP_SECRET);
        return restTemplate.getForObject("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}", String.class, param);
    }
}
