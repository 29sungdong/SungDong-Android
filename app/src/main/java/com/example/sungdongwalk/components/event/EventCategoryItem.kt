package com.example.sungdongwalk.components.event

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.sungdongwalk.api.utils.EventCategoryName
import com.example.sungdongwalk.screens.EventCategory
import com.example.sungdongwalk.ui.theme.SDblack
import com.example.sungdongwalk.ui.theme.SDblue
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography
@Composable
fun EventCategoryItem(
    selectedCategory: EventCategory,
    setSelectedCategory: (EventCategory) -> Unit,
    eventCategory: EventCategory
) {
    Card(
    modifier = Modifier
        .width(85.dp)
        .padding(top=12.dp)
        .clickable { setSelectedCategory(eventCategory) },
    shape = RoundedCornerShape(8.dp),
    colors = CardDefaults.cardColors(
    containerColor = if(selectedCategory == eventCategory) SDblue else SDwhite
    ),
    ){
        Text(
            text = when(eventCategory){
                EventCategory.ALL -> EventCategoryName.ALL
                EventCategory.EDUCATION -> EventCategoryName.EDUCATION
                EventCategory.CULTURE -> EventCategoryName.CULTURE
                EventCategory.EXHIBITION -> EventCategoryName.EXHIBITION
                EventCategory.FARM -> EventCategoryName.FARM
                EventCategory.FOREST -> EventCategoryName.FOREST
                EventCategory.PARK -> EventCategoryName.PARK
                EventCategory.VOLUNTEER -> EventCategoryName.VOLUNTEER
            },
            style = Typography.labelLarge,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = if(selectedCategory == eventCategory) SDwhite else SDblack
        )
    }
}