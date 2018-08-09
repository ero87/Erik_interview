package com.example.ero.mytask1.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.ero.mytask1.R;
import com.example.ero.mytask1.activitys.InfoActivity;
import com.example.ero.mytask1.models.Result;
import com.squareup.picasso.Picasso;
import java.util.Collections;
import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String USER_KEY = "key";
    private Context context;
    private List<Result> list = Collections.emptyList();

    public UserAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

        if (list.get(position).getGender().equals("female")) {
            return 0;
        }
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;
        if (0 == viewType) {
            view = inflater.inflate(R.layout.gilr_item, parent, false);
            return new GirlViewHolder(view);
        }
        view = inflater.inflate(R.layout.boy_item, parent, false);
        return new BoyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InfoActivity.class);
                intent.putExtra(USER_KEY, list.get(position));
                context.startActivity(intent);
            }
        });
        if (list.get(position).getGender().equals("female")) {
            ((GirlViewHolder) holder).bind(list.get(position));
        } else {
            ((BoyViewHolder) holder).bind(list.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<Result> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    class GirlViewHolder extends RecyclerView.ViewHolder {
        private final ImageButton phone;
        private final CircleImageView imageGirl;
        TextView titlegirl;

        GirlViewHolder(View itemView) {
            super(itemView);
            titlegirl = itemView.findViewById(R.id.name_girl);
            phone = itemView.findViewById(R.id.phone);
            imageGirl = itemView.findViewById(R.id.image_girl);
        }

        void bind(final Result result) {
            titlegirl.setText(result.getName().getFirst());
            Picasso.get().load(result.getPicture().getMedium()).fit().into(imageGirl);
            phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + result.getPhone()));
                    context.startActivity(intent);
                }
            });
        }
    }

    class BoyViewHolder extends RecyclerView.ViewHolder {
        TextView titleboy;
        CircleImageView imageBoy;
        ImageButton email;

        BoyViewHolder(View itemView) {
            super(itemView);
            titleboy = itemView.findViewById(R.id.name_boy);
            imageBoy = itemView.findViewById(R.id.image_boy);
            email = itemView.findViewById(R.id.email);
        }
        void bind(final Result result) {
            titleboy.setText(result.getName().getFirst());
            Picasso.get().load(result.getPicture().getMedium()).fit().into(imageBoy);
            email.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setData(Uri.parse("mailto:" + result.getEmail()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
