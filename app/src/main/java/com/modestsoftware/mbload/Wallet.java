package com.modestsoftware.mbload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;



import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Wallet extends AppCompatActivity {

    BottomNavigationView wallet_nav, to_do_nav;
    RelativeLayout wallet, wallet_h, wallet_histroy, income_page_h, expence_page_h, analytics, note, talikhata, to_do, to_dp_task, to_do_com;

    ExtendedFloatingActionButton fav,to_do_fav,fav_tali,fav_note;
    TextView tv_income, tv_expence, tv_main_balance,tv_rec,tv_pay,shop_name,wallet_holder;

    walletdbhelper helper;
    ListView incomelist, expencelist;
    RecyclerView note_grid, tali_list, to_do_list, to_do_list_com,recent_list;

    ImageView history_back, to_do_back, back_tali,ana_back,filter;

    TextInputEditText searchview_in, searchview_ex, note_search, to_do_search, tali_search;

    TabLayout wallet_tab,filter_tab;

    HashMap<String, String> hashMap, hashMap1, hashMap2, hashMap3, hashMap_tali, hashMap_task, hashMap_com;
    ArrayList<HashMap<String, String>> arrayList, arrayList1, arrayList2, arrayList3, arrayList_tali, arrayList_task, arrayList_com;

    LottieAnimationView loti1, loti2, loti3, note_loti, tali_loti, loti_task, loti_com;

    Animation item_anim;

    PieChart pieChart;
    LineChart lineChart;
    BarChart barChart;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);

        to_do_nav = findViewById(R.id.to_do_nav);
        to_do_fav = findViewById(R.id.to_do_fav);
        to_dp_task = findViewById(R.id.to_do_task);
        to_do_com = findViewById(R.id.to_do_com);
        to_do_back = findViewById(R.id.to_do_back);
        to_do_fav = findViewById(R.id.to_do_fav);
        to_do_list = findViewById(R.id.to_do_list);
        loti_task = findViewById(R.id.loti_task);
        to_do_search = findViewById(R.id.to_do_search);
        to_do_list_com = findViewById(R.id.to_do_list_com);
        loti_com = findViewById(R.id.loti_com);
        back_tali = findViewById(R.id.back_tali);
        ana_back = findViewById(R.id.ana_back);
        tali_search = findViewById(R.id.tali_search);
        pieChart = findViewById(R.id.pieChart);
        lineChart = findViewById(R.id.lineChart);
        barChart=findViewById(R.id.barChart);
        tv_rec=findViewById(R.id.tv_rec);
        tv_pay=findViewById(R.id.tv_pay);
        filter_tab=findViewById(R.id.filter_tab);
        shop_name=findViewById(R.id.shop_name);
        wallet_holder=findViewById(R.id.wallet_holder);

        history_back = findViewById(R.id.history_back);
        wallet_nav = findViewById(R.id.wallet_nav);
        wallet_tab = findViewById(R.id.wallat_tab);
        income_page_h = findViewById(R.id.income_page_h);
        expence_page_h = findViewById(R.id.expence_page_h);
        searchview_in = findViewById(R.id.searchview_in);
        searchview_ex = findViewById(R.id.searchview_ex);
        note_search = findViewById(R.id.note_search);
        loti1 = findViewById(R.id.loti1);
        loti2 = findViewById(R.id.loti2);
        loti3 = findViewById(R.id.loti3);
        note_loti = findViewById(R.id.note_loti);
        tali_loti = findViewById(R.id.tali_loti);

        wallet = findViewById(R.id.wallet);
        wallet_h = findViewById(R.id.wallet_h);
        analytics = findViewById(R.id.analytics);
        note = findViewById(R.id.note);
        talikhata = findViewById(R.id.talikhata);
        to_do = findViewById(R.id.to_do);
        filter=findViewById(R.id.filter);
        wallet_histroy = findViewById(R.id.wallet_histroy);
        fav = findViewById(R.id.fav);
        fav_note = findViewById(R.id.fav_note);
        fav_tali = findViewById(R.id.fav_tali);

        tv_income = findViewById(R.id.tv_income);
        tv_expence = findViewById(R.id.tv_expence);
        tv_main_balance = findViewById(R.id.tv_main_balance);


        incomelist = findViewById(R.id.incomelist);
        expencelist = findViewById(R.id.expencelist);
        recent_list = findViewById(R.id.recent_list);
        note_grid = findViewById(R.id.note_grid);
        tali_list = findViewById(R.id.tali_list);


        helper = new walletdbhelper(Wallet.this);



        //===========================poricoy==========================================

        Bundle bun = getIntent().getExtras();
        int val = bun.getInt("VAL");
        String tali_name = bun.getString("Name");
        String w_name = bun.getString("wallet_name");

        if (val == 91) {

            note.setVisibility(View.GONE);
            talikhata.setVisibility(View.GONE);
            to_do.setVisibility(View.GONE);
            wallet.setVisibility(View.VISIBLE);
            wallet_holder.setText(w_name);

            recent_list.addItemDecoration(new DividerItemDecoration(Wallet.this,
                    DividerItemDecoration.VERTICAL));


            update_in();

            income_history(helper.getallincome());
            expence_history(helper.getallexpence());
            recent_history(helper.recent());


            wallet_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                    int tabitem = tab.getPosition();

                    if (tabitem == 0) {
                        income_page_h.setVisibility(View.VISIBLE);
                        expence_page_h.setVisibility(View.GONE);
                    } else if (tabitem == 1) {
                        income_page_h.setVisibility(View.GONE);
                        expence_page_h.setVisibility(View.VISIBLE);
                    }

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

            filter_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {

                    int tabitem = tab.getPosition();

                    if (tabitem == 0) {
                        update_in();
                    } else if (tabitem == 1) {

                        day();

                    }
                    else if (tabitem == 2) {

                        week();

                    }
                    else if (tabitem == 3) {
                        month();

                    }
                    else if (tabitem == 4) {
                        year();

                    }


                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

            searchview_in.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    String key1 = searchview_in.getText().toString();
                    income_history(helper.search_income(key1));

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            searchview_ex.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    String key = searchview_ex.getText().toString();
                    expence_history(helper.search_expence(key));

                }

                @Override
                public void afterTextChanged(Editable s) {

                }


            });


            fav.setOnClickListener(v -> {

                add_data(Wallet.this);
            });


            wallet_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    if (item.getItemId() == R.id.w_home) {

                        wallet_histroy.setVisibility(View.GONE);
                        analytics.setVisibility(View.GONE);
                        wallet_h.setVisibility(View.VISIBLE);

                    } else if (item.getItemId() == R.id.analytics) {

                        setpie();
                        loadPieChartData();
                        line_lastweek();
                        bar_week();

                        wallet_histroy.setVisibility(View.GONE);
                        wallet_h.setVisibility(View.GONE);
                        analytics.setVisibility(View.VISIBLE);


                    } else if (item.getItemId() == R.id.history) {

                        wallet_h.setVisibility(View.GONE);
                        analytics.setVisibility(View.GONE);
                        wallet_histroy.setVisibility(View.VISIBLE);
                    }


                    return true;
                }
            });

            history_back.setOnClickListener(v -> {
                onBackPressed();
            });

            ana_back.setOnClickListener(v -> {
                onBackPressed();
            });

            filter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    filter_menu(v);

                }
            });




        }
        else if (val == 92) {

            wallet.setVisibility(View.GONE);
            talikhata.setVisibility(View.GONE);
            to_do.setVisibility(View.GONE);
            note.setVisibility(View.VISIBLE);

            get_note(helper.get_allnote());

            note_search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    String key = note_search.getText().toString();
                    get_note(helper.search_note(key));


                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            fav_note.setOnClickListener(v -> {

                add_note(Wallet.this);
            });


        }
        else if (val == 93) {
            wallet.setVisibility(View.GONE);
            note.setVisibility(View.GONE);
            to_do.setVisibility(View.GONE);
            talikhata.setVisibility(View.VISIBLE);
            shop_name.setText(tali_name);

             tali_list.addItemDecoration(new DividerItemDecoration(Wallet.this,
                    DividerItemDecoration.VERTICAL));

             tali_total(helper.get_user_tali());
             get_tali_user(helper.get_user_tali());



            tali_search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    String key44 = tali_search.getText().toString();
                    get_tali_user(helper.search_tali_user(key44));

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            fav_tali.setOnClickListener(v -> {

                add_tali_user(Wallet.this);

            });

            back_tali.setOnClickListener(v -> {
                onBackPressed();
            });


        }
        else if (val == 94) {
            wallet.setVisibility(View.GONE);
            note.setVisibility(View.GONE);
            talikhata.setVisibility(View.GONE);
            to_do.setVisibility(View.VISIBLE);


            task_history(helper.get_task());
            com_history(helper.get_com());

            to_do_search.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    String key3 = to_do_search.getText().toString();
                    task_history(helper.search_task(key3));


                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            to_do_fav.setOnClickListener(v -> {

                add_data_to_do(Wallet.this);

            });


            to_do_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    if (item.getItemId() == R.id.task) {

                        task_history(helper.get_task());
                        to_do_com.setVisibility(View.GONE);
                        to_dp_task.setVisibility(View.VISIBLE);

                    } else if (item.getItemId() == R.id.com) {

                        com_history(helper.get_com());
                        to_dp_task.setVisibility(View.GONE);
                        to_do_com.setVisibility(View.VISIBLE);


                    }


                    return true;
                }
            });


            to_do_back.setOnClickListener(v -> {
                onBackPressed();
            });
        }


    } //============oncreate===================



    //=============================================wallet=================================================================
    private void add_data(Context context) {


        BottomSheetDialog dialog = new BottomSheetDialog(this);

        // Inflate the layout for the BottomSheetDialog
        View view = getLayoutInflater().inflate(R.layout.add_data, null);


        // Set the layout for the BottomSheetDialog
        dialog.setContentView(view);


        view.findViewById(R.id.income).setOnClickListener(v -> {
            view.findViewById(R.id.view1).setVisibility(View.VISIBLE);
            view.findViewById(R.id.view2).setVisibility(View.GONE);
            view.findViewById(R.id.extence_page).setVisibility(View.GONE);
            view.findViewById(R.id.income_page).setVisibility(View.VISIBLE);

        });

        view.findViewById(R.id.extence).setOnClickListener(v -> {

            view.findViewById(R.id.view2).setVisibility(View.VISIBLE);
            view.findViewById(R.id.view1).setVisibility(View.GONE);
            view.findViewById(R.id.income_page).setVisibility(View.GONE);
            view.findViewById(R.id.extence_page).setVisibility(View.VISIBLE);


        });


        view.findViewById(R.id.in_submit).setOnClickListener(v -> {

            EditText add_in_edit1 = view.findViewById(R.id.add_in_edit1);
            EditText add_in_edit2 = view.findViewById(R.id.add_in_edit2);

            if (add_in_edit1.length() > 0) {

                if (add_in_edit2.length()==0){
                    add_in_edit2.setText("বিবরণ নাই");
                }

                double amount = Double.parseDouble(add_in_edit1.getText().toString());
                String reason = add_in_edit2.getText().toString();
                helper.add_income(amount, reason);
                update_in();
                filter_tab.selectTab(filter_tab.getTabAt(0));
                income_history(helper.getallincome());
                recent_history(helper.recent());
                dialog.dismiss();


            } else {
                add_in_edit1.setError("Enter Number");

            }


        });

        view.findViewById(R.id.ex_submit).setOnClickListener(v -> {

            EditText add_ex_edit1 = view.findViewById(R.id.add_ex_edit1);
            EditText add_ex_edit2 = view.findViewById(R.id.add_ex_edit2);

            if (add_ex_edit1.length() > 0) {

                if (add_ex_edit2.length()==0){
                    add_ex_edit2.setText("বিবরণ নাই");
                }

                double amount = Double.parseDouble(add_ex_edit1.getText().toString());
                String reason = add_ex_edit2.getText().toString();
                helper.add_extence(amount, reason);
                update_in();
                filter_tab.selectTab(filter_tab.getTabAt(0));
                expence_history(helper.getallexpence());
                recent_history(helper.recent());
                dialog.dismiss();


            } else {
                add_ex_edit1.setError("Enter Number");

            }


        });


        dialog.show();

    }

    private void up_and_delete_in(Context context, String id, String amount, String reason) {

        BottomSheetDialog dialog = new BottomSheetDialog(this);

        // Inflate the layout for the BottomSheetDialog
        View view = getLayoutInflater().inflate(R.layout.updateitem, null);

        // Set the layout for the BottomSheetDialog
        dialog.setContentView(view);

        EditText update_amount = view.findViewById(R.id.update_amount);
        EditText update_reason = view.findViewById(R.id.update_reason);
        CardView update_b = view.findViewById(R.id.update_b);
        CardView delete_b = view.findViewById(R.id.delete_b);

        update_amount.setText(amount);
        update_reason.setText(reason);


        update_b.setOnClickListener(v -> {

            if (update_amount.length() > 0 && update_reason.length() > 0) {


                String reason_a = update_reason.getText().toString().trim();
                String amount_a = update_amount.getText().toString().trim();
                Double up_amount = Double.parseDouble(amount_a);
                helper.update_income(id, up_amount, reason_a);
                update_in();
                income_history(helper.getallincome());
                recent_history(helper.recent());
                dialog.dismiss();


            } else {
                update_amount.setError("Enter Number");
                update_reason.setError("Enter text");

            }


        });

        delete_b.setOnClickListener(v -> {


            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setIcon(R.drawable.alert)
                    .setTitle("Delete!")
                    .setMessage("Are you sure?")

                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            helper.delete(id);
                            recent_history(helper.recent());
                            income_history(helper.getallincome());
                            update_in();

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


        dialog.show();

    }

    private void up_and_delete_ex(String id, String amount, String reason) {

        Context context = Wallet.this;
        BottomSheetDialog dialog = new BottomSheetDialog(this);

        // Inflate the layout for the BottomSheetDialog
        View view = getLayoutInflater().inflate(R.layout.updateitem, null);

        // Set the layout for the BottomSheetDialog
        dialog.setContentView(view);

        EditText update_amount = view.findViewById(R.id.update_amount);
        EditText update_reason = view.findViewById(R.id.update_reason);
        CardView update_b = view.findViewById(R.id.update_b);
        CardView delete_b = view.findViewById(R.id.delete_b);

        update_amount.setText(amount);
        update_reason.setText(reason);


        update_b.setOnClickListener(v -> {

            if (update_amount.length() > 0 && update_reason.length() > 0) {


                String reason_a = update_reason.getText().toString().trim();
                String amount_a = update_amount.getText().toString().trim();
                Double up_amount = Double.parseDouble(amount_a);
                helper.update_expence(id, up_amount, reason_a);
                update_in();
                expence_history(helper.getallexpence());
                recent_history(helper.recent());
                dialog.dismiss();

            } else {
                update_amount.setError("Enter Number");
                update_reason.setError("Enter text");

            }


        });

        delete_b.setOnClickListener(v -> {


            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setIcon(R.drawable.alert)
                    .setTitle("Delete!")
                    .setMessage("Are you sure?")

                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            helper.delete_ex(id);
                            recent_history(helper.recent());
                            expence_history(helper.getallexpence());
                            update_in();

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


        dialog.show();

    }


    private void update_in() {

        tv_expence.setText(helper.calculateextence() + "৳");
        tv_income.setText(helper.calculateincome() + "৳");
        double balance = helper.calculateincome() - helper.calculateextence();
        tv_main_balance.setText(balance + "৳");

    }


    private void day() {

        tv_expence.setText(helper.calculateextenceday() + "৳");
        tv_income.setText(helper.calculateincomeday() + "৳");
        double balance = helper.calculateincomeday() - helper.calculateextenceday();
        tv_main_balance.setText(balance + "৳");

    }

    private void week() {

        tv_expence.setText(helper.calculateexpenceweek() + "৳");
        tv_income.setText(helper.calculateincomeweek() + "৳");
        double balance = helper.calculateincomeweek() - helper.calculateexpenceweek();
        tv_main_balance.setText(balance + "৳");

    }
    private void month() {

        tv_expence.setText(helper.calculateexpencemonth() + "৳");
        tv_income.setText(helper.calculateincomemonth() + "৳");
        double balance = helper.calculateincomemonth() - helper.calculateexpencemonth();
        tv_main_balance.setText(balance + "৳");

    }
    private void year() {

        tv_expence.setText(helper.calculateexpenceyear() + "৳");
        tv_income.setText(helper.calculateincomeyear() + "৳");
        double balance = helper.calculateincomeyear() - helper.calculateexpenceyear();
        tv_main_balance.setText(balance + "৳");

    }


    private class incomeadapter extends BaseAdapter {

        LayoutInflater layoutInflater;

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.walletdata, parent, false);

            TextView amount = view.findViewById(R.id.amount);
            TextView reason = view.findViewById(R.id.reason);
            TextView time = view.findViewById(R.id.time);
            LinearLayout income_item = view.findViewById(R.id.income_item);
            LinearLayout anim4 = view.findViewById(R.id.income_item);

            item_anim = AnimationUtils.loadAnimation(Wallet.this, R.anim.item_anim);
            anim4.setAnimation(item_anim);


            HashMap<String, String> hashMap = arrayList.get(position);
            String fid = hashMap.get("id");
            String famount = hashMap.get("amount");
            String freason = hashMap.get("reason");
            String ftime = hashMap.get("time");


            amount.setText("+" + famount);
            reason.setText(freason);

            double atime = Double.parseDouble(ftime);
            SimpleDateFormat simpletimeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            SimpleDateFormat simpledateFormat = new SimpleDateFormat("dd MMM yyyy ", Locale.getDefault());

            String stime = simpletimeFormat.format(atime);
            String date = simpledateFormat.format(atime);

            time.setText(stime + " \n" + date);


            income_item.setOnClickListener(v -> {

                up_and_delete_in(Wallet.this, fid, famount, freason);

            });


            return view;
        }
    }

    private class expenceadapter extends BaseAdapter {

        LayoutInflater layoutInflater;

        @Override
        public int getCount() {
            return arrayList1.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.walletdata, parent, false);

            TextView amount = view.findViewById(R.id.amount);
            TextView reason = view.findViewById(R.id.reason);
            TextView time = view.findViewById(R.id.time);
            ImageView list_image = view.findViewById(R.id.list_image);
            LinearLayout ex_item = view.findViewById(R.id.income_item);
            LinearLayout anim4 = view.findViewById(R.id.income_item);
            item_anim = AnimationUtils.loadAnimation(Wallet.this, R.anim.item_anim);
            anim4.setAnimation(item_anim);

            HashMap<String, String> hashMap = arrayList1.get(position);
            String fid = hashMap.get("id");
            String famount = hashMap.get("amount");
            String freason = hashMap.get("reason");
            String ftime = hashMap.get("time");


            amount.setText("-" + famount);
            reason.setText(freason);
            amount.setTextColor(Color.parseColor("#DE0000"));

            double atime = Double.parseDouble(ftime);
            SimpleDateFormat simpletimeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            SimpleDateFormat simpledateFormat = new SimpleDateFormat("dd MMM yyyy ", Locale.getDefault());

            String stime = simpletimeFormat.format(atime);
            String date = simpledateFormat.format(atime);

            time.setText(stime + " \n" + date);

            list_image.setImageResource(R.drawable.spending);

            ex_item.setOnClickListener(v -> {

                up_and_delete_ex(fid, famount, freason);


            });


            return view;
        }
    }

    private class recent_list_adapter extends RecyclerView.Adapter<recent_list_adapter.viewholder>{


        private class viewholder extends RecyclerView.ViewHolder{

            TextView amount,reason,time;
            ImageView list_image;
            LinearLayout anim3;

            public viewholder(@NonNull View itemView) {
                super(itemView);

                amount = itemView.findViewById(R.id.amount);
                reason = itemView.findViewById(R.id.reason);
                time = itemView.findViewById(R.id.time);
                list_image = itemView.findViewById(R.id.list_image);
                anim3 = itemView.findViewById(R.id.income_item);
            }
        }

        @NonNull
        @Override
        public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.walletdata, parent, false);


            return new viewholder(view);
        }


        @Override
        public void onBindViewHolder(@NonNull viewholder holder, int position) {

            item_anim = AnimationUtils.loadAnimation(Wallet.this, R.anim.item_anim);
            holder.anim3.setAnimation(item_anim);
            holder.anim3.setClickable(false);

            HashMap<String, String> hashMap = arrayList2.get(position);
            String famount = hashMap.get("amount");
            String freason = hashMap.get("reason");
            String ftime = hashMap.get("time");
            String in_condition = hashMap.get("in_con");
            int condition_m = Integer.parseInt(in_condition);


            if (condition_m == 4) {

                holder.amount.setText("+" + famount);
                holder.reason.setText(freason);

                double atime = Double.parseDouble(ftime);
                SimpleDateFormat simpletimeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                SimpleDateFormat simpledateFormat = new SimpleDateFormat("dd MMM yyyy ", Locale.getDefault());

                String stime = simpletimeFormat.format(atime);
                String date = simpledateFormat.format(atime);

                holder.time.setText(stime + " \n" + date);


            } else {
                holder.amount.setText("-" + famount);
                holder.reason.setText(freason);
                holder.amount.setTextColor(Color.parseColor("#DE0000"));

                double atime = Double.parseDouble(ftime);
                SimpleDateFormat simpletimeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                SimpleDateFormat simpledateFormat = new SimpleDateFormat("dd MMM yyyy ", Locale.getDefault());

                String stime = simpletimeFormat.format(atime);
                String date = simpledateFormat.format(atime);

                holder.time.setText(stime + " \n" + date);

                holder.list_image.setImageResource(R.drawable.spending);


            }



        }


        @Override
        public int getItemCount() {
            return arrayList2.size();
        }

    }

    private class note extends RecyclerView.Adapter<note.view_h> {

        private class view_h extends RecyclerView.ViewHolder {
            TextView note_title, main_note, note_time;
            LinearLayout note_item;
            CardView anim2;

            public view_h(@NonNull View itemView) {
                super(itemView);
                note_title = itemView.findViewById(R.id.note_title);
                main_note = itemView.findViewById(R.id.main_note);
                note_time = itemView.findViewById(R.id.note_time);
                note_item = itemView.findViewById(R.id.note_item);
                anim2 = itemView.findViewById(R.id.anim2);

            }
        }


        @NonNull
        @Override
        public view_h onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.noteitem, parent, false);

            return new view_h(view);
        }

        @Override
        public void onBindViewHolder(@NonNull view_h holder, int position) {

            HashMap<String, String> hashMap = arrayList3.get(position);
            String fid = hashMap.get("id");
            String f_title = hashMap.get("title");
            String f_note = hashMap.get("note");
            String f_time = hashMap.get("time");

            holder.note_title.setText(f_title);
            holder.main_note.setText(f_note);
            double a_time = Double.parseDouble(f_time);
            SimpleDateFormat simpletimeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            SimpleDateFormat simpledateFormat = new SimpleDateFormat("dd MMM yyyy ", Locale.getDefault());

            String stime = simpletimeFormat.format(a_time);
            String date = simpledateFormat.format(a_time);

            holder.note_time.setText(date + "" + stime);

            holder.note_item.setOnClickListener(v -> {

                up_and_delete_note(fid, f_title, f_note);

            });

            item_anim = AnimationUtils.loadAnimation(Wallet.this, R.anim.item_anim);
            holder.anim2.setAnimation(item_anim);


        }

        @Override
        public int getItemCount() {
            return arrayList3.size();
        }


    }

    public void income_history(Cursor cursor) {

        if (cursor != null && cursor.getCount() > 0) {
            loti1.setVisibility(View.GONE);
            incomelist.setVisibility(View.VISIBLE);
            arrayList = new ArrayList<>();


            while (cursor.moveToNext()) {

                int id = cursor.getInt(0);
                double amount = cursor.getDouble(1);
                String reason = cursor.getString(2);
                double time = cursor.getDouble(3);


                hashMap = new HashMap<>();
                hashMap.put("id", "" + id);
                hashMap.put("amount", amount + "");
                hashMap.put("reason", reason);
                hashMap.put("time", "" + time);
                arrayList.add(hashMap);


            }

            incomeadapter adapter = new incomeadapter();
            incomelist.setAdapter(adapter);
        } else {
            loti1.setVisibility(View.VISIBLE);
            incomelist.setVisibility(View.GONE);
        }


    }

    public void expence_history(Cursor cursor) {


        if (cursor != null && cursor.getCount() > 0) {
            loti2.setVisibility(View.GONE);
            arrayList1 = new ArrayList<>();
            expencelist.setVisibility(View.VISIBLE);
            while (cursor.moveToNext()) {


                int id = cursor.getInt(0);
                double amount = cursor.getDouble(1);
                String reason = cursor.getString(2);
                double time = cursor.getDouble(3);


                hashMap1 = new HashMap<>();
                hashMap1.put("id", "" + id);
                hashMap1.put("amount", amount + "");
                hashMap1.put("reason", reason);
                hashMap1.put("time", "" + time);
                arrayList1.add(hashMap1);


            }

            expenceadapter ex_adapter = new expenceadapter();
            expencelist.setAdapter(ex_adapter);
        } else {
            loti2.setVisibility(View.VISIBLE);
            expencelist.setVisibility(View.GONE);

        }

    }

    public void recent_history(Cursor cursor) {


        if (cursor != null && cursor.getCount() > 0) {
            loti3.setVisibility(View.GONE);
            recent_list.setVisibility(View.VISIBLE);
            arrayList2 = new ArrayList<>();
            while (cursor.moveToNext()) {


                int id = cursor.getInt(0);
                double amount = cursor.getDouble(1);
                String reason = cursor.getString(2);
                double time = cursor.getDouble(3);
                int con = cursor.getInt(4);


                hashMap2 = new HashMap<>();
                hashMap2.put("id", "" + id);
                hashMap2.put("amount", amount + "৳");
                hashMap2.put("reason", reason);
                hashMap2.put("time", "" + time);
                hashMap2.put("in_con", "" + con);
                arrayList2.add(hashMap2);


            }

            recent_list_adapter adapter = new recent_list_adapter();
            recent_list.setAdapter(adapter);
            recent_list.setLayoutManager(new LinearLayoutManager(Wallet.this));
        } else {
            loti3.setVisibility(View.VISIBLE);
            recent_list.setVisibility(View.GONE);

        }

    }

    //=============================================wallet end=================================================================

    private void add_note(Context context) {

        BottomSheetDialog dialog = new BottomSheetDialog(this);

        // Inflate the layout for the BottomSheetDialog
        View view = getLayoutInflater().inflate(R.layout.noteadd, null);

        // Set the layout for the BottomSheetDialog
        dialog.setContentView(view);

        EditText title = view.findViewById(R.id.title);
        EditText note = view.findViewById(R.id.note);
        ImageView add_note = view.findViewById(R.id.add_note);
        ImageView close = view.findViewById(R.id.close);


        add_note.setOnClickListener(v -> {

            if (note.length() > 0) {

                if (title.length() == 0) {
                    title.setText("Title");
                }
                String title_w = title.getText().toString();
                String note_W = note.getText().toString();
                helper.add_note(title_w, note_W);
                get_note(helper.get_allnote());
                dialog.dismiss();


            } else {
                note.setError("Enter text");

            }


        });

        close.setOnClickListener(v -> {
            dialog.dismiss();
        });


        dialog.show();

    }

    private void up_and_delete_note(String id, String title, String note) {

        Context context = Wallet.this;
        // Inflate the custom layout
        View view = LayoutInflater.from(context).inflate(R.layout.note_update, null);

        // Create AlertDialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        AlertDialog dialog = builder.create();

        EditText title_up = view.findViewById(R.id.title_up);
        EditText note_up = view.findViewById(R.id.note_up);
        ImageView up_note = view.findViewById(R.id.up_note);
        ImageView close_up = view.findViewById(R.id.close_up);
        ImageView delete = view.findViewById(R.id.delete);
        ImageView copy = view.findViewById(R.id.copy);


        title_up.setText(title);
        note_up.setText(note);


        up_note.setOnClickListener(v -> {

            if (note_up.length() > 0) {

                if (title_up.length() == 0) {
                    title_up.setText("Title");
                }
                String title_a = title_up.getText().toString().trim();
                String note_a = note_up.getText().toString().trim();

                helper.update_note(id, title_a, note_a);
                get_note(helper.get_allnote());
                dialog.dismiss();

            } else {
                note_up.setError("Enter text");

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

                            helper.delete_note(id);
                            get_note(helper.get_allnote());


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

        copy.setOnClickListener(v -> {

            ClipboardManager clipboard = (ClipboardManager) getSystemService(Wallet.this.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("TextView", note_up.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(Wallet.this, "Copied", Toast.LENGTH_LONG).show();
        });


        dialog.show();

    }


    public void get_note(Cursor cursor) {


        if (cursor != null && cursor.getCount() > 0) {
            note_loti.setVisibility(View.GONE);
            note_grid.setVisibility(View.VISIBLE);
            arrayList3 = new ArrayList<>();
            while (cursor.moveToNext()) {


                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String note = cursor.getString(2);
                double time = cursor.getDouble(3);


                hashMap3 = new HashMap<>();
                hashMap3.put("id", "" + id);
                hashMap3.put("title", title);
                hashMap3.put("note", note);
                hashMap3.put("time", "" + time);
                arrayList3.add(hashMap3);


            }
            note myadapter_robi = new note();
            note_grid.setAdapter(myadapter_robi);
            StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
            note_grid.setLayoutManager(layoutManager);


        } else {
            note_loti.setVisibility(View.VISIBLE);
            note_grid.setVisibility(View.GONE);

        }

    }


    //=================================================================================
    public void add_tali_user(Context context) {


        BottomSheetDialog dialog = new BottomSheetDialog(this);

        View view = getLayoutInflater().inflate(R.layout.tali_user, null);

        // Set the layout for the BottomSheetDialog
        dialog.setContentView(view);


        view.findViewById(R.id.submit_user).setOnClickListener(v -> {

            EditText name = view.findViewById(R.id.name);
            EditText tikana = view.findViewById(R.id.tikana);

            if (name.length() > 0 && tikana.length()==9) {

                String name_a = name.getText().toString();
                String address = tikana.getText().toString();
                helper.add_user_tali(name_a, address);
                get_tali_user(helper.get_user_tali());
                dialog.dismiss();

            } else {
                name.setError("Enter name");
                tikana.setError("Enter number");

            }


        });

        dialog.show();

    }

    public void get_tali_user(Cursor cursor) {


        if (cursor != null && cursor.getCount() > 0) {
            tali_loti.setVisibility(View.GONE);
            arrayList_tali = new ArrayList<>();
            tali_list.setVisibility(View.VISIBLE);
            while (cursor.moveToNext()) {


                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String address = cursor.getString(2);
                double time = cursor.getDouble(3);


                hashMap_tali = new HashMap<>();
                hashMap_tali.put("id", "" + id);
                hashMap_tali.put("name", name);
                hashMap_tali.put("address", address);
                hashMap_tali.put("time", "" + time);
                arrayList_tali.add(hashMap_tali);

            }

            tali_user_list adapter = new tali_user_list();
            tali_list.setAdapter(adapter);
            tali_list.setLayoutManager(new LinearLayoutManager(Wallet.this));

        } else {
            tali_loti.setVisibility(View.VISIBLE);
            tali_list.setVisibility(View.GONE);
        }

    }

    public void tali_total(Cursor cursor) {

        double total=0;
        double total1=0;

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                int id = cursor.getInt(0);

                double ff = helper.calculate_rec(String.valueOf(id)) - helper.calculate_pay(String.valueOf(id));
                if (ff>0){
                    total=total+ff;
                    tv_rec.setText(""+total+"৳");

                }
                else if(ff<0){
                    double taka= ff*-1;
                    total1=total1+taka;
                    tv_pay.setText(""+total1+"৳");
                }
                else {
                    tv_rec.setText(""+total+"৳");
                    tv_pay.setText(""+total1+"৳");
                }

            }

        }
    }



    private class tali_user_list extends RecyclerView.Adapter<tali_user_list.tali_view> {

        private class tali_view extends RecyclerView.ViewHolder {

            TextView amount, reason, time,imageView;
            LinearLayout income_item;
            LinearLayout anim1;


            public tali_view(@NonNull View itemView) {
                super(itemView);
                amount = itemView.findViewById(R.id.amount);
                reason = itemView.findViewById(R.id.reason);
                income_item = itemView.findViewById(R.id.income_item);
                time = itemView.findViewById(R.id.time);
                imageView = itemView.findViewById(R.id.list_image);
                anim1 = itemView.findViewById(R.id.income_item);



            }
        }

        @NonNull
        @Override
        public tali_view onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.tali_user_show, parent, false);

            return new tali_view(view);
        }

        @Override
        public void onBindViewHolder(@NonNull tali_view holder, int position) {

            HashMap<String, String> hashMap = arrayList_tali.get(position);
            String fid = hashMap.get("id");
            String f_name = hashMap.get("name");
            String f_address = hashMap.get("address");

            
            Random mRandom = new Random();
            int color = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
            ((GradientDrawable) holder.imageView.getBackground()).setColor(color);
            holder.imageView.setText(f_name.substring(0, 1));


            holder.amount.setText(f_name);
            holder.amount.setTextColor(Color.parseColor("#dc1e76"));
            holder.reason.setText("01"+f_address);
            double ff = helper.calculate_rec(fid) - helper.calculate_pay(fid);



            if (ff > 0) {
                holder.time.setText("পাবো\n" + ff + "৳");
                holder.time.setTextColor(Color.parseColor("#14AC1A"));

            } else if (ff == 0) {
                holder.time.setText(ff + "৳");
            } else {
                double f_taka= ff*-1;
                holder.time.setText("দেবো\n" + f_taka + "৳");
                holder.time.setTextColor(Color.parseColor("#DE0000"));

            }


            holder.income_item.setOnClickListener(v -> {

                Codeshower.tali_title_1 = f_name;
                if (ff > 0) {

                    Codeshower.tv_dis1 = "মোট হিসাব: " + ff + "৳ পাবো।";
                } else if (ff == 0) {
                    Codeshower.tv_dis1 = "মোট হিসাব: " + ff + "৳";
                } else {
                    double f_taka= ff*-1;
                    Codeshower.tv_dis1 = "মোট হিসাব: " + f_taka + "৳ দেবো";
                }
                Integer ss = Integer.parseInt(fid);
                Intent nextActivity = new Intent(Wallet.this, Codeshower.class);
                Bundle bundle = new Bundle();
                bundle.putInt("VAL", 96);
                bundle.putInt("user_id", ss);
                bundle.putString("address", f_address);
                bundle.putString("name", f_name);
                bundle.putDouble("taka", ff);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);

            });

            item_anim = AnimationUtils.loadAnimation(Wallet.this, R.anim.item_anim);
            holder.anim1.setAnimation(item_anim);


            
        }

        @Override
        public int getItemCount() {
            return arrayList_tali.size();
        }

    }

    //=====================================================================

    private void add_data_to_do(Context context) {

        BottomSheetDialog dialog = new BottomSheetDialog(this);

        // Inflate the layout for the BottomSheetDialog
        View view = getLayoutInflater().inflate(R.layout.to_do_add, null);

        // Set the layout for the BottomSheetDialog
        dialog.setContentView(view);


        view.findViewById(R.id.to_do_submit).setOnClickListener(v -> {

            EditText to_do1 = view.findViewById(R.id.to_do_add);


            if (to_do1.length() > 0) {

                String to_do_item = to_do1.getText().toString();
                helper.add_to_do_task(to_do_item);
                task_history(helper.get_task());
                dialog.dismiss();


            } else {
                to_do1.setError("Enter Number");


            }


        });


        dialog.show();

    }


    private class to_do_adapter extends RecyclerView.Adapter<to_do_adapter.viewholder1> {


        private class viewholder1 extends RecyclerView.ViewHolder {

            CheckBox checkBox;
            TextView work;
            LinearLayout task_item;
            CardView anim;

            public viewholder1(@NonNull View itemView) {
                super(itemView);
                work = itemView.findViewById(R.id.work);
                task_item = itemView.findViewById(R.id.task_item);
                checkBox = itemView.findViewById(R.id.checkbox);
                anim = itemView.findViewById(R.id.anim);


            }
        }

        @NonNull
        @Override
        public viewholder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater4 = getLayoutInflater();
            View view = layoutInflater4.inflate(R.layout.to_do_item, parent, false);

            return new viewholder1(view);
        }

        @Override
        public void onBindViewHolder(@NonNull viewholder1 holder, int position) {


            HashMap<String, String> hashMap = arrayList_task.get(position);
            String fid = hashMap.get("id");
            String f_task = hashMap.get("task");


            holder.work.setText(f_task);

            holder.task_item.setOnClickListener(v -> {

                up_to_do(Wallet.this, f_task, fid);

            });

            holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {

                if (isChecked) {

                    helper.add_to_do_com(f_task);

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            helper.delete_to_do_task(fid);
                            task_history(helper.get_task());

                        }
                    }, 800);


                }

            });


            item_anim = AnimationUtils.loadAnimation(Wallet.this, R.anim.item_anim);
            holder.anim.setAnimation(item_anim);
        }

        @Override
        public int getItemCount() {
            return arrayList_task.size();
        }

    }

    public void task_history(Cursor cursor) {


        if (cursor != null && cursor.getCount() > 0) {
            loti_task.setVisibility(View.GONE);
            to_do_list.setVisibility(View.VISIBLE);
            arrayList_task = new ArrayList<>();
            while (cursor.moveToNext()) {


                int id = cursor.getInt(0);
                String task = cursor.getString(1);
                double time = cursor.getDouble(2);


                hashMap_task = new HashMap<>();
                hashMap_task.put("id", "" + id);
                hashMap_task.put("task", task);
                hashMap_task.put("time", "" + time);
                arrayList_task.add(hashMap_task);


            }
            to_do_adapter myadatper = new to_do_adapter();
            to_do_list.setAdapter(myadatper);
            to_do_list.setLayoutManager(new LinearLayoutManager(Wallet.this));
        } else {
            loti_task.setVisibility(View.VISIBLE);
            to_do_list.setVisibility(View.GONE);

        }

    }


    private void up_to_do(Context context, String task_title, String id) {

        BottomSheetDialog dialog = new BottomSheetDialog(this);

        // Inflate the layout for the BottomSheetDialog
        View view = getLayoutInflater().inflate(R.layout.to_do_add, null);

        // Set the layout for the BottomSheetDialog
        dialog.setContentView(view);
        TextView title = view.findViewById(R.id.task_title);
        TextView task_up = view.findViewById(R.id.task_up);
        title.setText("কাজের লিস্ট আপডেট করুন");
        task_up.setText("আপডেট");
        EditText to_do1 = view.findViewById(R.id.to_do_add);
        to_do1.setText(task_title);

        CardView to_do_submit = view.findViewById(R.id.to_do_submit);


        to_do_submit.setOnClickListener(v -> {


            if (to_do1.length() > 0) {

                String to_do_item = to_do1.getText().toString();
                helper.Update_task(id, to_do_item);
                task_history(helper.get_task());
                dialog.dismiss();


            } else {
                to_do1.setError("Enter Number");


            }


        });


        dialog.show();

    }


    private class com_adapter extends RecyclerView.Adapter<com_adapter.viewh4> {


        private class viewh4 extends RecyclerView.ViewHolder {
            CheckBox checkBox;
            TextView work;
            LinearLayout task_item;
            CardView anim2;

            public viewh4(@NonNull View itemView) {
                super(itemView);

                work = itemView.findViewById(R.id.work);
                task_item = itemView.findViewById(R.id.task_item);
                checkBox = itemView.findViewById(R.id.checkbox);
                anim2 = itemView.findViewById(R.id.anim);

            }
        }

        @NonNull
        @Override
        public viewh4 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.to_do_item, parent, false);
            return new viewh4(view);
        }

        @Override
        public void onBindViewHolder(@NonNull viewh4 holder, int position) {


            HashMap<String, String> hashMap = arrayList_com.get(position);
            String fid = hashMap.get("id");
            String f_task = hashMap.get("task");

            holder.work.setText(f_task);
            holder.checkBox.setChecked(true);
            holder.checkBox.setEnabled(false);


            holder.task_item.setOnClickListener(v -> {

                AlertDialog alertDialog = new AlertDialog.Builder(Wallet.this)
                        .setIcon(R.drawable.alert)
                        .setTitle("Delete!")
                        .setMessage("Are you sure?")

                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                helper.delete_com(fid);
                                com_history(helper.get_com());
                                Toast.makeText(Wallet.this, "Deleted", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setNegativeButton("Undo", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                helper.add_to_do_task(f_task);
                                task_history(helper.get_task());
                                helper.delete_com(fid);
                                com_history(helper.get_com());
                                Toast.makeText(Wallet.this, "Successfully Undo", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .show();
            });

            item_anim = AnimationUtils.loadAnimation(Wallet.this, R.anim.item_anim);
            holder.anim2.setAnimation(item_anim);
        }

        @Override
        public int getItemCount() {
            return arrayList_com.size();
        }

    }


    public void com_history(Cursor cursor) {


        if (cursor != null && cursor.getCount() > 0) {
            loti_com.setVisibility(View.GONE);
            to_do_list_com.setVisibility(View.VISIBLE);
            arrayList_com = new ArrayList<>();
            while (cursor.moveToNext()) {


                int id = cursor.getInt(0);
                String task = cursor.getString(1);
                double time = cursor.getDouble(2);


                hashMap_com = new HashMap<>();
                hashMap_com.put("id", "" + id);
                hashMap_com.put("task", task);
                hashMap_com.put("time", "" + time);
                arrayList_com.add(hashMap_com);


            }
            com_adapter myadatper = new com_adapter();
            to_do_list_com.setAdapter(myadatper);
            to_do_list_com.setLayoutManager(new LinearLayoutManager(Wallet.this));
        } else {
            loti_com.setVisibility(View.VISIBLE);
            to_do_list_com.setVisibility(View.GONE);

        }

    }


    @Override
    protected void onPostResume() {

        tali_total(helper.get_user_tali());
        get_tali_user(helper.get_user_tali());
        super.onPostResume();
    }

    private void setpie() {

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setEntryLabelTextSize(11f);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setCenterText("Expense\nTracker");
        pieChart.setCenterTextSize(22f);
        pieChart.getLegend().setEnabled(false);
        pieChart.animateY(1200);


    }

    private void loadPieChartData() {

        double income_pie = helper.calculateincome();
        double ex_pie = helper.calculateextence();
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry((float) income_pie, "Income"));
        entries.add(new PieEntry((float) ex_pie, "Expense"));


        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#dc1e76"));
        colors.add(Color.parseColor("#fae208"));
        PieDataSet dataSet = new PieDataSet(entries, "Wallet");
        dataSet.setSliceSpace(3f);
        dataSet.setColors(colors);
        dataSet.setSelectionShift(5f);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate(); // Refresh the chart
    }

    private void line_today() {

        Cursor cursor = helper.in_today();
        Cursor cursor1= helper.ex_today();
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Entry> entries1 = new ArrayList<>();
        float count= 0;
        float count1=0;
        // Add some sample data

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                double amount = cursor.getDouble(1);
                entries.add(new Entry( count, (float) amount));
                count++;

            }
        }


        if (cursor1 != null && cursor1.getCount() > 0) {

            while (cursor1.moveToNext()) {

                double amount = cursor1.getDouble(1);
                entries1.add(new Entry( count1, (float) amount));
                count1++;

            }
        }

            // Create a LineDataSet
            LineDataSet dataSet = new LineDataSet(entries, "Income");
            dataSet.setColor(Color.parseColor("#dc1e76"));
            dataSet.setCircleColor(Color.parseColor("#dc1e76"));

            dataSet.setLineWidth(3);
            dataSet.setCircleRadius(5);
            dataSet.setDrawValues(false);
            // Create a LineDataSet
            LineDataSet dataSet1 = new LineDataSet(entries1, "Expense");
            dataSet1.setColor(Color.parseColor("#fae208"));
            dataSet1.setLineWidth(3);
            dataSet1.setCircleColor(Color.parseColor("#dc1e76"));
            dataSet1.setCircleRadius(5);
            dataSet1.setDrawValues(false);




             LineData lineData = new LineData(dataSet,dataSet1);
            // Set data to the LineChart
            lineChart.setData(lineData);
            lineChart.invalidate(); // Refresh chart


        lineChart.getDescription().setEnabled(false);
        lineChart.getXAxis().setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getLegend().setEnabled(true);


    }
    private void bar_today(){


        Cursor cursor = helper.in_today();
        Cursor cursor1= helper.ex_today();
        float count= 0;
        float count1=0;
        ArrayList<BarEntry> group1Entries = new ArrayList<>();
        ArrayList<BarEntry> group2Entries = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                double amount = cursor.getDouble(1);
                group1Entries.add(new BarEntry(count, (float) amount));
                count++;

            }
        }

        if (cursor1!= null && cursor1.getCount() > 0) {

            while (cursor1.moveToNext()) {
                double amount = cursor1.getDouble(1);
                group2Entries.add(new BarEntry(count1, (float) amount));
                count1++;

            }
        }
;
        BarDataSet group1DataSet = new BarDataSet(group1Entries, "Income");
        group1DataSet.setColor(getResources().getColor(R.color.color1));
        group1DataSet.setDrawValues(false);

        BarDataSet group2DataSet = new BarDataSet(group2Entries, "Expense");
        group2DataSet.setColor(getResources().getColor(R.color.color));
        group2DataSet.setDrawValues(false);

        // Creating a BarData object and adding the data sets
        BarData barData = new BarData(group1DataSet, group2DataSet);

        // Setting bar width and formatting
        float groupSpace = 0.1f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.43f; // x2 dataset
        // (0.1 + 0.43) * 2 + 0.02 = 1.00 -> interval per "group"

        barData.setBarWidth(barWidth);
        barChart.setData(barData);
        barChart.groupBars(0f, groupSpace, barSpace);
        barChart.setFitBars(true);
        barChart.getDescription().setEnabled(false);
        barChart.animateY(1200);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getLegend().setEnabled(true);

        // Customizing the X-Axis labels
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{""}));
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        barChart.invalidate(); // refresh
    }
    private void line_lastday() {

        Cursor cursor = helper.in_lastday();
        Cursor cursor1= helper.ex_lastday();
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Entry> entries1 = new ArrayList<>();
        float count= 0;
        float count1=0;
        // Add some sample data

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                double amount = cursor.getDouble(1);
                entries.add(new Entry( count, (float) amount));
                count++;

            }
        }

        if (cursor1 != null && cursor1.getCount() > 0) {

            while (cursor1.moveToNext()) {

                double amount = cursor1.getDouble(1);
                entries1.add(new Entry( count1, (float) amount));
                count1++;

            }
        }

        // Create a LineDataSet
        LineDataSet dataSet = new LineDataSet(entries, "Income");
        dataSet.setColor(Color.parseColor("#dc1e76"));
        dataSet.setCircleColor(Color.parseColor("#dc1e76"));

        dataSet.setLineWidth(3);
        dataSet.setCircleRadius(5);
        dataSet.setDrawValues(false);
        // Create a LineDataSet
        LineDataSet dataSet1 = new LineDataSet(entries1, "Expense");
        dataSet1.setColor(Color.parseColor("#fae208"));
        dataSet1.setLineWidth(3);
        dataSet1.setCircleColor(Color.parseColor("#dc1e76"));
        dataSet1.setCircleRadius(5);
        dataSet1.setDrawValues(false);




        LineData lineData = new LineData(dataSet,dataSet1);
        // Set data to the LineChart
        lineChart.setData(lineData);
        lineChart.invalidate(); // Refresh chart


        lineChart.getDescription().setEnabled(false);
        lineChart.getXAxis().setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getLegend().setEnabled(true);


    }

    private void bar_last(){


        Cursor cursor = helper.in_lastday();
        Cursor cursor1= helper.ex_lastday();
        float count= 0;
        float count1=0;
        ArrayList<BarEntry> group1Entries = new ArrayList<>();
        ArrayList<BarEntry> group2Entries = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                double amount = cursor.getDouble(1);
                group1Entries.add(new BarEntry(count, (float) amount));
                count++;

            }
        }

        if (cursor1!= null && cursor1.getCount() > 0) {

            while (cursor1.moveToNext()) {
                double amount = cursor1.getDouble(1);
                group2Entries.add(new BarEntry(count1, (float) amount));
                count1++;

            }
        }
        ;
        BarDataSet group1DataSet = new BarDataSet(group1Entries, "Income");
        group1DataSet.setColor(getResources().getColor(R.color.color1));
        group1DataSet.setDrawValues(false);

        BarDataSet group2DataSet = new BarDataSet(group2Entries, "Expense");
        group2DataSet.setColor(getResources().getColor(R.color.color));
        group2DataSet.setDrawValues(false);

        // Creating a BarData object and adding the data sets
        BarData barData = new BarData(group1DataSet, group2DataSet);

        // Setting bar width and formatting
        float groupSpace = 0.1f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.43f; // x2 dataset
        // (0.1 + 0.43) * 2 + 0.02 = 1.00 -> interval per "group"

        barData.setBarWidth(barWidth);
        barChart.setData(barData);
        barChart.groupBars(0f, groupSpace, barSpace);
        barChart.setFitBars(true);
        barChart.getDescription().setEnabled(false);
        barChart.animateY(1200);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getLegend().setEnabled(true);

        // Customizing the X-Axis labels
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{""}));
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        barChart.invalidate(); // refresh
    }

    private void line_lastweek() {

        Cursor cursor = helper.in_lastweek();
        Cursor cursor1= helper.ex_lastweek();
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Entry> entries1 = new ArrayList<>();
        float count= 0;
        float count1=0;
        // Add some sample data

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                double amount = cursor.getDouble(1);
                entries.add(new Entry( count, (float) amount));
                count++;

            }
        }

        if (cursor1 != null && cursor1.getCount() > 0) {

            while (cursor1.moveToNext()) {

                double amount = cursor1.getDouble(1);
                entries1.add(new Entry( count1, (float) amount));
                count1++;

            }
        }

        // Create a LineDataSet
        LineDataSet dataSet = new LineDataSet(entries, "Income");
        dataSet.setColor(Color.parseColor("#dc1e76"));
        dataSet.setCircleColor(Color.parseColor("#dc1e76"));

        dataSet.setLineWidth(3);
        dataSet.setCircleRadius(5);
        dataSet.setDrawValues(false);
        // Create a LineDataSet
        LineDataSet dataSet1 = new LineDataSet(entries1, "Expense");
        dataSet1.setColor(Color.parseColor("#fae208"));
        dataSet1.setLineWidth(3);
        dataSet1.setCircleColor(Color.parseColor("#dc1e76"));
        dataSet1.setCircleRadius(5);
        dataSet1.setDrawValues(false);




        LineData lineData = new LineData(dataSet,dataSet1);
        // Set data to the LineChart
        lineChart.setData(lineData);
        lineChart.invalidate(); // Refresh chart


        lineChart.getDescription().setEnabled(false);
        lineChart.getXAxis().setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getLegend().setEnabled(true);


    }

    private void bar_week(){


        Cursor cursor = helper.in_lastweek();
        Cursor cursor1= helper.ex_lastweek();
        float count= 0;
        float count1=0;
        ArrayList<BarEntry> group1Entries = new ArrayList<>();
        ArrayList<BarEntry> group2Entries = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                double amount = cursor.getDouble(1);
                group1Entries.add(new BarEntry(count, (float) amount));
                count++;

            }
        }

        if (cursor1!= null && cursor1.getCount() > 0) {

            while (cursor1.moveToNext()) {
                double amount = cursor1.getDouble(1);
                group2Entries.add(new BarEntry(count1, (float) amount));
                count1++;

            }
        }
        ;
        BarDataSet group1DataSet = new BarDataSet(group1Entries, "Income");
        group1DataSet.setColor(getResources().getColor(R.color.color1));
        group1DataSet.setDrawValues(false);

        BarDataSet group2DataSet = new BarDataSet(group2Entries, "Expense");
        group2DataSet.setColor(getResources().getColor(R.color.color));
        group2DataSet.setDrawValues(false);

        // Creating a BarData object and adding the data sets
        BarData barData = new BarData(group1DataSet, group2DataSet);

        // Setting bar width and formatting
        float groupSpace = 0.1f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.43f; // x2 dataset
        // (0.1 + 0.43) * 2 + 0.02 = 1.00 -> interval per "group"

        barData.setBarWidth(barWidth);
        barChart.setData(barData);
        barChart.groupBars(0f, groupSpace, barSpace);
        barChart.setFitBars(true);
        barChart.getDescription().setEnabled(false);
        barChart.animateY(1200);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getLegend().setEnabled(true);

        // Customizing the X-Axis labels
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{""}));
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        barChart.invalidate(); // refresh
    }

    private void line_lastmonth() {

        Cursor cursor = helper.in_lastmonth();
        Cursor cursor1= helper.ex_lastmonth();
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Entry> entries1 = new ArrayList<>();
        float count= 0;
        float count1=0;
        // Add some sample data

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                double amount = cursor.getDouble(1);
                entries.add(new Entry( count, (float) amount));
                count++;

            }
        }

        if (cursor1 != null && cursor1.getCount() > 0) {

            while (cursor1.moveToNext()) {

                double amount = cursor1.getDouble(1);
                entries1.add(new Entry( count1, (float) amount));
                count1++;

            }
        }

        // Create a LineDataSet
        LineDataSet dataSet = new LineDataSet(entries, "Income");
        dataSet.setColor(Color.parseColor("#dc1e76"));
        dataSet.setCircleColor(Color.parseColor("#dc1e76"));

        dataSet.setLineWidth(3);
        dataSet.setCircleRadius(5);
        dataSet.setDrawValues(false);
        // Create a LineDataSet
        LineDataSet dataSet1 = new LineDataSet(entries1, "Expense");
        dataSet1.setColor(Color.parseColor("#fae208"));
        dataSet1.setLineWidth(3);
        dataSet1.setCircleColor(Color.parseColor("#dc1e76"));
        dataSet1.setCircleRadius(5);
        dataSet1.setDrawValues(false);




        LineData lineData = new LineData(dataSet,dataSet1);
        // Set data to the LineChart
        lineChart.setData(lineData);
        lineChart.invalidate(); // Refresh chart


        lineChart.getDescription().setEnabled(false);
        lineChart.getXAxis().setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getLegend().setEnabled(true);


    }

    private void bar_month(){


        Cursor cursor = helper.in_lastmonth();
        Cursor cursor1= helper.ex_lastmonth();
        float count= 0;
        float count1=0;
        ArrayList<BarEntry> group1Entries = new ArrayList<>();
        ArrayList<BarEntry> group2Entries = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                double amount = cursor.getDouble(1);
                group1Entries.add(new BarEntry(count, (float) amount));
                count++;

            }
        }

        if (cursor1!= null && cursor1.getCount() > 0) {

            while (cursor1.moveToNext()) {
                double amount = cursor1.getDouble(1);
                group2Entries.add(new BarEntry(count1, (float) amount));
                count1++;

            }
        }
        ;
        BarDataSet group1DataSet = new BarDataSet(group1Entries, "Income");
        group1DataSet.setColor(getResources().getColor(R.color.color1));
        group1DataSet.setDrawValues(false);

        BarDataSet group2DataSet = new BarDataSet(group2Entries, "Expense");
        group2DataSet.setColor(getResources().getColor(R.color.color));
        group2DataSet.setDrawValues(false);

        // Creating a BarData object and adding the data sets
        BarData barData = new BarData(group1DataSet, group2DataSet);

        // Setting bar width and formatting
        float groupSpace = 0.1f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.43f; // x2 dataset
        // (0.1 + 0.43) * 2 + 0.02 = 1.00 -> interval per "group"

        barData.setBarWidth(barWidth);
        barChart.setData(barData);
        barChart.groupBars(0f, groupSpace, barSpace);
        barChart.setFitBars(true);
        barChart.getDescription().setEnabled(false);
        barChart.animateY(1200);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getLegend().setEnabled(true);

        // Customizing the X-Axis labels
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{""}));
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        barChart.invalidate(); // refresh
    }

    private void line_lastyear() {

        Cursor cursor = helper.in_lastyear();
        Cursor cursor1= helper.ex_lastyear();
        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Entry> entries1 = new ArrayList<>();
        float count= 0;
        float count1=0;
        // Add some sample data

        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {

                double amount = cursor.getDouble(1);
                entries.add(new Entry( count, (float) amount));
                count++;

            }
        }

        if (cursor1 != null && cursor1.getCount() > 0) {

            while (cursor1.moveToNext()) {

                double amount = cursor1.getDouble(1);
                entries1.add(new Entry( count1, (float) amount));
                count1++;

            }
        }

        // Create a LineDataSet
        LineDataSet dataSet = new LineDataSet(entries, "Income");
        dataSet.setColor(Color.parseColor("#dc1e76"));
        dataSet.setCircleColor(Color.parseColor("#dc1e76"));

        dataSet.setLineWidth(3);
        dataSet.setCircleRadius(5);
        dataSet.setDrawValues(false);
        // Create a LineDataSet
        LineDataSet dataSet1 = new LineDataSet(entries1, "Expense");
        dataSet1.setColor(Color.parseColor("#fae208"));
        dataSet1.setLineWidth(3);
        dataSet1.setCircleColor(Color.parseColor("#dc1e76"));
        dataSet1.setCircleRadius(5);
        dataSet1.setDrawValues(false);




        LineData lineData = new LineData(dataSet,dataSet1);
        // Set data to the LineChart
        lineChart.setData(lineData);
        lineChart.invalidate(); // Refresh chart


        lineChart.getDescription().setEnabled(false);
        lineChart.getXAxis().setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        lineChart.getXAxis().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getLegend().setEnabled(true);


    }

    private void bar_year(){


        Cursor cursor = helper.in_lastyear();
        Cursor cursor1= helper.ex_lastyear();
        float count= 0;
        float count1=0;
        ArrayList<BarEntry> group1Entries = new ArrayList<>();
        ArrayList<BarEntry> group2Entries = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {

            while (cursor.moveToNext()) {
                double amount = cursor.getDouble(1);
                group1Entries.add(new BarEntry(count, (float) amount));
                count++;

            }
        }

        if (cursor1!= null && cursor1.getCount() > 0) {

            while (cursor1.moveToNext()) {
                double amount = cursor1.getDouble(1);
                group2Entries.add(new BarEntry(count1, (float) amount));
                count1++;

            }
        }
        ;
        BarDataSet group1DataSet = new BarDataSet(group1Entries, "Income");
        group1DataSet.setColor(getResources().getColor(R.color.color1));
        group1DataSet.setDrawValues(false);

        BarDataSet group2DataSet = new BarDataSet(group2Entries, "Expense");
        group2DataSet.setColor(getResources().getColor(R.color.color));
        group2DataSet.setDrawValues(false);

        // Creating a BarData object and adding the data sets
        BarData barData = new BarData(group1DataSet, group2DataSet);

        // Setting bar width and formatting
        float groupSpace = 0.1f;
        float barSpace = 0.02f; // x2 dataset
        float barWidth = 0.43f; // x2 dataset
        // (0.1 + 0.43) * 2 + 0.02 = 1.00 -> interval per "group"

        barData.setBarWidth(barWidth);
        barChart.setData(barData);
        barChart.groupBars(0f, groupSpace, barSpace);
        barChart.setFitBars(true);
        barChart.getDescription().setEnabled(false);
        barChart.animateY(1200);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getLegend().setEnabled(true);

        // Customizing the X-Axis labels
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{""}));
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        barChart.invalidate(); // refresh
    }

    private void filter_menu (View view) {

        // Create a ContextThemeWrapper with the custom style
        ContextThemeWrapper ctw = new ContextThemeWrapper(Wallet.this, R.style.CustomPopupMenu);
        // Create the PopupMenu with the custom context
        PopupMenu popupMenu = new PopupMenu(ctw, view);

        // Inflate the menu resource into the PopupMenu
        popupMenu.getMenuInflater().inflate(R.menu.filter_grap, popupMenu.getMenu());

        // Set a click listener for menu items
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId()==R.id.today){
                  line_today();
                  bar_today();
                }

                else if (item.getItemId()==R.id.lastday){

                    line_lastday();
                    bar_last();

                }

                else if (item.getItemId()==R.id.lastweek){

                    line_lastweek();
                    bar_week();

                }

                else if (item.getItemId()==R.id.lastmonth){

                    line_lastmonth();
                    bar_month();
                }

                else if (item.getItemId()==R.id.lastyear){

                    line_lastyear();
                    bar_year();
                }


                return false;
            }

        });

        popupMenu.show();
    }

}