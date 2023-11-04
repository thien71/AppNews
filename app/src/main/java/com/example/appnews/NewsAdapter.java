package com.example.appnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<String> titles;
    private ArrayList<String> imageUrls;

    public NewsAdapter(Context context, int layout, ArrayList<String> titles, ArrayList<String> imageUrls) {
        this.context = context;
        this.layout = layout;
        this.titles = titles;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
        }

        ImageView imageView = view.findViewById(R.id.imgHinhAnh);
        TextView textView = view.findViewById(R.id.txtTieuDe);

        String title = titles.get(position);
        String imageUrl = imageUrls.get(position);

        // Đặt tiêu đề
        textView.setText(title);

        if (imageUrl != null && !imageUrl.isEmpty()) {
            Picasso.get().load(imageUrl).into(imageView);
        } else {
            imageView.setImageResource(R.drawable.ic_launcher_background); // Sử dụng một hình ảnh mặc định
        }

        return view;
    }
}
