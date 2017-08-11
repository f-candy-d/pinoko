package com.f_candy_d.pinoko.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.f_candy_d.pinoko.DayOfWeek;
import com.f_candy_d.pinoko.R;
import com.f_candy_d.pinoko.model.Event;
import com.f_candy_d.pinoko.model.Location;
import com.f_candy_d.pinoko.model.MergeableTimeBlock;
import com.f_candy_d.pinoko.utils.LayoutContentsSwitcher;
import com.f_candy_d.pinoko.utils.TimeBlockFormer;

import java.util.ArrayList;
import java.util.Calendar;

public class EditEventTimeBlockFragment extends EditEntryObjectFragment<MergeableTimeBlock<Event>> {

    private enum FieldErrorCode {
        INVALID_TIME,
        INVALID_DATE,
        INVALID_DOW,
        EVENT_NAME_IS_EMPTY
    }

    private static final String ARG_TIME_TABLE_ID = "timeTableId";

    // Field data
    private TimeBlockFormer.Type mType;
    private Calendar mDateBegin;
    private Calendar mDateEnd;
    private DayOfWeek mDayOfWeek;
    private Event mEvent;

    // UI
    private EditText mInputEventNameBox;
    private EditText mInputEventNoteBox;

    // Misc
    @Nullable
    private MergeableTimeBlock<Event> mContent;
    private int mTimeTableId;
    private LayoutContentsSwitcher<TimeBlockFormer.Type> mLayoutContentsSwitcher;


    public EditEventTimeBlockFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment EditEventTimeBlockFragment.
     */
    public static EditEventTimeBlockFragment newInstance(final int timeTableId,
                                                         final MergeableTimeBlock<Event> timeBlock) {
        EditEventTimeBlockFragment fragment = new EditEventTimeBlockFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CONTENT, timeBlock);
        args.putInt(ARG_TIME_TABLE_ID, timeTableId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mContent = getArguments().getParcelable(ARG_CONTENT);
            mTimeTableId = getArguments().getInt(ARG_TIME_TABLE_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_event_time_block, container, false);
        init();
        initUI(view);
        return view;
    }

    private void init() {
        mType = TimeBlockFormer.Type.WEEKLY;
        mDateBegin = Calendar.getInstance();
        mDateEnd = Calendar.getInstance();
        mDayOfWeek = DayOfWeek.from(mDateBegin.get(Calendar.DAY_OF_WEEK));
        // Init event object
        mEvent = new Event();
        mEvent.setName(null);
        mEvent.setNote(null);
    }

    private void initUI(@NonNull final View view) {
        //LayoutContents
        FrameLayout contentsContainer = (FrameLayout) view.findViewById(R.id.config_contents_feetb);
        mLayoutContentsSwitcher = new LayoutContentsSwitcher<>(TimeBlockFormer.Type.class, contentsContainer);
        ConfigTimeAndDayOfWeekContents configTimeAndDayOfWeekContets = new ConfigTimeAndDayOfWeekContents();
        mLayoutContentsSwitcher.addLayout(TimeBlockFormer.Type.ONE_DAY, new ConfigDateAndTimeContents());
        mLayoutContentsSwitcher.addLayout(TimeBlockFormer.Type.EVERYDAY, new ConfigTimeContents());
        mLayoutContentsSwitcher.addLayout(TimeBlockFormer.Type.WEEKLY, configTimeAndDayOfWeekContets);
        mLayoutContentsSwitcher.setCurrentLayout(TimeBlockFormer.Type.WEEKLY);
        // Setup initial contents
        setupConfigTimeAndDayOfWeekContents(configTimeAndDayOfWeekContets);

        // Select time-block type button
        final Button typeBUtton = (Button) view.findViewById(R.id.type_feetb);
        typeBUtton.setText(mType.toString());
        typeBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SingleChoiceItemPickerFragment<TimeBlockFormer.Type> picker =
                        SingleChoiceItemPickerFragment.newInstance(
                                TimeBlockFormer.Type.values(),
                                mType,
                                new TimeBlockFormer.Type[]{TimeBlockFormer.Type.NULL_TYPE});
                picker.setTitle("Select a type");
                picker.setEventListener(new SingleChoiceItemPickerFragment.EventListener<TimeBlockFormer.Type>() {
                    @Override
                    public void onItemSelected(SingleChoiceItemPickerFragment<TimeBlockFormer.Type> picker) {
                        TimeBlockFormer.Type selectedType = picker.getSelectedItem();
                        if (selectedType != mType) {
                            switchConfigLayoutContents(selectedType);
                            typeBUtton.setText(selectedType.toString());
                            mType = selectedType;
                        }
                    }
                });
                picker.show(getActivity().getSupportFragmentManager(), null);
            }
        });

        // Select location button
        final Button locationButton = (Button) view.findViewById(R.id.location_feetb);
        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationPickerFragment pickerFragment = LocationPickerFragment.newInstance(mEvent.getLocations());
                pickerFragment.setEventListener(new LocationPickerFragment.EventListener() {
                    @Override
                    public void onLocationsSelected(LocationPickerFragment picker) {
                        ArrayList<Location> selectedLocations = picker.getSelectedLocations();
                        if (selectedLocations.size() != 0) {
                            locationButton.setText(selectedLocations.get(0).getName());
                        } else {
                            locationButton.setText("LOCATION NOT SELECTED");
                        }
                        mEvent.setLocations(selectedLocations);
                    }
                });
                pickerFragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });

        // Cancel button
        Button cancelButton = (Button) view.findViewById(R.id.cancel_button_feetb);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishEditing(true);
            }
        });

        // OK button
        Button okButton = (Button) view.findViewById(R.id.ok_button_feetb);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishEditing(false);
            }
        });

        // Text box where input event name and note
        mInputEventNameBox = (EditText) view.findViewById(R.id.event_name_feetb);
        mInputEventNoteBox = (EditText) view.findViewById(R.id.event_note_feetb);
    }

    private void finishEditing(final boolean isCanceled) {
        if (!isCanceled) {
            FieldErrorCode[] errorCodes = isFieldValid();
            if (errorCodes.length == 0) {
                if (mContent == null) {
                    mContent = new MergeableTimeBlock<>(Event.class);
                }

                mEvent.setName(mInputEventNameBox.getText().toString());
                if (mInputEventNoteBox.getText().toString().length() != 0) {
                    mEvent.setNote(null);
                }

                mContent.setDatetimeBegin(mDateBegin.getTimeInMillis());
                mContent.setDatetimeEnd(mDateEnd.getTimeInMillis());
                mContent.setCategory(TimeBlockFormer.Category.EVENT);
                mContent.setType(mType);
                mContent.setDayOfWeek(mDayOfWeek);
                mContent.setTargetId(mEvent.getId());
                mContent.setContent(mEvent);
                mContent.setTimeTableId(mTimeTableId);
                getMessageListener().onFinishEditing(mContent, false);

            } else {
                // TODO; When the field is invalid...
                Toast.makeText(getActivity(), "error # " + TextUtils.join(",", errorCodes), Toast.LENGTH_LONG).show();
            }

        } else {
            getMessageListener().onFinishEditing(mContent, true);
        }
    }

    private FieldErrorCode[] isFieldValid() {
        ArrayList<FieldErrorCode> errorCodes = new ArrayList<>();
        if (!isTimeValid()) {
            errorCodes.add(FieldErrorCode.INVALID_TIME);
        }

        if (mInputEventNameBox.getText().toString().length()  == 0) {
            errorCodes.add(FieldErrorCode.EVENT_NAME_IS_EMPTY);
        }

        switch (mType) {
            case ONE_DAY:
                if (!isDateValid()) {
                    errorCodes.add(FieldErrorCode.INVALID_DATE);
                }
                break;

            case WEEKLY:
                if (mDayOfWeek == DayOfWeek.NULL_VALUE) {
                    errorCodes.add(FieldErrorCode.INVALID_DOW);
                }
                break;

            case EVERYDAY:
                break;

            default:
                throw new IllegalStateException();
        }

        return errorCodes.toArray(new FieldErrorCode[]{});
    }

    private boolean isTimeValid() {
        final int bHour = mDateBegin.get(Calendar.HOUR_OF_DAY);
        final int bMin = mDateBegin.get(Calendar.MINUTE);
        final int eHour = mDateEnd.get(Calendar.HOUR_OF_DAY);
        final int eMin = mDateEnd.get(Calendar.MINUTE);

        return (bHour < eHour) || (bHour == eHour && bMin <= eMin);
    }

    private boolean isDateValid() {
        return (mDateBegin.get(Calendar.YEAR) == mDateEnd.get(Calendar.YEAR) &&
                mDateBegin.get(Calendar.MONTH) == mDateEnd.get(Calendar.MONTH) &&
                mDateBegin.get(Calendar.DAY_OF_MONTH) == mDateEnd.get(Calendar.DAY_OF_MONTH));
    }

    private void setupConfigDateAndTimeContents(@NonNull final ConfigDateAndTimeContents layoutContents) {
        // Select date button
        layoutContents.getDateButton().setText(formatDate(
                mDateBegin.get(Calendar.YEAR),
                mDateBegin.get(Calendar.MONTH),
                mDateBegin.get(Calendar.DAY_OF_MONTH)));
        layoutContents.getDateButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment pickerFragment = DatePickerFragment.newInstance(
                        mDateBegin.get(Calendar.YEAR),
                        mDateBegin.get(Calendar.MONTH),
                        mDateBegin.get(Calendar.DAY_OF_MONTH));
                pickerFragment.setEventListener(new DatePickerFragment.EventListener() {
                    @Override
                    public void onDateSelected(DatePickerFragment picker) {
                        int y = picker.getYear();
                        int m = picker.getMonth();
                        int d = picker.getDay();
                        String message = formatDate(y, m, d);

                        mDateBegin.set(Calendar.YEAR, y);
                        mDateBegin.set(Calendar.MONTH, m);
                        mDateBegin.set(Calendar.DAY_OF_MONTH, d);
                        mDateEnd.set(Calendar.YEAR, y);
                        mDateEnd.set(Calendar.MONTH, m);
                        mDateEnd.set(Calendar.DAY_OF_MONTH, d);
                        layoutContents.getDateButton().setText(message);
                    }
                });
                pickerFragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });

        // Select begin time button
        layoutContents.getBeginTimeButton().setText(formatTime(
                mDateBegin.get(Calendar.HOUR_OF_DAY),
                mDateBegin.get(Calendar.MINUTE)));
        layoutContents.getBeginTimeButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment pickerFragment = TimePickerFragment.newInstance(
                        mDateBegin.get(Calendar.HOUR_OF_DAY),
                        mDateBegin.get(Calendar.MINUTE),
                        true);
                pickerFragment.setEventListener(new TimePickerFragment.EventListener() {
                    @Override
                    public void onTimeSelected(TimePickerFragment picker) {
                        int h = picker.getHour();
                        int m = picker.getMinute();
                        String message = "[BEGIN] " + formatTime(h, m);

                        mDateBegin.set(Calendar.HOUR_OF_DAY, h);
                        mDateBegin.set(Calendar.MINUTE, m);
                        layoutContents.getBeginTimeButton().setText(message);
                    }
                });
                pickerFragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });

        // Select end time button
        layoutContents.getEndTimeButton().setText(formatTime(
                mDateEnd.get(Calendar.HOUR_OF_DAY),
                mDateEnd.get(Calendar.MINUTE)));
        layoutContents.getEndTimeButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment pickerFragment = TimePickerFragment.newInstance(
                        mDateEnd.get(Calendar.HOUR_OF_DAY),
                        mDateEnd.get(Calendar.MINUTE),
                        true);
                pickerFragment.setEventListener(new TimePickerFragment.EventListener() {
                    @Override
                    public void onTimeSelected(TimePickerFragment picker) {
                        int h = picker.getHour();
                        int m = picker.getMinute();
                        String message = "[END] " + formatTime(h, m);

                        mDateEnd.set(Calendar.HOUR_OF_DAY, h);
                        mDateEnd.set(Calendar.MINUTE, m);
                        layoutContents.getEndTimeButton().setText(message);
                    }
                });
                pickerFragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });
    }

    private void setupConfigTimeContents(@NonNull final ConfigTimeContents layoutContents) {
        // Select begin time button
        layoutContents.getBeginTimeButton().setText(formatTime(
                mDateBegin.get(Calendar.HOUR_OF_DAY),
                mDateBegin.get(Calendar.MINUTE)));
        layoutContents.getBeginTimeButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment pickerFragment = TimePickerFragment.newInstance(
                        mDateBegin.get(Calendar.HOUR_OF_DAY),
                        mDateBegin.get(Calendar.MINUTE),
                        true);
                pickerFragment.setEventListener(new TimePickerFragment.EventListener() {
                    @Override
                    public void onTimeSelected(TimePickerFragment picker) {
                        int h = picker.getHour();
                        int m = picker.getMinute();
                        String message = "[BEGIN] " + formatTime(h, m);

                        mDateBegin.set(Calendar.HOUR_OF_DAY, h);
                        mDateBegin.set(Calendar.MINUTE, m);
                        layoutContents.getBeginTimeButton().setText(message);
                    }
                });
                pickerFragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });

        // Select end time button
        layoutContents.getEndTimeButton().setText(formatTime(
                mDateEnd.get(Calendar.HOUR_OF_DAY),
                mDateEnd.get(Calendar.MINUTE)));
        layoutContents.getEndTimeButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerFragment pickerFragment = TimePickerFragment.newInstance(
                        mDateEnd.get(Calendar.HOUR_OF_DAY),
                        mDateEnd.get(Calendar.MINUTE),
                        true);
                pickerFragment.setEventListener(new TimePickerFragment.EventListener() {
                    @Override
                    public void onTimeSelected(TimePickerFragment picker) {
                        int h = picker.getHour();
                        int m = picker.getMinute();
                        String message = "[END] " + formatTime(h, m);

                        mDateEnd.set(Calendar.HOUR_OF_DAY, h);
                        mDateEnd.set(Calendar.MINUTE, m);
                        layoutContents.getEndTimeButton().setText(message);
                    }
                });
                pickerFragment.show(getActivity().getSupportFragmentManager(), null);
            }
        });
    }

    private void setupConfigTimeAndDayOfWeekContents
            (@NonNull final ConfigTimeAndDayOfWeekContents layoutContents) {

        setupConfigTimeContents(layoutContents.getConfigTimeContents());
        // Select a day of the week button
        layoutContents.getDayOfWeekButton().setText(mDayOfWeek.toString());
        layoutContents.getDayOfWeekButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingleChoiceItemPickerFragment<DayOfWeek> picker =
                        SingleChoiceItemPickerFragment.newInstance(
                                DayOfWeek.values(),
                                mDayOfWeek,
                                new DayOfWeek[]{DayOfWeek.NULL_VALUE});

                picker.setEventListener(new SingleChoiceItemPickerFragment.EventListener<DayOfWeek>() {
                    @Override
                    public void onItemSelected(SingleChoiceItemPickerFragment<DayOfWeek> picker) {
                        mDayOfWeek = picker.getSelectedItem();
                        layoutContents.getDayOfWeekButton().setText(mDayOfWeek.toString());
                    }
                });
                picker.setTitle("Select a day of the week");
                picker.show(getActivity().getSupportFragmentManager(), null);
            }
        });
    }

    private void switchConfigLayoutContents(@NonNull final TimeBlockFormer.Type key) {
        mLayoutContentsSwitcher.switchLayout(key);
        switch (key) {
            case ONE_DAY:
                setupConfigDateAndTimeContents((ConfigDateAndTimeContents) mLayoutContentsSwitcher.getCurrentLayout());
                break;

            case EVERYDAY:
                setupConfigTimeContents((ConfigTimeContents) mLayoutContentsSwitcher.getCurrentLayout());
                break;

            case WEEKLY:
                setupConfigTimeAndDayOfWeekContents((ConfigTimeAndDayOfWeekContents) mLayoutContentsSwitcher.getCurrentLayout());
                break;

            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * TODO; REMOVE THIS METHOD. THIS IS TEST CODE.
     */
    private String formatTime(int hour, int minute) {
        String h = String.valueOf(hour);
        String m = String.valueOf(minute);

        if (h.length() == 1) {
            h = "0" + h;
        }

        if (m.length() == 1) {
            m = "0" + m;
        }

        return h + " : " + m;
    }

    /**
     * TODO; REMOVE THIS METHOD. THIS IS TEST CODE.
     */
    private String formatDate(int year, int month, int day) {
        String y = String.valueOf(year);
        String m = String.valueOf(month);
        String d = String.valueOf(day);

        if (m.length() == 1) {
            m = "0" + m;
        }

        if (d.length() == 1) {
            d = "0" + d;
        }

        return y + "/" + m + "/" + d;
    }
}
