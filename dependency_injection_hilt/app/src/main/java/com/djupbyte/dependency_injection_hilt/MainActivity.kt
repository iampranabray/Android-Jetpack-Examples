package com.djupbyte.dependency_injection_hilt

import CounterUI
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.djupbyte.dependency_injection_hilt.hilt.counter_example.viewmodels.CounterViewModel
import com.djupbyte.dependency_injection_hilt.hilt.counter_example.viewmodels.DataCountUIState
import com.djupbyte.dependency_injection_hilt.ui.theme.TestAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //@Inject
    // lateinit var myDependency: MyDependency
    private val viewModel: CounterViewModel by viewModels()

    @SuppressLint("StateFlowValueCalledInComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var uiState: DataCountUIState by mutableStateOf(DataCountUIState.Loading)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.settingsUiState
                    .onEach {
                        uiState = it
                    }
                    .collect()

            }
        }

        setContent {
            val counter = Count(counterModel = uiState)
            //println("counter value: $uiState")
            TestAppTheme {
                Column {
                    Text("$counter")
                    CounterUI()
                }

            }
        }
    }
}

@Composable
private fun Count(
    counterModel: DataCountUIState
): Int = when (counterModel) {
    DataCountUIState.Loading -> 1
    is DataCountUIState.Success -> counterModel.countData.count
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestAppTheme {
        Greeting("Android")
    }
}