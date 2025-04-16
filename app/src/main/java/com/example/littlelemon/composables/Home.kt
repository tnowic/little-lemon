package com.example.littlelemon.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import body1
import bodyLarge
import button
import coil.compose.AsyncImage
import com.example.littlelemon.MenuItemRoom
import com.example.littlelemon.R
import com.example.littlelemon.compsables.Header
import com.example.littlelemon.ui.theme.Shapes
import com.example.littlelemon.ui.theme.charcoal
import com.example.littlelemon.ui.theme.cloud
import com.example.littlelemon.ui.theme.green
import com.example.littlelemon.ui.theme.grey
import com.example.littlelemon.ui.theme.yellow
import h1
import h2
import title


@Composable
fun Home(navController: NavController, menuItemsLiveData: LiveData<List<MenuItemRoom>>) {

    val searchPhrase = remember { mutableStateOf("") }
    val databaseMenuItems = menuItemsLiveData.observeAsState(listOf()).value
    val categories = databaseMenuItems.map { it.category }.distinct().sorted().toList()
    val selectedCategory = remember { mutableStateOf("") }
    val filteredMenuItems = remember { mutableStateOf(databaseMenuItems) }

    Column {
        Header(showProfileIcon = true, navController = navController)
        HeroSection(searchPhrase)
        MenuBreakdown(categories, selectedCategory)

        if (searchPhrase.value.isNotBlank()) {
            filteredMenuItems.value =
                databaseMenuItems.filter {
                    it.title.contains(
                        searchPhrase.value,
                        ignoreCase = true
                    )
                }
        } else {
            filteredMenuItems.value = databaseMenuItems
        }
        if (selectedCategory.value.isNotBlank()) {
            filteredMenuItems.value =
                filteredMenuItems.value.filter { it.category == selectedCategory.value }
        }

        MenuItems(filteredMenuItems)
    }
}

@Composable
fun HeroSection(searchPhrase: MutableState<String>) {
    Column(
        modifier = Modifier
            .background(green)
            .padding(start = 12.dp, end = 12.dp, top = 16.dp, bottom = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.title),
            style = h1,
            color = yellow
        )
        Text(
            text = stringResource(id = R.string.location),
            style = h2,
            fontSize = 24.sp,
            color = cloud,
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(top = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.description),
                style = bodyLarge,
                color = cloud,
                modifier = Modifier
                    .padding(bottom = 28.dp, end = 20.dp)
                    .fillMaxWidth(0.6f)

            )
            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero Section Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(shape = Shapes.medium),
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = green)
                .padding(bottom = 16.dp)
        ) {
            TextField(
                value = searchPhrase.value,
                onValueChange = { searchPhrase.value = it },
                placeholder = {
                    Text(stringResource(R.string.enterSearchPhrase))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
                    .clip(shape = Shapes.medium),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = ""
                    )
                },
                singleLine = true,
            )
        }
    }
}

@Composable
fun MenuBreakdown(categories: List<String>, selectedCategory: MutableState<String>) {
    Box(modifier = Modifier.border(BorderStroke(1.dp, grey))) {
        Text(
            style = title,
            modifier = Modifier.padding(start = 20.dp, top = 10.dp),
            text = stringResource(R.string.orderForDelivery)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 35.dp, bottom = 10.dp),
            state = rememberLazyListState(),
            contentPadding = PaddingValues(10.dp),
            reverseLayout = false,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            flingBehavior = ScrollableDefaults.flingBehavior(),
            userScrollEnabled = true
        ) {
            itemsIndexed(categories) { _, item ->
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    shape = Shapes.extraLarge,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = if (selectedCategory.value == item) cloud else charcoal,
                        containerColor = if (selectedCategory.value == item) green else grey
                    ),
                    onClick = {
                        selectedCategory.value = item
                    }
                )
                {
                    Text(
                        style = button,
                        text = item.capitalize(Locale.current)
                    )
                }
            }
        }
    }
}


@Composable
fun MenuItems(databaseMenuItems: MutableState<List<MenuItemRoom>>) {
    LazyColumn {
        itemsIndexed(databaseMenuItems.value) { _, item ->
            MenuItem(item)
        }
    }
}

@Composable
fun MenuItem(item: MenuItemRoom) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column {
            Text(
                text = item.title,
                style = title
            )
            Text(
                text = item.description,
                style = body1,
                modifier = Modifier
                    .fillMaxWidth(.75f)
                    .padding(top = 6.dp, bottom = 5.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "$${item.price}",
                style = h2
            )
        }
        AsyncImage(
            model = item.imageUrl,
            contentDescription = null,
            modifier = Modifier.size(100.dp, 100.dp),
            contentScale = ContentScale.Crop,
        )

    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        thickness = 1.dp,
        color = yellow
    )
}

@Preview(showBackground = true)
@Composable
fun HeroSectionPreview() {
    val searchPhrase = remember { mutableStateOf("Greek") }
    HeroSection(searchPhrase)
}

@Preview(showBackground = true)
@Composable
fun MenuBreakdownPreview() {
    val selectedCategory = remember { mutableStateOf("mains") }
    MenuBreakdown(listOf("desserts", "mains", "starters", "drinks"), selectedCategory)
}

@Preview(showBackground = true)
@Composable
fun MenuItemPreview() {
    MenuItem(
        item = MenuItemRoom(
            id = 0,
            title = "a dish",
            price = 10.0,
            description = "dish description",
            imageUrl = "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/lemonDessert%202.jpg?raw=true",
            category = "category"
        )
    )
}



