package com.example.dndapplication.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.dndapplication.Models.Sheet

import com.example.dndapplication.R


const val SHEET = "sheet-id"
class SheetDetail : Fragment() {

    private var sheet: Sheet? = null

    /**
     * sets the sheet from the provided bundle arguments
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            sheet = it.getParcelable(SHEET)
        }
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v: View = inflater.inflate(R.layout.sheet_detail_fragment, container, false)
        val charName: TextView = v.findViewById(R.id.CharacterNameID)

        val healthPoints: TextView = v.findViewById(R.id.HP_Value_Id)
        val armorClass: TextView = v.findViewById(R.id.ACValueId)
        val speed: TextView = v.findViewById(R.id.Speed_Value_Id)

        val race: TextView = v.findViewById(R.id.RaceValueId)
        val background: TextView = v.findViewById(R.id.BackgroundValueId)
        val alignment: TextView = v.findViewById(R.id.AlignmentValueId)

        val strength: TextView = v.findViewById(R.id.StrValueId)
        val dexterity: TextView = v.findViewById(R.id.DexValueID)
        val constitution: TextView = v.findViewById(R.id.ConValueId)
        val wisdom: TextView = v.findViewById(R.id.WisValueId)
        val intelligence: TextView = v.findViewById(R.id.IntValueId)
        val charisma: TextView = v.findViewById(R.id.ChaValueId)

        val level: TextView = v.findViewById(R.id.LvlValueId)
        val className: TextView = v.findViewById(R.id.ClassNameLabelId)
        val hitDie: TextView = v.findViewById(R.id.HitDieValueID)

        level.text = sheet?.class_levels.toString()
        className.text = sheet?.className
        hitDie.text = sheet?.hit_die.toString()

        strength.text = sheet?.strength.toString()
        dexterity.text = sheet?.dexterity.toString()
        constitution.text = sheet?.constitution.toString()
        wisdom.text = sheet?.wisdom.toString()
        intelligence.text = sheet?.intelligence.toString()
        charisma.text = sheet?.charisma.toString()

        healthPoints.text = sheet?.hp.toString()
        armorClass.text = sheet?.armorClass.toString()
        speed.text = sheet?.speed.toString()

        race.text = sheet?.race
        background.text = sheet?.background
        alignment.text = sheet?.alignment

        charName.text = sheet?.characterName

        return v
    }

    companion object {
        @JvmStatic
        fun newInstance(Sheet: Sheet) =
            SheetDetail().apply {
                arguments = Bundle().apply {
                    putParcelable(SHEET, Sheet)
                }
            }
    }
}
