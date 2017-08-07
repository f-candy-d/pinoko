package com.f_candy_d.pinoko.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.f_candy_d.pinoko.model.Entry;

/**
 * Created by daichi on 17/08/07.
 */

public class MyCH {

    public interface OnCardClickListener {
        void onCardClick(final int position, final Entry entry);
    }

    private MyCH() {}

    /**
     * Base view holder
     */
    public static class BaseCardHolder extends RecyclerView.ViewHolder {

        private OnCardClickListener mOnCardClickListener = null;
        private View mItemView = null;

        public BaseCardHolder(@NonNull final View view) {
            super(view);
            mItemView = view;
        }

        public void bind(final int position, final Entry entry, final OnCardClickListener clickListener) {
            mOnCardClickListener = clickListener;
            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnCardClickListener != null) {
                        mOnCardClickListener.onCardClick(position, entry);
                    }
                }
            });
        }
    }

    /**
     * Mini course card view holder
     */
    public static class MiniCourseCardHolder extends BaseCardHolder {

        public MiniCourseCardHolder(@NonNull final View view) {
            super(view);
        }
    }
}
