package commrhardman23.httpsgithub.employeedatabase;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class EmployeeDatabaseInterface extends AppCompatActivity {

    private EditText edtxtName;
    private EditText edtxtPosition;
    private EditText edtxtEmployeeNum;
    private EditText edtxtWage;
    private TextView txtvwResult;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_database_interface);

        edtxtName = (EditText) findViewById(R.id.edtxtName);
        edtxtPosition = (EditText) findViewById(R.id.edtxtPosition);
        edtxtEmployeeNum = (EditText) findViewById(R.id.edtxtEmployeeNum);
        edtxtWage = (EditText) findViewById(R.id.edtxtWage);
        txtvwResult = (TextView) findViewById(R.id.txtvwResult);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * insertData adds elements to the Employee database using information given by the user
     *
     * @param vw is the button the method is associated with
     */
    public void insertData(View vw) {

        /**
         * 1. Create a new EmployeeDatabaseHelper variable. You will need to use the following call:
         *    new EmployeeDatabaseHelper(this, null, null, 0);
         * 2. Create variables for all information stored in the Employee database
         * 3. Create a ContentValues variable
         * 4. Get a Writable Database reference using the variable name db (Remember your
         *    try-catch block. The if-else statement that follows should also go in your try block).
         */
        EmployeeDatabaseHelper employeeDatabaseHelper = new EmployeeDatabaseHelper(this, null, null, 0);
        SQLiteDatabase db;
        String name;
        String position;
        String EmployeeNum;
        String wage;
        ContentValues contentVal = new ContentValues();
        try {
            db = employeeDatabaseHelper.getWritableDatabase();
            //insert code from if-else statement here
            if (edtxtName.getText().length() == 0 || edtxtPosition.getText().length() == 0 ||
                    edtxtEmployeeNum.getText().length() == 0 || edtxtWage.getText().length() == 0) {

                txtvwResult.setText("You must enter all values to add an element!");

            } else {
                /**
                 * 1. Set each variable equal to the values from the EditTexts
                 * 2. put each value into the ContentValues variable
                 * 3. Call the EmployeeDatabaseHelper's insertElement method
                 * 4. Display that the element has been added successfully
                 */
                name = edtxtName.getText().toString();
                position = edtxtPosition.getText().toString();
                EmployeeNum = edtxtEmployeeNum.getText().toString();
                wage = edtxtWage.getText().toString();
                contentVal.put("NAME", name);
                contentVal.put("POSITION", position);
                contentVal.put("EMPLOYEE_NUM", EmployeeNum);
                contentVal.put("WAGE", wage);

                employeeDatabaseHelper.insertElement(db, contentVal);

                txtvwResult.setText("Information Has Been Successfully Added");
            db.close();
            }
        } catch (SQLiteException e) {
            //display that the database was not found
            txtvwResult.setText("Information not found. :(");
        }
    }

    /**
     * searchOrDelete opens the new activity where the user will be able to search or delete entries
     * in the Employee database
     * @param vw is the button that is associated with this method
     */
    public void searchOrDelete(View vw) {

        Intent goToSearchDelete = new Intent(this, SearchDatabase.class);

        startActivityForResult(goToSearchDelete, 0);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        edtxtName.setText("");
        edtxtPosition.setText("");
        edtxtEmployeeNum.setText("");
        edtxtWage.setText("");
        txtvwResult.setText("");

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "EmployeeDatabaseInterface Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://commrhardman23.httpsgithub.employeedatabase/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "EmployeeDatabaseInterface Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://commrhardman23.httpsgithub.employeedatabase/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
