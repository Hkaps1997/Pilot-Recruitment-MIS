package com.example.harshit.mymis;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnDatePicker, btnTimePicker,submit;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay;
 String pDate;

    Calendar cal=Calendar.getInstance();

    int yy = cal.get(Calendar.YEAR);
    int mm = cal.get(Calendar.MONTH);
    int dd = cal.get(Calendar.DAY_OF_MONTH);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDatePicker=(Button)findViewById(R.id.btn_date);
        btnTimePicker=(Button)findViewById(R.id.btn_time);
        txtDate=(EditText)findViewById(R.id.in_date);
        txtTime=(EditText)findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                if(monthOfYear/10!=0&&dayOfMonth/10!=0)
                                    txtDate.setText(year+ "-" + (monthOfYear + 1) + "-" +dayOfMonth );

                                else if(monthOfYear/10!=0&&dayOfMonth/10==0)
                                    txtDate.setText(year+ "-" + (monthOfYear + 1) + "-0" +dayOfMonth );

                                else if(monthOfYear/10==0&&dayOfMonth/10!=0)
                                    txtDate.setText(year+ "-0" + (monthOfYear + 1) + "-" +dayOfMonth );
                                else
                                    txtDate.setText(year+ "-0" + (monthOfYear + 1) + "-0" +dayOfMonth );

                                pDate=dayOfMonth+"/"+(monthOfYear+1)+"/"+year;

                            }
                        }, mYear, mMonth, mDay);

//                Calendar cal=Calendar.getInstance();

//                cal.set(Calendar.MONTH,mm);
//                cal.set(Calendar.YEAR,yy);
//                cal.set(Calendar.DAY_OF_MONTH,dd-3);
//


                try {
                    datePickerDialog.getDatePicker().setMinDate(new SimpleDateFormat("dd/MM/yyyy").parse("18/07/2017").getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                datePickerDialog.show();


            }
        });




        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                if(monthOfYear/10!=0&&dayOfMonth/10!=0)
                                txtTime.setText(year+ "-" + (monthOfYear + 1) + "-" +dayOfMonth );

                                else if(monthOfYear/10!=0&&dayOfMonth/10==0)
                                    txtTime.setText(year+ "-" + (monthOfYear + 1) + "-0" +dayOfMonth );

                                else if(monthOfYear/10==0&&dayOfMonth/10!=0)
                                    txtTime.setText(year+ "-0" + (monthOfYear + 1) + "-" +dayOfMonth );
                                else
                                    txtTime.setText(year+ "-0" + (monthOfYear + 1) + "-0" +dayOfMonth );


                            }
                        }, mYear, mMonth, mDay);


                try {
                    datePickerDialog.getDatePicker().setMinDate(new SimpleDateFormat("dd/MM/yyyy").parse(pDate).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                datePickerDialog.show();

            }
        });

        submit=(Button)findViewById(R.id.button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ViewActivity.class);

                i.putExtra("stDate",txtDate.getText().toString());
                i.putExtra("etDate",txtTime.getText().toString());

                startActivity(i);

            }
        });


    }


}
