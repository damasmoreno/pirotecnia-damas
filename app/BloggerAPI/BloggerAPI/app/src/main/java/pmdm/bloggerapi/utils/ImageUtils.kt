package pmdm.bloggerapi.utils



import pmdm.bloggerapi.R

object ImageUtils {

    fun getImageResource(imageName: String?): Int {
        return when (imageName?.lowercase()) {
            "chinos" -> R.drawable.chinos
            "aguilas" -> R.drawable.aguilas
            "super_falleros" -> R.drawable.super_falleros
            "super_xxl" -> R.drawable.super_xxl
            "traca_10" -> R.drawable.traca_10
            "bengalas_allstar" -> R.drawable.bengalas_allstar
            "bateria_macao" -> R.drawable.bateria_macao
            "parchis" -> R.drawable.parchis
            "qatar" -> R.drawable.qatar
            "confeti" -> R.drawable.confeti
            "colorkerzen" -> R.drawable.colorkerzen
            "sunset_tricolor" -> R.drawable.sunset_tricolor
            "aurea" -> R.drawable.aurea
            "giralda" -> R.drawable.giralda
            "laroca" -> R.drawable.laroca
            "capri" -> R.drawable.capri
            "trevi" -> R.drawable.trevi
            "piccola" -> R.drawable.piccola
            "big_flash" -> R.drawable.big_flash
            "traca_chinos" -> R.drawable.traca_chinos
            "traca_aguilas" -> R.drawable.traca_aguilas
            "silbido" -> R.drawable.silbido
            "rocket" -> R.drawable.rocket
            "winder" -> R.drawable.winder



            else -> R.drawable.pirotecnia_damas

        }

    }}