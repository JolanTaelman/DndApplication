package com.example.dndapplication.Fragments

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dndapplication.Adapters.ClassListAdapter
import com.example.dndapplication.Models.DndClass
import com.example.dndapplication.Models.Sheet

import com.example.dndapplication.R
import com.example.dndapplication.ViewModels.SheetAddViewModel
import kotlinx.android.synthetic.main.sheet_add_fragment.*

const val PLAYERNAME = "player-name"

class SheetAddFragment : Fragment(), View.OnClickListener {

    private lateinit var viewModel: SheetAddViewModel

    private var listener: OnClassListFragmentInteractionListener? = null
    private var dndClass: DndClass? = null
    private var playerName: String? = null

    /**
     * Sets up the fragment and seeds the classes into the database
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(SheetAddViewModel::class.java)

        val rootView = inflater.inflate(R.layout.sheet_add_fragment, container, false)

        viewModel.insert(DndClass(1, "Paladin", 10, 16))
        viewModel.insert(DndClass(2, "Druid", 8, 14))
        viewModel.insert(DndClass(3, "Rogue", 8, 13))
        viewModel.insert(DndClass(4, "Fighter", 10, 15))
        viewModel.insert(DndClass(5, "Sorcerer", 6, 13))

        val classRecyclerView = rootView.findViewById(R.id.ClassRecyclerViewId) as RecyclerView
        classRecyclerView.layoutManager = LinearLayoutManager(context)
        classRecyclerView.adapter = ClassListAdapter(listener)
        val dividerItemDecoration = DividerItemDecoration(
            context, DividerItemDecoration.VERTICAL
        )
        classRecyclerView.addItemDecoration(dividerItemDecoration)

        viewModel.classList.observe(this, Observer {
            Log.i("MainFragment", "Update for classes: $it")

            it?.let { classes ->
                (classRecyclerView.adapter as ClassListAdapter).setData(classes)
            }
        })

        val b = rootView.findViewById(R.id.AddSheetButtonId) as Button

        b.setOnClickListener(this)

        return rootView
    }

    /**
     * sets the playerName from the provided bundle arguments ;
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            playerName = it.getString(PLAYERNAME)
        }
    }



    override fun onClick(v: View?) {
        val charName: String =  AddCharacterNameId.text.toString()
        val strVar: Int =  Integer.parseInt(AddCharacterStrId.text.toString())
        val dexVar: Int =  Integer.parseInt(AddCharacterDexId.text.toString())
        val conVar: Int =  Integer.parseInt(AddCharacterConId.text.toString())
        val intVar: Int =  Integer.parseInt(AddCharacterIntId.text.toString())
        val wisVar: Int =  Integer.parseInt(AddCharacterWisId.text.toString())
        val chaVar: Int =  Integer.parseInt(AddCharacterChaId.text.toString())
        val race: String =  AddsheetRaceId.text.toString()
        val alignment: String = AddSheetAlignmentId.text.toString()

        val dndClassname: String = this.dndClass!!.name.toString()
        val dndClassAC: Int = this.dndClass!!.AC
        val dndClassHitDice: Int = this.dndClass!!.hit_die
        val background: String = SheetAddBackgroundId.text.toString()
        val dndClassLevel: Int =  Integer.parseInt(SheetAddLevelId.text.toString())

        val hp = dndClassLevel * dndClassHitDice

        val newSheet = Sheet("", playerName, charName, background, race, alignment,dndClassname, dndClassHitDice,  dndClassLevel, strVar, dexVar, conVar, intVar, wisVar, chaVar, dndClassAC, 30, hp  )

        viewModel.postSheet(newSheet)

        listener?.returnHome()
        }

    interface OnClassListFragmentInteractionListener {
        fun onListFragmentInteraction(item: DndClass?)

        fun returnHome()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnClassListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnLoginFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    fun setDndClass(item: DndClass) {
        currentClassID.text = item.name
        this.dndClass = item
    }

    companion object {

        fun newInstance(playerName : String) = SheetAddFragment()
            .apply {
                arguments = Bundle().apply {
                    putString(PLAYERNAME, playerName)
                }
            }
    }
}
