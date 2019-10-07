package com.itanik.keddit.commons

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Выполняет кусок кода, когда элементов, еще не показанных на экране, становится определенное кол-во
 * */
class InfiniteScrollListener(
    val func: () -> Unit,
    val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    private var previousTotal =
        0 //кол-во загруженных новостей до следующей загрузки. позволяет понять когда новости уже подгрузились
    private var loading =
        true //синхронизует загрузку новостей, чтобы не заспамить сервер вызовами API при каждом исполнении метода onScrolled
    private val visibleThreshold =
        5 //порог видимости. Чем больше, тем раньше подгрузится следующая партия новостей
    private var firstVisibleItem = 0 // первый видимый (верхний) на экране view в layoutManager
    private var visibleItemCount =
        0 //количество view в recyclerView (видимые на экране) примерно 6-9
    private var totalItemCount =
        0 //количетво view в layoutManager (загруженные и добавленные) 10+1 при вервой загрузке и +10 при каждой последующей

    //если количество загруженных впервые новостей будет меньше количества новостей,
    //которые могут отобразиться на экране, то остальные новости никогда не подгрузятся,
    //потому что метод OnScrolled никогда не выполнится
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            visibleItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }

            if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
                // End has been reached
                /*Log.d("InfiniteScrollListener", "totalItemCount = $totalItemCount - visibleItemCount = $visibleItemCount <=")
                Log.d("InfiniteScrollListener", "firstVisibleItem = $firstVisibleItem + visibleThreshold = $visibleThreshold")*/
                Log.i("InfiniteScrollListener", "End reached")
                func()
                loading = true
            }
        }
    }

}