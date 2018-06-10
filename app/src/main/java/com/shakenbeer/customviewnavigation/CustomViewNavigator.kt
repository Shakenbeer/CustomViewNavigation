package com.shakenbeer.customviewnavigation

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.withStyledAttributes
import androidx.core.view.plusAssign
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import java.util.*

@Navigator.Name("custom_view")
class CustomViewNavigator(private val container: ViewGroup) : Navigator<CustomViewNavigator.Destination>() {

    private val stack: Deque<Pair<Int, Int>> = LinkedList()

    override fun navigate(destination: Destination, args: Bundle?, navOptions: NavOptions?) {
        val layoutId = destination.layoutId
        stack.push(Pair(destination.id, layoutId))
        replaceView(layoutId)
        dispatchOnNavigatorNavigated(destination.id, BACK_STACK_DESTINATION_ADDED)
    }

    private fun replaceView(layoutId: Int) {
        container.removeAllViews()
        container += LayoutInflater.from(container.context)
                .inflate(layoutId, container, false)
    }

    override fun createDestination(): Destination {
        return Destination(this)
    }

    override fun popBackStack(): Boolean {
        return if (stack.isNotEmpty()) {
            stack.pop()
            replaceView(stack.peek().second)
            dispatchOnNavigatorNavigated(stack.peek().first, BACK_STACK_DESTINATION_POPPED)
            true
        } else {
            false
        }
    }

    class Destination(navigator: Navigator<out NavDestination>) : NavDestination(navigator) {
        @LayoutRes
        var layoutId: Int = 0

        override fun onInflate(context: Context, attrs: AttributeSet) {
            super.onInflate(context, attrs)
            context.withStyledAttributes(attrs, R.styleable.CustomViewNavigator, 0, 0, {
                layoutId = getResourceId(R.styleable.CustomViewNavigator_layoutId, 0)
            })
        }
    }
}