package com.android.study.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by runningwater on 2017/7/24.
 */

public class Forcast {
    public String date;
    @SerializedName("tmp")
    public Temprature temprature;

    @SerializedName("cond")
    public More more;

    public class Temprature {
        public String max;
        public String min;
    }

    public class More {
        @SerializedName("txt_d")
        public String info;
    }
}
