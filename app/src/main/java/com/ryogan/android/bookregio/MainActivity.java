package com.ryogan.android.bookregio;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ryogan.android.bookregio.adapter.BookAdapter;
import com.ryogan.android.bookregio.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {

    static ListView listView;
    private FloatingActionButton addButton;
    static BookAdapter adapter;
    static List<Book> bookArray = new ArrayList<>();
    static List<Book> filterBookArray = new ArrayList<>();
    private SearchView searchView;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        bookArray.add(new Book("Clean Code","Robert C. Martin","PRENTICE-HALL", "2008", getURLForResource(R.drawable.cleancode)));
        bookArray.add(new Book("Cien años de soledad","Gabriel García Márquez","Sudamericana", "1967", getURLForResource(R.drawable.cien)));
        bookArray.add(new Book("Programming Fundamentals with Swift","Matt Neuburg","O'Reilly Media", "2019", getURLForResource(R.drawable.swift)));


        searchView = (SearchView)findViewById(R.id.searchView);



        listView = findViewById(R.id.lvExample);
        addButton = findViewById(R.id.addButton);
        adapter = new BookAdapter(this,bookArray);
        listView.setTextFilterEnabled(true);
        listView.setAdapter(adapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                MainActivity.this.adapter.getFilter().filter(newText);
                return false;
            }
        });

        Intent intent = new Intent(this, AddBook.class);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }

    public static void deleteItem(int index){

        AlertDialog dialogo = new AlertDialog
                .Builder(context)
                .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bookArray.remove(index);
                        listView.setAdapter(null);
                        adapter = new BookAdapter(context,bookArray);
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setTitle("Confirmar") // El título
                .setMessage("¿Deseas eliminar este Libro?")
                .create();
        dialogo.show();
    }


    @Override
    protected void onResume() {

        bookArray = EditBook.list;
        bookArray = AddBook.list;
        adapter = new BookAdapter(this,bookArray);
        listView.setAdapter(null);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        super.onResume();
    }


    public String getURLForResource (int resourceId) {
        //use BuildConfig.APPLICATION_ID instead of R.class.getPackage().getName() if both are not same
        return Uri.parse("android.resource://"+R.class.getPackage().getName()+"/" +resourceId).toString();
    }
}