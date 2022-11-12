package com.anish.jetpackcomposependulumeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anish.jetpackcomposependulumeffect.ui.theme.JetpackComposePendulumEffectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposePendulumEffectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PendulumnEffect()
                }
            }
        }
    }
}

@Composable
fun PendulumnEffect() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

        Pendulumn {
            Surface(
                modifier = Modifier.size(24.dp),
                shape = CircleShape,
                color = Color.Blue,
                content = {}
            )
        }
    }
}

@Composable
fun Pendulumn(
    modifier: Modifier = Modifier,
    swingDuration: Int = 1500,
    startX: Float = .2f,
    endX: Float = .8f,
    topY: Float = .2f,
    bottomY: Float = .4f,
    content: @Composable () -> Unit
){
    val infiniteTransition = rememberInfiniteTransition()
    BoxWithConstraints {
        val start = maxWidth * startX
        val end = maxWidth * endX
        val top = maxHeight * topY
        val bottom = maxHeight * bottomY

        val x by infiniteTransition.animateFloat(
            initialValue = start.value,
            targetValue = end.value,
            animationSpec = infiniteRepeatable(
                animation = tween(swingDuration, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        val y by infiniteTransition.animateFloat(
            initialValue = top.value,
            targetValue = bottom.value,
            animationSpec = infiniteRepeatable(
                animation = tween(swingDuration / 2, easing = LinearOutSlowInEasing),
                repeatMode = RepeatMode.Reverse
            )
        )

        Box(
            modifier = Modifier.offset(x = x.dp, y = y.dp)
        ) {
            content()
        }
    }
}