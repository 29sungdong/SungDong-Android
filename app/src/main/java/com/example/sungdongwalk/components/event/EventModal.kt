package com.example.sungdongwalk.components.event

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.sungdongwalk.R
import com.example.sungdongwalk.api.Dto
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventModal(
    date: String,
    events: List<Dto.SimpleEventVo>,
    isShow: Boolean,
    setIsShow: (Boolean) -> Unit,
    setIsShowWebsite: (Boolean) -> Unit
){
    if(isShow){
        Dialog(
            onDismissRequest = { setIsShow(false)},
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = false
            )
        ) {
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(
                    containerColor = SDwhite
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
            ){
                LazyColumn(
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    stickyHeader {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Icon(
                                painterResource(id = R.drawable.ic_arrow_left),
                                null,
                                modifier = Modifier.clickable { setIsShow(false) }
                            )
                            Text(
                                text = date,
                                style = Typography.titleLarge,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 25.dp)
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_x_small),
                                contentDescription = null,
                                modifier = Modifier.clickable { setIsShow(false) }
                            )
                        }

                    }
                    itemsIndexed(events){ index, event ->
                        EventModalItem(event,setIsShowWebsite)
                        Spacer(modifier = Modifier.height(15.dp))
                }
                }
            }
        }
    }
}