package com.example.dndapplication.Models

class Sheet(
    var playerName: String?,
    var background: String?,
    var race: String?,
    var alignment: String?,
    //var dndClass: DndClass?,
    var dndClass: String?,
    var strength: Int,
    var dexterity: Int,
    var constitution: Int,
    var intelligence: Int,
    var wisdom: Int,
    var charisma: Int,
    var armorClass: Int,
    var speed: Int,
    var hp: Int
) {
    var uid: Int = 0
    var experience: Int = 0

    init {
        experience = 0
    }
}
