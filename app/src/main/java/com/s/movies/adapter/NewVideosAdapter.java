package com.s.movies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.s.movies.R;
import com.s.movies.model.Movies;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewVideosAdapter extends RecyclerView.Adapter<NewVideosAdapter.ViewHolder> {
    Context mContext;
    List<Movies> movies;



    public NewVideosAdapter(Context mContext, List<Movies> movies) {
        this.mContext = mContext;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movies movies1 = movies.get(position);
        holder.mTextViewTitle.setText(movies1.getTitle());
        String thumbnail="";
        if (movies1.getPoster_path()!=null) {
            thumbnail = "http://image.tmdb.org/t/p/w185" + movies1.getPoster_path();
        } else if (movies1.getBackdrop_path()!=null){
            thumbnail = "http://image.tmdb.org/t/p/w780" + movies1.getBackdrop_path();
        }
          if (thumbnail==""){
//              ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(100, 100);
//              holder.imgView.setLayoutParams(layoutParams);
              holder.imgView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.no_image));
              holder.progressBar.setVisibility(View.GONE);
          }else{
              Picasso.with(mContext)
                      .load(thumbnail)
                      .placeholder(R.drawable.no_image)
                      .into(holder.imgView, new Callback() {
                          @Override
                          public void onSuccess() {
                              holder.progressBar.setVisibility(View.GONE);
                          }

                          @Override
                          public void onError() {
                              holder.progressBar.setVisibility(View.GONE);
                          }
                      });
          }



    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.progressBar)
        ProgressBar progressBar;
        @BindView(R.id.imgView)
        ImageView imgView;
        @BindView(R.id.mTextViewTitle)
        TextView mTextViewTitle;
        @BindView(R.id.mTextViewYear)
        TextView mTextViewYear;
        @BindView(R.id.carView)
        CardView carView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
