import java.sql.DriverManager

fun main(args: Array<String>) {

    val url = "jdbc:sqlite:C:/SQLite/projects/menu.db"
    DriverManager.getConnection(url).use { connection ->
        val statement = connection.createStatement()

        for (i in 2..6) {
            val rnd = (0..3).random()
            val resultSetRow = statement.executeQuery("SELECT * FROM FoodMenu LIMIT $rnd, 1")
            val metaData = resultSetRow.metaData
            val columnName = metaData.getColumnName(i)

            if (resultSetRow.next()) {
                val value = resultSetRow.getString(i)
                println("Значение для столбца \"$columnName\": $value")
            } else {
                println("Строка $rnd не найдена")
            }
        }
    }
}
