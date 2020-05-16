package com.example.inlocker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class StoreListItemAdapter extends FirestoreRecyclerAdapter<StoreListItem, StoreListItemAdapter.StoreListItemHolder> {
    private OnItemClickListener listener;
    FirebaseStorage fStorage = FirebaseStorage.getInstance();


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public StoreListItemAdapter(@NonNull FirestoreRecyclerOptions<StoreListItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull StoreListItemHolder holder, int position, @NonNull StoreListItem model) {
        holder.textViewStoreName.setText(model.getName());
        Picasso.get().load(model.getLogoImageLink()).into(holder.imageViewStoreLogo);
    }

    @NonNull
    @Override
    public StoreListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_layout, parent, false);
        return new StoreListItemHolder(v);
    }

    class StoreListItemHolder extends RecyclerView.ViewHolder {

        TextView textViewStoreName;
        ImageView imageViewStoreLogo;

        public StoreListItemHolder(@NonNull View itemView) {
            super(itemView);
            textViewStoreName = itemView.findViewById(R.id.store_name);
            imageViewStoreLogo = itemView.findViewById(R.id.store_image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        //the document snapshot can be used to get anything from the object we retrieved
        //change para if need to send more stuff from adapter(here) to activity
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
