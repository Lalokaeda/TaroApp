package com.example.taro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SchemeAdapter extends RecyclerView.Adapter<SchemeAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final List<Scheme> states;
    private final SchemeAdapter.OnSchemeClickListener onClickListener;

    interface OnSchemeClickListener{
        void onSchemeClick(Scheme state, int position);
    }

    SchemeAdapter(Context context, List<Scheme> states, SchemeAdapter.OnSchemeClickListener onClickListener) {

        this.onClickListener = onClickListener;
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public SchemeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.scheme_item, parent, false);
        return new SchemeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SchemeAdapter.ViewHolder holder, int position) {
        Scheme state = states.get(position);
        holder.imscheme.setImageResource(state.getPicResource());
        holder.textQuest.setText(state.getQests());
        holder.schemeName.setText(state.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                // вызываем метод слушателя, передавая ему данные
                onClickListener.onSchemeClick(state, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textQuest;
        final TextView schemeName;
        final ImageView imscheme;
        ViewHolder(View view){
            super(view);
            imscheme = view.findViewById(R.id.imscheme);
            textQuest=view.findViewById(R.id.textQuest);
            schemeName=view.findViewById(R.id.schemeName);
        }
    }
}