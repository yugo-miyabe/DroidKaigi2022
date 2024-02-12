package com.example.jetsurvey.util

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * コンテンツの幅を水平方向に中央揃えで最大 840 dp にすることで、ワイド スクリーンをサポートします。
 */
fun Modifier.supportWideScreen() = this
    .fillMaxHeight()
    .wrapContentWidth(align = Alignment.CenterHorizontally)
    .widthIn(max = 840.dp)
