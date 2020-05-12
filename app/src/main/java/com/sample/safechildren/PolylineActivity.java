package com.sample.safechildren;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.PolylineOverlay;

import java.util.Arrays;
import java.util.List;
public class PolylineActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final List<LatLng> COORDS_1 = Arrays.asList(//
            new LatLng(37.57152, 126.97714),
            new LatLng(37.56607, 126.98268),
            new LatLng(37.56445, 126.97707),
            new LatLng(37.55855, 126.97822)
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);

        ActionBar actionBar = getSupportActionBar();//액션바
        if (actionBar != null) {//액션바가 아닐 경우
            actionBar.setDisplayHomeAsUpEnabled(true);// 최상위 레벨 또는 첫 페이지로 돌아가지 않고 UI에서 단일 레벨만큼 되돌아오면 이를 true로 설정하였습니다.
            actionBar.setDisplayShowHomeEnabled(true);// 포함여부를 설
        }
        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
// 지도를 출력하는 프래그먼트 클래스
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this); //비동기로 네이버 객체를 얻어 옵니다.

    }


    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        // 폴리라인 설정 부분
        int width = getResources().getDimensionPixelSize(R.dimen.overlay_line_width);//폴리라인 넓이
        int patternInterval = getResources().getDimensionPixelSize(R.dimen.overlay_pattern_interval);//속성을 사용하면 패턴 이미지 간 간격을 지정할 수 있습니다.
        PolylineOverlay polylineOverlay1 = new PolylineOverlay();// 폴리라인 오베러이 객체
        polylineOverlay1.setWidth(width);
        polylineOverlay1.setCoords(COORDS_1);//좌표열
        polylineOverlay1.setColor(ResourcesCompat.getColor(getResources(), R.color.primary, getTheme()));//폴리라인 오버레이 색깔
        polylineOverlay1.setMap(naverMap);
    }

}
