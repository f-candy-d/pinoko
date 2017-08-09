package com.f_candy_d.pinoko.controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.f_candy_d.pinoko.EntryObjectType;
import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.model.Course;
import com.f_candy_d.pinoko.model.EntryObject;
import com.f_candy_d.pinoko.model.MergeableTimeBlock;
import com.f_candy_d.pinoko.view.EditTimeBlockFragment;

public class EditEntryObjectActivity extends AppCompatActivity {

    private static final String EXTRA_ENTRY_OBJECT = "entryObject";
    private static final String EXTRA_ENTRY_OBJECT_TYPE = "entryObjectType";

    private EntryObject mContent;
    private EntryObjectType mContentType;

    public static Intent createIntentWithArg(@NonNull final EntryObject entryObject) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ENTRY_OBJECT, entryObject);
        intent.putExtra(EXTRA_ENTRY_OBJECT_TYPE, entryObject.getEntryObjectType());

        return intent;
    }

    public static Intent createIntent(@NonNull final EntryObjectType entryObjectType) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_ENTRY_OBJECT_TYPE, entryObjectType);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry_object);

        final Intent intent = getIntent();
        mContent = intent.getParcelableExtra(EXTRA_ENTRY_OBJECT);
        mContentType = (EntryObjectType) intent.getSerializableExtra(EXTRA_ENTRY_OBJECT_TYPE);

        launchFragment();
    }

    private void launchFragment() {
        Fragment fragment = null;

        switch (mContentType) {
            case MERGABLE_TIME_BLOCK:
                if (mContent != null) {
                    MergeableTimeBlock timeBlock = (MergeableTimeBlock) mContent;
                        fragment = EditTimeBlockFragment.newInstance(timeBlock);

                } else {
                    fragment = EditTimeBlockFragment.newInstance(null);
                }

                break;
        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(
                    R.id.fragment_container, fragment, null).commit();
        }
    }
}
