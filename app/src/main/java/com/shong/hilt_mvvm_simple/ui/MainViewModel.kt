package com.shong.hilt_mvvm_simple.ui

import android.app.Application
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.shong.hilt_mvvm_simple.data.Repository
import com.shong.hilt_mvvm_simple.data.db.entity.ExampleEntity
import com.shong.hilt_mvvm_simple.util.MillisConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository, app : Application) : AndroidViewModel(app) {
    private val TAG = this::class.java.simpleName+"_sHong"

    @Inject lateinit var millisConverter: MillisConverter
    val titleText: ObservableField<String> = ObservableField("<Simple MVVM Example>")
    val resultText: ObservableField<String> = ObservableField("")
    val idText: ObservableField<String> = ObservableField("")

    fun insert(){
        val idBuf = idText.get() ?: return
        try {
            insertExampleEntity(idBuf.toInt(), System.currentTimeMillis())
        } catch (e: Exception) {
            resultText.set("[InsertButton] ${e.localizedMessage}")
            Log.d(TAG, "[InsertButton] Id Error: $e")
        } finally {
            idText.set("")
        }
    }

    fun getAllData(){
        viewModelScope.launch{
            try{
                val items = repository.getAllExampleEntity()
                val map = mutableMapOf<Int, Long>()
                for (ex in items)
                    map.put(ex.id, ex.millis)

                var str = "<DB>\n"
                for (id in map.keys)
                    str += "[id : $id] Time<${millisConverter.millisToTime(map[id] ?: 0L)}>\n"
                resultText.set(str)

            }catch (e: Exception){
                resultText.set("[GetAllEx] ${e.localizedMessage}")
                Log.d(TAG, "[GetAllEx] $e")
            }
        }
    }

    fun nukeData(){
        viewModelScope.launch{
            try{
                repository.nukeExampleEntity()
                resultText.set("[NukeEx] All Clear Ok")
            }catch (e: Exception){
                resultText.set("[NukeEx] ${e.localizedMessage}")
                Log.d(TAG, "[NukeEx] $e")
            }
        }
    }

    private fun insertExampleEntity(id: Int, millis: Long){
        viewModelScope.launch{
            val ex = ExampleEntity(id, millis)
            try{
                repository.insertExampleEntity(ex)
                resultText.set("[InsertEx] Insert Data Ok")
            }catch (e: Exception){
                resultText.set("[InsertEx] ${e.localizedMessage}")
                Log.d(TAG, "[InsertEx] $e")
            }
        }
    }
}