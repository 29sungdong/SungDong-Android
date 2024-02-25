package com.example.sungdongwalk.components.navigator_bottom

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sungdongwalk.components.DimLoading
import com.example.sungdongwalk.screens.EventScreen
import com.example.sungdongwalk.screens.HomeScreen
import com.example.sungdongwalk.screens.MapScreen
import com.example.sungdongwalk.ui.theme.Gray500
import com.example.sungdongwalk.ui.theme.SDblack
import com.example.sungdongwalk.ui.theme.SDwhite
import com.example.sungdongwalk.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNav(navController: NavController){
    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = SDwhite
            ) {
                val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                listOfNavItems.forEach{ navItem ->
                    NavigationBarItem(
                        label = {
                            Text(
                                text = navItem.label,
                                style = Typography.displayLarge
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = SDblack,
                            selectedTextColor = SDblack,
                            unselectedIconColor = Gray500,
                            unselectedTextColor = Gray500,
                            indicatorColor = SDwhite
                        ),
                        selected = currentDestination?.hierarchy?.any{it.route == navItem.route} == true,
                        onClick = {
                                  bottomNavController.navigate(navItem.route){
                                      popUpTo(bottomNavController.graph.findStartDestination().id){
                                          saveState = true
                                      }
                                      launchSingleTop = true
                                      restoreState = true
                                  }
                        },
                        icon = { Icon(
                            painter = painterResource(id = navItem.icon),
                            contentDescription = ""
                        ) }
                    )
                }
            }
        }
    ){ paddingValue ->
        val (isLoading, setIsLoading) = remember{
            mutableStateOf(false)
        }
        NavHost(
            navController = bottomNavController,
            startDestination = BottomNavScreens.HomeScreen.name,
            modifier = Modifier
                .padding(paddingValue)
        ){
            composable(route = BottomNavScreens.HomeScreen.name){
                HomeScreen(setIsLoading)
            }
            composable(route = BottomNavScreens.MapScreen.name){
                MapScreen(setIsLoading)
            }
            composable(route = BottomNavScreens.EventScreen.name){
                EventScreen()
            }
        }
        if(isLoading)
            DimLoading()
    }
}