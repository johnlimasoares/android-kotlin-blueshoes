package br.com.john.blueshoes.data

import android.content.Context
import br.com.john.blueshoes.R
import br.com.john.blueshoes.domain.NavMenuItem

/**
 * Created by john on 17/02/2019.
 */
class NavMenuItemsDataBase(context: Context) {

    /*
     * Itens de menu gaveta que sempre estarão presentes,
     * independente do status do usuário (conectado ou
     * não).
     * */
    val items = listOf(
            NavMenuItem(
                    context.getString(R.string.item_all_shoes)
            ),
            NavMenuItem(
                    context.getString(R.string.item_flip_flops)
            ),
            NavMenuItem(
                    context.getString(R.string.item_cleats)
            ),
            NavMenuItem(
                    context.getString(R.string.item_sandals)
            ),
            NavMenuItem(
                    context.getString(R.string.item_ballet_shoes)
            ),
            NavMenuItem(
                    context.getString(R.string.item_suit_shoes)
            ),
            NavMenuItem(
                    context.getString(R.string.item_shoes)
            ),
            NavMenuItem(
                    context.getString(R.string.item_performance_shoes)
            ),
            NavMenuItem(
                    context.getString(R.string.item_contact),
                    R.drawable.ic_email_black_24dp
            ),
            NavMenuItem(
                    context.getString(R.string.item_about),
                    R.drawable.ic_domain_black_24dp
            ),
            NavMenuItem(
                    context.getString(R.string.item_privacy_policy),
                    R.drawable.ic_shield_lock_black_24dp
            )
    )

    /*
     * Itens de menu gaveta que estarão presentes somente
     * quando o usuário estiver conectado.
     * */
    val itemsLogged = listOf(
            NavMenuItem(
                    context.getString(R.string.item_my_orders),
                    R.drawable.ic_package_variant_closed_black_24dp
            ),
            NavMenuItem(
                    context.getString(R.string.item_settings),
                    R.drawable.ic_settings_black_24dp
            ),
            NavMenuItem(
                    context.getString(R.string.item_sign_out),
                    R.drawable.ic_exit_run_black_24dp
            )
    )
}