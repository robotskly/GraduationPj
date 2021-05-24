package com.example.graduationpj.module.navigation.navigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.enums.NaviType;
import com.amap.api.navi.model.AMapCalcRouteResult;
import com.amap.api.navi.model.AMapCarInfo;
import com.example.graduationpj.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GPSNaviFragment extends BaseNaviFragment {


    @Override
    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        mAMapNaviView.setAMapNaviViewListener(this);

        mAMapNavi.getNaviSetting().setScreenAlwaysBright(false);

    }

    @NotNull
    @Override
    public View onCreateContentView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation,container,false);
        mAMapNaviView = view.findViewById(R.id.navi_view);
        mAMapNaviView.onCreate(savedInstanceState);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onInitNaviSuccess() {
        super.onInitNaviSuccess();

        int strategy = 0;
        try {
            strategy = mAMapNavi.strategyConvert(true, false, false, false, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AMapCarInfo carInfo = new AMapCarInfo();
        carInfo.setCarNumber("京DFZ588");
        mAMapNavi.setCarInfo(carInfo);
        mAMapNavi.calculateDriveRoute(sList, eList, mWayPointList, strategy);

    }

    @Override
    public void onCalculateRouteSuccess(AMapCalcRouteResult aMapCalcRouteResult) {
        super.onCalculateRouteSuccess(aMapCalcRouteResult);

        mAMapNavi.startNavi(NaviType.GPS);
    }


}
