package com.kkp.gymworkoutplan

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kkp.gymworkoutplan.Adapters.PplExec
import com.kkp.gymworkoutplan.Adapters.PplExecAdapter
import com.kkp.gymworkoutplan.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WorkoutViewModel (application: Application) : AndroidViewModel(application) {
    private val _membersList = MutableStateFlow(mutableListOf<MemberAccount>())
    val membersList = _membersList.asStateFlow()
    private val _buddyState = MutableStateFlow(0)
    val buddyState = _buddyState.asStateFlow()
    private val _position = MutableStateFlow(0)
    val position = _position.asStateFlow()
    private val _exerList = MutableStateFlow(newExeList())
    val exerList = _exerList.asStateFlow()







    suspend fun getBuddy(buddyState : Int){
        viewModelScope.launch {
            _buddyState.value = buddyState
        }
    }

    suspend fun writeMembers(memberList : MutableList<MemberAccount>){
        viewModelScope.launch {
            _membersList.value = memberList
        }

    }

    suspend fun updatePosition(position : Int){
        viewModelScope.launch {
            _position.value = position
        }
    }
    
   fun newExeList() : MutableList<PplExec> {
       var temp = mutableListOf<PplExec>()
       getApplication<Application>().resources
           .getStringArray(R.array.Push).forEachIndexed { index, value ->
                temp.add(PplExec(value))
           }
       return temp
   }

    suspend fun updateExeList(position: Int){
        var temp = _exerList.value
        viewModelScope.launch {
           temp[position].progress = temp[position].progress + 25
        }
        _exerList.value = temp
    }


}