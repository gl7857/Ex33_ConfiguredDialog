package com.example.ex33_configureddialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity is the main screen of the app.
 * It provides five different buttons that each trigger an AlertDialog with different options.
 *
 * This activity demonstrates various features such as displaying simple messages,
 * handling single or multiple selections, and dynamically changing the background color.
 * It also includes functionality to capture text input and display it using Toast messages.
 *
 * Additionally, it contains a menu option to navigate to a credits screen.
 *
 * @author Gali Lavi <gl7857@bs.amalnet.k12.il>
 * @version 1.0
 * @since 07/02/2025
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The AlertDialog builder used to create different dialogs.
     */
    private AlertDialog.Builder adb;

    /**
     * The LinearLayout where the background color changes.
     */
    private LinearLayout linLayout;

    /**
     * The RGB values for color manipulation.
     */
    private int[] color = new int[3];

    /**
     * The list of color names.
     */
    private final String[] colors = {"Red", "Green", "Blue"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linLayout = findViewById(R.id.linlayout);
    }

    /**
     * Displays a dialog allowing one color choice.
     * Changes the background color based on the selected color.
     *
     * @param view The view that triggered the dialog.
     */
    public void oneChoice(View view) {
        color = new int[]{0, 0, 0};

        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("List of colors - one choice");
        adb.setItems(colors, (dialog, which) -> {
            color[which] = 255;
            linLayout.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
        });
        adb.setNeutralButton("Cancel", (dialog, which) -> dialog.cancel());

        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Displays a dialog allowing multiple color choices.
     * The background color changes based on the selected combinations.
     *
     * @param view The view that triggered the dialog.
     */
    public void multiChoices(View view) {
        color = new int[]{0, 0, 0};

        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("List of colors - multi choice");
        adb.setMultiChoiceItems(colors, null, (dialog, which, isChecked) -> {
            color[which] = isChecked ? 255 : 0;
            linLayout.setBackgroundColor(Color.rgb(color[0], color[1], color[2]));
        });
        adb.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        adb.setNeutralButton("Cancel", (dialog, which) -> dialog.cancel());

        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Resets the background color to white.
     *
     * @param view The view that triggered the action.
     */
    public void whiteOption(View view) {
        linLayout.setBackgroundColor(Color.WHITE);
    }

    /**
     * Displays a dialog with an EditText field, allowing the user to input text.
     * Shows the input text as a Toast message.
     *
     * @param view The view that triggered the dialog.
     */
    public void inputAndToast(View view) {
        adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle("EditText Widget");

        final EditText eT = new EditText(this);
        eT.setHint("Type text here");
        adb.setView(eT);

        adb.setPositiveButton("Copy", (dialog, which) -> {
            String str = eT.getText().toString();
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
        });
        adb.setNeutralButton("Cancel", (dialog, which) -> dialog.cancel());

        AlertDialog ad = adb.create();
        ad.show();
    }

    /**
     * Creates the options menu on the screen.
     *
     * @param menu The menu to create.
     * @return true to indicate that the menu was created successfully.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.creditsmenu, menu);
        return true;
    }

    /**
     * Handles item selection from the options menu.
     * Navigates to the credits screen if the corresponding item is selected.
     *
     * @param item The selected menu item.
     * @return true if the item is handled.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuCredits) {
            Intent si = new Intent(this, credits.class);
            startActivity(si);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
