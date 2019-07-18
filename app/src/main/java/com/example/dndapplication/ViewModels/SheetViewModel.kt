package com.example.dndapplication.ViewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.dndapplication.Models.Sheet
import com.example.dndapplication.Network.SheetApi
import com.example.dndapplication.base.BaseViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SheetViewModel : BaseViewModel() {

    private val sheets = MutableLiveData<List<Sheet>>()

    fun getSheets():  MutableLiveData<List<Sheet>>{
        return sheets
    }

    @Inject
    lateinit var sheetApi: SheetApi

    private var subscription: Disposable

    init {
        subscription = sheetApi.getSheets()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{ onRetrieveSheetStart()}
            .doOnTerminate{onRetrieveSheetFinish()}
            .subscribe(
                {result -> onRetrieveSheetsSucces(result)},
                {error -> onRetrieveSheetError(error)}
            )
     }


    private fun onRetrieveSheetError(error: Throwable?) {
        Log.e("unsucces", error!!.message)

    }

    private fun onRetrieveSheetsSucces(result: List<Sheet>?) {
        Log.e("supersucces", result.toString())
        sheets.value = result
        Log.e("RawSheet value:", sheets.value?.get(0)?.characterName)
    }


    private fun onRetrieveSheetStart() {
       // loadingVisibility.value = true
    }

    private fun onRetrieveSheetFinish() {
       // loadingVisibility.value = false
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}
