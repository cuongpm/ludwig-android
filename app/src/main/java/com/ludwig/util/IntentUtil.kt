package com.ludwig.util

import android.content.Context
import android.content.Intent
import android.os.Bundle
import javax.inject.Inject

interface IntentUtil {
    fun startActivity(context: Context, clazz: Class<*>)
    fun startActivity(context: Context, clazz: Class<*>, bundle: Bundle)
}

class IntentUtilImpl @Inject constructor() : IntentUtil {

    override fun startActivity(context: Context, clazz: Class<*>) {
        val intent = Intent(context, clazz)
        context.startActivity(intent)
    }

    override fun startActivity(context: Context, clazz: Class<*>, bundle: Bundle) {
        val intent = Intent(context, clazz).apply { putExtras(bundle) }
        context.startActivity(intent)
    }
}
