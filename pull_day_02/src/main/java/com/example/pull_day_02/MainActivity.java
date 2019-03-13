package com.example.pull_day_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapter.Myadapter;
import com.example.bean.Bean;
import com.example.netulit.NetUlit;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView pull;
    private String url = "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/";
    private int page = 1;
    List<Bean.ResultsBean> list = new ArrayList<>();
    private Myadapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pull = findViewById(R.id.pull);
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        pull.setScrollingWhileRefreshingEnabled(true);
        myadapter = new Myadapter(list, MainActivity.this);
        pull.setAdapter(myadapter);
        if (NetUlit.isGetconten(MainActivity.this)) {
            isgion(page);
            pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                    list.clear();
                    isgion(page);
                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                    page++;
                    isgion(page);
                }
            });
        } else {
            Toast.makeText(MainActivity.this, "没有网", Toast.LENGTH_SHORT).show();
        }
    }

    private void isgion(int page) {
        String str = url + page;
        NetUlit.MyAnsk(str, new NetUlit.MyCallBack() {
            @Override
            public void getDate(String s) {
                Gson gson = new Gson();
                Bean bean = gson.fromJson(s, Bean.class);
                List<Bean.ResultsBean> results = bean.getResults();
                list.addAll(results);
                myadapter.notifyDataSetChanged();
                pull.onRefreshComplete();
            }
        });
    }
}
