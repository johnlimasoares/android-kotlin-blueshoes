package br.com.john.blueshoes.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.john.blueshoes.R
import br.com.john.blueshoes.domain.NavMenuItem

/**
 * Created by john on 17/02/2019.
 */
class NavMenuItemsAdapter( val items: List<NavMenuItem> ):
        RecyclerView.Adapter<NavMenuItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context)
                .inflate(R.layout.nav_menu_item, parent, false)

        return ViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount() = items.size;

    inner class ViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        private val ivIcon: ImageView
        private val tvLabel: TextView

        init {
            ivIcon = itemView.findViewById(R.id.iv_icon)
            tvLabel = itemView.findViewById(R.id.tv_label)
        }

        fun setData(item : NavMenuItem ) {
            tvLabel.text = item.lable

            if(item.iconId != NavMenuItem.DEFAULT_ICON_ID){
                ivIcon.setImageResource(item.iconId)
                ivIcon.visibility = View.VISIBLE
            } else {
                ivIcon.visibility = View.GONE
            }
        }
    }

}