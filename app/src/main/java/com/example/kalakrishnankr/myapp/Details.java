package com.example.kalakrishnankr.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

public class Details extends AppCompatActivity{

    //RecyclerAdapter  adapter;
    RecyclerView rcview;
    DBHelper dbHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailedemployee);
       // rcview = (RecyclerView)findViewById(R.id.rlMain);
       // rcview.setLayoutManager(new LinearLayoutManager(this));
        dbHelper =new DBHelper(this);


    }

    /*private class RecyclerAdapter extends RecyclerView.Adapter<CustomViewHolder> {
        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            holder.nm.setText(EmployeDetail);

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rltv;
        TextView nm,ln,dbh,djh,dept,post,sal;
        public CustomViewHolder(View itemView) {
            super(itemView);
            rltv = (RecyclerView)itemView.findViewById(R.id.rlMain);
            nm = (TextView)itemView.findViewById(R.id.name_textview);
            ln=(TextView)itemView.findViewById(R.id.txtv_ln);
            dbh=(TextView)itemView.findViewById(R.id.txtv_dob);
            djh=(TextView)itemView.findViewById(R.id.txtv_doj);
            dept=(TextView)itemView.findViewById(R.id.txtv_dpt);
            post=(TextView)itemView.findViewById(R.id.txtv_ptn);
            sal=(TextView)itemView.findViewById(R.id. txtv_sal);
        }
    }*/
}