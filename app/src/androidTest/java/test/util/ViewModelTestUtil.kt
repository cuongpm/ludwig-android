package test.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ludwig.presentation.base.BaseViewModel

object ViewModelTestUtil {

    fun createFor(vararg models: BaseViewModel): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                models.forEach { model ->
                    if (modelClass.isAssignableFrom(model.javaClass)) {
                        @Suppress("UNCHECKED_CAST")
                        return model as T
                    }
                }
                throw IllegalArgumentException("unexpected model class $modelClass")
            }
        }
    }
}
