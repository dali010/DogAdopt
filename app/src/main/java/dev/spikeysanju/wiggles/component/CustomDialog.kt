package dev.spikeysanju.wiggles.component

import android.media.MediaPlayer
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import dev.spikeysanju.wiggles.R
import kotlinx.coroutines.delay


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CustomDialog(
    description: String,
    dialogType: String,
    onDismiss: () -> Unit
) {
    val isNeedExpansion = rememberSaveable { mutableStateOf(false) }
    var show by remember { mutableStateOf(false) }
    var playSuccessSound by remember { mutableStateOf(false) }
    var playErrorSound by remember { mutableStateOf(false) }
    val mContext = LocalContext.current
    val mediaPlayer = MediaPlayer.create(mContext, R.raw.success_sound)
    val errorMediaPlayer = MediaPlayer.create(mContext, R.raw.error_sound)
    val animatedSizeDp: Dp by animateDpAsState(targetValue = if (isNeedExpansion.value) 70.dp else 20.dp,
        animationSpec = tween(
            durationMillis = 2000,
            easing = {
                OvershootInterpolator(1f).getInterpolation(it)
            }
        ))

    LaunchedEffect(key1 = true) {
        isNeedExpansion.value = true
        delay(2000)
        show = true
        when (dialogType) {
            DialogType.SuccessDialog.type -> playSuccessSound = true
            DialogType.ErrorDialog.type -> playErrorSound = true
            else -> White
        }
        delay(4000)
        onDismiss()
    }

    Dialog(
        onDismissRequest = {
            onDismiss()
            when (dialogType) {
                DialogType.SuccessDialog.type -> mediaPlayer.stop()
                DialogType.ErrorDialog.type -> errorMediaPlayer.stop()
                else -> null
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = when (dialogType) {
                        DialogType.SuccessDialog.type -> Color(0xFF5CAC63)
                        DialogType.ErrorDialog.type -> Color(0xFFf93943)
                        else -> White
                    },
                    RoundedCornerShape(16.dp)
                )
                .defaultMinSize(minHeight = 180.dp)
                .padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (dialogType) {
                DialogType.SuccessDialog.type -> CircleImage(
                    image = R.drawable.success_ic,
                    imageSize = animatedSizeDp
                )
                DialogType.ErrorDialog.type -> CircleImage(
                    image = R.drawable.error_ic,
                    imageSize = animatedSizeDp
                )
                else -> White
            }
            AnimatedVisibility(visible = show) {
                Text(
                    text = description,
                    modifier = Modifier.padding(top = 10.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h2.copy(
                        color = when (dialogType) {
                            DialogType.SuccessDialog.type -> Color(0xFFD5FED9)
                            DialogType.ErrorDialog.type -> White
                            else -> White
                        },
                        fontSize = 15.sp
                    )
                )
            }
        }
    }
    if (playSuccessSound) {
        mediaPlayer.start()
    }
    if (playErrorSound) {
        errorMediaPlayer.start()
    }
}

@Composable
fun CircleImage(image: Int, imageSize: Dp) {
    Image(
        painter = painterResource(image),
        contentDescription = "Circle Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(imageSize)
            .clip(CircleShape)
    )
}