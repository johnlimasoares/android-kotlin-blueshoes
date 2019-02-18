package br.com.john.blueshoes.domain

/**
 * Created by john on 17/02/2019.
 */
class NavMenuItem(
        val lable: String,
        val iconId: Int = DEFAULT_ICON_ID) {
    companion object {
        const val DEFAULT_ICON_ID = -1
    }
}