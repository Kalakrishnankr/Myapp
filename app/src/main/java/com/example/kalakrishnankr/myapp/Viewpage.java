package com.example.kalakrishnankr.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by kalakrishnan.kr on 17/5/17.
 */
public class Viewpage extends AppCompatActivity {
    String api, uname, pass;
    FactoryClass.ResponseMessage responseMessage;
    FactoryClass mfactory;
    Handler handler;
    private final int SUCCESS = 200;
    RecyclerView recyclerView;
    DBHelper dbHelper;
    Cursor resultset;
    RecyclerAdapter adapter;
    List<EmployeDetail>Employeelist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpage);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dbHelper = new DBHelper(this);
        Employeelist = new ArrayList<EmployeDetail>();
        resultset = dbHelper.getData();

        try {
            if (resultset != null){

                resultset.moveToFirst();
                do {
                    Employeelist.add(new EmployeDetail(resultset.getString(1),resultset.getString(2),resultset.getString(3),resultset.getString(4),resultset.getString(5),resultset.getString(6),resultset.getString(7)));
                }while (resultset.moveToNext());

            }
        }catch (Exception e)
        {
            Toast.makeText(Viewpage.this,"Nothing to display",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        if(Employeelist !=null || Employeelist.size() > 0){
            adapter=new RecyclerAdapter();
            recyclerView.setAdapter(adapter);

        }

        /*api = "getOverAllRatings/";

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case SUCCESS:
                        try {
                            Gson gson = new Gson();


                        }catch (Exception e){

                        }


                }


                return false;
            }
        });


        Thread thread = new Thread((new Runnable() {
            @Override
            public void run() {
                try{
                    responseMessage = mfactory.executeRequest(api,uname,pass);
                    if(responseMessage != null) {
                        switch (responseMessage.responseCode) {
                            case 200:
                            case 201:
                                handler.sendEmptyMessage(SUCCESS);



                        }
                    }

                }
                catch (Exception e){

                }

            }
        }));thread.start();


    }*/
    }
    public class RecyclerAdapter extends RecyclerView.Adapter<CustomViewHolder> {


        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view, null);

            CustomViewHolder viewHolder = new CustomViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final CustomViewHolder holder, int position) {

            holder.nm.setText(Employeelist.get(position).name);
            holder.sub.setText(Employeelist.get(position).dob);


            holder.rlm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String dob = holder.nm.getText().toString();
                    String sb = holder.sub.getText().toString();

                    Bundle args =new Bundle();
                    args.putString("dj",dob);
                    args.putString("sub",sb);

                    Intent intent = new Intent(Viewpage.this,Details.class);

                    startActivity(intent);

                }
            });


        }

        @Override
        public int getItemCount() {
            int len = 0;
            if(Employeelist != null){
                len=Employeelist.size();
            }
                return len;
        }

    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView nm,sub;
        RelativeLayout rlm;
        public CustomViewHolder(View itemView) {
            super(itemView);
            rlm = (RelativeLayout)itemView.findViewById(R.id.rlMain);
            nm = (TextView)itemView.findViewById(R.id.name_textview);
            sub = (TextView)itemView.findViewById(R.id.txtv_dob);


        }
    }
}