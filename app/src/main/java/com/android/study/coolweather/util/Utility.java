package com.android.study.coolweather.util;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.android.study.coolweather.db.City;
import com.android.study.coolweather.db.County;
import com.android.study.coolweather.db.Province;
import com.android.study.coolweather.gson.bingImage.Images;
import com.android.study.coolweather.gson.weather.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by runningwater on 2017/7/21.
 */

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     *
     * @param response
     * @return
     */
    public static boolean handleProvinceResponse(String response) {
        if (TextUtils.isEmpty(response)) {
            return false;
        }
        try {
            JSONArray allProvince = new JSONArray(response);
            for (int i = 0; i < allProvince.length(); i++) {
                JSONObject provinceObject = allProvince.getJSONObject(i);
                Province province = new Province();
                province.setProvinceName(provinceObject.getString("name"));
                province.setProvinceCode(provinceObject.getInt("id"));
                province.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     *
     * @param response
     * @param provinceId
     * @return
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     *
     * @param response
     * @param cityId
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 将返回的 JSON 数据解析成 Weather 实体类
     *
     * @param response
     * @return
     */
    @Nullable
    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather5");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Bing 图片 API
     *
     * @param reponse
     * @return {
     * "images": [
     * {
     * "startdate": "20170725",
     * "fullstartdate": "201707251600",
     * "enddate": "20170726",
     * "url": "/az/hprichbg/rb/WilsonPeakWindow_ZH-CN10363033426_1920x1080.jpg",
     * "urlbase": "/az/hprichbg/rb/WilsonPeakWindow_ZH-CN10363033426",
     * "copyright": "从鬼城Alta看到的威尔逊峰，美国科罗拉多州 (© Grant Ordelheide/Tandem Stills + Motion)",
     * "copyrightlink": "http://www.bing.com/search?q=Wilson+Peak&form=hpcapt&mkt=zh-cn",
     * "quiz": "/search?q=Bing+homepage+quiz&filters=WQOskey:%22HPQuiz_20170725_WilsonPeakWindow%22&FORM=HPQUIZ",
     * "wp": false,
     * "hsh": "5287e909cbbb7cf8b7b71318c8f47ab6",
     * "drk": 1,
     * "top": 1,
     * "bot": 1,
     * "hs": []
     * }
     * ],
     * "tooltips": {
     * "loading": "正在加载...",
     * "previous": "上一个图像",
     * "next": "下一个图像",
     * "walle": "此图片不能下载用作壁纸。",
     * "walls": "下载今日美图。仅限用作桌面壁纸。"
     * }
     * }
     */
    public static Images handleBingImageResponse(String reponse) {
        try {
            JSONObject jsonObject = new JSONObject(reponse);
            JSONArray jsonArray = jsonObject.getJSONArray("images");
            String imageContents = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(imageContents, Images.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
