package biz.mesto.anaken.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import java.util.*

/**
 * Created by Yury Korobeynikov on 26.01.18.
 */
class IntentBuilder private constructor(

        val context: Context,
        val clazz: Class<*>,
        val flags: ArrayList<Int>?,
        val action: String?,
        val extras: Bundle?
) {

    private constructor(builder: Builder) : this(
            builder.context!!,
            builder.clazz!!,
            builder.flags,
            builder.action,
            builder.extras
    )

    companion object {
        fun create(init: Builder.() -> Unit) = Builder(init).build()
    }

    class Builder private constructor() {

        constructor(init: Builder.() -> Unit) : this() {
            init()
        }

        internal var context: Context? = null
        internal var clazz: Class<*>? = null
        internal val flags by lazy { ArrayList<Int>() }
        internal var action: String? = null
        internal var extras: Bundle? = null

        fun context(init: Builder.() -> Context) = apply { context = init() }

        fun extras(init: Builder.() -> Bundle?) = apply { extras = init() }

        fun flag(init: Builder.() -> Int) = apply { flags.add(init()) }

        fun destinationClass(init: Builder.() -> Class<*>) = apply { clazz = init() }

        fun action(init: Builder.() -> String?) = apply { action = init() }

        fun build(): Intent {

            val ib = IntentBuilder(this)
            val intent = Intent(ib.context, ib.clazz)

            intent.action = ib.action

            ib.extras?.let { intent.putExtras(it) }

            flags.forEach { intent.flags = it }

            return intent
        }
    }
}