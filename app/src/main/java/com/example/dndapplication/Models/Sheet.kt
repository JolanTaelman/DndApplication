package com.example.dndapplication.Models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Sheet(
    @field:Json(name = "Id") var uid: Int?,
    @field:Json(name = "Player-name") var playerName: String?,
    @field:Json(name = "Character-Name") var characterName: String?,
    @field:Json(name = "Background") var background: String?,
    @field:Json(name = "Race") var race: String?,
    @field:Json(name = "Alignment") var alignment: String?,
    @field:Json(name = "Classname") var className: String?,
    @field:Json(name = "Hit-Die") var hit_die: Int,
    @field:Json(name = "Class-Levels") var class_levels: Int,
    @field:Json(name = "Strength") var strength: Int,
    @field:Json(name = "Dexterity") var dexterity: Int,
    @field:Json(name = "Constitution") var constitution: Int,
    @field:Json(name = "Intelligence") var intelligence: Int,
    @field:Json(name = "Wisdom") var wisdom: Int,
    @field:Json(name = "Charisma") var charisma: Int,
    @field:Json(name = "Armor-class") var armorClass: Int,
    @field:Json(name = "Speed") var speed: Int,
    @field:Json(name = "Health-points") var hp: Int
): Parcelable {
}

