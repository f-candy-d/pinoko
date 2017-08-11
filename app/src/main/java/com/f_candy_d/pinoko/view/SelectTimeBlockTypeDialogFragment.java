package com.f_candy_d.pinoko.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.f_candy_d.pinoko.utils.TimeBlockFormer;

/**
 * Created by daichi on 8/10/17.
 */

public class SelectTimeBlockTypeDialogFragment extends DialogFragment {

    public interface EventListener {
        void onNegativeButtonClick(final SelectTimeBlockTypeDialogFragment dialog);
        void onPositivbeButtonClick(final SelectTimeBlockTypeDialogFragment dialog);
    }

    private static final String ARG_DEFAULT_TYPE = "default_type";

    private TimeBlockFormer.Type mSelectedType;
    private EventListener mListener = null;

    public static SelectTimeBlockTypeDialogFragment newInstance(final TimeBlockFormer.Type defaultType) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_DEFAULT_TYPE, defaultType);
        SelectTimeBlockTypeDialogFragment fragment = new SelectTimeBlockTypeDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mSelectedType = (TimeBlockFormer.Type) getArguments().getSerializable(ARG_DEFAULT_TYPE);
            if (mSelectedType == null) {
                mSelectedType = TimeBlockFormer.Type.NULL_TYPE;
            }
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final TimeBlockFormer.Type[] types = TimeBlockFormer.Type.values();
        String[] typesAsString = new String[types.length - 1];
        // Except NULL_TYPE
        for (int i = 1; i < types.length; ++i) {
            typesAsString[i - 1] = types[i].toString();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select a time block type")
                .setSingleChoiceItems(typesAsString, mSelectedType.toInt() - 1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mSelectedType = types[which + 1];
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mListener != null) {
                            mListener.onNegativeButtonClick(SelectTimeBlockTypeDialogFragment.this);
                        }
                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mListener != null) {
                            mListener.onPositivbeButtonClick(SelectTimeBlockTypeDialogFragment.this);
                        }
                    }
                });

        return builder.create();
    }

    public void setEventListener(final EventListener listener) {
        mListener = listener;
    }

    public TimeBlockFormer.Type getSelectedType() {
        return mSelectedType;
    }
}
