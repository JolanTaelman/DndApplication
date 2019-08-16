package com.example.dndapplication.Fragments

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dndapplication.R
import androidx.recyclerview.widget.DiffUtil
import com.example.dndapplication.Models.DndClass
import kotlinx.android.synthetic.main.fragment_sheets.view.dnd_class
import kotlinx.android.synthetic.main.fragment_sheets.view.dndNumberId
import kotlinx.android.synthetic.main.row_class.view.*

class ClassListAdapter(private val mListener: SheetAddFragment.OnClassListFragmentInteractionListener?) : RecyclerView.Adapter<ClassListAdapter.ViewHolder>() {

    private var data: MutableList<DndClass> = ArrayList()

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DndClass
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.mDndClass.text = item.name
        holder.mClassAcId.text = item.AC.toString()
        holder.mHitDice.text = item.hit_die.toString()
        holder.mDndNumberId.text = item.id.toString()

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
            .inflate(R.layout.row_class, parent, false)
        return ViewHolder(view)
    }


    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mDndNumberId: TextView = mView.dndNumberId
        var mDndClass: TextView = mView.dnd_class
        var mHitDice: TextView = mView.classHitDiceId
        var mClassAcId: TextView = mView.ClassAcId

        override fun toString(): String {
            return super.toString() + " '" + mDndClass.text + "'"
        }
    }

    fun setData(newData: List<DndClass>) {
        if (data != null) {
            val postDiffCallback = PostDiffCallback(data, newData)
            val diffResult = DiffUtil.calculateDiff(postDiffCallback)
            data.clear()
            data.addAll(newData)
            diffResult.dispatchUpdatesTo(this)
        } else {
            // first initialization
            data = newData as MutableList<DndClass>
        }
    }

    internal inner class PostDiffCallback(private val oldClasses: List<DndClass>, private val newClasses: List<DndClass>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int {
            return oldClasses.size
        }

        override fun getNewListSize(): Int {
            return newClasses.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldClasses[oldItemPosition].id === newClasses[newItemPosition].id
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldClasses[oldItemPosition] == newClasses[newItemPosition]
        }
    }

}