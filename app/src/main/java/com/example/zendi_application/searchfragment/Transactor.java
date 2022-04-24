package com.example.zendi_application.searchfragment;

import androidx.recyclerview.widget.RecyclerView;

import com.example.zendi_application.DataManager;
import com.example.zendi_application.dropFragment.product_package.product2;
import com.example.zendi_application.searchfragment.allShoe.MyEnum;

import java.util.ArrayList;

public class Transactor {
    private static ArrayList<product2> arrayList = new ArrayList<>();
    public static MyEnum.Sex sex;
    public static MyEnum.Brand brand;
    public static Transactor instance;
    public String oldBranch = "";
    public String oldType = "";
    public String oldID = "";
    public RecycleAdapter adtAdidasMen;
    public RecycleAdapter adtNikeMen;
    public RecycleAdapter adtPumaMen;
    public RecycleAdapter adtVansMen;
    public RecycleAdapter adtReeMen;
    public RecycleAdapter adtNewMen;
    public RecycleAdapter adtConverseMen;
    public RecycleAdapter adtAdidasWoMen;
    public RecycleAdapter adtNikeWoMen;
    public RecycleAdapter adtPumaWoMen;
    public RecycleAdapter adtVansWoMen;
    public RecycleAdapter adtReeWoMen;
    public RecycleAdapter adtNewWoMen;
    public RecycleAdapter adtConverseWoMen;
    public static Transactor getInstance() {
        if (instance == null) instance = new Transactor();
        return instance;
    }
    public ArrayList<product2> getArrayList()
    {return arrayList;}

    public static int ExistInShoeWish(product2 product) {
        for (int i = 0; i < DataManager.shoeInWish.size(); i++) {
            if (product.getProductId().equals(DataManager.shoeInWish.get(i).getProductId()))
                return i;
        }
        return -1;
    }
    public void menAdapter(RecyclerView.Adapter adidas, RecyclerView.Adapter vans, RecyclerView.Adapter cons, RecyclerView.Adapter ree, RecyclerView.Adapter news, RecyclerView.Adapter puma, RecyclerView.Adapter nike)
    {
        adtAdidasMen = (RecycleAdapter) adidas;
        adtConverseMen = (RecycleAdapter) cons;
        adtNewMen = (RecycleAdapter) news;
        adtNikeMen = (RecycleAdapter) nike;
        adtPumaMen = (RecycleAdapter) puma;
        adtReeMen = (RecycleAdapter) ree;
        adtVansMen = (RecycleAdapter) vans;
    }
    public void womenAdapter(RecyclerView.Adapter adidas, RecyclerView.Adapter vans, RecyclerView.Adapter cons, RecyclerView.Adapter ree, RecyclerView.Adapter news, RecyclerView.Adapter puma, RecyclerView.Adapter nike)
    {
        adtAdidasWoMen = (RecycleAdapter) adidas;
        adtConverseWoMen = (RecycleAdapter) cons;
        adtNewWoMen = (RecycleAdapter) news;
        adtNikeWoMen = (RecycleAdapter) nike;
        adtPumaWoMen = (RecycleAdapter) puma;
        adtReeWoMen = (RecycleAdapter) ree;
        adtVansWoMen = (RecycleAdapter) vans;
    }
    public void notifyAddProduct(product2 newProduct)
    {
        if(newProduct.getProductType().equals("1"))
        {
            //men
            switch (newProduct.getProductBrand().toUpperCase())
            {
                case "ADIDAS": adtAdidasMen.product2List.add(newProduct);adtAdidasMen.notifyDataSetChanged();break;
                case "NIKE": adtNikeMen.product2List.add(newProduct);adtNikeMen.notifyDataSetChanged();break;
                case "PUMA": adtPumaMen.product2List.add(newProduct);adtPumaMen.notifyDataSetChanged();break;
                case "NEW BALANCE": adtNewMen.product2List.add(newProduct);adtNewMen.notifyDataSetChanged();break;
                case "REEBOK": adtReeMen.product2List.add(newProduct);adtReeMen.notifyDataSetChanged();break;
                case "VANS": adtVansMen.product2List.add(newProduct);adtVansMen.notifyDataSetChanged();break;
                default: adtConverseMen.product2List.add(newProduct);adtConverseMen.notifyDataSetChanged();
            }
        }
        else if(newProduct.getProductType().equals("2"))
        {
            //women
            switch (newProduct.getProductBrand().toUpperCase())
            {
                case "ADIDAS": adtAdidasWoMen.product2List.add(newProduct);adtAdidasWoMen.notifyDataSetChanged();break;
                case "NIKE": adtNikeWoMen.product2List.add(newProduct);adtNikeWoMen.notifyDataSetChanged();break;
                case "PUMA": adtPumaWoMen.product2List.add(newProduct);adtPumaWoMen.notifyDataSetChanged();break;
                case "NEW BALANCE": adtNewWoMen.product2List.add(newProduct);adtNewWoMen.notifyDataSetChanged();break;
                case "REEBOK": adtReeWoMen.product2List.add(newProduct);adtReeWoMen.notifyDataSetChanged();break;
                case "VANS": adtVansWoMen.product2List.add(newProduct);adtVansWoMen.notifyDataSetChanged();break;
                default: adtConverseWoMen.product2List.add(newProduct);adtConverseWoMen.notifyDataSetChanged();
            }
        }
        else if(newProduct.getProductType().equals("3"))
        {
            //men and women
            switch (newProduct.getProductBrand().toUpperCase())
            {
                case "ADIDAS": adtAdidasMen.product2List.add(newProduct);adtAdidasMen.notifyDataSetChanged();break;
                case "NIKE": adtNikeMen.product2List.add(newProduct);adtNikeMen.notifyDataSetChanged();break;
                case "PUMA": adtPumaMen.product2List.add(newProduct);adtPumaMen.notifyDataSetChanged();break;
                case "NEW BALANCE": adtNewMen.product2List.add(newProduct);adtNewMen.notifyDataSetChanged();break;
                case "REEBOK": adtReeMen.product2List.add(newProduct);adtReeMen.notifyDataSetChanged();break;
                case "VANS": adtVansMen.product2List.add(newProduct);adtVansMen.notifyDataSetChanged();break;
                default: adtConverseMen.product2List.add(newProduct);adtConverseMen.notifyDataSetChanged();
            }
            switch (newProduct.getProductBrand().toUpperCase())
            {
                case "ADIDAS": adtAdidasWoMen.product2List.add(newProduct);adtAdidasWoMen.notifyDataSetChanged();break;
                case "NIKE": adtNikeWoMen.product2List.add(newProduct);adtNikeWoMen.notifyDataSetChanged();break;
                case "PUMA": adtPumaWoMen.product2List.add(newProduct);adtPumaWoMen.notifyDataSetChanged();break;
                case "NEW BALANCE": adtNewWoMen.product2List.add(newProduct);adtNewWoMen.notifyDataSetChanged();break;
                case "REEBOK": adtReeWoMen.product2List.add(newProduct);adtReeWoMen.notifyDataSetChanged();break;
                case "VANS": adtVansWoMen.product2List.add(newProduct);adtVansWoMen.notifyDataSetChanged();break;
                default: adtConverseWoMen.product2List.add(newProduct);adtConverseWoMen.notifyDataSetChanged();
            }
        }
    }
    public void notifyDeleteProduct(product2 deleteProduct)
    {
        if(deleteProduct.getProductType().equals("1"))
        {
            //men
            switch (deleteProduct.getProductBrand().toUpperCase())
            {
                case "ADIDAS": deleteInAdapter(deleteProduct,adtAdidasMen);adtAdidasMen.notifyDataSetChanged();break;
                case "NIKE": deleteInAdapter(deleteProduct,adtNikeMen);adtNikeMen.notifyDataSetChanged();break;
                case "PUMA": deleteInAdapter(deleteProduct,adtPumaMen);adtPumaMen.notifyDataSetChanged();break;
                case "NEW BALANCE": deleteInAdapter(deleteProduct,adtNewMen);adtNewMen.notifyDataSetChanged();break;
                case "REEBOK": deleteInAdapter(deleteProduct,adtReeMen);adtReeMen.notifyDataSetChanged();break;
                case "VANS": deleteInAdapter(deleteProduct,adtVansMen);adtVansMen.notifyDataSetChanged();break;
                default: deleteInAdapter(deleteProduct,adtConverseMen);adtConverseMen.notifyDataSetChanged();
            }
        }
        else if(deleteProduct.getProductType().equals("2"))
        {
            //women
            switch (deleteProduct.getProductBrand().toUpperCase())
            {
                case "ADIDAS": deleteInAdapter(deleteProduct,adtAdidasWoMen);adtAdidasWoMen.notifyDataSetChanged();break;
                case "NIKE": deleteInAdapter(deleteProduct,adtNikeWoMen);adtNikeWoMen.notifyDataSetChanged();break;
                case "PUMA": deleteInAdapter(deleteProduct,adtPumaWoMen);adtPumaWoMen.notifyDataSetChanged();break;
                case "NEW BALANCE": deleteInAdapter(deleteProduct,adtNewWoMen);adtNewWoMen.notifyDataSetChanged();break;
                case "REEBOK": deleteInAdapter(deleteProduct,adtReeWoMen);adtNewWoMen.notifyDataSetChanged();break;
                case "VANS": deleteInAdapter(deleteProduct,adtVansWoMen);adtVansWoMen.notifyDataSetChanged();break;
                default: deleteInAdapter(deleteProduct,adtConverseWoMen);adtConverseWoMen.notifyDataSetChanged();
            }
        }
        else if(deleteProduct.getProductType().equals("3"))
        {
            //men and women
            switch (deleteProduct.getProductBrand().toUpperCase())
            {
                case "ADIDAS": deleteInAdapter(deleteProduct,adtAdidasMen);adtAdidasMen.notifyDataSetChanged();break;
                case "NIKE": deleteInAdapter(deleteProduct,adtNikeMen);adtNikeMen.notifyDataSetChanged();break;
                case "PUMA": deleteInAdapter(deleteProduct,adtPumaMen);adtPumaMen.notifyDataSetChanged();break;
                case "NEW BALANCE": deleteInAdapter(deleteProduct,adtNewMen);adtNewMen.notifyDataSetChanged();break;
                case "REEBOK": deleteInAdapter(deleteProduct,adtReeMen);adtReeMen.notifyDataSetChanged();break;
                case "VANS": deleteInAdapter(deleteProduct,adtVansMen);adtVansMen.notifyDataSetChanged();break;
                default: deleteInAdapter(deleteProduct,adtConverseMen);adtConverseMen.notifyDataSetChanged();
            }
            switch (deleteProduct.getProductBrand().toUpperCase())
            {
                case "ADIDAS": deleteInAdapter(deleteProduct,adtAdidasWoMen);adtAdidasWoMen.notifyDataSetChanged();break;
                case "NIKE": deleteInAdapter(deleteProduct,adtNikeWoMen);adtNikeWoMen.notifyDataSetChanged();break;
                case "PUMA": deleteInAdapter(deleteProduct,adtPumaWoMen);adtPumaWoMen.notifyDataSetChanged();break;
                case "NEW BALANCE": deleteInAdapter(deleteProduct,adtNewWoMen);adtNewWoMen.notifyDataSetChanged();break;
                case "REEBOK": deleteInAdapter(deleteProduct,adtReeWoMen);adtNewWoMen.notifyDataSetChanged();break;
                case "VANS": deleteInAdapter(deleteProduct,adtVansWoMen);adtVansWoMen.notifyDataSetChanged();break;
                default: deleteInAdapter(deleteProduct,adtConverseWoMen);adtConverseWoMen.notifyDataSetChanged();
            }
        }
    }
    public void deleteInAdapter(product2 deleteProduct, RecycleAdapter adt)
    {
        for(int i = 0;i<adt.product2List.size();i++)
        {
            if(adt.product2List.get(i).getProductId().equals(deleteProduct.getProductId()))
            {
                adt.product2List.remove(i);
                break;
            }
        }
    }
    public void notifyEdit(product2 newProduct)
    {
        product2 oldProduct = new product2();
        oldProduct.setProductId(oldID.toUpperCase());
        oldProduct.setProductBrand(oldBranch.toUpperCase());
        oldProduct.setProductType(oldType.toUpperCase());
        notifyDeleteProduct(oldProduct);
        notifyAddProduct(newProduct);
    }
//    public void editInAdapter(product2 editProduct,RecycleAdapter adt)
//    {
//        for(product2 item : adt.product2List)
//        {
//            if(item.getProductId().equals(editProduct.getProductId()))
//            {
//                item.setProductName(editProduct.getProductName());
//                item.setProductBrand(editProduct.getProductBrand());
//                item.setProductPrice(editProduct.getProductPrice());
//                item.setProductType(editProduct.getProductType());
//                item.getRemainingAmount().set(0,Integer.parseInt(remaining55edit.getText().toString()));
//                item.getRemainingAmount().set(1,Integer.parseInt(remaining6edit.getText().toString()));
//                item.getRemainingAmount().set(2,Integer.parseInt(remaining65edit.getText().toString()));
//                item.getRemainingAmount().set(3,Integer.parseInt(remaining7edit.getText().toString()));
//                item.getRemainingAmount().set(4,Integer.parseInt(remaining75edit.getText().toString()));
//                item.getRemainingAmount().set(5,Integer.parseInt(remaining8edit.getText().toString()));
//                item.getRemainingAmount().set(6,Integer.parseInt(remaining85edit.getText().toString()));
//                item.getRemainingAmount().set(7,Integer.parseInt(remaining9edit.getText().toString()));
//                item.getRemainingAmount().set(8,Integer.parseInt(remaining95edit.getText().toString()));
//                item.getRemainingAmount().set(9,Integer.parseInt(remaining10edit.getText().toString()));
//                item.getRemainingAmount().set(10,Integer.parseInt(remaining105edit.getText().toString()));
//                item.getRemainingAmount().set(11,Integer.parseInt(remaining11edit.getText().toString()));
//                item.getRemainingAmount().set(12,Integer.parseInt(remaining115edit.getText().toString()));
//                item.getRemainingAmount().set(13,Integer.parseInt(remaining12edit.getText().toString()));
//            }
//        }
//    }
}
