package com.itanik.keddit.commons

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

/**
 * Фрагмент, реализующий возможность управления RX подписками
 */
open class RxBaseFragment : Fragment() {

    protected var subscriptions = CompositeDisposable()

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeDisposable()
    }

    override fun onPause() {
        super.onPause()
        subscriptions.clear()
    }
}