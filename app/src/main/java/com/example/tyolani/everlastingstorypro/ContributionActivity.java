package com.example.tyolani.everlastingstorypro;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;

import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.tyolani.everlastingstorypro.R.string.bookView_mockup_bookText;
import static com.example.tyolani.everlastingstorypro.R.string.contribution_addTextChapter;
import static com.example.tyolani.everlastingstorypro.R.string.contribution_addTextParagraph;


public class ContributionActivity extends AppCompatActivity {

    private String action;
    private String chapterBeforeOrAfter;
    private int chapterPosition;
    private static int RESULT_LOAD_IMAGE = 1;
    final Book activeBook = new Book("activeBook");

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


        if(action.equals("addChapter")) {
            setContentView(R.layout.contribution_add_chapter);
            Toolbar menu = findViewById(R.id.menu_contribution_addChapter);
            setSupportActionBar(menu);


            TextView textTextChapter = (TextView) findViewById(R.id.tv_contribution_add_chapter_title);
            textTextChapter.setTypeface(textTextChapter.getTypeface(), Typeface.BOLD);

            if (chapterBeforeOrAfter.equals("after")) {
                chapterPosition = chapterPosition + 1;
                textTextChapter.append("Chapter" + (chapterPosition + 1) + ": ");
            } else {
                textTextChapter.append("Chapter" + (chapterPosition + 1) + ": ");
            }
            EditText editTextChapterTitle = (EditText) findViewById(R.id.et_contribution_add_chapter_title);
            editTextChapterTitle.setText("Write a title!");

            EditText editTextContent = (EditText) findViewById(R.id.et_contribution_add_chapter_new_content);
            editTextContent.append(getString(contribution_addTextChapter));



        }else if(action.equals("addParagraph")) {
            final Chapter chapter = (Chapter) getIntent().getExtras().getSerializable("chapters");
            setContentView(R.layout.contribution_add_paragraph);
            Toolbar menu = findViewById(R.id.menu_contribution_addParagraph);
            setSupportActionBar(menu);

            TextView textViewParagraphTitle = (TextView) findViewById(R.id.tv_contribution_add_paragraph_title);
            textViewParagraphTitle.setTypeface(textViewParagraphTitle.getTypeface(), Typeface.BOLD);
            TextView textViewParagraphContent = (TextView) findViewById(R.id.tv_contribution_add_paragraph_content);

            textViewParagraphTitle.setText("Chapter" + (chapterPosition + 1) + ": " + chapter.getName());

            for (int i = 0; i<chapter.getContributions().size(); i++){
                textViewParagraphContent.append(chapter.getContributions().get(i).getTextContent());
            }

            //new paragraph
            EditText editTextNewParagraph = findViewById(R.id.et_contribution_add_paragraph_new_content);
            editTextNewParagraph.setTypeface(textViewParagraphTitle.getTypeface(), Typeface.BOLD);
            editTextNewParagraph.append(getString(contribution_addTextParagraph));

            //Scroll to the bottom of the screen when opening
            final ScrollView scrollview = ((ScrollView) findViewById(R.id.scrollView_contribution_add_paragraph));
            scrollview.post(new Runnable() {
                @Override
                public void run() {
                    scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                }
            });

        }else if (action.equals("addImage")) {
            setContentView(R.layout.contribution_add_image);
            Toolbar menu = findViewById(R.id.menu_contribution_addImage);
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (action.equals("addParagraph")){
            MenuItem addImage = menu.findItem(R.id.action_contribution_addImage);
            addImage.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission granted and now can proceed
                } else {
                    // permission denied
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
            case R.id.action_contribution_submit:
                // Create custom dialog object
                final Dialog dialog = new Dialog(ContributionActivity.this);
                final EditText editTextNewParagraph = findViewById(R.id.et_contribution_add_paragraph_new_content);
                final Chapter chapter = (Chapter) getIntent().getExtras().getSerializable("chapters");
                final CheckBox cbFinalContribution = findViewById(R.id.cb_final_contribution);

                final EditText editTextChapterContent = (EditText) findViewById(R.id.et_contribution_add_chapter_new_content);
                final EditText editTextChapterTitle = (EditText) findViewById(R.id.et_contribution_add_chapter_title);

                dialog.setContentView(R.layout.submit_dialog);
                //get where the user is coming from (add paragraph, image or chapter)
                if(action.equals("addParagraph")){
                    //find what the user added to the edit text

                    // If statemetent which checks how many contributions there are in the current chapter, if less than 3 set the checkbox to not visible.
                    if(chapter.getNumberOfContributions() < 3){
                        Log.d("contribuitons less 3 ", chapter.getNumberOfContributions()+"");
                        CheckBox checkBox = (CheckBox)dialog.findViewById(R.id.cb_final_contribution);
                        checkBox.setVisibility(View.INVISIBLE);
                    }

                    dialog.show();


                Button noBtn = (Button) dialog.findViewById(R.id.btn_submit_dialog_no);
                // if no btn is clicked
                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });

                Button yesBtn = (Button) dialog.findViewById(R.id.btn_submit_dialog_yes);
                // if yes btn is clicked
                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    Boolean isfin = false;
                    CheckBox checkBox = (CheckBox)dialog.findViewById(R.id.cb_final_contribution);

                    if(checkBox.isChecked()){
                        isfin = true;
                    }
                    dialog.dismiss();
                    final Contribution newContribution = new Contribution(String.valueOf(" \n \n"+editTextNewParagraph.getText())+" \n","author"+chapter.getNumberOfContributions()+"", false);
                    chapter.addContribution(newContribution);

                    activeBook.saveChapter(chapterPosition,chapter,"activeBook",isfin);
                    Intent bookview = new Intent(getApplicationContext(), BookView.class);
                    startActivity(bookview);
                    editTextNewParagraph.getText().clear();
                    }
                });
                }else if (action.equals("addChapter")){
                    CheckBox checkBox = (CheckBox)dialog.findViewById(R.id.cb_final_contribution);
                    checkBox.setVisibility(View.INVISIBLE);
                    dialog.show();

                    Button noBtn = (Button) dialog.findViewById(R.id.btn_submit_dialog_no);
                    // if no btn is clicked
                    noBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Close dialog
                            dialog.dismiss();
                        }
                    });

                    Button yesBtn = (Button) dialog.findViewById(R.id.btn_submit_dialog_yes);
                    // if yes btn is clicked
                    yesBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Contribution newChapterContribution = new Contribution(String.valueOf(" \n \n"+editTextChapterContent.getText())+" \n","author", false);
                            Chapter chap = new Chapter(newChapterContribution,String.valueOf(editTextChapterTitle.getText()));

                            activeBook.getChapters().add(chapterPosition,chap);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                public void run() {
                                removeBook("activeBook");
                                }
                            }, 2000);

                            Handler handler1 = new Handler();
                            handler1.postDelayed(new Runnable() {
                                public void run() {
                                activeBook.saveBookToFirebase("activeBook");
                                }
                            }, 2000);

                            Handler handler2 = new Handler();
                            handler2.postDelayed(new Runnable() {
                                public void run() {
                                Intent bookview1 = new Intent(getApplicationContext(), BookView.class);
                                startActivity(bookview1);
                                }
                            }, 2000);

                        }
                    });

                }
                return true;
            default:
                // the user's action was not recognized
                return super.onOptionsItemSelected(item);
        }
    }

    public void removeBook(String id) {
        DatabaseReference db_node = FirebaseDatabase.getInstance().getReference().child("Book");
        DatabaseReference book_idRef2 = db_node.child(id);
        book_idRef2.setValue(null);
    }

}
