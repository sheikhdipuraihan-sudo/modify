package com.modify.music.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerPlaceholder(
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    darkTheme: Boolean = false
) {
    val shimmerColors = if (darkTheme) {
        listOf(
            Color(0xFF3A3A3A),
            Color(0xFF4A4A4A),
            Color(0xFF3A3A3A)
        )
    } else {
        listOf(
            Color(0xFFE0E0E0),
            Color(0xFFF5F5F5),
            Color(0xFFE0E0E0)
        )
    }

    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerProgress"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim, y = translateAnim)
    )

    Box(
        modifier = modifier
            .clip(shape)
            .background(brush)
    )
}

@Composable
fun ShimmerSongCard(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = false
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ShimmerPlaceholder(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            shape = RoundedCornerShape(12.dp),
            darkTheme = darkTheme
        )
        ShimmerPlaceholder(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(16.dp),
            shape = RoundedCornerShape(4.dp),
            darkTheme = darkTheme
        )
        ShimmerPlaceholder(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(12.dp),
            shape = RoundedCornerShape(4.dp),
            darkTheme = darkTheme
        )
    }
}

@Composable
fun ShimmerHorizontalCard(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = false
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShimmerPlaceholder(
            modifier = Modifier
                .size(64.dp)
                .aspectRatio(1f),
            shape = RoundedCornerShape(8.dp),
            darkTheme = darkTheme
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier.weight(1f)
        ) {
            ShimmerPlaceholder(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(16.dp),
                shape = RoundedCornerShape(4.dp),
                darkTheme = darkTheme
            )
            ShimmerPlaceholder(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(12.dp),
                shape = RoundedCornerShape(4.dp),
                darkTheme = darkTheme
            )
        }
    }
}

@Composable
fun ShimmerPlayerBar(
    modifier: Modifier = Modifier,
    darkTheme: Boolean = false
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ShimmerPlaceholder(
            modifier = Modifier
                .size(48.dp)
                .aspectRatio(1f),
            shape = RoundedCornerShape(8.dp),
            darkTheme = darkTheme
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.weight(1f)
        ) {
            ShimmerPlaceholder(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .height(14.dp),
                shape = RoundedCornerShape(4.dp),
                darkTheme = darkTheme
            )
            ShimmerPlaceholder(
                modifier = Modifier
                    .fillMaxWidth(0.4f)
                    .height(10.dp),
                shape = RoundedCornerShape(4.dp),
                darkTheme = darkTheme
            )
        }
        ShimmerPlaceholder(
            modifier = Modifier
                .size(32.dp)
                .aspectRatio(1f),
            shape = RoundedCornerShape(16.dp),
            darkTheme = darkTheme
        )
    }
}
