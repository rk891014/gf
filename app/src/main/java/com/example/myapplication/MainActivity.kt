package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val simplelists = getsimpledata()
        populatelist(simplelists)


    }

    private fun populatelist(simpleModels: MutableList<SimpleModel>) {
        binding.recyclerview.withModels {
            simpleModels.forEachIndexed { position, model ->
                when (model.type) {
                    "Header" -> header {
                        id(position)
                        headerContent("Pos:$position ${model.content}")
                    }
                    "Content" -> content {
                        id(position)
                        simpleModel(model)
                        onClickContent { _ ->
                            Toast.makeText(this@MainActivity, model.content, Toast.LENGTH_SHORT).show()
                            simpleModels.removeAt(position)
                            populatelist(simpleModels)
                        }
                    }
                    "Footer" -> footer {
                        id(position)
                        footerContent("$position ${model.content}")
                    }
                }
            }
        }
    }
}