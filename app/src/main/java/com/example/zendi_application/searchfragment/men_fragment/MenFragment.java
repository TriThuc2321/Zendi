package com.example.zendi_application.searchfragment.men_fragment;

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
import com.example.zendi_application.dropFragment.product_package.product2;
import com.example.zendi_application.searchfragment.ElementOfRecycModel;
import com.example.zendi_application.searchfragment.RecycleAdapter;
import com.example.zendi_application.searchfragment.Transactor;
import com.example.zendi_application.searchfragment.allShoe.AllShoeActivity;
import com.example.zendi_application.searchfragment.allShoe.MyEnum;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.BRAND;

public class MenFragment extends Fragment {
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
        View view = inflater.inflate(R.layout.fragment_search_men, container, false);

        layoutAddidas = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtAddidas = new RecycleAdapter(MyEnum.Brand.ADDIDAS,MyEnum.Sex.MEN);
        rcvAddidas = view.findViewById(R.id.see_all_addidas_rcv_men);
        rcvAddidas.setHasFixedSize(true);
        rcvAddidas.setLayoutManager(layoutAddidas);
        rcvAddidas.setAdapter(adtAddidas);

        layoutBalance = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtBalance = new RecycleAdapter(MyEnum.Brand.NEW_BALANCE,MyEnum.Sex.MEN);
        rcvBalance = view.findViewById(R.id.see_all_newbalance_rcv_men);
        rcvBalance.setHasFixedSize(true);
        rcvBalance.setLayoutManager(layoutBalance);
        rcvBalance.setAdapter(adtBalance);

        layoutNike = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtNike = new RecycleAdapter(MyEnum.Brand.NIKE,MyEnum.Sex.MEN);
        rcvNike = view.findViewById(R.id.see_all_nike_rcv_men);
        rcvNike.setHasFixedSize(true);
        rcvNike.setLayoutManager(layoutNike);
        rcvNike.setAdapter(adtNike);

        layoutConverse = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtConverse = new RecycleAdapter(MyEnum.Brand.CONVERSE,MyEnum.Sex.MEN);
        rcvConverse = view.findViewById(R.id.see_all_converse_rcv_men);
        rcvConverse.setHasFixedSize(true);
        rcvConverse.setLayoutManager(layoutConverse);
        rcvConverse.setAdapter(adtConverse);

        layoutVans = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtVans = new RecycleAdapter(MyEnum.Brand.VANS,MyEnum.Sex.MEN);
        rcvVans = view.findViewById(R.id.see_all_vans_rcv_men);
        rcvVans.setHasFixedSize(true);
        rcvVans.setLayoutManager(layoutVans);
        rcvVans.setAdapter(adtVans);

        layoutPuma = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtPuma = new RecycleAdapter(MyEnum.Brand.PUMA,MyEnum.Sex.MEN);
        rcvPuma = view.findViewById(R.id.see_all_puma_rcv_men);
        rcvPuma.setHasFixedSize(true);
        rcvPuma.setLayoutManager(layoutPuma);
        rcvPuma.setAdapter(adtPuma);

        layoutRee = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtRee = new RecycleAdapter(MyEnum.Brand.REEBOOK,MyEnum.Sex.MEN);
        rcvRee = view.findViewById(R.id.see_all_reebook_rcv_men);
        rcvRee.setHasFixedSize(true);
        rcvRee.setLayoutManager(layoutRee);
        rcvRee.setAdapter(adtRee);

        bt_all_shoe = view.findViewById(R.id.shoes_button);
        bt_all_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.sex = MyEnum.Sex.MEN;
                Transactor.brand = MyEnum.Brand.ALL;
                Intent intent = new Intent(view.getContext(), AllShoeActivity.class);
                startActivity(intent);
            }
        });

        bt_adidas_shoe = view.findViewById(R.id.see_all_addidas_button_men);
        bt_adidas_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.sex = MyEnum.Sex.MEN;
                Transactor.brand = MyEnum.Brand.ADDIDAS;
                Intent intent = new Intent(view.getContext(), AllShoeActivity.class);
                startActivity(intent);
            }
        });

        bt_nike_shoe = view.findViewById(R.id.see_all_nike_button_men);
        bt_nike_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.sex = MyEnum.Sex.MEN;
                Transactor.brand = MyEnum.Brand.NIKE;
                Intent intent = new Intent(view.getContext(), AllShoeActivity.class);
                startActivity(intent);
            }
        });

        bt_puma_shoe = view.findViewById(R.id.see_all_puma_button_men);
        bt_puma_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.sex = MyEnum.Sex.MEN;
                Transactor.brand = MyEnum.Brand.PUMA;
                Intent intent = new Intent(view.getContext(), AllShoeActivity.class);
                startActivity(intent);
            }
        });

        bt_ree_shoe = view.findViewById(R.id.see_all_reebook_button_men);
        bt_ree_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.sex = MyEnum.Sex.MEN;
                Transactor.brand = MyEnum.Brand.REEBOOK;
                Intent intent = new Intent(view.getContext(), AllShoeActivity.class);
                startActivity(intent);
            }
        });

        bt_converse_shoe = view.findViewById(R.id.see_all_converse_button_men);
        bt_converse_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.sex = MyEnum.Sex.MEN;
                Transactor.brand = MyEnum.Brand.CONVERSE;
                Intent intent = new Intent(view.getContext(), AllShoeActivity.class);
                startActivity(intent);
            }
        });

        bt_vans_shoe = view.findViewById(R.id.see_all_vans_button_men);
        bt_vans_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.sex = MyEnum.Sex.MEN;
                Transactor.brand = MyEnum.Brand.VANS;
                Intent intent = new Intent(view.getContext(), AllShoeActivity.class);
                startActivity(intent);
            }
        });

        bt_balance_shoe = view.findViewById(R.id.see_all_newbalance_button_men);
        bt_balance_shoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Transactor.sex = MyEnum.Sex.MEN;
                Transactor.brand = MyEnum.Brand.NEW_BALANCE;
                Intent intent = new Intent(view.getContext(), AllShoeActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
