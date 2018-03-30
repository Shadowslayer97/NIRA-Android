package com.example.srirang.nira_android;

import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class UpdateSampleActivity extends AppCompatActivity {

    private ArrayList<String> mSampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sample);
        UpdateSampleAdapter adapter = new UpdateSampleAdapter(mSampleList);

        RecyclerView gridView = findViewById(R.id.rv_update_sample);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridView.setLayoutManager(gridLayoutManager);
        gridView.setAdapter(adapter);
    }

    private static class UpdateSampleAdapter extends RecyclerView.Adapter<UpdateSampleViewHolder> {

        private ArrayList<String> sampleList;

        public UpdateSampleAdapter(ArrayList<String> sampleList) {
            this.sampleList = sampleList;
        }

        @NonNull
        @Override
        public UpdateSampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_grid_item, parent, false);

            return new UpdateSampleViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final UpdateSampleViewHolder holder, int position) {
            holder.sampleName.setText("Sample name");
            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("images/pic.jpg");
            storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(holder.sampleImage.getContext()).load(uri.toString()).into(holder.sampleImage);
                }
            });

        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }

    private static class UpdateSampleViewHolder extends RecyclerView.ViewHolder {
        private ImageView sampleImage;
        private TextView sampleName;

        public UpdateSampleViewHolder(View itemView) {
            super(itemView);
            sampleImage = itemView.findViewById(R.id.iv_sample_image);
            sampleName = itemView.findViewById(R.id.tv_sample_name);
        }
    }
}
