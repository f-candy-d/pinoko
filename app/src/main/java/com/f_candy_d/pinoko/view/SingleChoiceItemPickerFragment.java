package com.f_candy_d.pinoko.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by daichi on 8/11/17.
 */

public class SingleChoiceItemPickerFragment<E extends Enum<E>> extends DialogFragment {

    public interface EventListener<E extends Enum<E>> {
        void onItemSelected(final SingleChoiceItemPickerFragment<E> picker);
    }

    private static final String ARG_ITEMS = "items";
    private static final String ARG_DEFAULT_ITEM = "default_item";

    private EventListener mEventListener;
    private String mTitle = null;
    private E mSelectedItem;
    private SparseArray<E> mItemMap;

    static public <E extends Enum<E>> SingleChoiceItemPickerFragment<E>
    newInstance(@NonNull final E[] items,@NonNull final E defaultItem) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_ITEMS, items);
        bundle.putSerializable(ARG_DEFAULT_ITEM, defaultItem);
        SingleChoiceItemPickerFragment<E> fragment = new SingleChoiceItemPickerFragment<>();
        fragment.setArguments(bundle);

        return fragment;
    }

    static public <E extends Enum<E>> SingleChoiceItemPickerFragment<E>
    newInstance(@NonNull final E[] items,@NonNull final E defaultItem, final E[] exceptions) {
        ArrayList<E> itemList = new ArrayList<>(Arrays.asList(items));
        for (E exception : exceptions) {
            itemList.remove(exception);
        }
        E[] actualItems = itemList.toArray(exceptions);

        return newInstance(actualItems, defaultItem);
    }

    public SingleChoiceItemPickerFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mSelectedItem = (E) getArguments().getSerializable(ARG_DEFAULT_ITEM);
            init((E[]) getArguments().getSerializable(ARG_ITEMS));
        }
    }

    private void init(@NonNull final E[] items) {
        mItemMap = new SparseArray<>();
        for (int i = 0; i < items.length; ++i) {
            mItemMap.put(i, items[i]);
        }
    }

    public void setEventListener(EventListener<E> eventListener) {
        mEventListener = eventListener;
    }

    public E getSelectedItem() {
        return mSelectedItem;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final String[] strings = new String[mItemMap.size()];
        for (int i = 0; i < mItemMap.size(); ++i) {
            strings[i] = mItemMap.valueAt(i).toString();
        }

        builder.setTitle(mTitle)
                .setSingleChoiceItems(strings, mItemMap.indexOfValue(mSelectedItem),
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSelectedItem = mItemMap.get(which);
                    }
                })
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mEventListener != null) {
                            mEventListener.onItemSelected(SingleChoiceItemPickerFragment.this);
                        }
                    }
                });

        return builder.create();
    }
}
