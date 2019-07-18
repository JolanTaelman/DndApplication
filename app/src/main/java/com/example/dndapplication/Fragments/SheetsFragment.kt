package com.example.dndapplication.Fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.dndapplication.R
import com.example.dndapplication.Models.Sheet
import com.example.dndapplication.ViewModels.SheetViewModel




/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [SheetsFragment.OnListFragmentInteractionListener] interface.
 */
class SheetsFragment : Fragment() {

    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

    private lateinit var viewModel: SheetViewModel

    //private lateinit var sheets: List<Sheet>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
        viewModel = ViewModelProviders.of(this).get(SheetViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sheets_list, container, false)
        if (view is RecyclerView) {

            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MySheetsRecyclerViewAdapter(listener)

                val dividerItemDecoration = DividerItemDecoration(
                    context,DividerItemDecoration.VERTICAL
                )
                addItemDecoration(dividerItemDecoration)
            }
            val sheetsObserver = Observer<List<Sheet>> { newSheets ->
                (view.adapter as MySheetsRecyclerViewAdapter).setData(newSheets)
            }
            viewModel.getSheets().observe(this,sheetsObserver)
        }
       // Log.e("Fraggers", sheets.toString())
        // Set the adapter
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun onListFragmentInteraction(item: Sheet?)
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance(columnCount: Int) =
            SheetsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}
