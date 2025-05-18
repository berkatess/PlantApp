package com.example.plantapp.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.plantapp.Api.OkhttpClient
import com.example.plantapp.Model.PlantModel
import com.example.plantapp.Model.PlantType
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject


class HomePageViewModel : ViewModel() {

    val TAG = "HomePageViewModel"

    val plantModelStatusList = MutableLiveData<List<PlantModel>>()  // questions
    val plantModelCategoryList = MutableLiveData<List<PlantModel>>() // categories

    fun getQuestions() {
        viewModelScope.launch {
            try {
                val response = OkhttpClient.getInstance().getQuestions()
                if (response != null) {
                    val parsedList = parseQuestionsJson(response)
                    plantModelStatusList.postValue(parsedList)
                    Log.i(TAG, "getQuestions Success: $response")
                } else {
                    Log.e(TAG, "getQuestions Failed")
                }
            } catch (e: Exception) {
                Log.e(TAG, "getQuestions Error: ${e.message}")
            }
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            try {
                val response = OkhttpClient.getInstance().getCategories()
                if (response != null) {
                    val parsedList = parseCategoriesJson(response)
                    plantModelCategoryList.postValue(parsedList)
                    Log.i(TAG, "getCategories Success: $response")
                } else {
                    Log.e(TAG, "getCategories Failed")
                }
            } catch (e: Exception) {
                Log.e(TAG, "getCategories Error: ${e.message}")
            }
        }
    }

    private fun parseQuestionsJson(jsonString: String): List<PlantModel> {
        val list = mutableListOf<PlantModel>()
        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val item = jsonArray.getJSONObject(i)

            val name = item.optString("title")
            val imageUrl = item.optString("image_uri")

            list.add(
                PlantModel(
                    name = name,
                    imageUrl = imageUrl,
                    type = PlantType.QUESTION
                )
            )
        }

        return list
    }

    private fun parseCategoriesJson(jsonString: String): List<PlantModel> {
        val list = mutableListOf<PlantModel>()

        val root = JSONObject(jsonString)
        val dataArray = root.getJSONArray("data")

        for (i in 0 until dataArray.length()) {
            val item = dataArray.getJSONObject(i)

            val name = item.optString("title")
            val imageObject = item.optJSONObject("image")
            val imageUrl = imageObject?.optString("url") ?: ""

            list.add(
                PlantModel(
                    name = name,
                    imageUrl = imageUrl,
                    type = PlantType.CATEGORY
                )
            )
        }

        return list
    }
}

