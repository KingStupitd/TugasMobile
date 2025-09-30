package com.example.test

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.net.URLEncoder
import java.util.Locale

class ProductAdapter(private val items: List<Product>) : RecyclerView.Adapter<ProductAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.productImage)
        val brand: TextView = view.findViewById(R.id.product_brand)
        val title: TextView = view.findViewById(R.id.productTitle)
        val price: TextView = view.findViewById(R.id.productPrice)
        val rating: TextView = view.findViewById(R.id.productRating)
        val reviewCount: TextView = view.findViewById(R.id.productReviewCount)
        val shareBtn: android.widget.ImageButton = view.findViewById(R.id.btnShare)
        // browser button removed; title click will open browser
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val p = items[position]
        holder.image.setImageResource(p.imageRes)
        holder.brand.text = p.brand
        holder.title.text = p.title
        holder.price.text = p.price
        holder.rating.text = String.format(Locale.getDefault(), "%.1f", p.rating)
//        holder.reviewCount.text = holder.itemView.context.getString(R.string.review_count_format, p.reviewCount)

        // share onclick
        holder.shareBtn.setOnClickListener {
            val ctx = holder.itemView.context
            val shareText = if (p.link.isNotBlank()) p.link else "${p.title} - ${p.price}"
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            ctx.startActivity(Intent.createChooser(intent, ctx.getString(R.string.share)))
        }

        holder.title.setOnClickListener {
            val ctx = holder.itemView.context
            val query = try {
                URLEncoder.encode(p.title, "UTF-8")
            } catch (e: Exception) {
                p.title.replace(" ", "+")
            }
            val url = "https://www.google.com/search?q=$query"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            ctx.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}
