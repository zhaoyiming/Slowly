package com.ming.slowly;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by 鸣 on 2018/2/19.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder>{
    private List<Recycle_item> mList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View recycle_item_view;
        ImageView imageView;
        TextView textView;
        public ViewHolder(View view){
            super(view);
            recycle_item_view=view;
            imageView=itemView.findViewById(R.id.recycle_view_image);
            textView=itemView.findViewById(R.id.recycle_view_text);
        }
    }

    public RecycleAdapter(List<Recycle_item> list){
        mList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.recycle_item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Sb",Toast.LENGTH_SHORT).show();
                int postion=holder.getAdapterPosition();
                Recycle_item recycle_item=mList.get(postion);
                if(!recycle_item.equals("")){
                    Toast.makeText(v.getContext(),recycle_item.getText(),Toast.LENGTH_SHORT).show();
                }



            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recycle_item recycle_item=mList.get(position);
        holder.textView.setText(mList.get(position).getText());
        holder.imageView.setImageResource(mList.get(position).getImageId());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
