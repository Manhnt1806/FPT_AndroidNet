package com.example.lab78;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

interface OnAlbumLongClickListener {
    void onAlbumLongClick(int position);
}
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> implements Filterable {
    private Context context;
    private List<Example> list;
    private List<Example> mList;
    private Retrofit retrofit;
    OnAlbumLongClickListener longClickListener;
    public void setOnAlbumLongClickListener(OnAlbumLongClickListener listener) {
        this.longClickListener = listener;
    }


    public UserAdapter(Context context, List<Example> list, Retrofit retrofit) {
        this.context = context;
        this.list = list;
        this.mList = list;
        this.retrofit = retrofit;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Example user = list.get(position);
        holder.tvId.setText(user.getId()+"");
        holder.tvName.setText(user.getTitle());
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longClickListener.onAlbumLongClick(position);
                return true;
            }
        });
        if (user.isShowStar()) {
            holder.starImageView.setVisibility(View.VISIBLE);
        } else {
            holder.starImageView.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty()){
                    list = mList;
                }else {
                    List<Example> list1 = new ArrayList<>();
                    for(Example user : mList){
                        if(user.getTitle().toLowerCase().contains(strSearch.toLowerCase())) {
                            list1.add(user);
                        }
                    }
                    list = list1;
                }
                FilterResults results = new FilterResults();
                results.values = list;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                list = (List<Example>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        TextView tvId, tvName;
        CardView cardView;
        ImageView starImageView;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.id);
            tvName = itemView.findViewById(R.id.name);
            cardView = itemView.findViewById(R.id.itemUsser);
            starImageView = itemView.findViewById(R.id.imgStart);
        }
    }
}


