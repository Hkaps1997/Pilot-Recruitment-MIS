package com.example.harshit.mymis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewActivity extends AppCompatActivity {

    private ProgressDialog loading;
    public static  String DATA_URL = "http://hkapswillrock.000webhostapp.com/mis.php"
            ;

    ArrayList<User> userList;

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        rv=(RecyclerView)findViewById(R.id.rv) ;


        userList=new ArrayList<>();
        Intent i=getIntent();

        final String stDate=i.getStringExtra("stDate");
        final  String etDate=i.getStringExtra("etDate");

        //Toast.makeText(this,stDate+"  "+etDate,Toast.LENGTH_LONG).show();

        loading = ProgressDialog.show(ViewActivity.this,"Please wait...","Fetching...",false,false);

       StringRequest stringRequest=new StringRequest(Request.Method.POST, DATA_URL, new Response.Listener<String>() {
            @Override
          public void onResponse(String response) {
                loading.dismiss();

                if(response.equals("Please enter all the values")||response.equals("Invalid Dates Entered")||response.equals("error loading"))
                {

                    Toast.makeText(ViewActivity.this,response,Toast.LENGTH_SHORT).show();
                    finish();
                }

                else
                   // Toast.makeText(ViewActivity.this,response,Toast.LENGTH_LONG).show();

                    showJSON(response);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loading.dismiss();

                Toast.makeText(ViewActivity.this,error.toString(),Toast.LENGTH_SHORT).show();

                finish();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params=new HashMap<String, String>();
                params.put("date1",stDate);
                params.put("date2",etDate);
                return params;


            }
        };

        RequestQueue queue= Volley.newRequestQueue(ViewActivity.this);
        queue.add(stringRequest);


       // Toast.makeText(ViewActivity.this,""+userList.size(),Toast.LENGTH_SHORT).show();








    }

    private void showJSON(String response) {



        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray result=jsonObject.getJSONArray("result");

            for(int i=0; i<result.length(); i++){

                JSONObject userData=result.getJSONObject(i);

               User user=new User(userData.getString("firstname"),
               userData.getString("lastname"),
                userData.getString("state"),
            userData.getString("gender"),
               userData.getString("category"),
               userData.getString("mobile"),
                userData.getString("email"),
              userData.getString("height"),
             userData.getString("bmi"),
               userData.getString("region"),
              userData.getString("qualification"),
                userData.getString("userid"));

                userList.add(user);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
       // Toast.makeText(ViewActivity.this,""+userList.size(),Toast.LENGTH_SHORT).show();
        userAdapter uAd=new userAdapter();

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(uAd);



    }
    class userHolder extends RecyclerView.ViewHolder{
        TextView fname,lname,state,gender,category,mobile,email,id,graduation,height,bmi,region,elligiblity;


        public userHolder(View itemView) {
            super(itemView);

           this.fname=(TextView)itemView.findViewById(R.id.firstname);
            this.lname=(TextView)itemView.findViewById(R.id.lastname);
            this.state=(TextView)itemView.findViewById(R.id.userState);
            this.gender=(TextView)itemView.findViewById(R.id.userGender);
            this.category=(TextView)itemView.findViewById(R.id.userCategory);
            this.mobile=(TextView)itemView.findViewById(R.id.userMobile);
            this.email=(TextView)itemView.findViewById(R.id.userEmail);
            this.id=(TextView)itemView.findViewById(R.id.userID);
            this.graduation=(TextView)itemView.findViewById(R.id.userGrad);
            this.height=(TextView)itemView.findViewById(R.id.userHeight);
            this.bmi=(TextView)itemView.findViewById(R.id.userBMI);
            this.region=(TextView)itemView.findViewById(R.id.userRegion);
            this.elligiblity=(TextView)itemView.findViewById(R.id.userEligiblity);
        }
    }

    class userAdapter extends RecyclerView.Adapter<userHolder>{

        @Override
        public userHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li=getLayoutInflater();
            View itemView=li.inflate(R.layout.activity_card,parent,false);

            return new userHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final userHolder holder, int position) {

            final User user=userList.get(position);
            Log.d("MA","USERLIST SIZE="+holder);
            holder.fname.setText("First Name: "+user.getfName());
            holder.lname.setText("Last Name: "+user.getlName());
            holder.state.setText("State: "+user.getState());
            holder.gender.setText("Gender: "+user.getGen());
            holder.category.setText("Category: "+user.getCat());
            holder.mobile.setText("Mobile: "+user.getMob());
            holder.email.setText("Email: "+user.getEmail());
            holder.height.setText("Height "+user.getHeight()+"cms");
            holder.bmi.setText("BMI: "+user.getBmi());
            holder.region.setText("Region: "+user.getRegion());
            holder.graduation.setText("Graduation: "+user.getGrad());

            if(user.getUserID().length()==4)
            holder.id.setText("User ID: "+user.getUserID());

            else
                holder.id.setText("User ID: "+user.getUserID().substring(0,4));

            int check=Integer.valueOf(user.getUserID())%10;

            if(check!=0)
                holder.elligiblity.setText("Elligible For Selection");
            else
                holder.elligiblity.setText("Not Elligible");







        }

        @Override
        public int getItemCount() {
            return userList.size();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
