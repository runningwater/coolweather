package com.android.study.coolweather;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.study.coolweather.db.City;
import com.android.study.coolweather.db.County;
import com.android.study.coolweather.db.Province;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by runningwater on 2017/7/21.
 */

public class ChooseAreaFragment extends Fragment {

    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;
    private ProgressDialog progressDialog;
    private TextView titleText;
    private Button backButton;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<>();

    private List<Province> provinceList;
    private List<City> cityList;
    private List<County> countyList;

    private Province selectedProvince; // 选中的省份
    private City selectedCity; // 选中的城市
    private int currentLevle; // 当前选中的级别

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.choose_area, container, false);
        titleText = (TextView) v.findViewById(R.id.title_text);
        backButton = (Button) v.findViewById(R.id.back_button);
        listView = (ListView) v.findViewById(R.id.list_view);
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,
                dataList);
        listView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevle == LEVEL_PROVINCE) {
                    selectedProvince = provinceList.get(position);
                    queryCities();
                } else if (currentLevle == LEVEL_CITY) {
                    selectedCity = cityList.get(position);
                    queryCounties();
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLevle == LEVEL_COUNTY) {
                    queryCities();
                } else if (currentLevle == LEVEL_CITY) {
                    queryProvinces();
                }
            }
        });
    }

    /**
     * 查询全国所有的省
     */
    private void queryProvinces() {
        titleText.setText("中国");
        backButton.setVisibility(View.GONE);
        provinceList = DataSupport.findAll(Province.class);
        if (provinceList.size() > 0) {
            dataList.clear();
            for (Province province : provinceList) {
                dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevle = LEVEL_PROVINCE;
        } else {
            String address ="http://guolin.tech/api/china";
            queryFromServer(address,"province");
        }
    }

    private void queryFromServer(String address, String province) {
    }

    private void queryCities() {
    }

    private void queryCounties() {

    }
}
