package com.f_candy_d.pinoko.utils;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.f_candy_d.pinoko.view.LayoutContents;

import java.util.EnumMap;

/**
 * Created by daichi on 8/11/17.
 *
 * ##### USAGE ###################################################################################
 *
 * # In a layout xml file...
 * <FrameLayout
 *   android:layout_width="match_parent"
 *   android:layout_height="wrap_content"
 *   android:id="@+id/config_contents"/>
 *
 * # In a class...
 * enum HogeEnum { APPLE, PINE }
 *
 * # In a some method...
 * ViewGroup container = (ViewGroup) findViewId(R.id.layout_contents_container);
 * mLayoutContentsSwitcher = new LayoutContentsSwitcher<>(HogeEnum.class, container);
 * mLayoutContentsSwitcher.addLayout(HogeEnum.APPLE, new HogeLayoutContents());
 * mLayoutContentsSwitcher.addLayout(HogeEnum.PINE, new FooLayoutContents());
 *
 * # Set the layout whose key is APPLE as a current layout...
 * mLayoutContentsSwitcher.setCurrentLayout(HogeEnum.APPLE);
 *
 * # When switch to the layout whose key is APPLE from the layout whose key is PINE...
 * mLayoutContentsSwitcher.switchLayout(HogeEnum.PINE);
 */

public class LayoutContentsSwitcher<E extends Enum<E>> {

    private EnumMap<E, LayoutContents> mLayoutContentsMap;
    private E mCurrentLayoutKey = null;
    private ViewGroup mContainer = null;

    public LayoutContentsSwitcher(@NonNull final Class<E> eClass) {
        this(eClass, null);
    }

    public LayoutContentsSwitcher(@NonNull final Class<E> eClass, final ViewGroup container) {
        mLayoutContentsMap = new EnumMap<>(eClass);
        mContainer = container;
    }

    public void addLayout(@NonNull final E key, final LayoutContents layoutContents) {
        mLayoutContentsMap.put(key, layoutContents);
        if (layoutContents.isInflated()) {
            layoutContents.deinflate();
        }
    }

    public void switchLayout(@NonNull final E key) {
        containsLayoutAndThrowExceptionIfNotExists(key);
        if (mCurrentLayoutKey == null) {
            throw new NullPointerException("There is no LayoutContents object in the map");
        }
        LayoutContents current = mLayoutContentsMap.get(mCurrentLayoutKey);
        LayoutContents next = mLayoutContentsMap.get(key);
        current.deinflate();
        next.inflate(mContainer);
        mCurrentLayoutKey = key;
    }

    public LayoutContents getCurrentLayout() {
        if (mCurrentLayoutKey == null) {
            return null;
        } else {
            return mLayoutContentsMap.get(mCurrentLayoutKey);
        }
    }

    public void setCurrentLayout(@NonNull final E key) {
        if (mCurrentLayoutKey != null) {
            switchLayout(key);
        } else {
            containsLayoutAndThrowExceptionIfNotExists(key);
            LayoutContents next = mLayoutContentsMap.get(key);
            next.inflate(mContainer);
            mCurrentLayoutKey = key;
        }
    }

    public void setContainer(ViewGroup container) {
        mContainer = container;
    }

    public ViewGroup getContainer() {
        return mContainer;
    }

    private void containsLayoutAndThrowExceptionIfNotExists(@NonNull final E key) {
        if (!mLayoutContentsMap.containsKey(key)) {
            throw new IllegalStateException("The Layoutcontents for the key '" + key.toString() + "' does not exists");
        }
    }
}
