package com.farzinzeynal.camerabakuadmin.ui.login;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.farzinzeynal.camerabakuadmin.R;
import com.farzinzeynal.camerabakuadmin.ui.retrofit.api.ItemApi;
import com.farzinzeynal.camerabakuadmin.ui.retrofit.model.ItemRequest;
import com.farzinzeynal.camerabakuadmin.ui.retrofit.model.dto.ItemDto;
import com.farzinzeynal.camerabakuadmin.ui.util.Contants;
import com.farzinzeynal.camerabakuadmin.ui.util.DateUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;


public class AddItemFragment extends Fragment {


    TextInputEditText et_item_name,et_item_price,et_item_description;
    ImageView imageView_item;
    Spinner type_spinner;
    Button add_button;
    ProgressBar progressBar_add_item;


    private String item_type;

    private final static  int REQUEST_CODE_GALLERY = 999;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_item, container, false);


        et_item_name = root.findViewById(R.id.et_item_name);
        et_item_price = root.findViewById(R.id.et_item_price);
        et_item_description = root.findViewById(R.id.et_item_description);
        imageView_item = root.findViewById(R.id.imageView_item);
        type_spinner = root.findViewById(R.id.type_spinner);
        add_button = root.findViewById(R.id.add_button);
        progressBar_add_item = root.findViewById(R.id.progressBar_add_item);

        add_button.setEnabled(false);

        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item_type = type_spinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                item_type = parent.getSelectedItem().toString();
            }
        });

        imageView_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY);

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_item_name.length() == 0) { et_item_name.setError("Boş buraxıla bilməz"); return;}
                if (et_item_price.length() == 0) { et_item_price.setError("Boş buraxıla bilməz"); return;}
                if (et_item_description.length() == 0) { et_item_description.setError("Boş buraxıla bilməz"); return;}



                Date date = Calendar.getInstance().getTime();
                String formattedDate = DateUtils.getFullDate(date);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Contants.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ItemApi itemApi = retrofit.create(ItemApi.class);


                ItemRequest itemRequest = new ItemRequest();
                itemRequest.setPhoto(imageViewToString(imageView_item));
                itemRequest.setTitle(et_item_name.getText().toString());
                itemRequest.setPrice(Long.parseLong(et_item_price.getText().toString()));
                itemRequest.setItem_type(item_type);
                itemRequest.setRating(4.5F);
                itemRequest.setCreated_date(formattedDate);
                Call<ItemDto> call = itemApi.createItem(itemRequest);


                progressBar_add_item.setVisibility(View.VISIBLE);

                call.enqueue(new Callback<ItemDto>() {
                    @Override
                    public void onResponse(Call<ItemDto> call, Response<ItemDto> response) {
                        if (response.isSuccessful()) {
                            progressBar_add_item.setVisibility(View.GONE);
                            Log.i("onResponseItemAdd", "OK: ");

                            Toast.makeText(getActivity(), "Məhsul əlava olundu", Toast.LENGTH_SHORT).show();

                            et_item_name.setText("");
                            et_item_description.setText("");
                            et_item_price.setText("");
                            add_button.setEnabled(false);
                            imageView_item.setImageResource(R.drawable.add_image);

                        }
                           else {
                            progressBar_add_item.setVisibility(View.GONE);
                            Log.i("onResponseItemAdd", "ERROR: "+response.code()+" "+response.message());
                            Toast.makeText(getActivity(), "Bilinməyən XƏTA", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ItemDto> call, Throwable t) {
                        progressBar_add_item.setVisibility(View.GONE);
                        Log.i("onResponseItemAdd", "FAIL: "+t.toString());
                        Toast.makeText(getActivity(), "Bilinməyən XƏTA", Toast.LENGTH_SHORT).show();
                    }
                });



            }
        });


        return root;
    }


    private byte[] imageViewTobyte(ImageView imageView)
    {
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream  = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private String imageViewToString(ImageView imageView)
    {
        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream  = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] byteArray = stream.toByteArray();
        String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return encodedImage;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==REQUEST_CODE_GALLERY)
            {
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(getContext(),this);
            }
        }

        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                imageView_item.setImageURI(resultUri);
                Log.i("imageUri", "OK");
                add_button.setEnabled(true);

            }

            else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
                Toast.makeText(getActivity(), " "+error, Toast.LENGTH_SHORT).show();
            }
        }





    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        //request for galery
        if (requestCode == REQUEST_CODE_GALLERY)
        {
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_GALLERY);
            }
            else
            {
                Toast.makeText(getActivity(),"Galareya'ya girməyə icazəniz yoxdur",Toast.LENGTH_LONG).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }





}