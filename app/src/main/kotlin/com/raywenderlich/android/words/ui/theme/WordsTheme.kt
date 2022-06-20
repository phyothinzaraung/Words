/*
 * Copyright (c) 2021 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.words.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.raywenderlich.android.words.R

@Composable
fun WordsTheme(content: @Composable () -> Unit) {
  val systemUiController = rememberSystemUiController()
  val color = WordsTheme.colors.primary
  SideEffect {
    systemUiController.setSystemBarsColor(color = color)
  }
  MaterialTheme(
      colors = WordsTheme.colors,
      typography = WordsTheme.typography,
      shapes = WordsTheme.shapes,
      content = content
  )
}

object WordsTheme {

  val colors: Colors
    @Composable get() = when {
      isSystemInDarkTheme() -> DarkColorPalette
      else -> LightColorPalette
    }
  val typography: Typography = wordsTypography
  val shapes: Shapes = wordsShapes
}

object WordsColors {

  val salem @Composable get() = colorResource(id = R.color.salem)
  val rwGrey @Composable get() = colorResource(id = R.color.rw_grey)
}

private val wordsShapes
  get() = Shapes(
      small = RoundedCornerShape(4.dp),
      medium = RoundedCornerShape(4.dp),
      large = RoundedCornerShape(0.dp)
  )

private val wordsTypography
  get() = Typography(
      body1 = TextStyle(
          fontFamily = FontFamily.Default,
          fontWeight = FontWeight.Normal,
          fontSize = 16.sp
      )
  )

private val DarkColorPalette
  @Composable get() = darkColors(
      primary = WordsColors.rwGrey,
  )

private val LightColorPalette
  @Composable get() = lightColors(
      primary = WordsColors.rwGrey,
  )


