package com.modestsoftware.mbload;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    MaterialToolbar toolbar;
    NavigationView nav_View;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    TextView tvdis;

    walletdbhelper helper;

    CardView card1,card2,card3,card4,card5,card6,card7,card8,card9,card02,card03,card01;


    boolean backpress = false;

   public static SharedPreferences sharedPreferences;
   SharedPreferences sharedPreferences2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new walletdbhelper(MainActivity.this);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        toolbar = findViewById(R.id. toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        nav_View = findViewById(R.id.nav_View);

        tvdis = findViewById(R.id.tvdis);

        card1=findViewById(R.id.card1);
        card2=findViewById(R.id.card2);
        card3=findViewById(R.id.card3);
        card4=findViewById(R.id.card4);
        card5=findViewById(R.id.card5);
        card6=findViewById(R.id.card6);
        card7=findViewById(R.id.card7);
        card8=findViewById(R.id.card8);
        card9=findViewById(R.id.card9);
        card02=findViewById(R.id.card02);
        card03=findViewById(R.id.card03);
        card01=findViewById(R.id.card01);


        askNotificationPermission();

        ConnectivityManager connectivityManager =  (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("notice");

        if(networkInfo!=null&&networkInfo.isConnected()){

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String mynotice = snapshot.child("mynotice").getValue(String.class);
                    tvdis.setText(mynotice);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
        else {
            tvdis.setText("আপনার ইন্টারনেট সংযোগ নেই");
        }


        //==========================tvdis_end================================


        card1.setOnClickListener(v -> {

            Intent nextActivity = new Intent(MainActivity.this, AllActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("VAL", 1);
            nextActivity.putExtras(bundle);
            startActivity(nextActivity);


        });

        card2.setOnClickListener(v -> {

            if(networkInfo!=null&&networkInfo.isConnected()){

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {

                        String order = snapshot.child("order").getValue(String.class);

                        if (order.contains("On")){

                            Intent nextActivity = new Intent(MainActivity.this, Offer.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("VAL", 101);
                            bundle.putInt("type",10);
                            nextActivity.putExtras(bundle);
                            startActivity(nextActivity);


                        }
                        else {

                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                    .setIcon(R.drawable.mb)
                                    .setTitle(" Now Offer Selling is OFF.")
                                    .setMessage("বর্তমানে অফার সেল বন্ধ আছে। কিছুক্ষণ পরে আবার চেষ্টা করুন।।\n\nআমাদের অফার সেল সার্ভিস সকাল ১০টা থেকে রাত ১০টা পর্যন্ত দেওয়া হয়।")
                                    .setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .show();
                        }



                    }

                    @Override
                    public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {


                    }
                });

            }
            else {
                no_internet(MainActivity.this);
            }



        });

        card3.setOnClickListener(v -> {

            Intent nextActivity = new Intent(MainActivity.this, AllActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("VAL", 2);
            nextActivity.putExtras(bundle);
            startActivity(nextActivity);


        });

        card4.setOnClickListener(v -> {

            sharedPreferences2= getSharedPreferences("my",MODE_PRIVATE);
            String w_name= sharedPreferences2.getString("wallet_name","");

            if (w_name.length()>0){

                Intent nextActivity = new Intent(MainActivity.this, Wallet.class);
                Bundle bundle = new Bundle();
                bundle.putInt("VAL", 91);
                bundle.putString("wallet_name",w_name);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
            }
            else {
                wallet_name(MainActivity.this);
            }


        });

        card5.setOnClickListener(v -> {

                sharedPreferences= getSharedPreferences("myapp",MODE_PRIVATE);
                String name= sharedPreferences.getString("tali_name","");

                if (name.length()>0){

                    Intent nextActivity = new Intent(MainActivity.this, Wallet.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("VAL", 93);
                    bundle.putString("Name",name);
                    nextActivity.putExtras(bundle);
                    startActivity(nextActivity);
                }
                else {
                    tali_name(MainActivity.this);
                }



        });

        card6.setOnClickListener(v -> {

            Intent nextActivity = new Intent(MainActivity.this, Wallet.class);
            Bundle bundle = new Bundle();
            bundle.putInt("VAL", 92);
            nextActivity.putExtras(bundle);
            startActivity(nextActivity);

        });

        card7.setOnClickListener(v -> {

            if(networkInfo!=null&&networkInfo.isConnected()){

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {

                        String order = snapshot.child("order").getValue(String.class);

                        if (order.contains("On")){

                            Intent nextActivity = new Intent(MainActivity.this, Offer.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("VAL", 101);
                            bundle.putInt("type",1);
                            nextActivity.putExtras(bundle);
                            startActivity(nextActivity);


                        }
                        else {

                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                    .setIcon(R.drawable.mb)
                                    .setTitle(" Now Offer Selling is OFF.")
                                    .setMessage("বর্তমানে অফার সেল বন্ধ আছে। কিছুক্ষণ পরে আবার চেষ্টা করুন।।\n\nআমাদের অফার সেল সার্ভিস সকাল ১০টা থেকে রাত ১০টা পর্যন্ত দেওয়া হয়।")
                                    .setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .show();
                        }



                    }

                    @Override
                    public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {

                    }
                });

            }
            else {
                no_internet(MainActivity.this);
            }


        });
        card8.setOnClickListener(v -> {


            if(networkInfo!=null&&networkInfo.isConnected()){

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {

                        String order = snapshot.child("order").getValue(String.class);
                        if (order.contains("On")){

                            Intent nextActivity = new Intent(MainActivity.this, Offer.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("VAL", 101);
                            bundle.putInt("type",2);
                            nextActivity.putExtras(bundle);
                            startActivity(nextActivity);


                        }
                        else {

                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                    .setIcon(R.drawable.mb)
                                    .setTitle(" Now Offer Selling is OFF.")
                                    .setMessage("বর্তমানে অফার সেল বন্ধ আছে। কিছুক্ষণ পরে আবার চেষ্টা করুন।।\n\nআমাদের অফার সেল সার্ভিস সকাল ১০টা থেকে রাত ১০টা পর্যন্ত দেওয়া হয়।")
                                    .setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .show();
                        }



                    }

                    @Override
                    public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {

                    }
                });

            }
            else {
                no_internet(MainActivity.this);
            }


        });
        card9.setOnClickListener(v -> {

            if(networkInfo!=null&&networkInfo.isConnected()){

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {

                        String order = snapshot.child("order").getValue(String.class);

                        if (order.contains("On")){

                            Intent nextActivity = new Intent(MainActivity.this, Offer.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("VAL", 101);
                            bundle.putInt("type",3);
                            nextActivity.putExtras(bundle);
                            startActivity(nextActivity);


                        }
                        else {

                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                    .setIcon(R.drawable.mb)
                                    .setTitle(" Now Offer Selling is OFF.")
                                    .setMessage("বর্তমানে অফার সেল বন্ধ আছে। কিছুক্ষণ পরে আবার চেষ্টা করুন।।\n\nআমাদের অফার সেল সার্ভিস সকাল ১০টা থেকে রাত ১০টা পর্যন্ত দেওয়া হয়।")
                                    .setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    })
                                    .show();
                        }



                    }

                    @Override
                    public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {

                    }
                });


            }
            else {
                no_internet(MainActivity.this);
            }


        });

        card01.setOnClickListener(v -> {

            Intent nextActivity = new Intent(MainActivity.this, Wallet.class);
            Bundle bundle = new Bundle();
            bundle.putInt("VAL", 94);
            nextActivity.putExtras(bundle);
            startActivity(nextActivity);

        });

        card02.setOnClickListener(v -> {

            Intent nextActivity = new Intent(MainActivity.this, AllActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("VAL", 5);
            nextActivity.putExtras(bundle);
            startActivity(nextActivity);


        });

        card03.setOnClickListener(v -> {


            if(networkInfo!=null&&networkInfo.isConnected()){

                Intent nextActivity = new Intent(MainActivity.this, AllActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("VAL", 4);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);


            }
            else {
                no_internet(MainActivity.this);
            }

        });






        toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout,toolbar, R.string.open, R.string.close);
         drawerLayout.addDrawerListener(toggle);


        nav_View.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId()==R.id.mHome){


                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                else if (item.getItemId()==R.id.shareapp){

                    shareapp(MainActivity.this);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                else if (item.getItemId()==R.id.rateapp){

                    Context context= MainActivity.this;
                    final String apppn = context.getPackageName();

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+apppn));
                    startActivity(intent);

                    try {

                    } catch (ActivityNotFoundException e){

                        Intent intent1 = new Intent(Intent.ACTION_VIEW);
                        intent1.setData(Uri.parse("https://play.google.com/store/apps/details?id="+apppn));
                        startActivity(intent1);

                    }

                    drawerLayout.closeDrawer(GravityCompat.START);
                }



                else if (item.getItemId()==R.id.policy){

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://sites.google.com/view/extulprivacy-policy"));
                    startActivity(intent);


                    drawerLayout.closeDrawer(GravityCompat.START);
                }




                else if (item.getItemId()==R.id.more){

                    String devmane = "Modest Software";
                    try {

                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub: "+devmane)));

                    } catch (ActivityNotFoundException e){

                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id= "+devmane)));

                    }

                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                else if (item.getItemId()==R.id.developer){
                    profile(MainActivity.this);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }


                return false;
            }
        });



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                        if (item.getItemId()==R.id.home){


                            Toast.makeText(MainActivity.this, "Reloaded!", Toast.LENGTH_LONG).show();

                        }
                        else if (item.getItemId()==R.id.offer){

                            if(networkInfo!=null&&networkInfo.isConnected()){
                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@androidx.annotation.NonNull DataSnapshot snapshot) {

                                        String order = snapshot.child("order").getValue(String.class);

                                        if (order.contains("On")){

                                            Intent nextActivity = new Intent(MainActivity.this, Offer.class);
                                            Bundle bundle = new Bundle();
                                            bundle.putInt("VAL", 101);
                                            bundle.putInt("type", 11);
                                            nextActivity.putExtras(bundle);
                                            startActivity(nextActivity);

                                        }
                                        else {

                                            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                                                    .setIcon(R.drawable.mb)
                                                    .setTitle(" Now Offer Selling is OFF.")
                                                    .setMessage("বর্তমানে অফার সেল বন্ধ আছে। কিছুক্ষণ পরে আবার চেষ্টা করুন।।\n\nআমাদের অফার সেল সার্ভিস সকাল ১০টা থেকে রাত ১০টা পর্যন্ত দেওয়া হয়।")
                                                    .setPositiveButton("Thanks", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {

                                                        }
                                                    })
                                                    .show();
                                        }



                                    }

                                    @Override
                                    public void onCancelled(@androidx.annotation.NonNull DatabaseError error) {

                                    }
                                });


                            }
                            else {
                                no_internet(MainActivity.this);
                            }



                        }

                        else if (item.getItemId()==R.id.chat){


                            if(networkInfo!=null&&networkInfo.isConnected()){

                                live_chat(MainActivity.this);

                            }
                            else {
                                no_internet(MainActivity.this);
                            }




                        }


                return false;
            }
        });


    } //=======================oncreatend==========================

    @Override
    public void onBackPressed() {


              if(backpress){
                  super.onBackPressed();
              }

             else if (this.backpress=true) {

                  Toast.makeText(MainActivity.this, "Press Twice to Exit.", Toast.LENGTH_LONG).show();

                  new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                      @Override
                      public void run() {

                          backpress=false;

                      }
                  },2000);

        }


    }



    private void live_chat (Context context) {
        // Inflate the custom layout
        View view = LayoutInflater.from(context).inflate(R.layout.livechat, null);

        // Create AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);


        AlertDialog dialog = builder.create();

        view.findViewById(R.id.fb).setOnClickListener(v -> {
            dialog.dismiss();
            Intent intent1 = new Intent(Intent.ACTION_VIEW);
            intent1.setData(Uri.parse("https://www.facebook.com/extulbd"));
            startActivity(intent1);


        });

        view.findViewById(R.id.whatapps).setOnClickListener(v -> {
            dialog.dismiss();
            Intent intent1 = new Intent(Intent.ACTION_VIEW);
            intent1.setData(Uri.parse("https://api.whatsapp.com/send?phone=+8801734814602 &text="));
            startActivity(intent1);


        });




        dialog.show();
    }

    private void profile (Context context) {
        // Inflate the custom layout
        View view = LayoutInflater.from(context).inflate(R.layout.profile, null);

        // Create AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);


        AlertDialog dialog = builder.create();

        view.findViewById(R.id.p_fb).setOnClickListener(v -> {
            String uri = "https://www.facebook.com/bashir45bd";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
            dialog.dismiss();

        });

        view.findViewById(R.id.instragram).setOnClickListener(v -> {
            String uri = "https://www.instagram.com/bashir_ahmed45/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
            dialog.dismiss();

        });

        view.findViewById(R.id.linkdin).setOnClickListener(v -> {
            String uri = "https://www.linkedin.com/in/bashir45bd/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);
            dialog.dismiss();

        });



        dialog.show();

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

    private void shareapp (Context context){

        final String appname = context.getPackageName();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT,"Download Now: https://play.google.com/store/apps/details?id="+appname);
        intent.setType("text/plain");
        context.startActivity(intent);


    }


    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {

                } else {

                }
            });

    private void askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) ==
                    PackageManager.PERMISSION_GRANTED) {


            } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.POST_NOTIFICATIONS)) {

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Notification permission!")
                        .setMessage("Notification permission is required, Please allow notification permission.")
                        .setPositiveButton("Oky", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            } else {

                requestPermissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS);
            }
        }
    }

    private void tali_name(Context context) {

        BottomSheetDialog dialog = new BottomSheetDialog(this);

        View view = getLayoutInflater().inflate(R.layout.to_do_add, null);
        TextView task_title= view.findViewById(R.id.task_title);
        EditText to_do1 = view.findViewById(R.id.to_do_add);
        TextInputLayout hint = view.findViewById(R.id.hint);
        task_title.setText("হিসাবকারীর অথবা ব্যবসা প্রতিষ্ঠান নাম");
        hint.setHint("নাম লিখুন ");



        dialog.setContentView(view);


        view.findViewById(R.id.to_do_submit).setOnClickListener(v -> {




            if (to_do1.length() > 0) {

                String to_do_item = to_do1.getText().toString();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("tali_name", to_do_item);
                editor.apply();
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Press again", Toast.LENGTH_LONG).show();

            } else {
                to_do1.setError("Enter Number");


            }


        });


        dialog.show();

    }

    private void wallet_name(Context context) {

        BottomSheetDialog dialog = new BottomSheetDialog(this);

        View view = getLayoutInflater().inflate(R.layout.to_do_add, null);
        TextView task_title= view.findViewById(R.id.task_title);
        EditText to_do1 = view.findViewById(R.id.to_do_add);
        TextInputLayout hint = view.findViewById(R.id.hint);
        task_title.setText("ওয়ালেট ধারকের নাম");
        hint.setHint("Enter full name");



        dialog.setContentView(view);


        view.findViewById(R.id.to_do_submit).setOnClickListener(v -> {




            if (to_do1.length() > 0) {

                String to_do_item = to_do1.getText().toString();
                SharedPreferences.Editor editor2 = sharedPreferences2.edit();
                editor2.putString("wallet_name", to_do_item);
                editor2.apply();
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Press again", Toast.LENGTH_LONG).show();

            } else {
                to_do1.setError("Enter Number");


            }


        });


        dialog.show();

    }



}