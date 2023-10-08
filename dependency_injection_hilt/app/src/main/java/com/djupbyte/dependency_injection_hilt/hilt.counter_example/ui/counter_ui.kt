import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.djupbyte.dependency_injection_hilt.Greeting
import com.djupbyte.dependency_injection_hilt.hilt.counter_example.viewmodels.CounterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterUI(
    myDependency: CounterViewModel= hiltViewModel()
){
    //val myDependency = hiltViewModel<CounterViewModel>()
    Scaffold(

    ) {
            padding->
        Column(
            Modifier
                .padding(padding)
        ) {
            Greeting("${myDependency.settingsUiState.collectAsState(1).value}")
            Button(onClick = { myDependency.doSomething() }) {
                Text("Click")
            }
        }

    }
}