package com.f_candy_d.pinoko.controller;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.f_candy_d.pinoko.model.EntryObject;
import com.f_candy_d.pinoko.view.MyCH;

/**
 * Created by daichi on 17/08/07.
 */

abstract public class CardAdapter extends RecyclerView.Adapter<MyCH.BaseCardHolder> {

    private MyCH.OnCardClickListener mOnCardClickListener = null;

    public CardAdapter() {}

    public CardAdapter(final MyCH.OnCardClickListener onCardClickListener) {
        mOnCardClickListener = onCardClickListener;
    }

    public MyCH.OnCardClickListener getOnCardClickListener() {
        return mOnCardClickListener;
    }

    public void setOnCardClickListener(MyCH.OnCardClickListener onCardClickListener) {
        mOnCardClickListener = onCardClickListener;
    }

    abstract public RecyclerView.LayoutManager getParentLayoutManager();

    abstract public void insertData(@NonNull final EntryObject content);
}
