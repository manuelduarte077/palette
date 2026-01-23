package dev.donmanuel.app.palette.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.donmanuel.app.palette.components.ColorCard
import dev.donmanuel.app.palette.components.MainSlider
import dev.donmanuel.app.palette.copyToClipboard
import dev.donmanuel.app.palette.viewmodels.ColorViewModel
import org.jetbrains.compose.resources.painterResource
import palette.composeapp.generated.resources.Res
import palette.composeapp.generated.resources.palette

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(viewModel: ColorViewModel = viewModel { ColorViewModel() }) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                {
                    Row {
                        Image(
                            painterResource(Res.drawable.palette),
                            "Logo",
                            modifier = Modifier.height(26.dp)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                {
                    viewModel.generateColor()
                },
                containerColor = Color.Black,
                contentColor = Color.White,
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) { innerPadding ->
        ContentHomeView(modifier = Modifier.padding(innerPadding))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentHomeView(
    modifier: Modifier,
    viewModel: ColorViewModel = viewModel { ColorViewModel() }
) {
    val colors by viewModel.colors.collectAsState()

    val modalState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    var showModal by remember { mutableStateOf(false) }
    var red by remember { mutableStateOf(0f) }
    var green by remember { mutableStateOf(0f) }
    var blue by remember { mutableStateOf(0f) }
    var id by remember { mutableStateOf(0) }

    LazyColumn(modifier) {
        items(colors) { color ->
            ColorCard(
                color.hex,
                color.rgb,
                {
                    red = color.red.toFloat()
                    green = color.green.toFloat()
                    blue = color.blue.toFloat()
                    id = color.id

                    showModal = true
                },
                {
                    viewModel.removeColorById(color.id)
                },
                {
                    copyToClipboard(color.rgb)
                },
            )
        }
    }

    if (showModal) {
        ModalBottomSheet(
            onDismissRequest = { showModal },
            sheetState = modalState
        ) {

            Column(
                modifier = Modifier
                    .padding(40.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MainSlider(
                    value = red,
                    onValueChange = { red = it },
                    color = Color.Red,
                )

                MainSlider(
                    value = green,
                    onValueChange = { green = it },
                    color = Color.Blue,
                )

                MainSlider(
                    value = blue,
                    onValueChange = { blue = it },
                    color = Color.Blue,
                )
            }

        }
    }
}