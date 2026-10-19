package com.example.voicevault.ui

import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Com targetSdk 35+ o Android 15/16 desenha o app por baixo da barra de status e da
 * barra de navegação. Este ajuste soma a altura dessas barras (e do recorte da tela)
 * ao padding que a view já tinha, para o conteúdo não ficar coberto.
 *
 * Em versões antigas, onde o sistema já posiciona a janela fora das barras, os insets
 * chegam zerados e o padding original permanece como estava.
 */
fun View.applySystemBarInsets() {
    val baseLeft = paddingLeft
    val baseTop = paddingTop
    val baseRight = paddingRight
    val baseBottom = paddingBottom

    // Permite que o conteúdo role por baixo das barras transparentes, sem cortar no padding.
    (this as? ViewGroup)?.clipToPadding = false

    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        val bars = insets.getInsets(
            WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
        )
        view.setPadding(
            baseLeft + bars.left,
            baseTop + bars.top,
            baseRight + bars.right,
            baseBottom + bars.bottom,
        )
        insets
    }
    ViewCompat.requestApplyInsets(this)
}
