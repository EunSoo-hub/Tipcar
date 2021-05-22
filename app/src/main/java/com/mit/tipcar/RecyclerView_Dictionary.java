package com.mit.tipcar;


public class RecyclerView_Dictionary {

    private String Title;
    private android.graphics.drawable.Drawable Image;
    private String Content;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public android.graphics.drawable.Drawable getImage() {
        return this.Image;
    }

    public void setImage(android.graphics.drawable.Drawable image) {
        Image = image;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public RecyclerView_Dictionary(String title, String image, String content) {
        this.Title = title;
        this.Image = android.graphics.drawable.Drawable.createFromPath(image);
        this.Content = content;
    }
}