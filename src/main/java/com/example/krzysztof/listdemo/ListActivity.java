package com.example.krzysztof.listdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = this.getApplicationContext();
        fetchData();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.currencies_list);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }


    private void fetchData(){
        CurrencyApi api = new CurrencyApi(new OnCurrencyReceived() {
            @Override
            public void onDataHandle(List<Currency> currencyData) {
                mRecyclerView.setAdapter(new MyAdapter(currencyData, mRecyclerView));
            }
        }, mContext);
        api.fetchData();
    }


}
