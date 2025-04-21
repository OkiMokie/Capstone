package com.example.elevateretailapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link itemPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class itemPage extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int product_id;
    private String product_name;
    private String product_description;
    private int category_id;
    private int supplier_id;
    private int imageResId;
    private String price;


    public itemPage() {
        // Required empty public constructor
    }

    // overload
    public itemPage(int product_id, String product_name, String product_description, int category_id, int supplier_id, int imageResId, String price) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.category_id = category_id;
        this.supplier_id = supplier_id;
        this.imageResId = imageResId;
        this.price = price;
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     */
    // TODO: Rename and change types and number of parameters
//    public static itemPage newInstance(String param1, String param2) {
//        itemPage fragment = new itemPage();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

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
        fragment.setArguments(args);
        return fragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

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
        }
    }


    //on create
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_item_page, container, false);

        // reference the widgets
        TextView sellerTxtBox = myView.findViewById(R.id.SellerTextView);
        TextView descriptionTxtBox = myView.findViewById(R.id.ItemDescriptionTextView);
        ImageView productImageBox = myView.findViewById(R.id.ProductImageView);
        TextView priceTagTxtBox = myView.findViewById(R.id.ProductPriceTagTextView);
        Button buyNowButton = myView.findViewById(R.id.BuyNowButton);
        Button addToCartButton = myView.findViewById(R.id.AddToCartBtn);

        // attach listener BTW this is lambda style, so you cant reuse any code you put here lmao
        buyNowButton.setOnClickListener(v -> {
            Product product = new Product(
                    product_id,
                    product_name,
                    product_description,
                    category_id,
                    supplier_id,
                    imageResId,
                    price
            );
            CartManager.addToCart(product);
            ((MainActivity) getActivity()).setCurrentFragment(new CheckoutFragment());
        });

        addToCartButton.setOnClickListener(v -> {
            // adds something to the cart array
            Product product = new Product(
                    product_id,
                    product_name,
                    product_description,
                    category_id,
                    supplier_id,
                    imageResId,
                    price
            );
            CartManager.addToCart(product);
            Toast.makeText(getContext(), "Added to cart", Toast.LENGTH_SHORT).show();
        });

        // debug
        //Toast.makeText(getContext(), "Product ID:" + product_id, Toast.LENGTH_SHORT).show();

        // fetch item


        // update
        sellerTxtBox.setText("Seller: " + supplier_id);
        descriptionTxtBox.setText(product_description);
        priceTagTxtBox.setText(price);


        return myView;
    }


}