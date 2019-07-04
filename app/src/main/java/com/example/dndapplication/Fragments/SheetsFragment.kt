package com.example.dndapplication.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.dndapplication.R

import com.example.dndapplication.Fragments.dummy.DummyContent
import com.example.dndapplication.Fragments.dummy.DummyContent.DummyItem
import com.example.dndapplication.Models.Sheet



/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [SheetsFragment.OnListFragmentInteractionListener] interface.
 */
class SheetsFragment : Fragment() {

    private var columnCount = 1

    private var listener: OnListFragmentInteractionListener? = null

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
        val view = inflater.inflate(R.layout.fragment_sheets_list, container, false)

        val sheets = createData()

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MySheetsRecyclerViewAdapter(sheets, listener)
                val dividerItemDecoration = DividerItemDecoration(
                    context,DividerItemDecoration.VERTICAL
                )

                addItemDecoration(dividerItemDecoration)
            }
        }
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

    fun createData(): MutableList<Sheet> {
        val sheetList =  mutableListOf<Sheet>()
       /* val newSheet = Sheet("Jolan", "Noble", "Elf", "NG", "Fighter", 15, 10, 10, 10, 10, 10 , 15, 30, 44)
        val newSheet2 = Sheet("Jolan Also", "Farmer", "Dwarf", "CE", "Paladin", 15, 10, 10, 10, 10, 10 , 15, 30, 44)
        sheetList.add(newSheet)
        sheetList.add(newSheet2)
*/
        for (i in 0..10) {
            val playername = "Player $i"
            val newSheet = Sheet(playername, "Noble", "Elf", "NG", "Fighter", 15, 10, 10, 10, 10, 10 , 15, 30, 44)
            sheetList.add(newSheet)
        }

        return sheetList
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
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
