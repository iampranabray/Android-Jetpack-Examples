import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.djupbyte.newsan.pdf.downloadAndSavePdf
import com.github.barteksc.pdfviewer.PDFView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File


@Composable
fun PDFViewerScreen() {
    val context1: Context = LocalContext.current

    val pdfUrl =
        "https://dt.andaman.gov.in/epaper/1610202310311885.pdf" // Replace with your PDF URL
    val pdfFileName = "1610202310311885.pdf" // Change to your desired file name

    var pdfUri: String? by remember { mutableStateOf(null) }
    LaunchedEffect(Unit) {
        GlobalScope.launch(Dispatchers.IO) {
            val downloadedUri = downloadAndSavePdf(pdfUrl, pdfFileName, context1)
            downloadedUri?.let { uri ->
                pdfUri = uri
            }
        }

    }

    pdfUri?.let { uri ->
        PdfRenderer(uri)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PdfRenderer(pdfUri: String) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Pdf View")})
            }

    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AndroidView(
                factory = { context ->
                    val adView = PDFView(context, null)
                    adView.fromFile(File(pdfUri))
                        .enableDoubletap(true)
                        .spacing(10)
                        .load()
                    adView

                },
                modifier = Modifier.fillMaxSize()
            )
        }

    }
}


private fun sharePdf(context: Context, uri: Uri) {
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "application/pdf"
        putExtra(Intent.EXTRA_STREAM, uri)
    }

    context.startActivity(Intent.createChooser(shareIntent, "Share PDF"))
}