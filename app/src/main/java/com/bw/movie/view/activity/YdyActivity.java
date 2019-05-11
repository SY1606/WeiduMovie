package com.bw.movie.view.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.bw.movie.R;
import com.bw.movie.view.fragment.Ydy1;
import com.bw.movie.view.fragment.Ydy2;
import com.bw.movie.view.fragment.Ydy3;
import com.bw.movie.view.fragment.Ydy4;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YdyActivity extends AppCompatActivity {

     int t;
    @BindView(R.id.ydyview_id)
    ViewPager pager;
    @BindView(R.id.ydy1_id)
    RadioButton ydy1Id;
    @BindView(R.id.ydy2_id)
    RadioButton ydy2Id;
    @BindView(R.id.ydy3_id)
    RadioButton ydy3Id;
    @BindView(R.id.ydy4_id)
    RadioButton ydy4Id;
    @BindView(R.id.group_id)
    RadioGroup groupId;
    private ArrayList<Fragment> fraglist;
    private SharedPreferences sp;
    private SharedPreferences.Editor edit;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ydy);
        ButterKnife.bind(this);

        sp = getSharedPreferences("jump",MODE_PRIVATE);
        edit = sp.edit();
        boolean jump = sp.getBoolean("jump", false);
        if (jump==true){
            Intent intent = new Intent(YdyActivity.this, Main2Activity.class);
            startActivity(intent);
            finish();
        }else{

        fraglist = new ArrayList();
        fraglist.add(new Ydy1());
        fraglist.add(new Ydy2());
        fraglist.add(new Ydy3());
        fraglist.add(new Ydy4());


        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fraglist.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fraglist.get(position);
            }
        });


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                t = i;
                groupId.check(groupId.getChildAt(i).getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        pager.setOnTouchListener(new View.OnTouchListener() {
            float startX;
            float endX;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX=event.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        endX=event.getX();
                        WindowManager windowManager= (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                        //获取屏幕的宽度
                        Point size = new Point();
                        windowManager.getDefaultDisplay().getSize(size);
                        int width=size.x;
                        //首先要确定的是，是否到了最后一页，然后判断是否向左滑动
                        // 并且滑动距离是否符合，我这里的判断距离是屏幕宽度的4分之一（这里可以适当控制）
                        if(t==(fraglist.size()-1)&&startX-endX>=(width/4)){
                            Log.i( "onTouch: ","触摸");
                            Intent intent = new Intent(YdyActivity.this,Main2Activity.class);
                            startActivity(intent);
                            edit.putBoolean("jump",true);
                            edit.commit();
                            finish();
                            //这部分代码是切换Activity时的动画，看起来就不会很生硬
                            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_in_left);
                        }
                        break;
                     }
                return false;
                }

            });
        }
    }
}





