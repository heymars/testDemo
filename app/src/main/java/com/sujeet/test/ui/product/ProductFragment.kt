package com.sujeet.test.ui.product

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.sujeet.test.R
import com.sujeet.test.ui.product.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class ProductFragment : Fragment() {

    private var columnCount = 1

    lateinit var viewModel: ProductViewModel
    private lateinit var view : View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         view = inflater.inflate(R.layout.fragment_product_list, container, false)

        // Set the adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        viewModel.getProductList();
        viewModel.productListMutableLiveData.observe(viewLifecycleOwner) {
           if (it.products != null) {
               if (view is RecyclerView) {
                   with(view) {
                       layoutManager = when {
                           columnCount <= 1 -> LinearLayoutManager(context)
                           else -> GridLayoutManager(context, columnCount)
                       }
                       adapter = MyItemRecyclerViewAdapter(it.products)
                   }
               }
           } else {
               it.msg?.let { it1 -> Log.d("", it1) }
           }
        }
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}