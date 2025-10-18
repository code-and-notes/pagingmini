package com.android.learning.pagingmini.ui


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import com.android.learning.pagingmini.R
import com.android.learning.pagingmini.db.data.ProductDimensions
import com.android.learning.pagingmini.db.entities.ProductDTO
import kotlin.random.Random


@Composable
fun ProductsScreen(
    modifier: Modifier = Modifier,
    productsList: LazyPagingItems<ProductDTO>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Top
    ) {
        items(productsList.itemCount){ count ->
            val item = productsList[count]
            if(item!= null )ProductsScreenItem(productDTO = item)
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ProductsScreenItem(productDTO: ProductDTO) {

    Row(
        modifier = Modifier.padding(5.dp),
        horizontalArrangement = Arrangement.Start

    ){
        AsyncImage(
            model = productDTO.thumbnail,
            contentDescription = "Product Image",
            placeholder = painterResource(id = R.drawable.ic_launcher_background),
            modifier = Modifier.padding(5.dp).weight(1f),
            contentScale = ContentScale.Fit
        )
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.weight(2f)
        ) {
            Text(text = productDTO.title, fontSize = 30.sp, color = Color.Black, maxLines = 1)
            Text(text = productDTO.description, maxLines = 2, color = Color.Gray, fontSize = 15.sp)
            Text(text = "${String.format("%.2f", productDTO.price)} - with ${String.format("%.2f", productDTO.discountPercentage)}% OFF", fontSize = 15.sp, color = Color.Black)

        }
    }

}


val tempProduct =  ProductDTO(
        id = 0, // Let Room autogenerate
        title = "Random Product ${Random.nextInt(1000)}",
        description = "A random product for testing.something and this is something",
        category = "test-category",
        price = Random.nextDouble(1.0, 100.0),
        discountPercentage = Random.nextDouble(0.0, 50.0),
        rating = Random.nextDouble(1.0, 5.0),
        stock = Random.nextInt(1, 100),
        tags = listOf("tag1", "tag2", "tag${Random.nextInt(100)}"),
        brand = "Brand${Random.nextInt(10)}",
        sku = "SKU${Random.nextInt(10000)}",
        weight = Random.nextInt(1, 10),
        dimensions = ProductDimensions(
            width = Random.nextDouble(1.0, 20.0),
            height = Random.nextDouble(1.0, 20.0),
            depth = Random.nextDouble(1.0, 20.0)
        ),
        warrantyInformation = "Warranty ${Random.nextInt(1, 12)} months",
        shippingInformation = "Ships in ${Random.nextInt(1, 7)} days",
        availabilityStatus = "In Stock",
        returnPolicy = "No returns",
        minimumOrderQuantity = Random.nextInt(1, 10),
        images = listOf("https://example.com/image1.jpg", "https://example.com/image2.jpg"),
        thumbnail = "https://images.unsplash.com/photo-1519125323398-675f0ddb6308?auto=format&fit=crop&w=400&q=80"
    )

@Composable
@Preview(showBackground = true)
fun ProductsScreenPreview() {
    ProductsScreenItem(tempProduct)
}



