package com.android.study.coolweather.gson.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by runningwater on 2017/7/24.
 */

public class Basic {
    @SerializedName("city")
    public String cityName;
    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update {
        @SerializedName("loc")
        public String updateTime;
    }

}
