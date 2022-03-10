package com.ryogan.android.bookregio.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import com.ryogan.android.bookregio.EditBook;
import com.ryogan.android.bookregio.MainActivity;
import com.ryogan.android.bookregio.R;
import com.ryogan.android.bookregio.model.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> implements Filterable {

    private List<Book> itemsModelsl;
    private List<Book> itemsModelListFiltered;
    private Context context;
    private BookFilter filter;

    public BookAdapter(@NonNull Context context,@NonNull List<Book> objects) {
        super(context, R.layout.book_item, objects);
        this.itemsModelsl = new ArrayList<Book>();
        this.itemsModelsl.addAll(objects);
        this.itemsModelListFiltered = objects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemsModelListFiltered.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter  = new BookFilter();
        }
        return filter;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book_item, parent,false);
        }

        ImageView image = convertView.findViewById(R.id.ivExample);
        TextView titulo = convertView.findViewById(R.id.titulo);
        TextView autor = convertView.findViewById(R.id.autor);
        TextView editorial = convertView.findViewById(R.id.editorial);
        TextView año = convertView.findViewById(R.id.año);
        Button editButton = convertView.findViewById(R.id.editarButton);
        Button borrarButton = convertView.findViewById(R.id.borrarButton);


        Book book = (Book) getItem(position);
        Glide.with(getContext()).load(book.getImagen()).into(image);
        titulo.setText(book.getTitulo());
        autor.setText(book.getAutor());
        editorial.setText(book.getEditorial());
        año.setText(book.getAño());


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditBook.class);
                intent.putExtra("book", (Serializable) book);
                getContext().startActivity(intent);
            }
        });

        borrarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.deleteItem(position);
            }
        });

        return convertView;
    }



    public class BookFilter extends Filter {


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            ArrayList<Book> FilteredArrayNames = new ArrayList<>();


            constraint = constraint.toString().toLowerCase();
            for (int i = 0; i < itemsModelsl.size(); i++) {
                Book dataNames = itemsModelsl.get(i);
                if (dataNames.getTitulo().toLowerCase().startsWith(constraint.toString()) || dataNames.getAutor().toLowerCase().startsWith(constraint.toString()) || dataNames.getEditorial().toLowerCase().startsWith(constraint.toString()) || dataNames.getAño().toLowerCase().startsWith(constraint.toString()) )  {
                    FilteredArrayNames.add(dataNames);
                }
            }

            results.count = FilteredArrayNames.size();
            results.values = FilteredArrayNames;
            Log.e("VALUES", results.values.toString());

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            itemsModelListFiltered = (ArrayList<Book>)results.values;
            notifyDataSetChanged();
            clear();
            for(int i = 0, l = itemsModelListFiltered.size(); i < l; i++)
                add(itemsModelListFiltered.get(i));
            notifyDataSetInvalidated();
        }
    }


}






