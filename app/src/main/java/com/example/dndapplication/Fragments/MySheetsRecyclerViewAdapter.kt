package com.example.dndapplication.Fragments

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dndapplication.R
import com.example.dndapplication.Fragments.SheetsFragment.OnListFragmentInteractionListener
import com.example.dndapplication.Models.Sheet
import kotlinx.android.synthetic.main.fragment_sheets.view.*
import androidx.recyclerview.widget.DiffUtil

class MySheetsRecyclerViewAdapter(
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MySheetsRecyclerViewAdapter.ViewHolder>() {

    private var data: MutableList<Sheet> = ArrayList()

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Sheet
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.mPlayerName.text = item.playerName
        holder.mDndClass.text = item.className

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_sheets, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mPlayerName: TextView = mView.dndNumberId
        val mDndClass: TextView = mView.dnd_class

        override fun toString(): String {
            return super.toString() + " '" + mDndClass.text + "'"
        }
    }

    fun setData(newData: List<Sheet>) {
        if (data != null) {
            val postDiffCallback = PostDiffCallback(data!!, newData)
            val diffResult = DiffUtil.calculateDiff(postDiffCallback)

            data.clear()

            data.addAll(newData)
            diffResult.dispatchUpdatesTo(this)
        } else {
            // first initialization
            data = newData as MutableList<Sheet>
        }
    }

    internal inner class PostDiffCallback(private val oldSheets: List<Sheet>, private val newSheets: List<Sheet>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldSheets.size
        }

        override fun getNewListSize(): Int {
            return newSheets.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldSheets[oldItemPosition].uid === newSheets[newItemPosition].uid
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldSheets[oldItemPosition] == newSheets[newItemPosition]
        }
    }

}