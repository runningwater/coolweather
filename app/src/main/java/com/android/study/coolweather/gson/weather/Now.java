package com.android.study.coolweather.gson.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by runningwater on 2017/7/24.
 */

public class Now {
    @SerializedName("tmp")
    public String temprature;

    @SerializedName("cond")
    public More more;

    public class More {
        public String code;
        @SerializedName("txt")
        public String info;
    }
}
