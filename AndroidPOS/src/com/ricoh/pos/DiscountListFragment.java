package com.ricoh.pos;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ricoh.pos.data.Order;
import com.ricoh.pos.data.Product;
import com.ricoh.pos.model.ProductsManager;
import com.ricoh.pos.model.RegisterManager;

/**
 * A fragment representing a single Category detail screen. This fragment is
 * either contained in a {@link CategoryListActivity} in two-pane mode (on
 * tablets) or a {@link CategoryDetailActivity} on handsets.
 */
public class DiscountListFragment extends ListFragment {

	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";

	private RegisterManager registerManager;

	private String category;
	private ArrayList<Product> productList;

	public DiscountListFragment() {
		this.registerManager = RegisterManager.getInstance();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle bundle = getArguments();
		this.category = bundle.getString(CategoryDetailFragment.ARG_ITEM_ID);

		productList = ProductsManager.getInstance().getProductsInCategory(category);
		setListAdapter(new ListAdapter(getActivity()));

		getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
	}

	public class ListAdapter extends BaseAdapter {
		// private Context contextInAdapter;
		private LayoutInflater inflater;

		public ListAdapter(Context context) {
			// contextInAdapter = context;
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			return productList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO: Not implement

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.discount_product_row, null);
			}

			Product product = productList.get(position);

			ImageView imageView = (ImageView) convertView.findViewById(R.id.photo);
			imageView.setLayoutParams(new LinearLayout.LayoutParams(120, 120));
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			imageView.setImageResource(getResourceID(product.getProductImagePath()));

			TextView textView = (TextView) convertView.findViewById(R.id.filename);
			textView.setPadding(10, 0, 0, 0);
			String productName = product.getName();
			if (productName == null || productName.length() == 0) {
				throw new NullPointerException("Product name is not valid");
			}
			textView.setText(productName);

			TextView priceView = (TextView) convertView.findViewById(R.id.price);
			priceView.setPadding(10, 0, 0, 0);
			priceView.setText(String.valueOf(product.getPrice()));

			TextView numberOfSalseView = (TextView) convertView.findViewById(R.id.numberOfSales);
			numberOfSalseView.setPadding(10, 0, 0, 0);

			Order order = registerManager.findOrderOfTheProduct(product);
			if (order == null || order.getNumberOfOrder() == 0) {
				//TODO
				numberOfSalseView.setText("");
			} else {
				int numberOfSales = order.getNumberOfOrder();
				numberOfSalseView.setText(String.valueOf(numberOfSales));
			}

			return convertView;
		}

		private int getResourceID(String fileName) {
			int resID = getResources().getIdentifier(fileName, "drawable", "com.ricoh.pos");
			return resID;
		}
	}

	public class DiscountValueWatcher implements TextWatcher {
		public DiscountValueWatcher(ProductEditText view) {
			// TODO: Not implement
		}

		@Override
		public void afterTextChanged(Editable s) {
			// TODO: Not implement
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			// TODO: Not implement
		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			// TODO: Not implement
		}

	}

}
