package com.example.graduationpj.test.movie;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.example.graduationpj.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by zhouwei on 16/11/16.
 */

public class MovieAdapter extends RecyclerView.Adapter {
    private List<Movie> mMovies;


    public void setMovies(List<Movie> movies) {
        mMovies = movies;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_song_card,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        MovieHolder movieHolder = (MovieHolder) holder;
        ImageLoader.getInstance().displayImage(movie.images.small,movieHolder.mImageView);
        movieHolder.time.setText("上映时间："+movie.year + "年");
        movieHolder.title.setText(movie.title);
        movieHolder.subTitle.setText(movie.original_title);

    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0:mMovies.size();
    }

    public static class MovieHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView title;
        public TextView subTitle;
        public TextView time;
        public MovieHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.songPicIv);
            title = (TextView) itemView.findViewById(R.id.songTitleTv);
//            subTitle = (TextView) itemView.findViewById(R.id.movie_sub_title);
//            time = (TextView) itemView.findViewById(R.id.movie_time);
        }
    }
}
