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


class MySheetsRecyclerViewAdapter(
    private val mValues: List<Sheet>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<MySheetsRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Sheet
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_sheets, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mPlayerName.text = item.playerName
        holder.mDndClass.text = item.className

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mPlayerName: TextView = mView.player_name
        val mDndClass: TextView = mView.dnd_class

        override fun toString(): String {
            return super.toString() + " '" + mDndClass.text + "'"
        }
    }
}
