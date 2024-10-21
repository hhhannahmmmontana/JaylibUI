package com.volodya.jlui

import kotlin.text.Regex

class Filter {
    companion object {
        @JvmStatic
        val unsignedFloatFilter = Regex("(0|[1-9][0-9]*)([.][0-9]+)?")
        @JvmStatic
        val floatFilter = Regex("-?($unsignedFloatFilter)")
    }
}
