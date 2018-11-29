package com.example.hollo.sel.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.hollo.sel.R;
import com.example.hollo.sel.activities.HomeActivity;
import com.example.hollo.sel.activities.BookActivity;
import com.example.hollo.sel.activities.PaymentActivity;
import com.example.hollo.sel.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {
    Context mContext;
    ArrayList<Book> mBooks;
    String mOwnerId;

    public BooksAdapter(Context context, ArrayList<Book> books, String ownerId) {
        this.mContext = context;
        this.mBooks = books;
        this.mOwnerId = ownerId;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.recyclerview_item, parent, shouldAttachToParentImmediately);
        BookViewHolder bookViewHolder = new BookViewHolder(view);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    void setNewsItems(List<Book> books) {
        mBooks = new ArrayList(books);
        notifyDataSetChanged();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView author;
        Button edit;
        Button buy;

        public BookViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            author = (TextView) itemView.findViewById(R.id.author);
            edit = (Button) itemView.findViewById(R.id.edit);
            buy = (Button) itemView.findViewById(R.id.buy);
        }

        void bind(final int listIndex) {
            final Book book = mBooks.get(listIndex);
            title.setText(book.getTitle());
            author.setText(book.getAuthor());
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, BookActivity.class);
                    intent.putExtra("key", book.getKey());
                    ((Activity) mContext).startActivityForResult(intent, HomeActivity.EDIT_BOOK_ACTIVITY_REQUEST_CODE);
                }
            });
            buy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, PaymentActivity.class);
                    intent.putExtra("key", book.getKey());
                    ((Activity) mContext).startActivityForResult(intent, HomeActivity.BUY_BOOK_ACTIVITY_REQUEST_CODE);
                }
            });
            if (mOwnerId.equalsIgnoreCase(book.getOwnerId())) {
                edit.setVisibility(View.VISIBLE);
                buy.setVisibility(View.INVISIBLE);
            } else {
                edit.setVisibility(View.INVISIBLE);
                buy.setVisibility(View.VISIBLE);
            }
        }
    }
}
