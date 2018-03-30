package com.example.srirang.nira_android;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class UpdateSampleActivity extends AppCompatActivity {

    private ArrayList<AddModal> mSampleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sample);
        final UpdateSampleAdapter adapter = new UpdateSampleAdapter(this);

        mSampleList = new ArrayList<>();
        final RecyclerView gridView = findViewById(R.id.rv_update_sample);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridView.setLayoutManager(gridLayoutManager);

        DatabaseReference db = FirebaseDatabase.getInstance().getReference()
                .child("sampler")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("completed");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    mSampleList.add(ds.getValue(AddModal.class));
                    adapter.notifyDataSetChanged();
                }
                gridView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private class UpdateSampleAdapter extends RecyclerView.Adapter<UpdateSampleViewHolder> {
        private Context context;

        public UpdateSampleAdapter(Context context) {
            this.context = context;
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
//            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("images/pic.jpg");
//            storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                @Override
//                public void onSuccess(Uri uri) {
//                    Glide.with(holder.sampleImage.getContext()).load(uri.toString()).into(holder.sampleImage);
//                }
//            });

            Glide.with(context)
                    .load(mSampleList.get(position).getUrl()).into(holder.sampleImage);

        }

        @Override
        public int getItemCount() {
            return mSampleList != null ? mSampleList.size() : 0;
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
