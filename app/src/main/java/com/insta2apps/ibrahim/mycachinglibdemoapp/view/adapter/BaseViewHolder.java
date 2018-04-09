package com.insta2apps.ibrahim.mycachinglibdemoapp.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Ibrahim AbdelGawad on 3/12/2018.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected T mBusinessObject ;

    public void bindData(T businessObject , int position) {
        this.mBusinessObject = businessObject ;
        this.bindViewData(businessObject , position);
    }

    public abstract void bindViewData(T businessObject , int position);
    public BaseViewHolder(View itemView) {
        super(itemView);
    }
}
