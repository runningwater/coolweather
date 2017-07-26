package com.android.study.coolweather.gson.weather;

import com.google.gson.annotations.SerializedName;

/**
 * Created by runningwater on 2017/7/24.
 */

public class Suggestion {
    @SerializedName("comf")
    public Comfort comfort;

    @SerializedName("cw")
    public CarWash carWash;

    public Sport sport;

    public class Comfort {
        public String brf;
        @SerializedName("txt")
        public String info;
    }

    public class CarWash {
        public String brf;
        @SerializedName("txt")
        public String info;
    }

    public class Sport {
        public String brf;
        @SerializedName("txt")
        public String info;
    }
}
