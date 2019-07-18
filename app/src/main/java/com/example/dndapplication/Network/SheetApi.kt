package com.example.dndapplication.Network

import com.example.dndapplication.Models.Sheet
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST
import android.icu.lang.UCharacter.GraphemeClusterBreak.T



interface SheetApi {

    @GET("/sheets")
    fun getSheets(): Observable<List<Sheet>>

    @GET("/sheets/{sheetID}")
    fun getSheet(@Path("sheetID") sheetID: String): Observable<Sheet>


    @FormUrlEncoded
    @POST("/sheets")
    fun postSheet(@Field("playerName") playerName: String,
                  @Field("characterName") characterName: String,
                  @Field("background") background: String,
                  @Field("race") race: String,
                  @Field("alignment") alignment: String,
                  @Field("classname") classname: String,
                  @Field("hitDie") hitDie: Int,
                  @Field("classLevels") classLevels: Int,
                  @Field("strength") strength: Int,
                  @Field("dexterity") dexterity: Int,
                  @Field("constitution") constitution: Int,
                  @Field("intelligence") intelligence: Int,
                  @Field("wisdom") wisdom: Int,
                  @Field("charisma") charisma: Int,
                  @Field("armorClass") armorClass: Int,
                  @Field("speed") speed: Int,
                  @Field("healthPoints") healthPoints: Int
                  ): Observable<Sheet>


}
