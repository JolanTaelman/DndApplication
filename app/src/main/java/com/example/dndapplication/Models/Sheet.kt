package com.example.dndapplication.Models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sheet(
    @field:Json(name = "_id") var uid: String?,
    @field:Json(name = "playerName") var playerName: String?,
    @field:Json(name = "characterName") var characterName: String?,
    @field:Json(name = "background") var background: String?,
    @field:Json(name = "race") var race: String?,
    @field:Json(name = "alignment") var alignment: String?,
    @field:Json(name = "classname") var className: String?,
    @field:Json(name = "hitDie") var hit_die: Int,
    @field:Json(name = "classLevels") var class_levels: Int,
    @field:Json(name = "strength") var strength: Int,
    @field:Json(name = "dexterity") var dexterity: Int,
    @field:Json(name = "constitution") var constitution: Int,
    @field:Json(name = "intelligence") var intelligence: Int,
    @field:Json(name = "wisdom") var wisdom: Int,
    @field:Json(name = "charisma") var charisma: Int,
    @field:Json(name = "armorClass") var armorClass: Int,
    @field:Json(name = "speed") var speed: Int,
    @field:Json(name = "healthPoints") var hp: Int
): Parcelable