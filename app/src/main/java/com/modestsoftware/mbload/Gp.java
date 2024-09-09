package com.modestsoftware.mbload;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Gp extends Fragment {
    ListView gp_bundel,gp_minute,gp_mb;
    Animation item_anim;
    TextView bundel,minute,mb;

    HashMap<String,String> hashMap_bundel,hashMap_mb,hashMap_minute;
    ArrayList< HashMap<String,String> > arrayList = new ArrayList<>();
    ArrayList< HashMap<String,String> > arrayList1 = new ArrayList<>();
    ArrayList< HashMap<String,String> > arrayList2 = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_gp, container, false);


        gp_bundel=view.findViewById(R.id.gp_bundel);
        gp_mb=view.findViewById(R.id.gp_mb);
        gp_minute=view.findViewById(R.id.gp_minute);
        bundel =view.findViewById(R.id.bundel);
        mb =view.findViewById(R.id.mb);
        minute =view.findViewById(R.id.minute);



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("gp");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    String title = snapshot.child("title").getValue(String.class);
                    String price = snapshot.child("price").getValue(String.class);
                    String discount = snapshot.child("discount").getValue(String.class);
                    String rprice = snapshot.child("rprice").getValue(String.class);
                    String location = snapshot.child("location").getValue(String.class);

                    hashMap_bundel= new HashMap<>();
                    hashMap_bundel.put("atitle",title);
                    hashMap_bundel.put("aprice",price);
                    hashMap_bundel.put("adiscount",discount);
                    hashMap_bundel.put("arprice",rprice);
                    hashMap_bundel.put("alocation",location);
                    arrayList.add(hashMap_bundel);


                }

                adapter mradapter = new adapter();
                gp_bundel.setAdapter(mradapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {

                Log.w("MainActivity", "Failed to read value.", error.toException());
            }
        });



        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("gp_minute");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {


                for (DataSnapshot snapshot1 : dataSnapshot1.getChildren()) {

                    String title = snapshot1.child("title").getValue(String.class);
                    String price = snapshot1.child("price").getValue(String.class);
                    String discount = snapshot1.child("discount").getValue(String.class);
                    String rprice = snapshot1.child("rprice").getValue(String.class);
                    String location = snapshot1.child("location").getValue(String.class);

                    hashMap_minute= new HashMap<>();
                    hashMap_minute.put("atitle",title);
                    hashMap_minute.put("aprice",price);
                    hashMap_minute.put("adiscount",discount);
                    hashMap_minute.put("arprice",rprice);
                    hashMap_minute.put("alocation",location);
                    arrayList1.add(hashMap_minute);


                }

                adapter1 mradapter1 = new adapter1();
                gp_minute.setAdapter(mradapter1);
            }

            @Override
            public void onCancelled(DatabaseError error) {

                Log.w("MainActivity", "Failed to read value.", error.toException());
            }
        });



        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child("gp_mb");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot2) {


                for (DataSnapshot snapshot2 : dataSnapshot2.getChildren()) {

                    String title = snapshot2.child("title").getValue(String.class);
                    String price = snapshot2.child("price").getValue(String.class);
                    String discount = snapshot2.child("discount").getValue(String.class);
                    String rprice = snapshot2.child("rprice").getValue(String.class);
                    String location = snapshot2.child("location").getValue(String.class);

                    hashMap_mb= new HashMap<>();
                    hashMap_mb.put("atitle",title);
                    hashMap_mb.put("aprice",price);
                    hashMap_mb.put("adiscount",discount);
                    hashMap_mb.put("arprice",rprice);
                    hashMap_mb.put("alocation",location);
                    arrayList2.add(hashMap_mb);


                }

                adapter2 mradapter2 = new adapter2();
                gp_mb.setAdapter(mradapter2);
            }

            @Override
            public void onCancelled(DatabaseError error) {

                Log.w("MainActivity", "Failed to read value.", error.toException());
            }
        });





        bundel.setOnClickListener(v -> {

            gp_bundel.setVisibility(View.VISIBLE);
            gp_mb.setVisibility(View.GONE);
            gp_minute.setVisibility(View.GONE);

        });

        mb.setOnClickListener(v -> {

            gp_bundel.setVisibility(View.GONE);
            gp_mb.setVisibility(View.VISIBLE);
            gp_minute.setVisibility(View.GONE);

        });

        minute.setOnClickListener(v -> {
            gp_bundel.setVisibility(View.GONE);
            gp_mb.setVisibility(View.GONE);
            gp_minute.setVisibility(View.VISIBLE);

        });










        return view;
    }


    public class adapter extends BaseAdapter {

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
            View view = layoutInflater.inflate(R.layout.list,parent,false);

            TextView title = view.findViewById(R.id.title);
            TextView price = view.findViewById(R.id.price);
            TextView rprice = view.findViewById(R.id.rprice);
            TextView location = view.findViewById(R.id.location);
            LinearLayout sumbit_box = view.findViewById(R.id.click);
            ImageView imageView =view.findViewById(R.id.image);

            item_anim= AnimationUtils.loadAnimation(getContext(), R.anim.item_anim);
            sumbit_box.setAnimation(item_anim);

            imageView.setImageResource(R.drawable.gp);
            imageView.setColorFilter(Color.parseColor("#dc1e76"));

            HashMap<String,String>hashMap=arrayList.get(position);
            String ftitle = hashMap.get("atitle");
            String fprice = hashMap.get("aprice");
            String frprice = hashMap.get("arprice");
            String flocation = hashMap.get("alocation");


            title.setText(ftitle);
            price.setText(fprice+"৳");
            rprice.setText(frprice+"৳");
            location.setText(flocation);

            sumbit_box.setOnClickListener(v -> {

                Intent nextActivity = new Intent(getContext(), Offer.class);
                Bundle bundle = new Bundle();
                bundle.putString("title",ftitle);
                bundle.putString("location",flocation);
                bundle.putString("price",fprice);
                bundle.putInt("VAL", 103);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
            });




            return view;
        }
    }
    public class adapter1 extends BaseAdapter {

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
            View view = layoutInflater.inflate(R.layout.list,parent,false);

            TextView title = view.findViewById(R.id.title);
            TextView price = view.findViewById(R.id.price);
            TextView rprice = view.findViewById(R.id.rprice);
            TextView location = view.findViewById(R.id.location);
            LinearLayout sumbit_box = view.findViewById(R.id.click);
            ImageView imageView =view.findViewById(R.id.image);

            item_anim= AnimationUtils.loadAnimation(getContext(), R.anim.item_anim);
            sumbit_box.setAnimation(item_anim);

            imageView.setImageResource(R.drawable.gp);
            imageView.setColorFilter(Color.parseColor("#dc1e76"));

            HashMap<String,String>hashMap=arrayList1.get(position);
            String ftitle = hashMap.get("atitle");
            String fprice = hashMap.get("aprice");
            String frprice = hashMap.get("arprice");
            String flocation = hashMap.get("alocation");


            title.setText(ftitle);
            price.setText(fprice+"৳");
            rprice.setText(frprice+"৳");
            location.setText(flocation);

            sumbit_box.setOnClickListener(v -> {

                Intent nextActivity = new Intent(getContext(), Offer.class);
                Bundle bundle = new Bundle();
                bundle.putString("title",ftitle);
                bundle.putString("location",flocation);
                bundle.putString("price",fprice);
                bundle.putInt("VAL", 103);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
            });




            return view;
        }
    }
    public class adapter2 extends BaseAdapter {

        LayoutInflater layoutInflater;

        @Override
        public int getCount() {
            return arrayList2.size();
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
            View view = layoutInflater.inflate(R.layout.list,parent,false);

            TextView title = view.findViewById(R.id.title);
            TextView price = view.findViewById(R.id.price);
            TextView rprice = view.findViewById(R.id.rprice);
            TextView location = view.findViewById(R.id.location);
            LinearLayout sumbit_box = view.findViewById(R.id.click);
            ImageView imageView =view.findViewById(R.id.image);

            item_anim= AnimationUtils.loadAnimation(getContext(), R.anim.item_anim);
            sumbit_box.setAnimation(item_anim);

            imageView.setImageResource(R.drawable.gp);
            imageView.setColorFilter(Color.parseColor("#dc1e76"));

            HashMap<String,String>hashMap=arrayList2.get(position);
            String ftitle = hashMap.get("atitle");
            String fprice = hashMap.get("aprice");
            String frprice = hashMap.get("arprice");
            String flocation = hashMap.get("alocation");


            title.setText(ftitle);
            price.setText(fprice+"৳");
            rprice.setText(frprice+"৳");
            location.setText(flocation);

            sumbit_box.setOnClickListener(v -> {

                Intent nextActivity = new Intent(getContext(), Offer.class);
                Bundle bundle = new Bundle();
                bundle.putString("title",ftitle);
                bundle.putString("location",flocation);
                bundle.putString("price",fprice);
                bundle.putInt("VAL", 103);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);
            });




            return view;
        }
    }

}