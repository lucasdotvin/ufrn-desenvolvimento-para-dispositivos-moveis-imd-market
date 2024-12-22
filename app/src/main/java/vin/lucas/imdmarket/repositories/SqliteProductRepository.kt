package vin.lucas.imdmarket.repositories

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import vin.lucas.imdmarket.contracts.ProductRepository
import vin.lucas.imdmarket.entities.Product

class SqliteProductRepository(context: Context, name: String) : ProductRepository, SQLiteOpenHelper(context, name, null, DATABASE_VERSION) {
    private companion object {
        const val TABLE = "products"
        const val ID_COLUMN = "ID";
        const val CODE_COLUMN = "CODE";
        const val NAME_COLUMN = "NAME";
        const val DESCRIPTION_COLUMN = "DESCRIPTION";
        const val STOCK_COLUMN = "STOCK";

        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS $TABLE (
                $ID_COLUMN INTEGER PRIMARY KEY AUTOINCREMENT,
                $CODE_COLUMN TEXT NOT NULL,
                $NAME_COLUMN TEXT NOT NULL,
                $DESCRIPTION_COLUMN TEXT NOT NULL,
                $STOCK_COLUMN INTEGER NOT NULL
            );
        """

        private const val SQL_DROP_TABLE = """
            DROP TABLE IF EXISTS $TABLE;
        """

        private const val SQL_INSERT = """
            INSERT INTO $TABLE ($ID_COLUMN, $CODE_COLUMN, $NAME_COLUMN, $DESCRIPTION_COLUMN, $STOCK_COLUMN)
            VALUES (?, ?, ?, ?, ?);
        """

        private const val SQL_SELECT = """
            SELECT *
            FROM $TABLE;
        """

        private const val SQL_DELETE = """
            DELETE FROM $TABLE;
        """
    }

    override fun onCreate(p0: SQLiteDatabase) {
        p0.execSQL(SQL_CREATE_TABLE)
    }

    override fun onUpgrade(p0: SQLiteDatabase, p1: Int, p2: Int) {
        val products = load()

        p0.execSQL(SQL_DROP_TABLE)
        onCreate(p0)

        dump(products)
    }

    override fun load(): List<Product> {
        val cursor = readableDatabase.rawQuery(SQL_SELECT, null)
        val products = mutableListOf<Product>()

        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val code = cursor.getInt(1)
            val name = cursor.getString(2)
            val description = cursor.getString(3)
            val stock = cursor.getInt(4)

            products.add(Product(id, code, name, description, stock))
        }

        cursor.close()
        readableDatabase.close()

        return products
    }

    override fun dump(products: List<Product>) {
        val statement = writableDatabase.compileStatement(SQL_INSERT)

        writableDatabase.beginTransaction()
        writableDatabase.execSQL(SQL_DELETE)

        for (product in products) {
            statement.bindString(1, product.id.toString())
            statement.bindString(2, product.code.toString())
            statement.bindString(3, product.name)
            statement.bindString(4, product.description)
            statement.bindString(5, product.stock.toString())

            statement.executeInsert()
        }

        writableDatabase.setTransactionSuccessful()
        writableDatabase.endTransaction()
        writableDatabase.close()
    }
}
