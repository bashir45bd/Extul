package com.modestsoftware.mbload;


import android.content.Intent;
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


public class Robi extends Fragment {


    ListView robi_bundel,robi_minute,robi_mb,robi_family;
    Animation item_anim;
    TextView bundel,minute,mb,family,note2;

    HashMap<String,String> hashMap_bundel,hashMap_mb,hashMap_minute,hashMap_family;
    ArrayList< HashMap<String,String> > arrayList = new ArrayList<>();
    ArrayList< HashMap<String,String> > arrayList1 = new ArrayList<>();
    ArrayList< HashMap<String,String> > arrayList2 = new ArrayList<>();
    ArrayList< HashMap<String,String> > arrayList3 = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View view= inflater.inflate(R.layout.fragment_robi, container, false);

        robi_bundel=view.findViewById(R.id.robi_bundel);
        robi_mb=view.findViewById(R.id.robi_mb);
        robi_minute=view.findViewById(R.id.robi_minute);
        robi_family=view.findViewById(R.id.robi_family);
        bundel =view.findViewById(R.id.bundel);
        mb =view.findViewById(R.id.mb);
        minute =view.findViewById(R.id.minute);
        family =view.findViewById(R.id.family);
        note2 =view.findViewById(R.id.note2);



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("robi");
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
                robi_bundel.setAdapter(mradapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {

                Log.w("MainActivity", "Failed to read value.", error.toException());
            }
        });

        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference().child("robi_mb");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot1) {


                for (DataSnapshot snapshot1 : dataSnapshot1.getChildren()) {

                    String title = snapshot1.child("title").getValue(String.class);
                    String price = snapshot1.child("price").getValue(String.class);
                    String discount = snapshot1.child("discount").getValue(String.class);
                    String rprice = snapshot1.child("rprice").getValue(String.class);
                    String location = snapshot1.child("location").getValue(String.class);

                    hashMap_mb= new HashMap<>();
                    hashMap_mb.put("atitle",title);
                    hashMap_mb.put("aprice",price);
                    hashMap_mb.put("adiscount",discount);
                    hashMap_mb.put("arprice",rprice);
                    hashMap_mb.put("alocation",location);
                    arrayList1.add(hashMap_mb);


                }

                adapter1 mradapter1 = new adapter1();
                robi_mb.setAdapter(mradapter1);
            }

            @Override
            public void onCancelled(DatabaseError error) {

                Log.w("MainActivity", "Failed to read value.", error.toException());
            }
        });

        DatabaseReference databaseReference2 = FirebaseDatabase.getInstance().getReference().child("robi_minute");
        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot2) {


                for (DataSnapshot snapshot2 : dataSnapshot2.getChildren()) {

                    String title = snapshot2.child("title").getValue(String.class);
                    String price = snapshot2.child("price").getValue(String.class);
                    String discount = snapshot2.child("discount").getValue(String.class);
                    String rprice = snapshot2.child("rprice").getValue(String.class);
                    String location = snapshot2.child("location").getValue(String.class);

                    hashMap_minute= new HashMap<>();
                    hashMap_minute.put("atitle",title);
                    hashMap_minute.put("aprice",price);
                    hashMap_minute.put("adiscount",discount);
                    hashMap_minute.put("arprice",rprice);
                    hashMap_minute.put("alocation",location);
                    arrayList2.add(hashMap_minute);


                }

                adapter2 mradapter2 = new adapter2();
                robi_minute.setAdapter(mradapter2);
            }

            @Override
            public void onCancelled(DatabaseError error) {

                Log.w("MainActivity", "Failed to read value.", error.toException());
            }
        });

        DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference().child("robi_family");
        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot3) {


                for (DataSnapshot snapshot3 : dataSnapshot3.getChildren()) {

                    String title = snapshot3.child("title").getValue(String.class);
                    String price = snapshot3.child("price").getValue(String.class);
                    String discount = snapshot3.child("discount").getValue(String.class);
                    String rprice = snapshot3.child("rprice").getValue(String.class);
                    String location = snapshot3.child("location").getValue(String.class);

                    hashMap_family= new HashMap<>();
                    hashMap_family.put("atitle",title);
                    hashMap_family.put("aprice",price);
                    hashMap_family.put("adiscount",discount);
                    hashMap_family.put("arprice",rprice);
                    hashMap_family.put("alocation",location);
                    arrayList3.add(hashMap_family);


                }

                adapter3 mradapter3 = new adapter3();
                robi_family.setAdapter(mradapter3);
            }

            @Override
            public void onCancelled(DatabaseError error) {

                Log.w("MainActivity", "Failed to read value.", error.toException());
            }
        });





        bundel.setOnClickListener(v -> {

            robi_bundel.setVisibility(View.VISIBLE);
            robi_mb.setVisibility(View.GONE);
            robi_minute.setVisibility(View.GONE);
            robi_family.setVisibility(View.GONE);
            note2.setVisibility(View.GONE);

        });

        mb.setOnClickListener(v -> {

            robi_bundel.setVisibility(View.GONE);
            robi_mb.setVisibility(View.VISIBLE);
            robi_minute.setVisibility(View.GONE);
            robi_family.setVisibility(View.GONE);
            note2.setVisibility(View.GONE);

        });

        minute.setOnClickListener(v -> {

            robi_bundel.setVisibility(View.GONE);
            robi_mb.setVisibility(View.GONE);
            robi_minute.setVisibility(View.VISIBLE);
            robi_family.setVisibility(View.GONE);
            note2.setVisibility(View.GONE);
        });

        family.setOnClickListener(v -> {

            robi_bundel.setVisibility(View.GONE);
            robi_mb.setVisibility(View.GONE);
            robi_minute.setVisibility(View.GONE);
            robi_family.setVisibility(View.VISIBLE);
            note2.setVisibility(View.VISIBLE);
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


            imageView.setImageResource(R.drawable.robi);
            imageView.setPadding( 8, 8, 8,8);

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


            imageView.setImageResource(R.drawable.robi);
            imageView.setPadding( 8, 8, 8,8);

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


            imageView.setImageResource(R.drawable.robi);
            imageView.setPadding( 8, 8, 8,8);

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

    public class adapter3 extends BaseAdapter {

        LayoutInflater layoutInflater;

        @Override
        public int getCount() {
            return arrayList3.size();
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


            imageView.setImageResource(R.drawable.robi);
            imageView.setPadding( 8, 8, 8,8);

            HashMap<String,String>hashMap=arrayList3.get(position);
            String ftitle = hashMap.get("atitle");
            String fprice = hashMap.get("aprice");
            String frprice = hashMap.get("arprice");
            String flocation = hashMap.get("alocation");
            String luc= flocation+", রবি।";


            title.setText(ftitle);
            price.setText(fprice+"৳");
            rprice.setText(frprice+"৳");
            location.setText(flocation+", রবি।");


            sumbit_box.setOnClickListener(v -> {

                Intent nextActivity = new Intent(getContext(), Offer.class);
                Bundle bundle = new Bundle();
                bundle.putString("title",ftitle);
                bundle.putString("location",luc );
                bundle.putString("price",fprice);
                bundle.putInt("VAL", 103);
                nextActivity.putExtras(bundle);
                startActivity(nextActivity);

            });




            return view;
        }
    }



}