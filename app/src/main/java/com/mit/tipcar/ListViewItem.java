package com.mit.tipcar;

public class ListViewItem {
    private android.graphics.drawable.Drawable iconDrawable ;
    private String titleStr ;
    private String descStr ;
    private String Content;
    private String tx_visible_count;
    private String tx_favorite_count;
    private String tx_write_date;


    public void setIcon(android.graphics.drawable.Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setContent(String mContent) {
        Content = mContent ;
    }
    public void setTx_visible_count(String visible_count) {
        tx_visible_count = visible_count ;
    }
    public void setTx_favorite_count(String favorite_count) {
        tx_favorite_count = favorite_count ;
    }
    public void setTx_write_date(String write_date) {
        tx_write_date = write_date ;
    }

    public android.graphics.drawable.Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public String getContent() {
        return this.Content ;
    }
    public String getTx_visible_count() {
        return this.tx_visible_count ;
    }
    public String getTx_favorite_count() {
        return this.tx_favorite_count ;
    }
    public String getTx_write_date() {
        return this.tx_write_date ;
    }
}