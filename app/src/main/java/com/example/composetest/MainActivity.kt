package com.example.composetest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var strflow : MutableSharedFlow<String> = MutableSharedFlow()

        val parent = findViewById<ConstraintLayout>(R.id.parent)
        var str :String = ""
        parent.apply {
            setContent {
                LazyColumn {
                    for (i in 0..50) {

                        item {

                            TextField(value = strflow.collectAsState(initial = "").value,
                                onValueChange = {
                                    GlobalScope.launch {
                                        strflow.emit(it)
                                    }
                                },
                                enabled = true,
                                //color = Color.Companion.Red,
                                modifier = Modifier.fillMaxWidth())
                            //Spacer(modifier = Modifier.height(4.dp))
                            Spacer(modifier = Modifier.height(50.dp))

                        }
                    }
                }
            }
        }

    }
}