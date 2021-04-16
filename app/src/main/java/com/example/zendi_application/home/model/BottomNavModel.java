package com.example.zendi_application.home.model;

public class BottomNavModel {
    private ItemMenuInBar DropItem = new ItemMenuInBar("Drop",true);
    private ItemMenuInBar SearchItem = new ItemMenuInBar("Search",false);
    private ItemMenuInBar ShopItem = new ItemMenuInBar("Shop",false);
    private ItemMenuInBar WishListItem = new ItemMenuInBar("Shop",false);

    public ItemMenuInBar getDropItem()
    {
        return this.DropItem;
    }

    public ItemMenuInBar getSearchItem() {
        return SearchItem;
    }

    public ItemMenuInBar getShopItem() {
        return ShopItem;
    }

    public ItemMenuInBar getWishListItem() {
        return WishListItem;
    }

    public void setDropItem(ItemMenuInBar dropItem) {
        DropItem = dropItem;
    }

    public void setSearchItem(ItemMenuInBar searchItem) {
        SearchItem = searchItem;
    }

    public void setShopItem(ItemMenuInBar shopItem) {
        ShopItem = shopItem;
    }

    public void setWishListItem(ItemMenuInBar wishListItem) {
        WishListItem = wishListItem;
    }
}
