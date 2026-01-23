package dev.donmanuel.app.palette.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.donmanuel.app.palette.components.ColorCard
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


@Composable
fun ContentHomeView(
    modifier: Modifier,
    viewModel: ColorViewModel = viewModel { ColorViewModel() }
) {
    val colors by viewModel.colors.collectAsState()

    LazyColumn(modifier) {
        items(colors) { color ->
            ColorCard(
                color.hex,
                color.rgb,
                {},
                {
                    viewModel.removeColorById(color.id)
                },
                {
                    copyToClipboard(color.rgb)
                },
            )
        }
    }
}