package com.org.xiuer.androidlearningmanual.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.org.xiuer.androidlearningmanual.Interface.ItemClickListener;
import com.org.xiuer.androidlearningmanual.R;
import com.org.xiuer.androidlearningmanual.model.Guide;
import com.org.xiuer.androidlearningmanual.model.MainGuides;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiuer on 16/8/29.
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {

    private  final static   String TAG="LeftAdapter";
     //String[]mTitles;

    List<MainGuides> mGuides;
    ItemClickListener  itemClickListener;



    public LeftAdapter(List<MainGuides>mGuides) {
        this.mGuides = mGuides;
    }

    public LeftAdapter(List<MainGuides> mGuides, ItemClickListener itemClickListener) {
        this.mGuides = mGuides;
        this.itemClickListener = itemClickListener;
    }

    public  static  class  ViewHolder extends RecyclerView.ViewHolder{


       private TextView mLeftTitle;
       private TextView mLeftIntroduce;
        private View mItemView;



        public ViewHolder(View itemView) {
            super(itemView);
             mLeftTitle=(TextView)itemView.findViewById(R.id.left_title_tv);
             mLeftIntroduce=(TextView)itemView.findViewById(R.id.left_title_introduce_tv);

             this.mItemView=itemView;

        }

        public TextView getmLeftTitle() {
            return mLeftTitle;
        }
        public TextView getmLeftIntroduce() {
            return mLeftIntroduce;
        }

        public View getmItemView() {
            return mItemView;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       View v= LinearLayout.inflate(parent.getContext(),
               R.layout.activity_left_item,null);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        TextView titleView= holder.getmLeftTitle();
        titleView.setText(mGuides.get(position).getTitle());

        holder.getmLeftIntroduce().setText(mGuides.get(position).getContent());


        View itemView=holder.getmItemView();

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title= mGuides.get(position).getTitle();

                itemClickListener.onItemClick(view,position);

             }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                itemClickListener.onLongItemClick(view,position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {

        return mGuides.size();
        
    }
}
