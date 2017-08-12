package com.f_candy_d.pinoko.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.f_candy_d.pinoko.model.EntryObject;

/**
 * Created by daichi on 8/11/17.
 */

public class EditEntryObjectFragment<T extends EntryObject> extends Fragment {

    public interface MessageListener {
        void onFinishEditing(final EntryObject content, final boolean isCanceled);
    }

    protected static final String ARG_CONTENT = "content";

    private MessageListener mMessageListener = null;

    public EditEntryObjectFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof EditEntryObjectFragment.MessageListener) {
            mMessageListener = (EditEntryObjectFragment.MessageListener) context;
        } else {
            throw new RuntimeException(context.toString()
            + " must implement EditCourseTimeBlockFragment.MessageListener");
        }
    }

    protected MessageListener getMessageListener() {
        return mMessageListener;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mMessageListener = null;
    }
}
