package com.example.zendi_application.searchfragment.men_fragment;

import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
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

    private CardView cv_adidas;
    private CardView cv_vans;
    private CardView cv_nike;
    private CardView cv_balance;
    private CardView cv_puma;
    private CardView cv_ree;
    private CardView cv_converse;
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
        cv_adidas = view.findViewById(R.id.card_adidas_men);

        layoutBalance = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtBalance = new RecycleAdapter(MyEnum.Brand.NEW_BALANCE,MyEnum.Sex.MEN);
        rcvBalance = view.findViewById(R.id.see_all_newbalance_rcv_men);
        rcvBalance.setHasFixedSize(true);
        rcvBalance.setLayoutManager(layoutBalance);
        rcvBalance.setAdapter(adtBalance);
        cv_balance = view.findViewById(R.id.card_balance_men);

        layoutNike = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtNike = new RecycleAdapter(MyEnum.Brand.NIKE,MyEnum.Sex.MEN);
        rcvNike = view.findViewById(R.id.see_all_nike_rcv_men);
        rcvNike.setHasFixedSize(true);
        rcvNike.setLayoutManager(layoutNike);
        rcvNike.setAdapter(adtNike);
        cv_nike = view.findViewById(R.id.card_nike_men);

        layoutConverse = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtConverse = new RecycleAdapter(MyEnum.Brand.CONVERSE,MyEnum.Sex.MEN);
        rcvConverse = view.findViewById(R.id.see_all_converse_rcv_men);
        rcvConverse.setHasFixedSize(true);
        rcvConverse.setLayoutManager(layoutConverse);
        rcvConverse.setAdapter(adtConverse);
        cv_converse = view.findViewById(R.id.card_converse_men);

        layoutVans = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtVans = new RecycleAdapter(MyEnum.Brand.VANS,MyEnum.Sex.MEN);
        rcvVans = view.findViewById(R.id.see_all_vans_rcv_men);
        rcvVans.setHasFixedSize(true);
        rcvVans.setLayoutManager(layoutVans);
        rcvVans.setAdapter(adtVans);
        cv_vans = view.findViewById(R.id.card_vans_men);

        layoutPuma = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtPuma = new RecycleAdapter(MyEnum.Brand.PUMA,MyEnum.Sex.MEN);
        rcvPuma = view.findViewById(R.id.see_all_puma_rcv_men);
        rcvPuma.setHasFixedSize(true);
        rcvPuma.setLayoutManager(layoutPuma);
        rcvPuma.setAdapter(adtPuma);
        cv_puma = view.findViewById(R.id.card_puma_men);

        layoutRee = new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false);
        adtRee = new RecycleAdapter(MyEnum.Brand.REEBOOK,MyEnum.Sex.MEN);
        rcvRee = view.findViewById(R.id.see_all_reebook_rcv_men);
        rcvRee.setHasFixedSize(true);
        rcvRee.setLayoutManager(layoutRee);
        rcvRee.setAdapter(adtRee);
        cv_ree = view.findViewById(R.id.card_ree_men);

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

        if(adtAddidas.getItemCount() == 0)
        {
            rcvAddidas.setVisibility(View.GONE);
            bt_adidas_shoe.setVisibility(View.GONE);
            cv_adidas.setVisibility(View.GONE);
        }
        else
        {
            rcvAddidas.setVisibility(View.VISIBLE);
            bt_adidas_shoe.setVisibility(View.VISIBLE);
            cv_adidas.setVisibility(View.VISIBLE);
        }

        if(adtNike.getItemCount() == 0)
        {
            rcvNike.setVisibility(View.GONE);
            bt_nike_shoe.setVisibility(View.GONE);
            cv_nike.setVisibility(View.GONE);
        }
        else
        {
            rcvNike.setVisibility(View.VISIBLE);
            bt_nike_shoe.setVisibility(View.VISIBLE);
            cv_nike.setVisibility(View.VISIBLE);
        }

        if(adtRee.getItemCount() == 0)
        {
            rcvRee.setVisibility(View.GONE);
            bt_ree_shoe.setVisibility(View.GONE);
            cv_ree.setVisibility(View.GONE);
        }
        else
        {
            rcvRee.setVisibility(View.VISIBLE);
            bt_ree_shoe.setVisibility(View.VISIBLE);
            cv_ree.setVisibility(View.VISIBLE);
        }

        if(adtPuma.getItemCount() == 0)
        {
            rcvPuma.setVisibility(View.GONE);
            bt_puma_shoe.setVisibility(View.GONE);
            cv_puma.setVisibility(View.GONE);
        }
        else
        {
            rcvPuma.setVisibility(View.VISIBLE);
            bt_puma_shoe.setVisibility(View.VISIBLE);
            cv_puma.setVisibility(View.VISIBLE);
        }

        if(adtVans.getItemCount() == 0)
        {
            rcvVans.setVisibility(View.GONE);
            bt_vans_shoe.setVisibility(View.GONE);
            cv_vans.setVisibility(View.GONE);
        }
        else
        {
            rcvVans.setVisibility(View.VISIBLE);
            bt_vans_shoe.setVisibility(View.VISIBLE);
            cv_vans.setVisibility(View.VISIBLE);
        }

        if(adtConverse.getItemCount() == 0)
        {
            rcvConverse.setVisibility(View.GONE);
            bt_converse_shoe.setVisibility(View.GONE);
            cv_converse.setVisibility(View.GONE);
        }
        else
        {
            rcvConverse.setVisibility(View.VISIBLE);
            bt_converse_shoe.setVisibility(View.VISIBLE);
            cv_converse.setVisibility(View.VISIBLE);
        }

        if(adtBalance.getItemCount() == 0)
        {
            rcvBalance.setVisibility(View.GONE);
            bt_balance_shoe.setVisibility(View.GONE);
            cv_balance.setVisibility(View.GONE);
        }
        else
        {
            rcvBalance.setVisibility(View.VISIBLE);
            bt_balance_shoe.setVisibility(View.VISIBLE);
            cv_balance.setVisibility(View.VISIBLE);
        }
        Transactor.getInstance().menAdapter(adtAddidas, adtVans, adtConverse, adtRee, adtBalance, adtPuma, adtNike);
        return view;
    }
}
