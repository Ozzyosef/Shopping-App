package com.example.shoppingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textViewWelcome;
    private RecyclerView recyclerViewProducts;
    private RecyclerView recyclerViewCart;
    private List<Product> productList;
    private List<Product> cartList;
    private ProductAdapter productAdapter;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String username = getIntent().getStringExtra("username");
        textViewWelcome = findViewById(R.id.textViewWelcome);
        textViewWelcome.setText("Hello, " + username);

        setupProducts();
        setupRecyclerViews();
    }

    private void setupProducts() {
        productList = new ArrayList<>();
        cartList = new ArrayList<>();

        productList.add(new Product("Milk", R.drawable.milk));
        productList.add(new Product("Eggs", R.drawable.eggs));
        productList.add(new Product("Bread", R.drawable.bread));
        productList.add(new Product("Banana", R.drawable.banana));
        productList.add(new Product("Tomato", R.drawable.tomato));
        productList.add(new Product("Cheese", R.drawable.cheese));
        productList.add(new Product("Apple", R.drawable.apple));
        productList.add(new Product("Lettuce", R.drawable.lettuce));
        productList.add(new Product("Grape", R.drawable.grape));
        productList.add(new Product("Rice", R.drawable.rice));
    }

    private void setupRecyclerViews() {
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);
        recyclerViewCart = findViewById(R.id.recyclerViewCart);

        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));

        productAdapter = new ProductAdapter(productList);
        cartAdapter = new CartAdapter(cartList);

        recyclerViewProducts.setAdapter(productAdapter);
        recyclerViewCart.setAdapter(cartAdapter);
    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
        private List<Product> products;

        ProductAdapter(List<Product> products) {
            this.products = products;
        }

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product, parent, false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ProductViewHolder holder, int position) {
            Product product = products.get(position);
            holder.textViewProductName.setText(product.getName());
            holder.imageViewProduct.setImageResource(product.getImageResourceId());
            holder.editTextQuantity.setText(String.valueOf(product.getQuantity()));

            holder.buttonAddToCart.setOnClickListener(v -> {
                int quantity = Integer.parseInt(holder.editTextQuantity.getText().toString());
                if (quantity > 0) {
                    product.setQuantity(quantity);
                    if (!cartList.contains(product)) {
                        cartList.add(product);
                    }
                    cartAdapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return products.size();
        }

        class ProductViewHolder extends RecyclerView.ViewHolder {
            ImageView imageViewProduct;
            TextView textViewProductName;
            EditText editTextQuantity;
            Button buttonAddToCart;

            ProductViewHolder(View itemView) {
                super(itemView);
                imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
                textViewProductName = itemView.findViewById(R.id.textViewProductName);
                editTextQuantity = itemView.findViewById(R.id.editTextQuantity);
                buttonAddToCart = itemView.findViewById(R.id.buttonAddToCart);
            }
        }
    }

    private class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
        private List<Product> cartItems;

        CartAdapter(List<Product> cartItems) {
            this.cartItems = cartItems;
        }

        @Override
        public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_cart, parent, false);
            return new CartViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CartViewHolder holder, int position) {
            Product product = cartItems.get(position);
            holder.textViewCartProductName.setText(product.getName());
            holder.imageViewCartProduct.setImageResource(product.getImageResourceId());
            holder.textViewCartQuantity.setText(String.valueOf(product.getQuantity()));

            holder.buttonRemoveFromCart.setOnClickListener(v -> {
                cartItems.remove(position);
                notifyDataSetChanged();
            });
        }

        @Override
        public int getItemCount() {
            return cartItems.size();
        }

        class CartViewHolder extends RecyclerView.ViewHolder {
            ImageView imageViewCartProduct;
            TextView textViewCartProductName;
            TextView textViewCartQuantity;
            Button buttonRemoveFromCart;

            CartViewHolder(View itemView) {
                super(itemView);
                imageViewCartProduct = itemView.findViewById(R.id.imageViewCartProduct);
                textViewCartProductName = itemView.findViewById(R.id.textViewCartProductName);
                textViewCartQuantity = itemView.findViewById(R.id.textViewCartQuantity);
                buttonRemoveFromCart = itemView.findViewById(R.id.buttonRemoveFromCart);
            }
        }
    }
}