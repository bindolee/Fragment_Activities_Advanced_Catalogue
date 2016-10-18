package sbin.com.cataloguefragment.Products;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import sbin.com.cataloguefragment.DataProvider;
import sbin.com.cataloguefragment.R;


public class ProductListFragment extends ListFragment {

    private ListFragmentListener mListener;
    private List<Product> products = DataProvider.productList;

    public ProductListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        ProductListAdapter adapter = new ProductListAdapter(
                getContext(), R.layout.list_item, products);

        setListAdapter(adapter);
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String uriString = "http://hplussport.com/catalog/productid/" + position;
        Uri uri = Uri.parse(uriString);
        mListener.onListItemClick(uri);}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListFragmentListener) {
            mListener = (ListFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement ListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface ListFragmentListener {
        void onListItemClick(Uri uri);
    }
}
