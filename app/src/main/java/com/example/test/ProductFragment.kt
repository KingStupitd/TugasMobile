package com.example.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProductFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment_product.xml
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerViewProduct)
        recycler.layoutManager = GridLayoutManager(requireContext(), 2)
        recycler.setHasFixedSize(true)

        val spacing = (8 * resources.displayMetrics.density).toInt()
        recycler.addItemDecoration(GridSpacingItemDecoration(2, spacing, true))

        val sampleProducts = listOf(
            Product("KRAVEBEAUTY", "Matcha Heartleaf Hydrating Cleanser", "Rp 331.000", 4.8, 212, R.drawable.ic_product_placeholder_2, "https://www.officialkravebeauty.com/products/matcha-heartleaf-hydrating-cleanser"),
            Product("KRAVEBEAUTY", "Hydrating Serum", "Rp 199.000", 4.6, 98, R.drawable.ic_product_placeholder_2, "https://www.officialkravebeauty.com/products/hydrating-serum"),
            Product("KRAVEBEAUTY", "Moisturizer", "Rp 250.000", 4.4, 76, R.drawable.ic_product_placeholder_2, "https://www.officialkravebeauty.com/products/moisturizer"),
            Product("KRAVEBEAUTY", "Sunscreen SPF50", "Rp 120.000", 4.7, 143, R.drawable.ic_product_placeholder_2, "https://www.officialkravebeauty.com/products/sunscreen-spf50"),
            Product("KRAVEBEAUTY", "Night Cream", "Rp 180.000", 4.5, 54, R.drawable.ic_product_placeholder_2, "https://www.officialkravebeauty.com/products/night-cream"),
            Product("KRAVEBEAUTY", "Face Mist", "Rp 89.000", 4.3, 33, R.drawable.ic_product_placeholder_2, "https://www.officialkravebeauty.com/products/face-mist")
        )

        recycler.adapter = ProductAdapter(sampleProducts)
    }
}
