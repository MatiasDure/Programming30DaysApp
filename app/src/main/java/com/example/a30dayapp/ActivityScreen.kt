package com.example.a30dayapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30dayapp.data.ActivityData
import com.example.a30dayapp.model.ProgrammingActivity
import com.example.a30dayapp.ui.theme.ProgrammingDaysAppTheme

@Composable
fun ActivityItem(
    programmingActivity: ProgrammingActivity,
    modifier: Modifier = Modifier
)
{
    Card(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .clip(
                RoundedCornerShape(8.dp)
            )
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Text(
                text = stringResource(id = R.string.day, programmingActivity.day)
            )
            Text(
                text = stringResource(id = programmingActivity.titleRes),
            )
            Image(
                painterResource(id = programmingActivity.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
                    .width(dimensionResource(id = R.dimen.activity_width))
                    .height(dimensionResource(id = R.dimen.activity_height))
                    .padding(vertical = dimensionResource(id = R.dimen.padding_small)),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = stringResource(id = programmingActivity.descriptionRes),
                textAlign = TextAlign.Justify
            )
        }
    }
}

@Composable
fun ActivityList(programmingActivities: List<ProgrammingActivity>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(programmingActivities) { programmingActivity: ProgrammingActivity ->
            ActivityItem(programmingActivity)
        }
    }
}

@Composable
fun ProgrammingDaysApp(modifier: Modifier = Modifier) {
    ActivityList(ActivityData.programmingActivities, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProgrammingDaysAppTheme {
        ProgrammingDaysApp()
//        ActivityItem(ActivityData.programmingActivities[0],"1");
    }
}