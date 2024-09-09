package com.modestsoftware.mbload;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Offer extends AppCompatActivity {


    ImageView back;
    TabLayout offertab;
    FrameLayout offerfag;
    RelativeLayout offer_page,report,order_page;
    TabLayout tali_tab;
    LinearLayout rec_his,pay_his;

    RecyclerView report_list,take_list;

    CardView rules;

    HashMap<String,String> hashMap;
    ArrayList< HashMap<String,String> > arrayList;
    HashMap<String,String> hashMap1;
    ArrayList< HashMap<String,String> > arrayList1= new ArrayList<>();



    walletdbhelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer);

        tali_tab = findViewById(R.id.tali_tab);
        rec_his = findViewById(R.id.rec_his);
        pay_his = findViewById(R.id.pay_his);
        order_page = findViewById(R.id.order_page);
        take_list = findViewById(R.id.take_list);
        rules = findViewById(R.id.rules);

        back=findViewById(R.id.back);
        offertab=findViewById(R.id.offertab);
        offerfag = findViewById(R.id.offerfag);
        report= findViewById(R.id.report);
        offer_page = findViewById(R.id.offer_page);
        report_list = findViewById(R.id.report_list);
        helper= new walletdbhelper(Offer.this);

        ConnectivityManager connectivityManager =  (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


        Bundle bun=getIntent().getExtras();
        int val =bun.getInt("VAL");
        int user_id =bun.getInt("user_id");
        int type = bun.getInt("type");
        String ftitle =bun.getString("title");
        String flocation =bun.getString("location");
        String fprice =bun.getString("price");

        if(val==101) {

            offer_page.setVisibility(View.VISIBLE);

            if (type==10)
            {
                FragmentManager myfragment = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = myfragment.beginTransaction();
                fragmentTransaction.add(R.id.offerfag, new Bl());
                offertab.selectTab(offertab.getTabAt(1));
                fragmentTransaction.commit();
            }
            else if (type==1) {
                FragmentManager myfragment = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = myfragment.beginTransaction();
                fragmentTransaction.add(R.id.offerfag, new Robi());
                offertab.selectTab(offertab.getTabAt(2));
                fragmentTransaction.commit();
            }
            else if (type==2) {
                FragmentManager myfragment = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = myfragment.beginTransaction();
                fragmentTransaction.add(R.id.offerfag, new Airtel());
                offertab.selectTab(offertab.getTabAt(0));
                fragmentTransaction.commit();
            }
            else if (type==3) {
                FragmentManager myfragment = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = myfragment.beginTransaction();
                fragmentTransaction.add(R.id.offerfag, new Gp());
                offertab.selectTab(offertab.getTabAt(3));
                fragmentTransaction.commit();
            }
            else if (type==11) {
                FragmentManager myfragment = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = myfragment.beginTransaction();
                fragmentTransaction.add(R.id.offerfag, new Airtel());
                fragmentTransaction.commit();
            }




            offertab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                    int airtel = tab.getPosition();

                        if(airtel==0){

                            FragmentManager myfragment = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = myfragment.beginTransaction();
                            fragmentTransaction.add(R.id.offerfag, new Airtel());
                            fragmentTransaction.commit();

                        }

                        else if (airtel==1){

                            FragmentManager myfragment = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = myfragment.beginTransaction();
                            fragmentTransaction.add(R.id.offerfag, new Bl());
                            fragmentTransaction.commit();
                        }

                        else if (airtel==2){

                            FragmentManager myfragment = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = myfragment.beginTransaction();
                            fragmentTransaction.add(R.id.offerfag, new Robi());
                            fragmentTransaction.commit();

                        }

                        else if (airtel==3){

                            FragmentManager myfragment = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = myfragment.beginTransaction();
                            fragmentTransaction.add(R.id.offerfag, new Gp());
                            fragmentTransaction.commit();

                        }

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

        }

        else if(val==102) {
            offer_page.setVisibility(View.GONE);
            report.setVisibility(View.VISIBLE);

            String key = String.valueOf(user_id);
            get_data(helper.get_user_history(key));
            take_data(helper.take_user_history(key));


             tali_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                 @Override
                 public void onTabSelected(TabLayout.Tab tab) {

                     int tabitem = tab.getPosition();

                     if (tabitem==0){
                         rec_his.setVisibility(View.VISIBLE);
                         pay_his.setVisibility(View.GONE);
                     }
                     else if (tabitem==1) {
                         rec_his.setVisibility(View.GONE);
                         pay_his.setVisibility(View.VISIBLE);
                     }
                 }

                 @Override
                 public void onTabUnselected(TabLayout.Tab tab) {

                 }

                 @Override
                 public void onTabReselected(TabLayout.Tab tab) {

                 }
             });


        }

        else if (val==103){
            offer_page.setVisibility(View.GONE);
            report.setVisibility(View.GONE);
            order_page.setVisibility(View.VISIBLE);


            rules.setOnClickListener(v -> {

                Intent nextActivity = new Intent(Offer.this, AllActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("VAL", 3);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
            });

            TextView confirm = findViewById(R.id.confirm);
            TextView f_stitle = findViewById(R.id.sumbit_title);
            TextView f_sloc =findViewById(R.id.sumbit_loc);
            TextView price_dis = findViewById(R.id.price_dis);
            TextView bkash_num = findViewById(R.id.bkash_num);
            TextView nagad_num = findViewById(R.id.nagad_num);
            TextView rocket_num = findViewById(R.id.rocket_num);
            ImageView copy1 =findViewById(R.id.copy1);
            ImageView copy2 = findViewById(R.id.copy2);
            ImageView copy3 = findViewById(R.id.copy3);
            EditText all_num= findViewById(R.id.all_num);
            EditText all_num1= findViewById(R.id.all_num1);
            CheckBox check_bkash = findViewById(R.id.check_bkash);
            CheckBox check_nagad = findViewById(R.id.check_nagad);
            CheckBox check_rocket = findViewById(R.id.check_rocket);

           check_bkash.setOnClickListener(v -> {
               check_bkash.setChecked(true);
               check_nagad.setChecked(false);
               check_rocket.setChecked(false);
           });

            check_nagad.setOnClickListener(v -> {
                check_bkash.setChecked(false);
                check_nagad.setChecked(true);
                check_rocket.setChecked(false);
            });
            check_rocket.setOnClickListener(v -> {
                check_bkash.setChecked(false);
                check_nagad.setChecked(false);
                check_rocket.setChecked(true);
            });

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference("xnumber");

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String bkash_n = snapshot.child("bkash").getValue(String.class);
                    bkash_num.setText(bkash_n);
                    String nagad_n = snapshot.child("nagad").getValue(String.class);
                    nagad_num.setText(nagad_n);
                    String rocket_n = snapshot.child("rocket").getValue(String.class);
                    rocket_num.setText(rocket_n);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            f_sloc.setText(flocation);
            f_stitle.setText(ftitle);
            price_dis.setText("মাত্র "+fprice+"৳");


            copy1.setOnClickListener(v1 -> {
                Context context=Offer.this;
                ClipboardManager cm = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(bkash_num.getText());
                Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();

            });

            copy2.setOnClickListener(v1 -> {
                Context context=Offer.this;
                ClipboardManager cm = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(nagad_num.getText());
                Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();

            });

            copy3.setOnClickListener(v1 -> {
                Context context=Offer.this;
                ClipboardManager cm = (ClipboardManager)context.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(rocket_num.getText());
                Toast.makeText(context, "Copied", Toast.LENGTH_SHORT).show();

            });

            confirm.setOnClickListener(v1 -> {

                if(networkInfo!=null&&networkInfo.isConnected()){


                    if (all_num.length()==9 && all_num1.length()>0){

                        if (check_bkash.isChecked())
                        {
                            String all_num_f= all_num.getText().toString();
                            String all_num1_f= all_num1.getText().toString();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=+8801734814602 &text="+ftitle+"\n"+flocation+"\n"+fprice+" টাকা"+"\nNumber: 01"+all_num_f+"\nTrxID/last Number: "+all_num1_f+"\nPayment: Bkash"+"\n\nঅফারটি নিতে চাই।"));
                            startActivity(intent);
                        }
                        else if (check_nagad.isChecked()){
                            String all_num_f= all_num.getText().toString();
                            String all_num1_f= all_num1.getText().toString();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=+8801734814602 &text="+ftitle+"\n"+flocation+"\n"+fprice+" টাকা"+"\nNumber: 01"+all_num_f+"\nTrxID/last Number: "+all_num1_f+"\nPayment: Nagad"+"\n\nঅফারটি নিতে চাই।"));
                            startActivity(intent);
                        }

                        else if (check_rocket.isChecked()){
                            String all_num_f= all_num.getText().toString();
                            String all_num1_f= all_num1.getText().toString();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=+8801734814602 &text="+ftitle+"\n"+flocation+"\n"+fprice+" টাকা"+"\nNumber: 01"+all_num_f+"\nTrxID/last Number: "+all_num1_f+"\nPayment: Rocket"+"\n\nঅফারটি নিতে চাই।"));
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Offer.this, "Confirm Payment and select payment method", Toast.LENGTH_SHORT).show();
                        }


                    }
                    else {
                        all_num.setError("Enter Number");
                        all_num1.setError("Enter Number");

                    }


                }
                else{

                    no_internet(Offer.this);
                }


            });


        }







        back.setOnClickListener(v -> {
           onBackPressed();
       });






    }//=========================Oncreat eend===========================




    public void get_data(Cursor cursor){



        if (cursor!=null && cursor.getCount()>0){

            arrayList = new ArrayList<>();
            while (cursor.moveToNext()){

                int id = cursor.getInt(0);
                double amount= cursor.getDouble(1);
                String reason =cursor.getString(2);
                double time =cursor.getDouble(4);



                hashMap=new HashMap<>();
                hashMap.put("id",""+id);
                hashMap.put("amount",""+amount);
                hashMap.put("reason",reason);
                hashMap.put("time",""+time);
                arrayList.add(hashMap);

            }
            report_adapter adapter = new report_adapter();
            report_list.setAdapter(adapter);
            report_list.setLayoutManager(new LinearLayoutManager(Offer.this));

        }
        else {


        }

    }

    public void take_data(Cursor cursor){



        if (cursor!=null && cursor.getCount()>0){



            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                double amount= cursor.getDouble(1);
                String reason =cursor.getString(2);
                double time =cursor.getDouble(4);



                hashMap1=new HashMap<>();
                hashMap1.put("id",""+id);
                hashMap1.put("amount",""+amount);
                hashMap1.put("reason",reason);
                hashMap1.put("time",""+time);
                arrayList1.add(hashMap1);

            }
            adapter adapter = new adapter();
            take_list.setAdapter(adapter);
            take_list.setLayoutManager(new LinearLayoutManager(Offer.this));

        }
        else {
        }

    }


    public class report_adapter extends RecyclerView.Adapter<report_adapter.view_report>{


        public class view_report extends RecyclerView.ViewHolder{

              TextView user_amount,user_reason,user_time;
              LinearLayout user_item;
            public view_report(@NonNull View itemView) {
                super(itemView);
                user_amount=itemView.findViewById(R.id.user_amount);
                user_reason= itemView.findViewById(R.id.user_reason);
                user_time= itemView.findViewById(R.id.user_time);
                user_item = itemView.findViewById(R.id.user_item);
            }
        }

        @NonNull
        @Override
        public view_report onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.user_item,parent,false);
            return new view_report(view);
        }

        @Override
        public void onBindViewHolder(@NonNull view_report holder, int position) {

            HashMap<String,String>hashMap=arrayList.get(position);
            String fid = hashMap.get("id");
            String f_amount = hashMap.get("amount");
            String f_reason = hashMap.get("reason");
            String f_time = hashMap.get("time");

            holder.user_amount.setText("-"+f_amount+"৳");
            holder.user_amount.setTextColor(Color.parseColor("#DE0000"));
            holder.user_reason.setText(f_reason);
            double a_time = Double.parseDouble(f_time);
            SimpleDateFormat simpletimeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            SimpleDateFormat simpledateFormat = new SimpleDateFormat("dd MMM yyyy ", Locale.getDefault());

            String stime = simpletimeFormat.format(a_time);
            String date = simpledateFormat.format(a_time);

            holder.user_time.setText(stime+"\n "+date);

            holder.user_item.setOnClickListener(v -> {

                Bundle bun=getIntent().getExtras();
                int user_id =bun.getInt("user_id");
                up_and_delete_item1(fid,f_amount,f_reason,user_id);
            });

        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

    }


    public  class  adapter extends  RecyclerView.Adapter <adapter.view_h2>{



        public class view_h2 extends RecyclerView.ViewHolder{

            TextView user_amount1,user_reason1,user_time1;
            LinearLayout user_item1;
            public view_h2(@NonNull View itemView) {
                super(itemView);
                user_amount1=itemView.findViewById(R.id.user_amount);
                user_reason1= itemView.findViewById(R.id.user_reason);
                user_time1= itemView.findViewById(R.id.user_time);
                user_item1 = itemView.findViewById(R.id.user_item);
            }
        }
        @NonNull
        @Override
        public view_h2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view2 = layoutInflater.inflate(R.layout.user_item,parent,false);
            return new view_h2(view2);
        }

        @Override
        public void onBindViewHolder(@NonNull view_h2 holder, int position) {

            HashMap<String,String>hashMap=arrayList1.get(position);
            String fid = hashMap.get("id");
            String f_amount = hashMap.get("amount");
            String f_reason = hashMap.get("reason");
            String f_time = hashMap.get("time");

            holder.user_amount1.setText(f_amount+"৳");
            holder.user_amount1.setText("+"+f_amount+"৳");
            holder.user_reason1.setText(f_reason);
            double a_time = Double.parseDouble(f_time);
            SimpleDateFormat simpletimeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            SimpleDateFormat simpledateFormat = new SimpleDateFormat("dd MMM yyyy ", Locale.getDefault());

            String stime = simpletimeFormat.format(a_time);
            String date = simpledateFormat.format(a_time);

            holder.user_time1.setText(stime+"\n "+date);

            holder.user_item1.setOnClickListener(v -> {

                Bundle bun=getIntent().getExtras();
                int user_id =bun.getInt("user_id");
                up_and_delete_item2(fid,f_amount,f_reason,user_id);
            });

        }

        @Override
        public int getItemCount() {
            return arrayList1.size();
        }


    }

    private void up_and_delete_item1(String id,String amount, String title,int user_id) {

        Context context = Offer.this;
        BottomSheetDialog dialog = new BottomSheetDialog(this);

        // Inflate the layout for the BottomSheetDialog
        View view = getLayoutInflater().inflate(R.layout.up_user_item, null);

        // Set the layout for the BottomSheetDialog
        dialog.setContentView(view);

        EditText amount_e = view.findViewById(R.id.amount_up);
        EditText title_a = view.findViewById(R.id.reason_up);
        ImageView up_note= view.findViewById(R.id.up_n_item);
        ImageView close_up= view.findViewById(R.id.close_user_item);
        ImageView delete= view.findViewById(R.id.user_item_delete);

        amount_e.setText(amount);
        title_a.setText(title);


        up_note.setOnClickListener(v -> {

            if (amount_e.length()>0 && title_a.length()>0){


                String amount_d = amount_e.getText().toString().trim();
                Double dd = Double.parseDouble(amount_d);
                String title_S = title_a.getText().toString().trim();
                helper.up_rec_tali(id,dd,title_S,user_id);
                dialog.dismiss();
                ui_update();


            } else {
                amount_e.setError("Enter Number");
                title_a.setError("Enter text");

            }




        });

        delete.setOnClickListener(v -> {



            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setIcon(R.drawable.alert)
                    .setTitle("Delete!")
                    .setMessage("Are you sure?")

                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            helper.delete_rec(id);
                            ui_update();


                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .show();
            dialog.dismiss();
        });


        close_up.setOnClickListener(v -> {
            dialog.dismiss();
        });





        dialog.show();

    }

    private void up_and_delete_item2(String id,String amount, String title,int user_id) {

        Context context = Offer.this;
        BottomSheetDialog dialog = new BottomSheetDialog(this);

        // Inflate the layout for the BottomSheetDialog
        View view = getLayoutInflater().inflate(R.layout.up_user_item, null);

        // Set the layout for the BottomSheetDialog
        dialog.setContentView(view);

        EditText amount_e = view.findViewById(R.id.amount_up);
        EditText title_a = view.findViewById(R.id.reason_up);
        ImageView up_note= view.findViewById(R.id.up_n_item);
        ImageView close_up= view.findViewById(R.id.close_user_item);
        ImageView delete= view.findViewById(R.id.user_item_delete);


        amount_e.setText(amount);
        title_a.setText(title);


        up_note.setOnClickListener(v -> {

            if (amount_e.length()>0 && title_a.length()>0){


                String amount_d = amount_e.getText().toString().trim();
                Double dd = Double.parseDouble(amount_d);
                String title_S = title_a.getText().toString().trim();
                helper.up_pay_tali(id,dd,title_S,user_id);
                dialog.dismiss();
                ui_update();


            } else {
                amount_e.setError("Enter Number");
                title_a.setError("Enter text");

            }




        });

        delete.setOnClickListener(v -> {



            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setIcon(R.drawable.alert)
                    .setTitle("Delete!")
                    .setMessage("Are you sure?")

                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            helper.delete_pay(id);
                            ui_update();


                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    })
                    .show();
            dialog.dismiss();
        });


        close_up.setOnClickListener(v -> {
            dialog.dismiss();
        });





        dialog.show();

    }

   private void ui_update(){

    finish();
    startActivity(getIntent());

}

    private void no_internet (Context context) {
        // Inflate the custom layout
        View view = LayoutInflater.from(context).inflate(R.layout.no_internet, null);

        // Create AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);


        AlertDialog dialog = builder.create();
        view.findViewById(R.id.close).setOnClickListener(v -> {
            dialog.dismiss();


        });



        dialog.show();
    }



}