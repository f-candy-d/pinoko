package com.f_candy_d.pinoko.view;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by daichi on 8/11/17.
 */

abstract public class LayoutContents {

    private ViewGroup mContainer;
    private View mContentView;
    private boolean mIsInflated;

    public LayoutContents() {
        mIsInflated = false;
        mContainer = null;
        mContentView = null;
    }

    public LayoutContents(@NonNull final ViewGroup container) {
        mIsInflated = false;
        inflate(container);
    }

    /**
     * @return An id of a layout xml file.
     */
    abstract protected int getLayoutXml();

    abstract protected void initUI(@NonNull final View view);

    public View getContentView() {
        return mContentView;
    }

    public ViewGroup getContainer() {
        return mContainer;
    }

    final public void inflate(@NonNull final ViewGroup container) {
        if (!mIsInflated) {
            mContentView = LayoutInflater.from(container.getContext()).inflate(getLayoutXml(), container);
            mContainer = container;
            initUI(mContentView);
            mIsInflated = true;
            onRestoreState();
        }
    }

    final public void deinflate() {
        if (mIsInflated) {
            onSaveState();
            mContainer.removeAllViews();
            mContainer = null;
            mContentView = null;
            mIsInflated = false;
            onDeinflated();
        }
    }

    final public boolean isInflated() {
        return mIsInflated;
    }

    // Save layout states in this method if you want
    protected void onSaveState() {}
    // Restore saved layout states in this method if you want
    protected void onRestoreState() {}
    // Set null to ui parts in this method if you want
    protected void onDeinflated() {}
}
