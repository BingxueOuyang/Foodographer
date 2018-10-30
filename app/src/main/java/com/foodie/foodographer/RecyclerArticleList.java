//https://stackoverflow.com/questions/30751870/can-one-use-cardview-for-listview-item-and-how

package com.foodie.foodographer;

import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import android.view.*;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import java.io.InputStream;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.yelp.fusion.client.models.Business;


public class RecyclerArticleList extends RecyclerView.Adapter<RecyclerArticleList.MyViewHolder> {
    private List<Article> article_list;

    public RecyclerArticleList (ArrayList<Article> article_list){
        this.article_list = article_list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_card, parent, false);

        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Article article = article_list.get(position);
        holder.artTitle.setText(article.getTitle());
        new DownloadImageTask(holder.artIMG).execute(article.getIMGURL());
    }


    @Override
    public int getItemCount() {
        return article_list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView artTitle;
        private ImageView artIMG;
        public MyViewHolder(View itemView) {
            super(itemView);
            artTitle = (TextView) itemView.findViewById(R.id.title);
            artIMG = (ImageView) itemView.findViewById(R.id.IMG);
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}
