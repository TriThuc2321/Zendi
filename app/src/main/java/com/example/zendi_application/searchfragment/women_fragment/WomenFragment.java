package com.example.zendi_application.searchfragment.women_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.R;
import com.example.zendi_application.searchfragment.RecycleAdapter;
import com.example.zendi_application.searchfragment.Transactor;
import com.example.zendi_application.searchfragment.allShoe.AllShoeActivity;
import com.example.zendi_application.searchfragment.allShoe.MyEnum;
import com.example.zendi_application.searchfragment.men_fragment.MenFragment;

public class WomenFragment extends Fragment {
    private RecyclerView rcvAddidas;
    private RecyclerView.Adapter adtAddidas;
    private RecyclerView.LayoutManager layoutAddidas;

    private RecyclerView rcvNike;
    private RecyclerView.Adapter adtNike;
    private RecyclerView.LayoutManager layoutNike;

    private RecyclerView rcvRee;
    private RecyclerView.Adapter adtRee;
    private RecyclerView.LayoutManager layoutRee;

    private RecyclerView rcvBalance;
    private RecyclerView.Adapter adtBalance;
    private RecyclerView.LayoutManager layoutBalance;

    private RecyclerView rcvVans;
    private RecyclerView.Adapter adtVans;
    private RecyclerView.LayoutManager layoutVans;

    private RecyclerView rcvConverse;
    private RecyclerView.Adapter adtConverse;
    private RecyclerView.LayoutManager layoutConverse;

    private RecyclerView rcvPuma;
    private RecyclerView.Adapter adtPuma;
    private RecyclerView.LayoutManager layoutPuma;

    private Button bt_all_shoe;
    private Button bt_adidas_shoe;
    private Button bt_nike_shoe;
    private Button bt_ree_shoe;
    private Button bt_vans_shoe;
    private Button bt_puma_shoe;
    private Button bt_converse_shoe;
    private Button bt_balance_shoe;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_women, container, false);

        layoutAddidas = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtAddidas = new RecycleAdapter(MyEnum.Brand.ADDIDAS,MyEnum.Sex.WOMEN);
        rcvAddidas = view.findViewById(R.id.see_all_addidas_rcv);
        rcvAddidas.setHasFixedSize(true);
        rcvAddidas.setLayoutManager(layoutAddidas);
        rcvAddidas.setAdapter(adtAddidas);

        layoutBalance = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtBalance = new RecycleAdapter(MyEnum.Brand.NEW_BALANCE,MyEnum.Sex.WOMEN);
        rcvBalance = view.findViewById(R.id.see_all_newbalance_rcv);
        rcvBalance.setHasFixedSize(true);
        rcvBalance.setLayoutManager(layoutBalance);
        rcvBalance.setAdapter(adtBalance);

        layoutNike = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtNike = new RecycleAdapter(MyEnum.Brand.NIKE,MyEnum.Sex.WOMEN);
        rcvNike = view.findViewById(R.id.see_all_nike_rcv);
        rcvNike.setHasFixedSize(true);
        rcvNike.setLayoutManager(layoutNike);
        rcvNike.setAdapter(adtNike);

        layoutConverse = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtConverse = new RecycleAdapter(MyEnum.Brand.CONVERSE,MyEnum.Sex.WOMEN);
        rcvConverse = view.findViewById(R.id.see_all_converse_rcv);
        rcvConverse.setHasFixedSize(true);
        rcvConverse.setLayoutManager(layoutConverse);
        rcvConverse.setAdapter(adtConverse);

        layoutVans = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtVans = new RecycleAdapter(MyEnum.Brand.VANS,MyEnum.Sex.WOMEN);
        rcvVans = view.findViewById(R.id.see_all_vans_rcv);
        rcvVans.setHasFixedSize(true);
        rcvVans.setLayoutManager(layoutVans);
        rcvVans.setAdapter(adtVans);

        layoutPuma = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtPuma = new RecycleAdapter(MyEnum.Brand.PUMA,MyEnum.Sex.WOMEN);
        rcvPuma = view.findViewById(R.id.see_all_puma_rcv);
        rcvPuma.setHasFixedSize(true);
        rcvPuma.setLayoutManager(layoutPuma);
        rcvPuma.setAdapter(adtPuma);

        layoutRee = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtRee = new RecycleAdapter(MyEnum.Brand.REEBOOK,MyEnum.Sex.WOMEN);
        rcvRee = view.findViewById(R.id.see_all_reebook_rcv);
        rcvRee.setHasFixedSize(true);
        rcvRee.setLayoutManager(layoutRee);
        rcvRee.setAdapter(adtRee);

        bt_all_shoe = view.findViewById(R.id.shoes_button_women);
        bt_all_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.brand = MyEnum.Brand.ALL;
                Transactor.sex = MyEnum.Sex.WOMEN;
                Intent intent = new Intent(view.getContext(), AllShoeActivity.class);
                startActivity(intent);
            }
        });

        bt_adidas_shoe = view.findViewById(R.id.see_all_addidas_button);
        bt_adidas_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.sex = MyEnum.Sex.WOMEN;
                Transactor.brand = MyEnum.Brand.ADDIDAS;
                Intent intent = new Intent(view.getContext(), AllShoeActivity.class);
                startActivity(intent);
            }
        });

        bt_nike_shoe = view.findViewById(R.id.see_all_nike_button);
        bt_nike_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.sex = MyEnum.Sex.WOMEN;
                Transactor.brand = MyEnum.Brand.NIKE;
                Intent intent = new Intent(view.getContext(), AllShoeActivity.class);
                startActivity(intent);
            }
        });

        bt_puma_shoe = view.findViewById(R.id.see_all_puma_button);
        bt_puma_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.sex = MyEnum.Sex.WOMEN;
                Transactor.brand = MyEnum.Brand.PUMA;
                Intent intent = new Intent(view.getContext(), AllShoeActivity.class);
                startActivity(intent);
            }
        });

        bt_ree_shoe = view.findViewById(R.id.see_all_reebook_button);
        bt_ree_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.sex = MyEnum.Sex.WOMEN;
                Transactor.brand = MyEnum.Brand.REEBOOK;
                Intent intent = new Intent(view.getContext(), AllShoeActivity.class);
                startActivity(intent);
            }
        });

        bt_converse_shoe = view.findViewById(R.id.see_all_converse_button);
        bt_converse_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.sex = MyEnum.Sex.WOMEN;
                Transactor.brand = MyEnum.Brand.CONVERSE;
                Intent intent = new Intent(view.getContext(), AllShoeActivity.class);
                startActivity(intent);
            }
        });

        bt_vans_shoe = view.findViewById(R.id.see_all_vans_button);
        bt_vans_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.sex = MyEnum.Sex.WOMEN;
                Transactor.brand = MyEnum.Brand.VANS;
                Intent intent = new Intent(view.getContext(), AllShoeActivity.class);
                startActivity(intent);
            }
        });

        bt_balance_shoe = view.findViewById(R.id.see_all_newbalance_button);
        bt_balance_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.sex = MyEnum.Sex.WOMEN;
                Transactor.brand = MyEnum.Brand.NEW_BALANCE;
                Intent intent = new Intent(view.getContext(), AllShoeActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
