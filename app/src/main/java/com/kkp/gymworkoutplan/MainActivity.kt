package com.kkp.gymworkoutplan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.api.LogDescriptor
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kkp.gymworkoutplan.databinding.ActivityMainBinding
import com.kkp.gymworkoutplan.frags.MixFragment
import com.kkp.gymworkoutplan.frags.PplFramgent
import com.kkp.gymworkoutplan.frags.SplitFragment
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import java.lang.IndexOutOfBoundsException


data class MemberAccount(val name : String ="", val deadliftPR : Float = 0f,
                         val chestPressPR : Float =0f, val squatPR : Float = 0f )

class MainActivity : AppCompatActivity() {
    var TAG = "Main Activity"
    private lateinit var binding: ActivityMainBinding
    private lateinit var dbWorkoutPlan : com.google.firebase.firestore.DocumentReference
    private lateinit var viewModel : WorkoutViewModel
    var members: MutableList<MemberAccount>? = mutableListOf()
    val pplFragment = PplFramgent()
    val splitFragment = SplitFragment()
    val mixFragment = MixFragment()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            getBothUsers()
            withContext(Dispatchers.Main){
                setCurrentFragment(pplFragment)
            }
        }

    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launchWhenStarted {
            changeUser()
            binding.bottomNavigationView.setOnNavigationItemSelectedListener {
                when(it.itemId){
                    R.id.miPPLegs -> setCurrentFragment(pplFragment)
                    R.id.miSPLIT -> setCurrentFragment(splitFragment)
                    R.id.miMix -> setCurrentFragment(mixFragment)
                }
                true
            }
        }

    }

    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, fragment)
            commit()
        }
    }

    suspend fun getBothUsers() {
        val dbWorkoutPlan = Firebase.firestore.collection("Coroutines")
        lifecycleScope.launchWhenStarted {
            withContext(Dispatchers.IO) {
                members = dbWorkoutPlan.get().await().toObjects(MemberAccount::class.java)
                viewModel.writeMembers(members as MutableList<MemberAccount>)
                withContext(Dispatchers.Main){
                    try {
                        binding.tvAccName.text = "${members?.get(0)?.name ?: "Twoj trening"}"
                    }catch (e : Exception) {
                        if (e is CancellationException){
                            throw e
                        }
                        binding.tvAccName.text = "Błędna ścieżka $e"
                    }
                }
            }
        }
    }
    suspend fun changeUser() {
        var memberVsBuddy = 0
        lifecycleScope.launch(Dispatchers.Main) {
            binding.swMemberVsBuddy.setOnCheckedChangeListener { _, isChecked ->
                if (!isChecked) memberVsBuddy=0 else memberVsBuddy=1
                lifecycleScope.launch(Dispatchers.IO){
                    viewModel.getBuddy(memberVsBuddy)
                    Log.d("Buddy1", "${memberVsBuddy}")
                }
            try {
                binding.tvAccName.text = "${members?.get(memberVsBuddy)?.name ?: "Twoj trening"}"
                }catch (e : Exception) {
                    if (e is CancellationException){
                        throw e
                    }
                    binding.swMemberVsBuddy.apply{
                        this.visibility = View.INVISIBLE
                        this.isChecked = false
                    }
                    Toast.makeText(applicationContext,"No buddy, sorry",Toast.LENGTH_SHORT).show()
                    Log.d("CorMain", "changeUser: $e")
                }
            }
        }
    }
}
