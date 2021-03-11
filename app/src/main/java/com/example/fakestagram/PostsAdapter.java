package com.example.fakestagram;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.widget.TextView;
import android.widget.ImageView;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;

    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Post post = posts.get(position);

        holder.bind(post);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items
    public void addAll(List<Post> postList) {
        posts.addAll(postList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivProfileImage;

        private TextView tvUsername;

        private TextView tvDescription;

        private ImageView ivImage;


        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            tvUsername = itemView.findViewById(R.id.tvUsername);

            tvDescription = itemView.findViewById(R.id.tvDescription);

            ivImage = itemView.findViewById(R.id.ivImage);

            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);

        }

        public void bind(Post post) {

            //bind post data to view elements
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            ParseFile image = post.getImage();
            ParseFile Avatar = post.getAvatar();
            if (image != null) {

                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);

                Glide.with(context).load(post.getAvatar().getUrl()).transform(new CircleCrop()).into(ivProfileImage);

            }

        }

    }

}
