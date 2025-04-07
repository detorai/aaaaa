package org.example.presenceapp.adapters

import app.cash.sqldelight.ColumnAdapter

class IntAdapter: ColumnAdapter<Int, Long> {
    override fun decode(databaseValue: Long) = databaseValue.toInt()
    override fun encode(value: Int) = value.toLong()
}