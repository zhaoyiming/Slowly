package com.ming.slowly;

import android.graphics.Color;
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

public class RecycleAdapter1 extends RecyclerView.Adapter<RecycleAdapter1.ViewHolder>{
    private List<Recycle_item> mList;

    private int selectedPos = 0;
    private OnItemClickListener mOnItemClickListener;


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        View recycle_item_view;
        ImageView imageView;
        TextView textView;
        public ViewHolder(View view){
            super(view);
            recycle_item_view=view;
            imageView=itemView.findViewById(R.id.recycle_view_image1);
            textView=itemView.findViewById(R.id.recycle_view_text1);
        }
    }

    public RecycleAdapter1(List<Recycle_item> list){
        mList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_1,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.recycle_item_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int postion=holder.getAdapterPosition();
                Recycle_item recycle_item=mList.get(postion);
                notifyItemChanged(selectedPos);
                selectedPos = postion;
                notifyItemChanged(selectedPos);
                Toast.makeText(v.getContext(),"您已选择了"+recycle_item.getText()+"白噪音",Toast.LENGTH_SHORT).show();
            mOnItemClickListener.onItemClick(v,postion);
            }
        });
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recycle_item recycle_item=mList.get(position);
        holder.textView.setText(mList.get(position).getText());
        holder.imageView.setImageResource(mList.get(position).getImageId());
        if(selectedPos == position){
            holder.textView.setTextColor(Color.WHITE);
            holder.imageView.setImageAlpha(200);
        }else{
            holder.textView.setTextColor(Color.BLACK);
            holder.imageView.setImageAlpha(100);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
