package com.yanyusong.divideritemdecoration.y_recycleradapter;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by mac on 15/12/19.
 */
public class GeneralRecyclerViewHolder extends RecyclerView.ViewHolder {

    private final View mItemView;
    private final SparseArray<View> childViews;

    public GeneralRecyclerViewHolder(View itemView) {
        super(itemView);
        this.mItemView = itemView;
        this.childViews = new SparseArray<>(20);
    }

    public <T extends View> T getChildView(int childViewId) {
        View view = childViews.get(childViewId);
        if (view == null) {
            view = mItemView.findViewById(childViewId);
            childViews.put(childViewId, view);
        }
        return (T) view;
    }

    public GeneralRecyclerViewHolder setText(int childViewId, String text) {
        TextView textView = getChildView(childViewId);
        textView.setText(text);
        return this;
    }

}
