package com.osamaaftab.nutmeg.common

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.text.isDigitsOnly
import androidx.test.platform.app.InstrumentationRegistry
import com.osamaaftab.nutmeg.common.AssetReaderManager.asset
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class SuccessDispatcher(
    private val context: Context = (InstrumentationRegistry.getInstrumentation().targetContext
        .applicationContext)
) : Dispatcher() {

    private val responseFilesByPath: Map<String, String> = mapOf(
        APIPaths.ALBUM_LIST to MockFiles.ALBUM_LIST_SUCCESS,
        APIPaths.PHOTO_LIST to MockFiles.PHOTO_LIST_SUCCESS,
        APIPaths.USER to MockFiles.USER_SUCCESS
    )

    override fun dispatch(request: RecordedRequest): MockResponse {
        val errorResponse = MockResponse().setResponseCode(404)
        var pathWithoutQueryParams = Uri.parse(request.path).lastPathSegment ?: return errorResponse
        if (pathWithoutQueryParams.isDigitsOnly()) {
            pathWithoutQueryParams = APIPaths.USER
        }
        val responseFile = responseFilesByPath[pathWithoutQueryParams]
        return if (responseFile != null) {
            val responseBody = asset(context, responseFile)
            MockResponse().setResponseCode(200).setBody(responseBody)
        } else {
            errorResponse
        }
    }

}