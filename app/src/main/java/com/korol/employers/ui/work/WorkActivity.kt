package com.korol.employers.ui.work

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.korol.employers.R
import com.korol.employers.databinding.ActivityWorkBinding

class WorkActivity : AppCompatActivity() {
    private lateinit var binding:ActivityWorkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityWorkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val host: NavHostFragment =supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
    }

}