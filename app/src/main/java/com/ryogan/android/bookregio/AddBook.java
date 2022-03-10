package com.ryogan.android.bookregio;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.ryogan.android.bookregio.adapter.BookAdapter;
import com.ryogan.android.bookregio.model.Book;

import java.util.ArrayList;

public class AddBook extends AppCompatActivity {

    private Book book;
    private EditText titulo;
    private EditText actor;
    private EditText editorial;
    private EditText año;
    private EditText precio;
    private EditText categoria;
    private ImageView imagen;
    private Button saveButton;
    private Uri urii;
    static BookAdapter adapter;
    static ArrayList<Book> list = (ArrayList<Book>) MainActivity.bookArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        initViews();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //adapter.add(new Book(titulo.getText().toString(), actor.getText().toString(), editorial.getText().toString(), año.getText().toString(), urii.toString()));
                list.add(new Book(titulo.getText().toString(), actor.getText().toString(), editorial.getText().toString(), año.getText().toString(), urii.toString(), precio.getText().toString(), categoria.getText().toString()));
                finish();
            }
        });

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(intent.createChooser(intent,"Selecccione la aplicacion"),10);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Uri path = data.getData();
            this.urii = path;
            Glide.with(this).load(path).into(imagen);

        }
    }


    public void initViews(){
        titulo = findViewById(R.id.txtTitulo);
        actor = findViewById(R.id.txtAutor);
        editorial = findViewById(R.id.txtEditorial);
        año = findViewById(R.id.txtAño);
        precio = findViewById(R.id.txtPrecio);
        categoria = findViewById(R.id.txtCategoria);
        imagen = findViewById(R.id.image);
        saveButton = findViewById(R.id.saveButton);
    }
}