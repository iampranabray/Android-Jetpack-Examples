import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.djupbyte.dependency_injection_hilt.Greeting
import com.djupbyte.dependency_injection_hilt.hilt.counter_example.viewmodels.CounterViewModel
import com.djupbyte.dependency_injection_hilt.hilt.counter_example.viewmodels.DataCountUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterUI(
    counterViewModel: CounterViewModel = hiltViewModel()
) {
    //val myDependency = hiltViewModel<CounterViewModel>()
    val counterModel by counterViewModel.settingsUiState.collectAsStateWithLifecycle()

    val counter = CountUI(counterModel = counterModel)
    Scaffold(

    ) { padding ->
        Column(
            Modifier
                .padding(padding)
        ) {
            Greeting("$counter")
            Button(onClick = { counterViewModel.doSomething() }) {
                Text("Click")
            }
        }

    }
}

@Composable
private fun CountUI(
    counterModel: DataCountUIState
): Int = when (counterModel) {
    DataCountUIState.Loading -> 0
    is DataCountUIState.Success -> counterModel.countData.count
}