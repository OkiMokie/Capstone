package com.example.elevateretailapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

public class itemPage extends Fragment {

    private int product_id;
    private String product_name;
    private String product_description;
    private int category_id;
    private int supplier_id;
    private int imageResId;
    private String price;
    private String imageUrl;

    public itemPage() {
        // Required empty public constructor
    }

    public static itemPage newInstance(Product product) {
        itemPage fragment = new itemPage();
        Bundle args = new Bundle();
        args.putInt("product_id", product.getProduct_id());
        args.putString("product_name", product.getProduct_name());
        args.putString("product_description", product.getProduct_description());
        args.putInt("category_id", product.getCategory_id());
        args.putInt("supplier_id", product.getSupplier_id());
        args.putInt("imageResId", product.getImageResId());
        args.putString("price", product.getPrice());
        args.putString("image_url", product.getImageUrl());
        fragment.setArguments(args);
        return fragment;
    }

    public itemPage(int product_id, String product_name, String product_description,
                    int category_id, int supplier_id, int imageResId, String price, String imageUrl) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.category_id = category_id;
        this.supplier_id = supplier_id;
        this.imageResId = imageResId;
        this.price = price;
        this.imageUrl = imageUrl;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            product_id = getArguments().getInt("product_id");
            product_name = getArguments().getString("product_name");
            product_description = getArguments().getString("product_description");
            category_id = getArguments().getInt("category_id");
            supplier_id = getArguments().getInt("supplier_id");
            imageResId = getArguments().getInt("imageResId");
            price = getArguments().getString("price");
            imageUrl = getArguments().getString("image_url");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_item_page, container, false);

        TextView sellerTxtBox = myView.findViewById(R.id.SellerTextView);
        TextView descriptionTxtBox = myView.findViewById(R.id.ItemDescriptionTextView);
        ImageView productImageBox = myView.findViewById(R.id.ProductImageView);
        TextView priceTagTxtBox = myView.findViewById(R.id.ProductPriceTagTextView);
        Button buyNowButton = myView.findViewById(R.id.BuyNowButton);
        Button addToCartButton = myView.findViewById(R.id.AddToCartBtn);

        // Construct full Product object (for cart use)
        Product product = new Product(product_id, product_name, product_description,
                category_id, supplier_id, imageResId, price, imageUrl);

        // Load image from URL or fallback
        if (imageUrl != null && !imageUrl.isEmpty() && !imageUrl.equalsIgnoreCase("null")) {
            Glide.with(this)
                    .load(imageUrl)
                    .placeholder(imageResId)
                    .into(productImageBox);
        } else {
            productImageBox.setImageResource(imageResId);
        }

        sellerTxtBox.setText("Seller: " + supplier_id);
        descriptionTxtBox.setText(product_description);
        priceTagTxtBox.setText(price);

        buyNowButton.setOnClickListener(v -> {
            CartManager.addToCart(product);
            ((MainActivity) getActivity()).setCurrentFragment(new CheckoutFragment());
        });

        addToCartButton.setOnClickListener(v -> {
            CartManager.addToCart(product);
            Toast.makeText(getContext(), "Added to cart", Toast.LENGTH_SHORT).show();
        });

        return myView;
    }
}
