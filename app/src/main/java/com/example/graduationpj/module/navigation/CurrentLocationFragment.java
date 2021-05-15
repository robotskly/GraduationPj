package com.example.graduationpj.module.navigation;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.graduationpj.R;
import com.example.graduationpj.support.base.page.BaseFragment;

public class CurrentLocationFragment extends BaseFragment implements AMapLocationListener, LocationSource {


    private Activity mActivity;
    private AMap mMap;
    private MapView mapView;
    private OnLocationChangedListener mListener;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    private LatLng myLocation;
    private BitmapDescriptor successDescripter;
    private EditText etAddress;
    private TextView tvAddressName;
    private LinearLayout llOk;
    private View view;

    public static CurrentLocationFragment newInstance() {

        Bundle args = new Bundle();

        CurrentLocationFragment fragment = new CurrentLocationFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // LogUtils.d("zh", "onCreateView进来了");
        mActivity = getActivity();
        view = View.inflate(mActivity, R.layout.fragment_home_navigation, null);
        mapView = (MapView) view.findViewById(R.id.mapView);
       // etAddress = (EditText) view.findViewById(R.id.et_address);
       // tvAddressName = (TextView) view.findViewById(R.id.tv_address_name);
       // llOk = (LinearLayout) view.findViewById(R.id.ll_ok);
        mapView.onCreate(savedInstanceState);
        initMap();
        setUpLocationStyle();
        //initListener();
        return view;
    }

    /**
     * 监听
     */
//    private void initListener() {
//        llOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String signedAddress = etAddress.getText().toString().trim();//签到地址
//                String addressName = tvAddressName.getText().toString().trim();//定位地址名称
//                if (TextUtils.isEmpty(signedAddress)) {
//                    ToastUtils.showToast("请输入签到地点");
//                    return;
//                }
//
//            }
//        });
//    }

    private void initMap() {
        if (mMap == null) {
            mMap = mapView.getMap();
        }

        mMap.setLocationSource(this);// 设置定位监听
        mMap.setMyLocationEnabled(true);
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);

        CameraUpdate cameraUpdate = CameraUpdateFactory.zoomTo(15);
        mMap.moveCamera(cameraUpdate);
        successDescripter = BitmapDescriptorFactory.fromResource(R.drawable.icon_warning_mini);
    }

    private void setUpLocationStyle() {
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.icon_warning_mini));
        myLocationStyle.strokeWidth(0);
        myLocationStyle.radiusFillColor(Color.TRANSPARENT);
        mMap.setMyLocationStyle(myLocationStyle);
    }

    @Override
    public void onResume() {
        super.onResume();
      //  LogUtils.d("zh", "onResume进来了");
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //LogUtils.d("zh", mapView + ":onPause");
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //LogUtils.d("zh", mapView + ":onSaveInstanceState");
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //LogUtils.d("zh", mapView + ":onDestroy进来了");
        mLocationOption = null;
        mLocationClient = null;
        mMap = null;
        mapView.onDestroy();

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
//        LogUtils.d("zh", "onLocationChanged进来了");
//        LogUtils.d("zh", aMapLocation + "");
//        LogUtils.d("zh", aMapLocation.getErrorCode() + "");
        if (aMapLocation != null && aMapLocation.getErrorCode() == 0) {
            if (mListener != null) {
                mListener.onLocationChanged(aMapLocation);// 显示系统小蓝点
            }
            //获取当前经纬度坐标
            String address = aMapLocation.getAddress();
         //   LogUtils.d("zh", address);
            myLocation = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
            //fixedMarker();
            tvAddressName.setText(address);
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
     //   LogUtils.d("zh", "activate进来了");
        mListener = onLocationChangedListener;
        if (mLocationClient == null) {
            mLocationClient = new AMapLocationClient(mActivity);
            mLocationOption = new AMapLocationClientOption();
            //设置定位监听
            mLocationClient.setLocationListener(this);
            //设置为高精度定位模式
            mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            //设置定位参数
            mLocationOption.setOnceLocation(true);//只定位一次
            mLocationOption.setHttpTimeOut(2000);
            mLocationClient.setLocationOption(mLocationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            mLocationClient.startLocation();//开始定位
        }
    }

    @Override
    public void deactivate() {
      //  LogUtils.d("zh", "deactivate我是什么时候进来的");
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }
}