package com.example.memo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.memo.R
import com.example.memo.databinding.ActivityMainBinding
<<<<<<< HEAD:presentation/src/main/java/com/example/memo/ui/MainActivity.kt
import dagger.hilt.android.AndroidEntryPoint
=======
import com.example.memo.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
>>>>>>> 38e9c44b313752fe15efe8e74e7f51ce98e2beb0:app/src/main/java/com/example/memo/ui/MainActivity.kt

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.framelayout, Fragment_Main())
        transaction.commit()

    }

}