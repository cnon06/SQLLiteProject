package cnon.software.sqlliteproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        try {
            SQLiteDatabase db = this.openOrCreateDatabase("fishes", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS fishes (id INTEGER PRIMARY KEY, name VARCHAR, weight INT)");
            //db.execSQL("INSERT INTO fishes (name, weight) VALUES ('Barbun',4)");
            //db.execSQL("INSERT INTO fishes (name, weight) VALUES ('Hamsi',4)");
            //db.execSQL("INSERT INTO fishes (name, weight) VALUES ('Palamut',4)");
            //db.execSQL("INSERT INTO fishes (name, weight) VALUES ('Sargoz',4)");

            //db.execSQL("UPDATE fishes SET name='ÇUPRA' WHERE id=3");
            //db.execSQL("DELETE FROM fishes WHERE id=3");


             //Cursor cursor = db.rawQuery("SELECT * FROM fishes WHERE name LIKE 'H%'", null);
            Cursor cursor = db.rawQuery("SELECT * FROM fishes", null);

            int nameIndex = cursor.getColumnIndex("name");
            int weightIndex = cursor.getColumnIndex("weight");
            int iDx = cursor.getColumnIndex("id");

            while (cursor.moveToNext()) {
                System.out.println(cursor.getString(iDx) + " " +
                        cursor.getString(nameIndex) + " " +
                        cursor.getInt(weightIndex));
            }

            cursor.close();

        } catch (Exception e) {
            System.out.println("Hata: "+e.getMessage());
            e.printStackTrace();
        }
    }
}