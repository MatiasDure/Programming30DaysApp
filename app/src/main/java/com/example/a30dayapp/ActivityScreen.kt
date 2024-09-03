package com.example.a30dayapp

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun ActivityDescriptionButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if(expanded) Icons.Filled.ExpandLess  else Icons.Filled.ExpandMore,
            contentDescription = null
        )
    }
}

@Composable
fun ActivityItem(
    programmingActivity: ProgrammingActivity,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_medium))
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp)),
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .width(dimensionResource(id = R.dimen.activity_width))
                .align(Alignment.CenterHorizontally)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.day, programmingActivity.day)
                )
                Spacer(modifier = Modifier.weight(1f))
                ActivityDescriptionButton(expanded, { expanded = !expanded})
            }

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
            if(expanded) {
                Text(
                    text = stringResource(id = programmingActivity.descriptionRes),
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
            }

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
    ProgrammingDaysAppTheme(darkTheme = true) {
        ProgrammingDaysApp()
//        ActivityItem(ActivityData.programmingActivities[0],"1");
    }
}