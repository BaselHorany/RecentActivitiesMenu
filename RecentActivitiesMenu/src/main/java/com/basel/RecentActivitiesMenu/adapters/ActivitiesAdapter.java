package com.basel.RecentActivitiesMenu.adapters;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import com.basel.RecentActivitiesMenu.R;
import com.basel.RecentActivitiesMenu.model.activity;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.CustomViewHolder>{

    private List<activity> list;
    private int lastPosition = -1;
    private int viewWidth = -1;
    private Context mContext;

    public ActivitiesAdapter(Context context, List<activity> list) {
        this.list = list;
        this.mContext = context;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        viewWidth = (metrics.widthPixels/4);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item, viewGroup, false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = viewWidth;
        view.setLayoutParams(layoutParams);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        activity act = list.get(i);

        customViewHolder.activity_view.setImageBitmap(act.getView());
        customViewHolder.mComponentName = act.getComponent();
        customViewHolder.mBundle = act.getBundle();

        setAnimation(customViewHolder.itemView, i);
    }

    private void setAnimation(View view, int position) {
        if (position > lastPosition) {
            ScaleAnimation scaleIn =  new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scaleIn.setDuration(300);
            scaleIn.setFillAfter(true);
            view.startAnimation(scaleIn);
            lastPosition = position;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return (null != list ? list.size() : 0);
    }

    class CustomViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener{

        private ImageView activity_view;
        private ComponentName mComponentName;
        private Bundle mBundle;

        private CustomViewHolder(View itemView) {
            super(itemView);

            this.activity_view = itemView.findViewById(R.id.activity_view);

            this.itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setComponent(this.mComponentName);
            intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
            if(this.mBundle!=null)intent.putExtras(this.mBundle);
            mContext.startActivity(intent);
        }
    }

}