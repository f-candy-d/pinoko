package com.f_candy_d.pinoko.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.f_candy_d.pinoko.model.Entry;
import com.f_candy_d.pinoko.model.EntryObject;
import com.f_candy_d.pinoko.model.MergeableTimeBlock;
import com.f_candy_d.pinoko.utils.DBContract;
import com.f_candy_d.pinoko.utils.DBDataManager;

import java.util.ArrayList;

/**
 * Created by daichi on 8/12/17.
 */

public class TimeBlockPickerFragment extends DialogFragment {

    public interface EventListener {
        void onTimeBlockSelected(@NonNull final TimeBlockPickerFragment picker);
    }

    private static final String ARG_TIME_TABLE_ID = "time_table_id";

    private ArrayList<MergeableTimeBlock<?>> mTimeBlocks;
    private int mSelectedItemIndex = -1;
    private int mTimeTableId;
    private EventListener mEventListener;

    public static TimeBlockPickerFragment newInstance(final int timeTableId) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_TIME_TABLE_ID, timeTableId);
        TimeBlockPickerFragment fragment = new TimeBlockPickerFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTimeTableId = getArguments().getInt(ARG_TIME_TABLE_ID);
        } else {
            throw new NullPointerException();
        }

        init();
    }

    private void init() {
        mTimeBlocks = new ArrayList<>();

        DBDataManager dataManager = new DBDataManager(getActivity());
        dataManager.openAsReadable();
        if (dataManager.isOpen()) {
            ArrayList<Entry> results = dataManager.selectWhereColumnIs(
                    DBContract.TimeBlockEntry.TABLE_NAME,
                    DBContract.TimeBlockEntry.ATTR_TIME_TABLE_ID,
                    mTimeTableId,
                    DBContract.TimeBlockEntry.TABLE_NAME);

            if (results != null) {
                for (Entry entry : results) {
                    mTimeBlocks.add(new MergeableTimeBlock<>(EntryObject.class, entry));
                }
            }

            dataManager.close();
        }
    }

    public void setEventListener(EventListener eventListener) {
        mEventListener = eventListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String[] itemNames = new String[mTimeBlocks.size()];
        for (int i = 0; i < mTimeBlocks.size(); ++i) {
            itemNames[i] = "id is " + mTimeBlocks.get(i).getId();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select a time block");
        if (mTimeBlocks.size() != 0) {
            builder.setItems(itemNames, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mSelectedItemIndex = which;
                    if (mEventListener != null) {
                        mEventListener.onTimeBlockSelected(TimeBlockPickerFragment.this);
                    }
                }
            });

        } else {
            builder.setMessage("No item.");
        }

        builder.setNegativeButton("Cancel", null);

        return builder.create();
    }

    public MergeableTimeBlock<?> getSelectedTimeBlock() {
        if (mSelectedItemIndex < 0 || mTimeBlocks.size() == 0) {
            return null;
        } else {
            return mTimeBlocks.get(mSelectedItemIndex);
        }
    }
}
