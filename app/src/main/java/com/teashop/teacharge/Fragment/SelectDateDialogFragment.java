package com.teashop.teacharge.Fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;

import com.bumptech.glide.load.engine.Resource;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.teashop.teacharge.R;
import com.teashop.teacharge.View.ProductsCategory;
import com.teashop.teacharge.View.TransactionHistory;
import com.teashop.teacharge.databinding.SelectDateDialogFragmentBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

public class SelectDateDialogFragment extends DialogFragment  {

    SelectDateDialogFragmentBinding binding;
    public static SelectDateDialogFragment newInstance() {
        return new SelectDateDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.select_date_dialog_fragment, container, false);

        return binding.getRoot();

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       binding.fromImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

                DatePicker(binding.fromDate);

           }
       });


       binding.toImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               DatePicker(binding.toDate);
           }
       });


       binding.ok.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               ((TransactionHistory)getActivity()).InitRecyclerView(binding.fromDate.getText().toString(),binding.toDate.getText().toString());
                getDialog().dismiss();
           }
       });
    }



    private void showDate(int year, int month, int day,TextView view) {
       view.setText(new StringBuilder().append(year).append("-")
                .append(month+1).append("-").append(day));
    }
    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getDialog().getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(lp);
    }

        void DatePicker(final TextView view)
        {

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

                    showDate(i,i1,i2,view);
                }
            }, year, month, day);
            dialog.getDatePicker().setMaxDate(c.getTimeInMillis());

            dialog.show();


        }


}
