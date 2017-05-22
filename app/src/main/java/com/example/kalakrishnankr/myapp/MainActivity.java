package com.example.kalakrishnankr.myapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public EditText fn,ls,sal,dob,doj;
    Button rgst;
    Spinner spin,spin1,spin2;
    SimpleDateFormat sdf;
    String name,lname,salary,db,dj,dept,pos;
    DBHelper helper;

    Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(this);
        fn = (EditText) findViewById(R.id.name);
        ls = (EditText) findViewById(R.id.ln);
        sal = (EditText) findViewById(R.id.salary);
        rgst = (Button) findViewById(R.id.btn);
        spin = (Spinner) findViewById(R.id.spnner1);
        spin1 = (Spinner)findViewById(R.id.spinner2);
        dob = (EditText) findViewById(R.id.edit_dob);
        doj = (EditText) findViewById(R.id.edit_doj);

        spin.setOnItemSelectedListener(this);
        List<String> Departments= new ArrayList<String>();
        Departments.add("Programming");
        Departments.add("Testing");
        Departments.add("Outsourcing");
        Departments.add("Conuction");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Departments);
        spin.setAdapter(adapter);

        spin1.setOnItemSelectedListener(this);
        List<String>Position = new ArrayList<String>();
        Position.add("CEO");
        Position.add("Manager");
        Position.add("HR");
        Position.add("System Analyst");
        String compareValue = "Choose Value";
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,Position);
        spin1.setAdapter(adapter1);


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date date=myCalendar.getTime();
                dob.setText(sdf.format(date));

            }

        };
        final DatePickerDialog.OnDateSetListener date1 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                sdf = new SimpleDateFormat(myFormat, Locale.US);
                Date date=myCalendar.getTime();
                doj.setText(sdf.format(date));

            }

        };
        dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        doj.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MainActivity.this, date1, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        rgst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fn.getText().toString().length() == 0 ){
                    Toast.makeText(MainActivity.this,"Name cannot be blank",Toast.LENGTH_LONG).show();
                }else if (ls.getText().toString().length() == 0){
                    Toast.makeText(MainActivity.this,"Last Name cannot be blank",Toast.LENGTH_LONG).show();
                }
                else if(sal.getText().toString().length() == 0){
                    Toast.makeText(MainActivity.this,"Salary cannot be blank",Toast.LENGTH_LONG).show();
                }else {

                    name = fn.getText().toString();
                    lname = ls.getText().toString();
                    db = dob.getText().toString();
                    dj = doj.getText().toString();
                    dept = spin.getSelectedItem().toString();
                    pos = spin1.getSelectedItem().toString();
                    salary = sal.getText().toString();
                    helper.insertTable(name,lname,db,dj,dept,pos,salary);
                    if (true){
                        Toast.makeText(MainActivity.this,"Registration Successfull",Toast.LENGTH_LONG).show();
                        fn.setText("");
                        ls.setText("");
                        dob.setText("");
                        doj.setText("");
                        sal.setText("");
                    }else {
                        Toast.makeText(MainActivity.this,"Can you please try again",Toast.LENGTH_LONG).show();
                    }

                }

            }
        });


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String Dprtment = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(),"Selected Depatment",+Dprtment,Toast.LENGTH_LONG).show();
        String post     =  parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}
