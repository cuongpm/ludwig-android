package test.util

import android.content.res.Resources
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class RecyclerViewMatcher(val id: Int) {

    fun atPosition(position: Int): Matcher<View> {
        return atPositionOnView(position, -1)
    }

    fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            var resources: Resources? = null
            var childView: View? = null

            override fun describeTo(description: Description) {
                var idDescription = Integer.toString(id)
                if (this.resources != null) {
                    idDescription = try {
                        this.resources!!.getResourceName(id)
                    } catch (var4: Resources.NotFoundException) {
                        String.format("%s (resource name not found)", id)
                    }

                }
                description.appendText("RecyclerView with id: $idDescription at position: $position")
            }

            public override fun matchesSafely(view: View): Boolean {
                this.resources = view.resources
                if (childView == null) {
                    val recyclerView = view.rootView.findViewById<View>(id) as? RecyclerView
                    if (recyclerView != null && recyclerView.id == id) {
                        val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                        if (viewHolder != null) {
                            childView = viewHolder.itemView
                        }
                    } else {
                        return false
                    }
                }

                return when {
                    targetViewId == -1 -> view === childView
                    childView == null -> false
                    else -> {
                        val targetView = childView!!.findViewById<View>(targetViewId)
                        view === targetView
                    }
                }
            }
        }
    }

    companion object {
        fun recyclerViewWithId(@IdRes id: Int): RecyclerViewMatcher {
            return RecyclerViewMatcher(id)
        }

        fun recyclerViewSizeIs(size: Int) = object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            var realSize: Int = -1
            override fun describeTo(description: Description?) {
                description?.appendText("RecyclerView size should be $size but it's $realSize")
            }

            override fun matchesSafely(item: RecyclerView?): Boolean {
                realSize = item?.adapter?.itemCount ?: -2
                return realSize == size
            }
        }
    }
}
