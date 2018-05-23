package com.example.krzysztof.listdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter {

    // źródło danych
    private List<Currency> mCurrencies = new ArrayList<>();

    // obiekt listy artykułów
    private RecyclerView mRecyclerView;
    // implementacja wzorca ViewHolder
    // każdy obiekt tej klasy przechowuje odniesienie do layoutu elementu listy
    // dzięki temu wywołujemy findViewById() tylko raz dla każdego elementu
    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mCode;
        public TextView mRate;

        public MyViewHolder(View pItem) {
            super(pItem);
            mName = (TextView) pItem.findViewById(R.id.currency_name);
            mCode = (TextView) pItem.findViewById(R.id.currency_code);
            mRate = (TextView) pItem.findViewById(R.id.currency_rate);
        }
    }

    // konstruktor adaptera
    public MyAdapter(List<Currency> pCurrencies, RecyclerView pRecyclerView){
        mCurrencies = pCurrencies;
        mRecyclerView = pRecyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        // tworzymy layout artykułu oraz obiekt ViewHoldera
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.currency_layout, viewGroup, false);

        // dla elementu listy ustawiamy obiekt OnClickListener,
        // który usunie element z listy po kliknięciu na niego
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // odnajdujemy indeks klikniętego elementu
                int positionToDelete = mRecyclerView.getChildAdapterPosition(v);
                // usuwamy element ze źródła danych
                mCurrencies.remove(positionToDelete);
                // poniższa metoda w animowany sposób usunie element z listy
                notifyItemRemoved(positionToDelete);
            }
        });

        // tworzymy i zwracamy obiekt ViewHolder
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        // uzupełniamy layout artykułu
        Currency article = mCurrencies.get(i);
        ((MyViewHolder) viewHolder).mName.setText(article.getName());
        ((MyViewHolder) viewHolder).mCode.setText(article.getCode());
        ((MyViewHolder) viewHolder).mRate.setText(String.valueOf(article.getExchangeRate()));
    }

    @Override
    public int getItemCount() {
        return mCurrencies.size();
    }
}