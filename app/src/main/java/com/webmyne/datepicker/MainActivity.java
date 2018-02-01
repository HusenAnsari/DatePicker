package com.webmyne.datepicker;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private EditText edtStart, edtEnd, sdate, edate;
    private TextView txtCalculate, txtResult, txtCdate, txtRdate;
    int val = 0;
    private Calendar calendar1;
    private String strStart, strEnd, strSdate, strEdate;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }


    private void init() {
        edtStart = (EditText) findViewById(R.id.edtStartdate);
        edtEnd = (EditText) findViewById(R.id.edtEnddate);
        txtCalculate = (TextView) findViewById(R.id.txtCalculate);
        txtResult = (TextView) findViewById(R.id.txtResult);

        sdate = (EditText) findViewById(R.id.sDate);
        edate = (EditText) findViewById(R.id.eDate);
        txtCdate = (TextView) findViewById(R.id.cDate);
        txtRdate = (TextView) findViewById(R.id.rDate);
        context = this;



        edtStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatedialog(edtStart);
                val = 1;
            }
        });

        edtEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatedialog(edtEnd);
                val = 2;
            }
        });

        sdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatedialog(sdate);
                val = 3;
            }
        });

        edate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatedialog(edate);
                val = 4;
            }
        });

        txtCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation1();
                SimpleDateFormat myFormat = new SimpleDateFormat("dd MMM, yyyy");
                String startDate = edtStart.getText().toString().trim();
                String endDate = edtEnd.getText().toString().trim();

                try {
                    Date date1 = myFormat.parse(startDate);
                    Date date2 = myFormat.parse(endDate);
                    long diff = date2.getTime() - date1.getTime();
                    txtResult.setText("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                    // System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

        });

        txtCdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkValidation2();
                SimpleDateFormat myFormat = new SimpleDateFormat("dd MMM, yyyy");
                String startDate = sdate.getText().toString().trim();
                String endDate = edate.getText().toString().trim();
                try {
                    Date date1 = myFormat.parse(startDate);
                    Date date2 = myFormat.parse(endDate);
                    long diff = date2.getTime() - date1.getTime();

                    if (date1.after(date2)){
                        Toast.makeText(context, "PLEAS SELECT PROPER START DATE", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    txtRdate.setText("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                    // System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void checkValidation1() {
        strStart = edtStart.getText().toString().trim();
        strEnd = edtEnd.getText().toString().trim();


        if (TextUtils.isEmpty(strStart)) {
            Toast.makeText(context, "Please Enter Start Date", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(strEnd)) {
            Toast.makeText(context, "Please Enter End Date", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    private void checkValidation2() {
        strSdate = sdate.getText().toString().trim();
        strEdate = edate.getText().toString().trim();

        if (TextUtils.isEmpty(strSdate)) {
            Toast.makeText(context, "Please Enter End Date", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(strEdate)) {
            Toast.makeText(context, "Please Enter End Date", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    private void showDatedialog(EditText edt) {

        if (edt == edtStart) {
            calendar1 = Calendar.getInstance();

            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    calendar1.set(Calendar.YEAR, year);
                    calendar1.set(Calendar.MONTH, month);
                    calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    updateLable(val);

                }
            }, calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMaxDate(calendar1.getTimeInMillis());
            datePickerDialog.show();

        } else if (edt == edtEnd) {
            calendar1 = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    calendar1.set(Calendar.YEAR, year);
                    calendar1.set(Calendar.MONTH, month);
                    calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    updateLable(val);

                }
            }, calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMinDate(calendar1.getTimeInMillis());
            datePickerDialog.show();

        } else if (edt == sdate) {
            calendar1 = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    calendar1.set(Calendar.YEAR, year);
                    calendar1.set(Calendar.MONTH, month);
                    calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    updateLable(val);

                }
            }, calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH));
            // datePickerDialog.getDatePicker().setMaxDate(calendar1.getTimeInMillis());
            datePickerDialog.show();

        } else if (edt == edate) {

            if (TextUtils.isEmpty(sdate.getText().toString().trim())) {
                Toast.makeText(context, "Please Select Start Date First", Toast.LENGTH_SHORT).show();
                return;
            }
           /* try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMM, yyyy");
                strSdate = sdate.getText().toString().trim();
                Date sdate = formatter.parse(strSdate);
                strEdate = edate.getText().toString().trim();
                Date edate = formatter.parse(strEdate);

                if (sdate.after(edate)){
                    Toast.makeText(this, "Start aaaaaaaa", Toast.LENGTH_SHORT).show();

                }

            } catch (ParseException e) {
                e.printStackTrace();
            }*/
            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                    calendar1.set(Calendar.YEAR, year);
                    calendar1.set(Calendar.MONTH, month);
                    calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    updateLable(val);

                }
            }, calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.getDatePicker().setMinDate(calendar1.getTimeInMillis());
            datePickerDialog.show();
        }

    }

    private void updateLable(int val) {
        String myFormat = "dd MMM, yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        if (val == 1) {
            edtStart.setText(sdf.format(calendar1.getTime()));
        } else if (val == 2) {
            edtEnd.setText(sdf.format(calendar1.getTime()));
        } else if (val == 3) {
            sdate.setText(sdf.format(calendar1.getTime()));
        } else if (val == 4) {
            edate.setText(sdf.format(calendar1.getTime()));
        }
    }
}
