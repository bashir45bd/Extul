package com.modestsoftware.mbload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.HashMap;

public class Codeshower extends AppCompatActivity {


    TextInputEditText rec_amount,rec_dec,pay_amount,pay_dec;
    CardView rec_add,pay_add,rec_submit,pay_submit;
    LinearLayout rec_page,pay_page,tali;
    TextView tali_title,tv_dis;

    Animation item_anim;

    boolean color=true;
    boolean color3=true;


    public static String tali_title_1="";
    public static String tv_dis1="";


    RelativeLayout robi,airtel,gp,bl,secret_code;

    RecyclerView recyclerview_ar,recyclerview_robi,recyclerview_gp,recyclerview_bl,recyclerview_scode;

    HashMap<String,String> hashMap,hashMap_ar,hashMap_gp,hashMap_bl;
    ArrayList< HashMap<String,String> > arrayList = new ArrayList<>();
    ArrayList< HashMap<String,String> > arrayList_ar = new ArrayList<>();
    ArrayList< HashMap<String,String> > arrayList_gp = new ArrayList<>();
    ArrayList< HashMap<String,String> > arrayList_bl = new ArrayList<>();

    public static ArrayList< HashMap<String,String> > arrayList_scode = new ArrayList<>();

    TextView bar_text,text1,text2;
    ImageView s_shower,tali_back,dots,call,msg,whatapps_msg;

    public static String bartext= "";

    walletdbhelper helper;
    LinearLayout color1,color2;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codeshower);



        rec_amount = findViewById(R.id.rec_amount);
        rec_dec = findViewById(R.id.rec_dec);
        pay_amount = findViewById(R.id.pay_amount);
        pay_dec = findViewById(R.id.pay_dec);
        dots = findViewById(R.id.dots);
        tv_dis = findViewById(R.id.tv_dis);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        call = findViewById(R.id.call);
        msg = findViewById(R.id.msg);
        whatapps_msg= findViewById(R.id.whatapps_msg);


        rec_add = findViewById(R.id.rec_add);
        pay_add = findViewById(R.id.pay_add);
        rec_submit = findViewById(R.id.rec_submit);
        pay_submit = findViewById(R.id.pay_submit);
        rec_page = findViewById(R.id.rec_page);
        pay_page = findViewById(R.id.pay_page);
        tali_title = findViewById(R.id.tali_title);
        tali_back = findViewById(R.id.tali_back);
        color1 = findViewById(R.id.color1);
        color2 = findViewById(R.id.color2);



        robi= findViewById(R.id.robi);
        airtel = findViewById(R.id.airtel);
        gp = findViewById(R.id.gp);
        bl = findViewById(R.id.bl);
        secret_code = findViewById(R.id.secret_code);
        s_shower = findViewById(R.id.s_shower);
        bar_text = findViewById(R.id.bar_text);
        tali = findViewById(R.id.tali);


        recyclerview_robi = findViewById(R.id.recyclerview_robi);
        recyclerview_ar = findViewById(R.id.recyclerview_ar);
        recyclerview_gp = findViewById(R.id.recyclerview_gp);
        recyclerview_bl = findViewById(R.id.recyclerview_bl);
        recyclerview_scode= findViewById(R.id.recyclerview_scode);

        helper=new walletdbhelper(Codeshower.this);


        bar_text.setText(bartext);

        Bundle bun=getIntent().getExtras();
        int val =bun.getInt("VAL");
        int user_id =bun.getInt("user_id");
        String number =bun.getString("address");
        double taka =bun.getDouble("taka");


        if(val==91) {
            robi.setVisibility(View.VISIBLE);

            recyclerview_robi.addItemDecoration(new DividerItemDecoration(Codeshower.this,
                    DividerItemDecoration.VERTICAL));
            robi_data();
            rey_adapter_robi myadapter_robi = new rey_adapter_robi();
            recyclerview_robi.setAdapter(myadapter_robi);
            recyclerview_robi.setLayoutManager(new LinearLayoutManager(Codeshower.this));

        }
        else if(val==92) {
            robi.setVisibility(View.GONE);
            airtel.setVisibility(View.VISIBLE);


            recyclerview_ar.addItemDecoration(new DividerItemDecoration(Codeshower.this,
                    DividerItemDecoration.VERTICAL));
            ar_data();
            rey_adapter_ar myadapter_ar = new rey_adapter_ar();
            recyclerview_ar.setAdapter(myadapter_ar);
            recyclerview_ar.setLayoutManager(new LinearLayoutManager(Codeshower.this));

        }
        else if(val==93) {
            robi.setVisibility(View.GONE);
            airtel.setVisibility(View.GONE);
            gp.setVisibility(View.VISIBLE);


            recyclerview_gp.addItemDecoration(new DividerItemDecoration(Codeshower.this,
                    DividerItemDecoration.VERTICAL));
            gp_data();
            rey_adapter_gp myadapter_gp = new rey_adapter_gp();
            recyclerview_gp.setAdapter(myadapter_gp);
            recyclerview_gp.setLayoutManager(new LinearLayoutManager(Codeshower.this));

        }
        else if(val==94) {
            robi.setVisibility(View.GONE);
            airtel.setVisibility(View.GONE);
            gp.setVisibility(View.GONE);
            bl.setVisibility(View.VISIBLE);


            recyclerview_bl.addItemDecoration(new DividerItemDecoration(Codeshower.this,
                    DividerItemDecoration.VERTICAL));
            bl_data();
            rey_adapter_bl myadapter_bl = new rey_adapter_bl();
            recyclerview_bl.setAdapter(myadapter_bl);
            recyclerview_bl.setLayoutManager(new LinearLayoutManager(Codeshower.this));

        }
        else if(val==95) {
            robi.setVisibility(View.GONE);
            airtel.setVisibility(View.GONE);
            gp.setVisibility(View.GONE);
            bl.setVisibility(View.GONE);
            secret_code.setVisibility(View.VISIBLE);


            s_code mys_code = new s_code();
            recyclerview_scode.setAdapter(mys_code);
            recyclerview_scode.setLayoutManager(new LinearLayoutManager(Codeshower.this));

        }

        else if(val==96) {
            robi.setVisibility(View.GONE);
            airtel.setVisibility(View.GONE);
            gp.setVisibility(View.GONE);
            bl.setVisibility(View.GONE);
            secret_code.setVisibility(View.GONE);
            tali.setVisibility(View.VISIBLE);

           String name= MainActivity.sharedPreferences.getString("tali_name","");


            call.setOnClickListener(v -> {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+"01"+number));
                startActivity(intent);
            });

            msg.setOnClickListener(v -> {

                if (taka>0){

                    Uri uri = Uri.parse("smsto:01"+number+"");
                    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                    intent.putExtra("sms_body", "আপনার কাছে "+taka+"৳ পাওনা আছে।\nঅনুগ্রহ করে টাকা পরিশোধ করুন।\n\n"+name+"\nধন্যবাদ।");
                    startActivity(intent);
                }
                else if (taka<0) {
                    double f_taka= taka*-1;
                    Uri uri = Uri.parse("smsto:01"+number+"");
                    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                    intent.putExtra("sms_body", "আপনি আমার নিকট "+f_taka+"৳ পাবেন।\nখুব দ্রুত আপনার টাকা পরিশোধ করব।\n\n"+name+"\nধন্যবাদ।");
                    startActivity(intent);
                }

                else if (taka==0) {

                    Uri uri = Uri.parse("smsto:01"+number+"");
                    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                    intent.putExtra("sms_body", "আপনার সাথে কোনো দেনা পাওনা নেই।\n\n"+name+"\nধন্যবাদ।");
                    startActivity(intent);
                }


            });

            whatapps_msg.setOnClickListener(v -> {


                if (taka>0){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=+8801"+number+" &text=আপনার কাছে "+taka+"৳ পাওনা আছে।\nঅনুগ্রহ করে টাকা পরিশোধ করুন।\n\n"+name+"\nধন্যবাদ।"));
                    startActivity(intent);
                }
                else if (taka<0) {
                    double f_taka= taka*-1;
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=+8801"+number+" &text=আপনি আমার নিকট "+f_taka+"৳ পাবেন।\nখুব দ্রুত আপনার টাকা পরিশোধ করব।\n\n"+name+"\nধন্যবাদ।"));
                    startActivity(intent);
                }

                else if (taka==0) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=+8801"+number+" &text=আপনার সাথে কোনো দেনা পাওনা নেই।\n\n"+name+"\nধন্যবাদ।"));
                    startActivity(intent);
                }


            });

            rec_submit.setOnClickListener(v -> {


                if (rec_amount.length()>0){

                    if(rec_dec.length()==0)
                    {
                        rec_dec.setText("বিবরণ নেই");
                    }
                    String rec_amount_a = rec_amount.getText().toString();
                    String rec_dec_a = rec_dec.getText().toString();
                    double am = Double.parseDouble(rec_amount_a);
                    helper.add_rec_tali(am,rec_dec_a,user_id);

                   rec_amount.setText("");
                   rec_dec.setText("");
                   finish();
                   Toast.makeText(Codeshower.this, "Successfully added", Toast.LENGTH_LONG).show();




                } else {
                    rec_amount.setError("Enter Number");

                }

            });

            pay_submit.setOnClickListener(v -> {

                if (pay_amount.length()>0){

                        if(pay_dec.length()==0)
                        {
                            pay_dec.setText("বিবরণ নেই");
                        }
                    String pay_amount_a = pay_amount.getText().toString();
                    String pay_dec_a = pay_dec.getText().toString();
                    double pay = Double.parseDouble(pay_amount_a);

                    helper.add_pay_tali(pay,pay_dec_a,user_id);

                    pay_amount.setText("");
                    pay_dec.setText("");
                    finish();
                    Toast.makeText(Codeshower.this, "Successfully added", Toast.LENGTH_LONG).show();



                } else {
                    pay_amount.setError("Enter Number");

                }



            });

            dots.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   user_menu(v);
                }
            });

            tali_back.setOnClickListener(v -> {
                onBackPressed();
            });

            rec_add.setOnClickListener(v -> {
                rec_page.setVisibility(View.VISIBLE);
                pay_page.setVisibility(View.GONE);
                if (color==true)
                {
                    color1.setBackgroundColor(Color.parseColor("#dc1e76"));
                    text1.setTextColor(Color.parseColor("#ffffff"));
                    color=false;
                }
                else{
                    color1.setBackgroundColor(Color.parseColor("#dc1e76"));
                    color2.setBackgroundColor(Color.parseColor("#fae208"));
                    text1.setTextColor(Color.parseColor("#ffffff"));
                    text2.setTextColor(Color.parseColor("#dc1e76"));
                    color=true;
                }


            });

            pay_add.setOnClickListener(v -> {
                rec_page.setVisibility(View.GONE);
                pay_page.setVisibility(View.VISIBLE);
                if (color3==true)
                {
                    color2.setBackgroundColor(Color.parseColor("#dc1e76"));
                    color1.setBackgroundColor(Color.parseColor("#fae208"));
                    text2.setTextColor(Color.parseColor("#ffffff"));
                    text1.setTextColor(Color.parseColor("#dc1e76"));
                    color=false;
                }
                else {
                    color2.setBackgroundColor(Color.parseColor("#fae208"));
                    color1.setBackgroundColor(Color.parseColor("#dc1e76"));
                    text2.setTextColor(Color.parseColor("#dc1e76"));
                    color3=true;
                }



            });

            tv_dis.setText(tv_dis1);
            tali_title.setText(tali_title_1);

        }


        s_shower.setOnClickListener(v -> {
            onBackPressed();
        });



    }//=============================================


  public  class  rey_adapter_bl extends RecyclerView.Adapter<rey_adapter_bl.viewholder_bl>{


      public class viewholder_bl extends RecyclerView.ViewHolder{

          TextView title_bl,code_bl;
          ImageView call_bl,copy_bl,image1;
          LinearLayout anim10;

          public viewholder_bl(@NonNull View itemView) {
              super(itemView);


              title_bl=itemView.findViewById(R.id.utitle);
              code_bl=itemView.findViewById(R.id.ucode);
              image1=itemView.findViewById(R.id.image1);
              call_bl=itemView.findViewById(R.id.ucall);
              copy_bl= itemView.findViewById(R.id.ucopy);
              anim10 = itemView.findViewById(R.id.anim8);

          }
      }

      @NonNull
      @Override
      public viewholder_bl onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

          LayoutInflater layoutInflater = getLayoutInflater();
          View view = layoutInflater.inflate(R.layout.ucodeairtel,parent,false);

          return new viewholder_bl(view);
      }

      @Override
      public void onBindViewHolder(@NonNull viewholder_bl holder, int position) {

          HashMap<String,String>hashMap=arrayList_bl.get(position);
          String ftitle = hashMap.get("atitle");
          String fcode = hashMap.get("acode");

          holder.title_bl.setText(ftitle);
          holder.code_bl.setText(fcode);
          holder.image1.setImageResource(R.drawable.bl);

          holder.call_bl.setOnClickListener(v -> {

              Intent intent = new Intent(Intent.ACTION_DIAL);
              intent.setData(Uri.parse("tel:"+fcode));
              startActivity(intent);
              Toast.makeText(Codeshower.this, "যদি # না থাকে তবে শেষে # যোগ করুন।", Toast.LENGTH_LONG).show();
          });


          holder.copy_bl.setOnClickListener(v -> {
              ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
              ClipData clip = ClipData.newPlainText("TextView",holder.code_bl.getText().toString());
              clipboard.setPrimaryClip(clip);

              Toast.makeText(Codeshower.this, "Copy.", Toast.LENGTH_LONG).show();

          });

          item_anim= AnimationUtils.loadAnimation(Codeshower.this, R.anim.item_anim);
          holder.anim10.setAnimation(item_anim);

      }

      @Override
      public int getItemCount() {
          return arrayList_bl.size();
      }

  }

    public class rey_adapter_gp extends RecyclerView.Adapter<rey_adapter_gp.viewholder_gp>{


        public class viewholder_gp extends RecyclerView.ViewHolder{

            TextView title_gp,code_gp;
            ImageView call_gp,copy_gp,image1;
            LinearLayout anim9;

            public viewholder_gp(@NonNull View itemView) {
                super(itemView);

                title_gp=itemView.findViewById(R.id.utitle);
                code_gp=itemView.findViewById(R.id.ucode);
                image1=itemView.findViewById(R.id.image1);
                call_gp=itemView.findViewById(R.id.ucall);
                copy_gp= itemView.findViewById(R.id.ucopy);
                anim9= itemView.findViewById(R.id.anim8);
            }
        }

        @NonNull
        @Override
        public viewholder_gp onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.ucodeairtel,parent,false);

            return new viewholder_gp(view);
        }

        @Override
        public void onBindViewHolder(@NonNull viewholder_gp holder, int position) {


            HashMap<String,String>hashMap=arrayList_gp.get(position);
            String ftitle = hashMap.get("atitle");
            String fcode = hashMap.get("acode");

            holder.title_gp.setText(ftitle);
            holder.code_gp.setText(fcode);
            holder.image1.setImageResource(R.drawable.gp);
            holder.image1.setColorFilter(Color.parseColor("#dc1e76"));

            holder.call_gp.setOnClickListener(v -> {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+fcode));
                startActivity(intent);
                Toast.makeText(Codeshower.this, "যদি # না থাকে তবে শেষে # যোগ করুন।", Toast.LENGTH_LONG).show();
            });


            holder.copy_gp.setOnClickListener(v -> {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView",holder.code_gp.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(Codeshower.this, "Copy.", Toast.LENGTH_LONG).show();

            });

            item_anim= AnimationUtils.loadAnimation(Codeshower.this, R.anim.item_anim);
            holder.anim9.setAnimation(item_anim);

        }

        @Override
        public int getItemCount() {
            return arrayList_gp.size();
        }

    }

    public class rey_adapter_ar extends RecyclerView.Adapter<rey_adapter_ar.viewholder>{


        public class viewholder extends RecyclerView.ViewHolder{

            TextView utitle_ar,code_ar;
            ImageView call_ar,copy_ar,image1;
            LinearLayout anim8;

            public viewholder(@NonNull View itemView) {
                super(itemView);

                utitle_ar=itemView.findViewById(R.id.utitle);
                image1=itemView.findViewById(R.id.image1);
                code_ar=itemView.findViewById(R.id.ucode);
                call_ar=itemView.findViewById(R.id.ucall);
                copy_ar= itemView.findViewById(R.id.ucopy);
                anim8 = itemView.findViewById(R.id.anim8);

            }
        }

        @NonNull
        @Override
        public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.ucodeairtel,parent,false);


            return new viewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull viewholder holder, int position) {



            HashMap<String,String>hashMap=arrayList_ar.get(position);
            String ftitle = hashMap.get("atitle");
            String fcode = hashMap.get("acode");

            holder.utitle_ar.setText(ftitle);
            holder.code_ar.setText(fcode);
            holder.image1.setColorFilter(Color.parseColor("#dc1e76"));

            holder.call_ar.setOnClickListener(v -> {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+fcode));
                startActivity(intent);
                Toast.makeText(Codeshower.this, "যদি # না থাকে তবে শেষে # যোগ করুন।", Toast.LENGTH_LONG).show();
            });


            holder.copy_ar.setOnClickListener(v -> {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView",holder.code_ar.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(Codeshower.this, "Copy.", Toast.LENGTH_LONG).show();

            });

            item_anim= AnimationUtils.loadAnimation(Codeshower.this, R.anim.item_anim);
            holder.anim8.setAnimation(item_anim);
        }

        @Override
        public int getItemCount() {
            return arrayList_ar.size();
        }

    }

    public class rey_adapter_robi extends RecyclerView.Adapter<rey_adapter_robi.viewholder_robi>{


        public class viewholder_robi extends RecyclerView.ViewHolder{

            TextView title_robi,code_robi;
            ImageView call_robi,copy_robi,image1;

            LinearLayout anim7;

            public viewholder_robi(@NonNull View itemView) {
                super(itemView);
                title_robi=itemView.findViewById(R.id.utitle);
                image1=itemView.findViewById(R.id.image1);
                code_robi=itemView.findViewById(R.id.ucode);
                call_robi=itemView.findViewById(R.id.ucall);
                copy_robi= itemView.findViewById(R.id.ucopy);
                anim7 = itemView.findViewById(R.id.anim8);
            }
        }


        @NonNull
        @Override
        public viewholder_robi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.ucodeairtel,parent,false);

            return new viewholder_robi(view);
        }

        @Override
        public void onBindViewHolder(@NonNull viewholder_robi holder, int position) {


            HashMap<String,String>hashMap=arrayList.get(position);
            String ftitle = hashMap.get("atitle");
            String fcode = hashMap.get("acode");

            holder.title_robi.setText(ftitle);
            holder.code_robi.setText(fcode);
            holder.image1.setImageResource(R.drawable.robi);

            holder.call_robi.setOnClickListener(v -> {

                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+fcode));
                startActivity(intent);
                Toast.makeText(Codeshower.this, "যদি # না থাকে তবে শেষে # যোগ করুন।", Toast.LENGTH_LONG).show();
            });


            holder.copy_robi.setOnClickListener(v -> {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView",holder.code_robi.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(Codeshower.this, "Copy.", Toast.LENGTH_LONG).show();

            });

            item_anim= AnimationUtils.loadAnimation(Codeshower.this, R.anim.item_anim);
            holder.anim7.setAnimation(item_anim);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }


    }

    public class s_code extends RecyclerView.Adapter<s_code.scode_viewholder>{


        public class scode_viewholder extends RecyclerView.ViewHolder{
            TextView title_scode,code_scode;
            ImageView copy_scode;
            LinearLayout anim6;
            public scode_viewholder(@NonNull View itemView) {
                super(itemView);
                title_scode=itemView.findViewById(R.id.title_scode);
                code_scode=itemView.findViewById(R.id.code_scode);
                copy_scode= itemView.findViewById(R.id.copy_scode);
                anim6 = itemView.findViewById(R.id.anim6);
            }
        }

        @NonNull
        @Override
        public scode_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.s_code_list,parent,false);
            return new scode_viewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull scode_viewholder holder, int position) {

            HashMap<String,String>hashMap=arrayList_scode.get(position);
            String s_title = hashMap.get("title");
            String s_code = hashMap.get("code_s");

            holder.title_scode.setText(s_title);
            holder.code_scode.setText(s_code);


            holder.copy_scode.setOnClickListener(v -> {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("TextView",holder.code_scode.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(Codeshower.this, "Copy.", Toast.LENGTH_LONG).show();

            });


            item_anim= AnimationUtils.loadAnimation(Codeshower.this, R.anim.item_anim);
            holder.anim6.setAnimation(item_anim);

        }

        @Override
        public int getItemCount() {
            return arrayList_scode.size();
        }


    }

    public void user_menu(View view) {
        // Create a ContextThemeWrapper with the custom style
        ContextThemeWrapper ctw = new ContextThemeWrapper(Codeshower.this, R.style.CustomPopupMenu);
        // Create the PopupMenu with the custom context
        PopupMenu popupMenu = new PopupMenu(ctw, view);

        // Inflate the menu resource into the PopupMenu
        popupMenu.getMenuInflater().inflate(R.menu.tali_menu, popupMenu.getMenu());

        // Set a click listener for menu items
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId()==R.id.user_delete1){

                    AlertDialog alertDialog = new AlertDialog.Builder(Codeshower.this)
                            .setIcon(R.drawable.alert)
                            .setTitle("Delete!")
                            .setMessage("Are you sure?")

                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    Bundle bun=getIntent().getExtras();
                                    int user_id =bun.getInt("user_id");
                                    String ssd= String.valueOf(user_id);
                                    helper.delete_user(ssd);
                                    finish();

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            })
                            .show();
                }

                else if (item.getItemId()==R.id.user_edit1){
                    Bundle bun=getIntent().getExtras();
                    int user_id =bun.getInt("user_id");
                    String dd = String.valueOf(user_id);
                    String name = bun.getString("name");
                    String address = bun.getString("address");
                    user_up(Codeshower.this,dd,name,address);
                }

                else if (item.getItemId()==R.id.user_report){

                    Bundle bun=getIntent().getExtras();
                    int user_id =bun.getInt("user_id");
                    Intent nextActivity = new Intent(Codeshower.this, Offer.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("user_id",user_id);
                    bundle.putInt("VAL", 102);
                    nextActivity.putExtras(bundle);
                    startActivity(nextActivity);

                }
                        return false;
                }

        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu.setForceShowIcon(true);
        }
        popupMenu.show();
    }


    public void user_up (Context context,String id,String name,String address) {

        BottomSheetDialog dialog = new BottomSheetDialog(this);

        // Inflate the layout for the BottomSheetDialog
        View view = getLayoutInflater().inflate(R.layout.tali_user, null);

        // Set the layout for the BottomSheetDialog
        dialog.setContentView(view);

        TextView title = view.findViewById(R.id.title);
        EditText name1 = view.findViewById(R.id.name);
        EditText address1 = view.findViewById(R.id.tikana);

        title.setText("হিসাব আপডেট");
        name1.setText(name);
        address1.setText(address);


        view.findViewById(R.id.submit_user).setOnClickListener(v -> {


            if (name1.length()>0 && address1.length()==9){

                String name_a = name1.getText().toString();
                String address_a = address1.getText().toString();
                helper.tali_user_up(id,name_a,address_a);
                dialog.dismiss();
                finish();

            } else {
                name1.setError("Enter Number");
                address1.setError("Enter text");

            }



        });

        dialog.show();

    }



    private void bl_data(){

        hashMap_bl= new HashMap<>();
        hashMap_bl.put("atitle","ব্যালেন্স চেক করা।");
        hashMap_bl.put("acode","*124#");
        arrayList_bl.add(hashMap_bl);

        hashMap_bl= new HashMap<>();
        hashMap_bl.put("atitle","Any BalanceCheck");
        hashMap_bl.put("acode","*121*1#");
        arrayList_bl.add(hashMap_bl);

        hashMap_bl= new HashMap<>();
        hashMap_bl.put("atitle","লোন চেক");
        hashMap_bl.put("acode","*874*0#");
        arrayList_bl.add(hashMap_bl);

        hashMap_bl= new HashMap<>();
        hashMap_bl.put("atitle","মিনিট দেখতে।");
        hashMap_bl.put("acode","*124*2#");
        arrayList_bl.add(hashMap_bl);

        hashMap_bl= new HashMap<>();
        hashMap_bl.put("atitle","My Offer ");
        hashMap_bl.put("acode","*121*2#");
        arrayList_bl.add(hashMap_bl);

        hashMap_bl= new HashMap<>();
        hashMap_bl.put("atitle","ইন্টারনেট দেখতে।");
        hashMap_bl.put("acode","*5000*500#");
        arrayList_bl.add(hashMap_bl);

        hashMap_bl= new HashMap<>();
        hashMap_bl.put("atitle","ইন্টারনেট দেখতে ২।");
        hashMap_bl.put("acode","*124*44#");
        arrayList_bl.add(hashMap_bl);

        hashMap_bl= new HashMap<>();
        hashMap_bl.put("atitle","বোনাস ইন্টারনেট দেখতে।");
        hashMap_bl.put("acode","*124*5#");
        arrayList_bl.add(hashMap_bl);

        hashMap_bl= new HashMap<>();
        hashMap_bl.put("atitle","টাকা ধার");
        hashMap_bl.put("acode","*874#");
        arrayList_bl.add(hashMap_bl);

        hashMap_bl= new HashMap<>();
        hashMap_bl.put("atitle","সিমের নাম্বার দেখতে।");
        hashMap_bl.put("acode","*511#");
        arrayList_bl.add(hashMap_bl);

        hashMap_bl= new HashMap<>();
        hashMap_bl.put("atitle","অফার দেখতে।");
        hashMap_bl.put("acode","*125#");
        arrayList_bl.add(hashMap_bl);

        hashMap_bl= new HashMap<>();
        hashMap_bl.put("atitle","SMS দেখতে।");
        hashMap_bl.put("acode","*124*2#");
        arrayList_bl.add(hashMap_bl);

        hashMap_bl= new HashMap<>();
        hashMap_bl.put("atitle","আমার অফার দেখতে।");
        hashMap_bl.put("acode","*888#");
        arrayList_bl.add(hashMap_bl);

        hashMap_bl= new HashMap<>();
        hashMap_bl.put("atitle","পরে কল করো।");
        hashMap_bl.put("acode","*126*Number#");
        arrayList_bl.add(hashMap_bl);

        hashMap_bl= new HashMap<>();
        hashMap_bl.put("atitle","বোনাস টক টাইম দেখতে।");
        hashMap_bl.put("acode","*124*3#");
        arrayList_bl.add(hashMap_bl);



    }

    private void gp_data(){

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","ব্যালেন্স চেক করা।");
        hashMap_gp.put("acode","*566#");
        arrayList_gp.add(hashMap_gp);

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","লোন চেক");
        hashMap_gp.put("acode","*566*28#");
        arrayList_gp.add(hashMap_gp);

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","মিনিট দেখতে।");
        hashMap_gp.put("acode","*121*1*2#");
        arrayList_gp.add(hashMap_gp);

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","মিনিট দেখতে 2");
        hashMap_gp.put("acode","*121*1*4#");
        arrayList_gp.add(hashMap_gp);

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle"," বোনাস মিনিট দেখতে।");
        hashMap_gp.put("acode","*566*20#");
        arrayList_gp.add(hashMap_gp);

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","ইন্টারনেট দেখতে।");
        hashMap_gp.put("acode","*121*1*1*4#");
        arrayList_gp.add(hashMap_gp);

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","আমার অফার দেখতে।");
        hashMap_gp.put("acode","*121*5#");
        arrayList_gp.add(hashMap_gp);

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","GP Services");
        hashMap_gp.put("acode","*121#");
        arrayList_gp.add(hashMap_gp);


        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","সিমের নাম্বার দেখতে।");
        hashMap_gp.put("acode","*2#");
        arrayList_gp.add(hashMap_gp);

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","অফার দেখতে।");
        hashMap_gp.put("acode","*121*3#");
        arrayList_gp.add(hashMap_gp);

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","MMS Check");
        hashMap_gp.put("acode","*566*14#");
        arrayList_gp.add(hashMap_gp);

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","Minute Pack");
        hashMap_gp.put("acode","*121*4#");
        arrayList_gp.add(hashMap_gp);

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","SMS দেখতে।");
        hashMap_gp.put("acode","*121*1*2#");
        arrayList_gp.add(hashMap_gp);

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","SMS দেখতে 2");
        hashMap_gp.put("acode","*566*2#");
        arrayList_gp.add(hashMap_gp);

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","Emergency Balance");
        hashMap_gp.put("acode","*121*1*3#");
        arrayList_gp.add(hashMap_gp);

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","Emergency Data");
        hashMap_gp.put("acode","*121*1*8#");
        arrayList_gp.add(hashMap_gp);

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","Call BlockService");
        hashMap_gp.put("acode","*121*6*4#");
        arrayList_gp.add(hashMap_gp);

        hashMap_gp= new HashMap<>();
        hashMap_gp.put("atitle","WelcomeTune");
        hashMap_gp.put("acode","*121*6*2#");
        arrayList_gp.add(hashMap_gp);





    }

    private void ar_data(){

        hashMap_ar= new HashMap<>();
        hashMap_ar.put("atitle","ব্যালেন্স চেক করা।");
        hashMap_ar.put("acode","*1#");
        arrayList_ar.add(hashMap_ar);

        hashMap_ar= new HashMap<>();
        hashMap_ar.put("atitle","মিনিট দেখতে।");
        hashMap_ar.put("acode","*778*5#");
        arrayList_ar.add(hashMap_ar);

        hashMap_ar= new HashMap<>();
        hashMap_ar.put("atitle","লোন চেক");
        hashMap_ar.put("acode","*141*600#");
        arrayList_ar.add(hashMap_ar);

        hashMap_ar= new HashMap<>();
        hashMap_ar.put("atitle","মিনিট দেখতে 2");
        hashMap_ar.put("acode","*778*8#");
        arrayList_ar.add(hashMap_ar);

        hashMap_ar= new HashMap<>();
        hashMap_ar.put("atitle","মিনিট বান্ডেলস");
        hashMap_ar.put("acode","*0#");
        arrayList_ar.add(hashMap_ar);

        hashMap_ar= new HashMap<>();
        hashMap_ar.put("atitle","ইন্টারনেট দেখতে।");
        hashMap_ar.put("acode","*3#");
        arrayList_ar.add(hashMap_ar);


        hashMap_ar= new HashMap<>();
        hashMap_ar.put("atitle","ইন্টারনেট দেখতে 2");
        hashMap_ar.put("acode","*8444*88#");
        arrayList_ar.add(hashMap_ar);


        hashMap_ar= new HashMap<>();
        hashMap_ar.put("atitle","সিমের নাম্বার দেখতে।");
        hashMap_ar.put("acode","*2#");
        arrayList_ar.add(hashMap_ar);

        hashMap_ar= new HashMap<>();
        hashMap_ar.put("atitle","অফার দেখতে।");
        hashMap_ar.put("acode","*4#");
        arrayList_ar.add(hashMap_ar);

        hashMap_ar= new HashMap<>();
        hashMap_ar.put("atitle","অফার দেখতে 2");
        hashMap_ar.put("acode","*121*2*1*1#");
        arrayList_ar.add(hashMap_ar);

        hashMap_ar= new HashMap<>();
        hashMap_ar.put("atitle","অফার দেখতে 3");
        hashMap_ar.put("acode","*121*2*1*3#");
        arrayList_ar.add(hashMap_ar);

        hashMap_ar= new HashMap<>();
        hashMap_ar.put("atitle","SMS দেখতে।");
        hashMap_ar.put("acode","*778*2#");
        arrayList_ar.add(hashMap_ar);

        hashMap_ar= new HashMap<>();
        hashMap_ar.put("atitle","SMS দেখতে 2");
        hashMap_ar.put("acode","*222*13#");
        arrayList_ar.add(hashMap_ar);


        hashMap_ar= new HashMap<>();
        hashMap_ar.put("atitle","পরে কল করো।");
        hashMap_ar.put("acode","*121*5#");
        arrayList_ar.add(hashMap_ar);




    }

    private void robi_data(){

        hashMap= new HashMap<>();
        hashMap.put("atitle","ব্যালেন্স চেক করা।");
        hashMap.put("acode","*222#");
        arrayList.add(hashMap);

        hashMap= new HashMap<>();
        hashMap.put("atitle","মিনিট দেখতে।");
        hashMap.put("acode","*222*3#");
        arrayList.add(hashMap);

        hashMap= new HashMap<>();
        hashMap.put("atitle","লোন চেক");
        hashMap.put("acode","*123*600#");
        arrayList.add(hashMap);

        hashMap= new HashMap<>();
        hashMap.put("atitle","মিনিট দেখতে 2");
        hashMap.put("acode","*222*8#");
        arrayList.add(hashMap);

        hashMap= new HashMap<>();
        hashMap.put("atitle","ইন্টারনেট দেখতে।");
        hashMap.put("acode","*8444*88#");
        arrayList.add(hashMap);

        hashMap= new HashMap<>();
        hashMap.put("atitle","সিমের নাম্বার দেখতে।");
        hashMap.put("acode","*140*14#");
        arrayList.add(hashMap);

        hashMap= new HashMap<>();
        hashMap.put("atitle","অফার দেখতে।");
        hashMap.put("acode","*8444#");
        arrayList.add(hashMap);

        hashMap= new HashMap<>();
        hashMap.put("atitle","SMS দেখতে।");
        hashMap.put("acode","*222*12#");
        arrayList.add(hashMap);

        hashMap= new HashMap<>();
        hashMap.put("atitle","টাকা ধার");
        hashMap.put("acode","*8811#");
        arrayList.add(hashMap);

        hashMap= new HashMap<>();
        hashMap.put("atitle","Emergency Internet");
        hashMap.put("acode","*123*003#");
        arrayList.add(hashMap);


        hashMap= new HashMap<>();
        hashMap.put("atitle","Emergency Minutes");
        hashMap.put("acode","*123*008#");
        arrayList.add(hashMap);

        hashMap= new HashMap<>();
        hashMap.put("atitle","My Offers");
        hashMap.put("acode","*999#");
        arrayList.add(hashMap);

        hashMap= new HashMap<>();
        hashMap.put("atitle","Menu");
        hashMap.put("acode","*123#");
        arrayList.add(hashMap);

        hashMap= new HashMap<>();
        hashMap.put("atitle","BalanceTransfer");
        hashMap.put("acode","*140*6#");
        arrayList.add(hashMap);

        hashMap= new HashMap<>();
        hashMap.put("atitle","Call WaitingServiceON");
        hashMap.put("acode","*43#OFF: #43#");
        arrayList.add(hashMap);

        hashMap= new HashMap<>();
        hashMap.put("atitle","FnF & Priyo");
        hashMap.put("acode","*140*5#");
        arrayList.add(hashMap);





    }

}