package com.modestsoftware.mbload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;

public class AllActivity extends AppCompatActivity {


    RelativeLayout ussdcode,rule,scode,social,text_repeter;
    ImageView back1,sback,ussdback,s_back,text_repeter_back;
    CardView ucard1,ucard2,ucard3,ucard4,copy_text,share_text,join_what,join_fb_group,join_facebook;

    TextInputLayout inputlayout1,inputlayout2;
    TextInputEditText edittext1,edittext2;
    TextView tvdisplay,x10,x50,x100,x1000,custom,submit;

    RecyclerView s_recyclerview;
    HashMap<String,String> hashMap,codemap;
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();

    ArrayList<HashMap<String,String>> codelist = new ArrayList<>();

    Animation item_anim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);

        ussdcode=findViewById(R.id.ussdcode);
        rule=findViewById(R.id.rule);
        scode=findViewById(R.id.scode);
        social=findViewById(R.id.social);
        text_repeter=findViewById(R.id.text_repeter);

        join_facebook= findViewById(R.id.join_facebook);
        join_fb_group = findViewById(R.id.join_fb_group);
        join_what = findViewById(R.id.join_what);

        back1=findViewById(R.id.back1);
        sback=findViewById(R.id.sback);
        s_back=findViewById(R.id.s_back);
        ussdback=findViewById(R.id.ussdback);
        text_repeter_back = findViewById(R.id.text_repeter_back);

        ucard1 = findViewById(R.id.ucard1);
        ucard2 = findViewById(R.id.ucard2);
        ucard3 = findViewById(R.id.ucard3);
        ucard4 = findViewById(R.id.ucard4);

        s_recyclerview=findViewById(R.id.s_recyclerview);


        inputlayout1=findViewById(R.id.inputlayout1);
        inputlayout2=findViewById(R.id.inputlayout2);
        edittext1= findViewById(R.id.edittext1);
        edittext2= findViewById(R.id.edittext2);

        tvdisplay= findViewById(R.id.tvdisplay);
        x10=findViewById(R.id.x10);
        x50=findViewById(R.id.x50);
        x100=findViewById(R.id.x100);
        x1000=findViewById(R.id.x1000);
        custom=findViewById(R.id.custom);
        submit=findViewById(R.id.submit);

        copy_text = findViewById(R.id.copy_text);
        share_text = findViewById(R.id.share_text);


        Bundle bun=getIntent().getExtras();
        int val =bun.getInt("VAL");

        if(val==1) {
            ussdcode.setVisibility(View.VISIBLE);

        }
        else if(val==2) {
            ussdcode.setVisibility(View.GONE);
            scode.setVisibility(View.VISIBLE);

        }
        else if(val==3) {
            ussdcode.setVisibility(View.GONE);
            rule.setVisibility(View.VISIBLE);
            scode.setVisibility(View.GONE);

        }
        else if(val==4) {
            ussdcode.setVisibility(View.GONE);
            rule.setVisibility(View.GONE);
            scode.setVisibility(View.GONE);
            social.setVisibility(View.VISIBLE);

            join_what.setOnClickListener(v -> {
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("https://chat.whatsapp.com/Cfl6RGA5wm65RHCd93A4gf"));
                startActivity(intent1);
            });

            join_fb_group.setOnClickListener(v -> {

                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("https://www.facebook.com/groups/extulbd"));
                startActivity(intent1);
            });

            join_facebook.setOnClickListener(v -> {
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("https://www.facebook.com/extulbd"));
                startActivity(intent1);
            });

        }
        else if(val==5) {
            ussdcode.setVisibility(View.GONE);
            rule.setVisibility(View.GONE);
            scode.setVisibility(View.GONE);
            social.setVisibility(View.GONE);
            text_repeter.setVisibility(View.VISIBLE);



            inputlayout1.setEndIconOnClickListener(v -> {
                inputlayout2.setVisibility(View.GONE);
                edittext1.getText().clear();
                tvdisplay.setText("");
            });

            inputlayout2.setEndIconOnClickListener(v -> {
                edittext2.getText().clear();
                tvdisplay.setText("");

            });


            x10.setOnClickListener(v -> {
                inputlayout2.setVisibility(View.GONE);
                tvdisplay.setText("");
                if (edittext1.length()>0)
                {
                    String text = edittext1.getText().toString();
                    for (int i = 1; i <=10; i++) {
                        tvdisplay.append(text+"\n");
                    }
                } else edittext1.setError("Enter Text");

            });

            x50.setOnClickListener(v -> {
                inputlayout2.setVisibility(View.GONE);
                tvdisplay.setText("");
                if (edittext1.length()>0)
                {
                    String text = edittext1.getText().toString();
                    for (int i = 1; i <=50; i++) {
                        tvdisplay.append(text+"\n");
                    }
                } else edittext1.setError("Enter Text");

            });

            x100.setOnClickListener(v -> {

                inputlayout2.setVisibility(View.GONE);
                tvdisplay.setText("");

                if (edittext1.length()>0)
                {
                    String text = edittext1.getText().toString();
                    for (int i = 1; i <=100; i++) {
                        tvdisplay.append(text+"\n");
                    }
                } else edittext1.setError("Enter Text");

            });

            x1000.setOnClickListener(v -> {
                inputlayout2.setVisibility(View.GONE);
                tvdisplay.setText("");
                if (edittext1.length()>0)
                {
                    String text = edittext1.getText().toString();
                    for (int i = 1; i <=1000; i++) {
                        tvdisplay.append(text+"\n");
                    }
                } else edittext1.setError("Enter Text");

            });

            custom.setOnClickListener(v -> {
                tvdisplay.setText("");
                inputlayout2.setVisibility(View.VISIBLE);
            });

            submit.setOnClickListener(v -> {

                tvdisplay.setText("");


                if (edittext1.length()>0 && edittext2.length()>0)
                {
                    int count = Integer.parseInt(edittext2.getText().toString());
                    String text = edittext1.getText().toString();

                    for (int i = 1; i <=count; i++) {
                        tvdisplay.append(text+"\n");
                    }
                }
                else {
                    edittext2.setError("Enter Number");
                    edittext1.setError("Enter Text");
                }
            });

            copy_text.setOnClickListener(v -> {

                        if (tvdisplay.length()>0){
                            ClipboardManager clipboard = (ClipboardManager) getSystemService(AllActivity.this.CLIPBOARD_SERVICE);
                            ClipData clip = ClipData.newPlainText("TextView",tvdisplay.getText().toString());
                            clipboard.setPrimaryClip(clip);

                            Toast.makeText(AllActivity.this, "Copied", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(AllActivity.this, "No Text", Toast.LENGTH_LONG).show();
                        }

            });

            share_text.setOnClickListener(v -> {

             share(AllActivity.this);
            });

        }



        back1.setOnClickListener(v -> {
          onBackPressed();
        });

        sback.setOnClickListener(v -> {
            onBackPressed();
        });

        ussdback.setOnClickListener(v -> {
            onBackPressed();
        });

        s_back.setOnClickListener(v -> {
            onBackPressed();
        });

        text_repeter_back.setOnClickListener(v -> {
            onBackPressed();
        });



        ucard1.setOnClickListener(v -> {

                Intent nextActivity = new Intent(AllActivity.this, Codeshower.class);
                Bundle bundle = new Bundle();
                bundle.putInt("VAL", 91);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);

        });

        ucard2.setOnClickListener(v -> {

                Intent nextActivity = new Intent(AllActivity.this, Codeshower.class);
                Bundle bundle = new Bundle();
                bundle.putInt("VAL", 92);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);

        });


        ucard3.setOnClickListener(v -> {

                Intent nextActivity = new Intent(AllActivity.this, Codeshower.class);
                Bundle bundle = new Bundle();
                bundle.putInt("VAL", 93);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);

        });


        ucard4.setOnClickListener(v -> {

                Intent nextActivity = new Intent(AllActivity.this, Codeshower.class);
                Bundle bundle = new Bundle();
                bundle.putInt("VAL", 94);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);

        });

         s_table();

        screat_recyclerview myadapter_ar = new screat_recyclerview();
        s_recyclerview.setAdapter(myadapter_ar);
        s_recyclerview.setLayoutManager(new LinearLayoutManager(AllActivity.this));



















    } //=======================================================



    private void share (Context context){


        if (tvdisplay.length()>0){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,"\n"+tvdisplay.getText().toString());
            intent.setType("text/plain");
            context.startActivity(intent);
        }
        else {
            Toast.makeText(AllActivity.this, "No Text", Toast.LENGTH_LONG).show();
        }


    }

    private class screat_recyclerview extends RecyclerView.Adapter<screat_recyclerview.s_viewholder>{



        private class s_viewholder extends RecyclerView.ViewHolder{


            TextView secret_text;
            LinearLayout s_click;
            CardView anim4;
            public s_viewholder(@NonNull View itemView) {
                super(itemView);

                secret_text=itemView.findViewById(R.id.secret_text);
                s_click=itemView.findViewById(R.id.s_click);
                anim4=itemView.findViewById(R.id.anim4);


            }
        }


        @NonNull
        @Override
        public s_viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.s_layout,parent,false);

            return new s_viewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull s_viewholder holder, int position) {

            HashMap<String,String>hashMap=arrayList.get(position);
            String m_mobile1 = hashMap.get("mobile");


            holder.secret_text.setText(m_mobile1);

            holder.s_click.setOnClickListener(v -> {

                if (m_mobile1.equals("Samsung Secret Code"))

                {
                    Codeshower.bartext= m_mobile1;
                    samsang_code();
                    Codeshower.arrayList_scode = codelist;
                    Intent nextActivity = new Intent(AllActivity.this, Codeshower.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("VAL", 95);
                    nextActivity.putExtras(bundle);
                    startActivity(nextActivity);
                }
                else if (m_mobile1.equals("Xiaomi Secret Code")) {

                    Codeshower.bartext= m_mobile1;
                    xiaomi_code();
                    Codeshower.arrayList_scode=codelist;
                    Intent nextActivity = new Intent(AllActivity.this, Codeshower.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("VAL", 95);
                    nextActivity.putExtras(bundle);
                    startActivity(nextActivity);
                }
                else if (m_mobile1.equals("Oppo Secret Code")) {

                    Codeshower.bartext= m_mobile1;
                    opp_code();
                    Codeshower.arrayList_scode=codelist;
                    Intent nextActivity = new Intent(AllActivity.this, Codeshower.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("VAL", 95);
                    nextActivity.putExtras(bundle);
                    startActivity(nextActivity);
                }
                else if (m_mobile1.equals("Lenovo Secret Code")) {

                    Codeshower.bartext= m_mobile1;
                    lenevo_code();
                    Codeshower.arrayList_scode=codelist;
                    Intent nextActivity = new Intent(AllActivity.this, Codeshower.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("VAL", 95);
                    nextActivity.putExtras(bundle);
                    startActivity(nextActivity);
                }
                else if (m_mobile1.equals("iPhone Secret Code")) {

                    Codeshower.bartext= m_mobile1;
                    iphone_code();
                    Codeshower.arrayList_scode=codelist;
                    Intent nextActivity = new Intent(AllActivity.this, Codeshower.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("VAL", 95);
                    nextActivity.putExtras(bundle);
                    startActivity(nextActivity);
                }

                else if (m_mobile1.equals("Huawei Secret Code")) {

                    Codeshower.bartext= m_mobile1;
                    hauwi_code();
                    Codeshower.arrayList_scode=codelist;
                    Intent nextActivity = new Intent(AllActivity.this, Codeshower.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("VAL", 95);
                    nextActivity.putExtras(bundle);
                    startActivity(nextActivity);
                }


            });

            item_anim= AnimationUtils.loadAnimation(AllActivity.this, R.anim.item_anim);
            holder.anim4.setAnimation(item_anim);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }


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


   private void s_table (){

       hashMap= new HashMap<>();
       hashMap.put("item","code");
       hashMap.put("mobile","Samsung Secret Code");
       arrayList.add(hashMap);

       hashMap= new HashMap<>();
       hashMap.put("item","code");
       hashMap.put("mobile","Xiaomi Secret Code");
       arrayList.add(hashMap);

       hashMap= new HashMap<>();
       hashMap.put("item","code");
       hashMap.put("mobile","Oppo Secret Code");
       arrayList.add(hashMap);

       hashMap= new HashMap<>();
       hashMap.put("item","code");
       hashMap.put("mobile","Lenovo Secret Code");
       arrayList.add(hashMap);

       hashMap= new HashMap<>();
       hashMap.put("item","code");
       hashMap.put("mobile","iPhone Secret Code");
       arrayList.add(hashMap);

       hashMap= new HashMap<>();
       hashMap.put("item","code");
       hashMap.put("mobile","Huawei Secret Code");
       arrayList.add(hashMap);





   }

   private  void  samsang_code (){

        codelist.clear();

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","IMEI Number Check");
       codemap.put("code_s","*#06#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Originality Check");
       codemap.put("code_s","*#0*#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Version Check");
       codemap.put("code_s","*#1234#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title"," Main Version Check");
       codemap.put("code_s","*#12580*369#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Battery Status Check");
       codemap.put("code_s","*#0228#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","System Dump Setting");
       codemap.put("code_s","*#9900#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Serial Number Check");
       codemap.put("code_s","*#0001#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Memory Capacity Check");
       codemap.put("code_s","*#9998246#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Debug Screen");
       codemap.put("code_s","*#9998324#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Vibration Test");
       codemap.put("code_s","#9998842#\n#8999842#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Ringtone Test");
       codemap.put("code_s","#999889#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Software Version Check");
       codemap.put("code_s","*#0837#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Display Contract");
       codemap.put("code_s","*#0523#\n*#8999523#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Sim Card Information");
       codemap.put("code_s","*#8999778#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Show Date and Alarm");
       codemap.put("code_s","*#8999782#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Display During Warning");
       codemap.put("code_s","*#8999786#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Network Information");
       codemap.put("code_s","*#8999638#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Display current Firmware");
       codemap.put("code_s","*#1234#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Factory Software Reset");
       codemap.put("code_s","##7780##");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","GPS Test Setting");
       codemap.put("code_s","##197328640##");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Software & HW info");
       codemap.put("code_s","*#12580*369#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Bluetooth Address");
       codemap.put("code_s","*#232337#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Vibration Motor Test");
       codemap.put("code_s","*#0842#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Audio Test Mode");
       codemap.put("code_s","*#0673#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_f");
       codemap.put("title","Camera Firmware Update");
       codemap.put("code_s","*#34971539#");
       codelist.add(codemap);

   }

   private void  xiaomi_code (){

        codelist.clear();

       codemap= new HashMap<>();
       codemap.put("item","code_xi");
       codemap.put("title","IMEI Number Check");
       codemap.put("code_s","*#06#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_xi");
       codemap.put("title","Touch Screen Test");
       codemap.put("code_s","##2664#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_xi");
       codemap.put("title","Battery Information");
       codemap.put("code_s","##4636#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_xi");
       codemap.put("title","Ram Version Check");
       codemap.put("code_s","##3264#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_xi");
       codemap.put("title","Wi-Fi Mac Address");
       codemap.put("code_s","##232338#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_xi");
       codemap.put("title","Audio Test");
       codemap.put("code_s","##0289#");
       codelist.add(codemap);

       codemap= new HashMap<>();
       codemap.put("item","code_xi");
       codemap.put("title","QC Test");
       codemap.put("code_s","##64663#");
       codelist.add(codemap);



    }

    private void  opp_code (){

        codelist.clear();

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","IMEI Number Check");
        codemap.put("code_s","*#06#");
        codelist.add(codemap);


        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Sim reset");
        codemap.put("code_s","*#*#46*#*#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Show service Menu");
        codemap.put("code_s","*#0*#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Factory reset \nwithout removing data");
        codemap.put("code_s","*#*#7780#*#*");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Check lock status");
        codemap.put("code_s","*#7465625#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","System dump mood");
        codemap.put("code_s","*#9900#");
        codelist.add(codemap);


        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Vibration Test");
        codemap.put("code_s","*#*#0842#*#*");
        codelist.add(codemap);


        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","USB login status");
        codemap.put("code_s","*#872564#");
        codelist.add(codemap);



        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Software details");
        codemap.put("code_s","*#1234#");
        codelist.add(codemap);


        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Bluetooth test");
        codemap.put("code_s","*#805#");
        codelist.add(codemap);


    }

    private void  lenevo_code (){

        codelist.clear();

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","IMEI Number Check");
        codemap.put("code_s","*#06#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Touch Screen Test");
        codemap.put("code_s","*#*#2664#*#*");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Battery Information");
        codemap.put("code_s","*#*#4636#*#*");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Ram Version Check");
        codemap.put("code_s","*#*#3264#*#*");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Wi-Fi Mac Address");
        codemap.put("code_s","*#*#232338#*#*");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Audio Test");
        codemap.put("code_s","*#*#0289#*#*");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","QC Test");
        codemap.put("code_s","*#*#64663*#*#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Lenovo Menu");
        codemap.put("code_s","*#000000#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","GPS Test");
        codemap.put("code_s","*#*#1575*#*#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Bluetooth Test");
        codemap.put("code_s","*#232331#*#*");
        codelist.add(codemap);


    }

    private void  iphone_code (){

        codelist.clear();

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","IMEI Number Check");
        codemap.put("code_s","*#06#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Use information info");
        codemap.put("code_s","*3282#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title"," Call Waiting status");
        codemap.put("code_s","*#43#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Active waiting");
        codemap.put("code_s","*43#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Deactivate waiting");
        codemap.put("code_s","#4#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Hide Number");
        codemap.put("code_s","*#31#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Check text msg");
        codemap.put("code_s","*#50005*7672#");
        codelist.add(codemap);



    }

    private void  hauwi_code(){

        codelist.clear();

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","IMEI Number Check");
        codemap.put("code_s","*#06#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Touch Screen Test");
        codemap.put("code_s","##2664#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Dump Mood");
        codemap.put("code_s","*#9900#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","USB Control");
        codemap.put("code_s","*#872564#");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Wi-Fi Lan test");
        codemap.put("code_s","##232339##");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Audio Test");
        codemap.put("code_s","##2846##");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Vibration Test");
        codemap.put("code_s","##0842##");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Software Version");
        codemap.put("code_s","##1111##");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","GPS Test");
        codemap.put("code_s","##1575##");
        codelist.add(codemap);

        codemap= new HashMap<>();
        codemap.put("item","code_xi");
        codemap.put("title","Bluetooth test");
        codemap.put("code_s","##232331##");
        codelist.add(codemap);



    }






}