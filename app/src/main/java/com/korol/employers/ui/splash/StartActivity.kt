package com.korol.employers.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.korol.employers.api.Common
import com.korol.employers.app.App
import com.korol.employers.databinding.ActivitySplashBinding
import com.korol.employers.models.EmployersType
import com.korol.employers.ui.work.WorkActivity

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    @javax.inject.Inject
    lateinit var vmFactory: SplashViewModelFactory

    private lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        (applicationContext as App).appComponent.injectSplashActivity(this)

        splashViewModel =
            ViewModelProvider(this, vmFactory).get(SplashViewModel::class.java)


        splashViewModel.error.observe(this) {
           if (it) {
            binding.progressBar.visibility = View.GONE
            binding.tvError.visibility = View.VISIBLE
            binding.btnRetry.visibility =View.VISIBLE
           } else {
               binding.progressBar.visibility = View.VISIBLE
               binding.tvError.visibility = View.GONE
               binding.btnRetry.visibility =View.GONE
               
           }
        }
        
        splashViewModel.startWorkActivity.observe(this) {
            if (it) {
                val intent = Intent(this, WorkActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.btnRetry.setOnClickListener {
            splashViewModel.getEmployersType()
        }


    }
}