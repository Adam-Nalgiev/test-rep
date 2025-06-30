package ru.myapp.myapplication

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.os.SystemClock
import android.util.Log

class ContentProvider : ContentProvider() {

    private var startTime = 0L

    override fun onCreate(): Boolean {
        startTime = SystemClock.uptimeMillis()
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String?>?,
        selection: String?,
        selectionArgs: Array<out String?>?,
        sortOrder: String?
    ): Cursor? {
        val matrixCursor = MatrixCursor(arrayOf("start_time"))
        matrixCursor.addRow(arrayOf(startTime))
        Log.d("CP URI", uri.toString())
        return matrixCursor
    }

    override fun getType(uri: Uri): String? = null

    override fun insert(
        uri: Uri,
        values: ContentValues?
    ): Uri? = null

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String?>?
    ): Int = 0

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String?>?
    ): Int = 0
}