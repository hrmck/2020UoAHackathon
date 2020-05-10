package com.example.inlocker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.Currency;

public class ProductListItemAdapter extends FirestoreRecyclerAdapter<ProductListItem, ProductListItemAdapter.ProductListItemHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ProductListItemAdapter(@NonNull FirestoreRecyclerOptions<ProductListItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ProductListItemHolder holder, int position, @NonNull ProductListItem model) {
        Currency currency = Currency.getInstance("JPY");
        holder.textViewItemName.setText(model.getName());
        String editedPriceText = currency.getSymbol() + String.valueOf(model.getPrice());
        holder.textViewItemPrice.setText(editedPriceText);
        String editedAmountText = "Amount: " + String.valueOf(model.getAmount());
        holder.textViewItemAmount.setText(editedAmountText);
    }

    @NonNull
    @Override
    public ProductListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_list_layout, parent, false);
        return new ProductListItemHolder(v);
    }

    class ProductListItemHolder extends RecyclerView.ViewHolder {
        TextView textViewItemName, textViewItemPrice, textViewItemAmount;

        public ProductListItemHolder(@NonNull View itemView) {
            super(itemView);
            textViewItemName = itemView.findViewById(R.id.itemName_productItemList_textView);
            textViewItemPrice = itemView.findViewById(R.id.itemPrice_productItemList_textView);
            textViewItemAmount = itemView.findViewById(R.id.itemAmount_productItemList_textView);
        }
    }
}
