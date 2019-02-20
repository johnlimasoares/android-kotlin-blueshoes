package br.com.john.blueshoes.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.john.blueshoes.R
import br.com.john.blueshoes.domain.NavMenuItem
import br.com.john.blueshoes.util.NavMenuItemDetails

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(items[position])
    }

    override fun getItemCount() = items.size;

    inner class ViewHolder(itemView: android.view.View): RecyclerView.ViewHolder(itemView){
        private val ivIcon: ImageView
        private val tvLabel: TextView
        val itemDetails: NavMenuItemDetails
        init {
            ivIcon = itemView.findViewById(R.id.iv_icon)
            tvLabel = itemView.findViewById(R.id.tv_label)
            itemDetails = NavMenuItemDetails()
        }

        fun setData(item : NavMenuItem ) {
            tvLabel.text = item.lable

            if(item.iconId != NavMenuItem.DEFAULT_ICON_ID){
                ivIcon.setImageResource(item.iconId)
                ivIcon.visibility = View.VISIBLE
            } else {
                ivIcon.visibility = View.GONE
            }

            itemDetails.item = item
            itemDetails.adapterPosition = adapterPosition
        }
    }
}