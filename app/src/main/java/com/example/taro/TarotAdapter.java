package com.example.taro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TarotAdapter  extends RecyclerView.Adapter<TarotAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Cards> states;
    private final OnCardClickListener onClickListener;

    interface OnCardClickListener{
        void onCardClick(Cards state, int position);
    }

    TarotAdapter(Context context, List<Cards> states, OnCardClickListener onClickListener) {

        this.onClickListener = onClickListener;
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public TarotAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TarotAdapter.ViewHolder holder, int position) {
        Cards state = states.get(position);
        holder.sprcard.setImageResource(state.getPicResource());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                // вызываем метод слушателя, передавая ему данные
                onClickListener.onCardClick(state, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView sprcard;
        ViewHolder(View view){
            super(view);
            sprcard = view.findViewById(R.id.sprcard);
        }
    }
}