package com.example.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Dao.SanPhamDao;

import com.example.adapter.ProductAdapter;
import com.example.asm_cuatoi.R;
import com.example.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ProductFragment extends Fragment {

    private RecyclerView recyclerViewProduct;
    private FloatingActionButton floatAdd;

    private SanPhamDao sanPhamDao;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product, container, false);
        //code...

        recyclerViewProduct = view.findViewById(R.id.recyclerProduct);
        floatAdd = view.findViewById(R.id.floatAdd);

        sanPhamDao = new SanPhamDao(getContext());
        ArrayList<Product> list = sanPhamDao.getDS();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewProduct.setLayoutManager(linearLayoutManager);
        ProductAdapter adapter = new ProductAdapter(getContext(), list);
        recyclerViewProduct.setAdapter(adapter);

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    private void showDialogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add, null);
        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        EditText edtTensp = view.findViewById(R.id.edtTensp);
        EditText edtGiapsp = view.findViewById(R.id.edtGiasp);
        EditText edtSLSP = view.findViewById(R.id.edtSLSP);
        Button btnThemSP = view.findViewById(R.id.btnThemSP);
        Button btnHuy = view.findViewById(R.id.btnHuy);

        btnThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensp = edtTensp.getText().toString();
                int giasp = Integer.parseInt(edtGiapsp.getText().toString());
                String soLuong = edtSLSP.getText().toString();

                if (tensp.length() == 0 || soLuong.length() == 0){
                    Toast.makeText(getContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {

                }
            }
        });

    }
}
