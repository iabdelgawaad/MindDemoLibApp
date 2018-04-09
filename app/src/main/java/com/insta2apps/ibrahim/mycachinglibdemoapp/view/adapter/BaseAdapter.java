package com.insta2apps.ibrahim.mycachinglibdemoapp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ibrahim AbdelGawad on 3/12/2018.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected Context mContext;
    protected List<T> mlstData;

    public BaseAdapter(Context objView)
    {
        this.mContext = objView ;
        mlstData=new ArrayList<T>();
    }

    public BaseAdapter(Context objView, int loadingResourceId)
    {
        mContext=objView;
        mlstData=new ArrayList<T>();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(getHolderResourceId(), parent, false);
        return getHolderInstance(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        T obj = mlstData.get(position);
        holder.bindData(obj , position);
    }

    public void add(List<T> items) {
        int previousDataSize = this.mlstData.size();
        this.mlstData.addAll(items);
        notifyItemRangeInserted(previousDataSize, items.size());
    }

    public void addToTop(List<T> items) {
        int previousDataSize = this.mlstData.size();
        this.mlstData.addAll(0 ,items);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.mlstData.size() ;
    }

    public List<T> getDataList() {
        return this.mlstData;
    }

    public void setDataList(List<T> data) {
        this.mlstData = data;
        this.notifyDataSetChanged();
    }

    protected abstract BaseViewHolder getHolderInstance(View view);
    protected abstract int getHolderResourceId();

}
