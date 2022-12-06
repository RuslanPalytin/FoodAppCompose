package com.example.foodapp.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodapp.data.FoodModel
import com.example.foodapp.ui.theme.GrayLite1
import com.example.foodapp.ui.theme.Italiano
import com.example.foodapp.ui.theme.NutinoRegular
import com.example.foodapp.ui.theme.Orange
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<FoodModel>, pagerState: PagerState, search: MutableState<String>) {

    val isVisible = remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()

    isVisible.value = search.value == ""

    if (isVisible.value) {
        TabRow(
            modifier = Modifier
                .background(color = GrayLite1)
                .padding(horizontal = 16.dp),
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = GrayLite1,
            contentColor = Orange,
            indicator = { tabsPosition ->
                TabRowDefaults.Indicator(
                    color = Orange,
                    modifier = Modifier
                        .pagerTabIndicatorOffset(pagerState, tabsPosition)
                        .height(3.dp)
                )
            }
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    modifier = Modifier
                        .clip(RoundedCornerShape(40.dp))
                        .background(color = GrayLite1),
                    text = {
                        Text(
                            text = tab.category,
                            fontFamily = NutinoRegular,
                            fontWeight = FontWeight.Bold,
                            fontSize = 17.sp,
                            color = if (pagerState.currentPage == index) Orange else Color.Gray
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
    } else {
        Text(
            text = "Result",
            modifier = Modifier
                .fillMaxWidth()
                .background(color = GrayLite1)
                .padding(start = 16.dp),
            fontFamily = Italiano,
            fontSize = 48.sp
        )
    }
}