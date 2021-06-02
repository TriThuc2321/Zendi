package com.example.zendi_application.searchfragment.allShoe;

public class ShoeItemModel {
    private boolean isLike;
    private int indexInShoeWish;
    public ShoeItemModel(boolean isLike, int indexInShoeWish)
    {
        this.isLike = isLike;
        this.indexInShoeWish = indexInShoeWish;
    }

    public boolean isLike() {
        return isLike;
    }
    public void setLike(boolean like) {
        isLike = like;
    }

    public int getIndexInShoeWish() {
        return indexInShoeWish;
    }

    public void setIndexInShoeWish(int indexInShoeWish) {
        this.indexInShoeWish = indexInShoeWish;
    }
}
