package com.example.tyolani.everlastingstorypro;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;

import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



import static com.example.tyolani.everlastingstorypro.R.string.bookView_mockup_bookText;
import static com.example.tyolani.everlastingstorypro.R.string.contribution_addTextChapter;
import static com.example.tyolani.everlastingstorypro.R.string.contribution_addTextParagraph;


public class ContributionActivity extends AppCompatActivity {

    private String action;
    private String chapterBeforeOrAfter;
    private int chapterPosition;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},1);


        String extraChapter = getIntent().getExtras().getString("addChapter");
        int extraChapter2 = getIntent().getExtras().getInt("position");
        int extraParagraph = getIntent().getExtras().getInt("addParagraph");
        String extraParagraph2 = getIntent().getExtras().getString("addP");
        int extraImage = getIntent().getExtras().getInt("addImage");
        String extraImage3 = getIntent().getExtras().getString("addI");


        if (extraChapter != null) {
            action = "addChapter";
            chapterBeforeOrAfter = extraChapter;
            chapterPosition = extraChapter2;
        }else if (extraParagraph2 != null) {
            action = "addParagraph";
            chapterPosition = extraParagraph;
        }else if (extraImage3 != null) {
            action = "addImage";
            chapterPosition = extraImage;
        }

        Log.d("TROLOLAction: ",""+action);

        if(action.equals("addChapter")) {
            Log.d("TROLOLOLOL addChapter","");
            setContentView(R.layout.contribution_add_chapter);
            Toolbar menu = (Toolbar) findViewById(R.id.menu_contribution_addChapter);
            setSupportActionBar(menu);

            EditText editTextChapter = (EditText) findViewById(R.id.et_contribution_add_chapter_title);
            editTextChapter.setTypeface(editTextChapter.getTypeface(), Typeface.BOLD);

            if (chapterBeforeOrAfter.equals("after")) {
                chapterPosition = chapterPosition + 1;
                editTextChapter.append("Chapter" + (chapterPosition + 1) + ": ");
            } else {
                editTextChapter.append("Chapter" + (chapterPosition + 1) + ": ");
            }

            EditText editTextContent = (EditText) findViewById(R.id.et_contribution_add_chapter_new_content);
            editTextContent.append(getString(contribution_addTextChapter));

        }else if(action.equals("addParagraph")) {
            Log.d("TROLOLOLOL addParagraph","");
            setContentView(R.layout.contribution_add_paragraph);
            Toolbar menu = (Toolbar) findViewById(R.id.menu_contribution_addParagraph);
            setSupportActionBar(menu);

            //get right chapter and load it
            TextView textViewParagraphTitle = (TextView) findViewById(R.id.tv_contribution_add_paragraph_title);
            //todo retrieve chapter title from database according to position
            textViewParagraphTitle.setText("Chapter" + (chapterPosition + 1) + ": ");
            textViewParagraphTitle.setTypeface(textViewParagraphTitle.getTypeface(), Typeface.BOLD);

            //todo retrieve chapter contributions from database according to position
            TextView textViewParagraphContent = (TextView) findViewById(R.id.tv_contribution_add_paragraph_content);
            textViewParagraphContent.setText(getString(bookView_mockup_bookText));

            //new paragraph
            EditText editTextNewParagraph = (EditText) findViewById(R.id.et_contribution_add_paragraph_new_content);
            editTextNewParagraph.append(getString(contribution_addTextParagraph));


        }else if (action.equals("addImage")) {
            setContentView(R.layout.contribution_add_image);
            Toolbar menu = (Toolbar) findViewById(R.id.menu_contribution_addImage);
            setSupportActionBar(menu);

            TextView textViewImageTitle = (TextView) findViewById(R.id.tv_contribution_add_image_title);
            textViewImageTitle.setTypeface(textViewImageTitle.getTypeface(), Typeface.BOLD);
            textViewImageTitle.setText("Chapter" + (chapterPosition + 1) + ": ");
            TextView textViewImageContent = (TextView) findViewById(R.id.tv_contribution_add_image_content);
            textViewImageContent.setText(getString(bookView_mockup_bookText));

            //todo User can add ONE image to the beginning of a chapter if it does not have any text (initialized)
            //todo User can add image right beneath another image
            Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted and now can proceed

                    Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();

                } else {
                    // permission denied
                    Toast.makeText(this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.iv_contribution_image);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contribution_items, menu);
        return true;
    }

    //Handling actions from the menu_bookView toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_contribution_addImage:
                return true;
            default:
                // the user's action was not recognized
                return super.onOptionsItemSelected(item);
        }
    }

}
