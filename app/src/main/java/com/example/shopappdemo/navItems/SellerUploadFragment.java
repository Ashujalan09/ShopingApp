package com.example.shopappdemo.navItems;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.shopappdemo.customObjects.Categories;
import com.example.shopappdemo.R;
import com.example.shopappdemo.customObjects.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class SellerUploadFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;
    static int DATACODE;
    static int IMAGECODE;
    private SellerUploadViewModel mViewModel;

    public static SellerUploadFragment newInstance() {
        return new SellerUploadFragment();
    }

    Uri imageUri;

    String selectedCategory, productName;
    String productCount, productPrice;

    Categories categories;
    ImageView itemImage;
    Button uploadBtn, proceedBtn;
    EditText productNameEdt, productPriceEdt, productCountEdt;
    Spinner categorySpinner;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.seller_upload_fragment, container, false);
        itemImage = view.findViewById(R.id.seller_upload_frag_image);
        uploadBtn = view.findViewById(R.id.seller_upload_frag_btn_upload);
        proceedBtn = view.findViewById(R.id.seller_upload_frag_btn_proceed);
        productNameEdt = view.findViewById(R.id.seller_upload_frag_product_name);
        productPriceEdt = view.findViewById(R.id.seller_upload_frag_price);
        productCountEdt = view.findViewById(R.id.seller_upload_frag_count);
        categorySpinner = view.findViewById(R.id.seller_upload_frag_category);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        categories = new Categories();
        List<String> categoryList = categories.getCategoryList();

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categoryList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(spinnerAdapter);


        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenFileChooser();
            }
        });

        mViewModel = new ViewModelProvider(this).get(SellerUploadViewModel.class);
        // TODO: Use the ViewModel

        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedItem();
            }
        });
    }

    private void proceedItem() {

        productPrice = productPriceEdt.getText().toString();
        selectedCategory = categorySpinner.getSelectedItem().toString();
        productName = productNameEdt.getText().toString();
        productCount = productCountEdt.getText().toString();


        if(productPrice.isEmpty()){
            productPriceEdt.setError("Price is required!");
            productPriceEdt.requestFocus();
            return;
        }
        if(productName.isEmpty()){
            productNameEdt.setError("Product name is required!");
            productNameEdt.requestFocus();
            return;
        }
        if(productCount.isEmpty()) {
            productCountEdt.setError("Phone Number is required!");
            productCountEdt.requestFocus();
            return;
        }
        if(imageUri == null){
            Toast.makeText(getContext(), "Product image is needed", Toast.LENGTH_SHORT).show();
            return;
        }

        Item currentItem = new Item( selectedCategory,productName, Integer.parseInt(productPrice) , Integer.parseInt(productCount),null);
        DATACODE = mViewModel.uploadItemData(currentItem);
        IMAGECODE = mViewModel.uploadItemImage(imageUri, productName);
        if(DATACODE == 1 && IMAGECODE == 1){
            Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getContext(), "failure", Toast.LENGTH_SHORT).show();


    }

    private void OpenFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && data != null &&
        resultCode == RESULT_OK && data.getData() != null){

            imageUri = data.getData();

            Picasso.get().load(imageUri).fit().into(itemImage);
        }

    }




}