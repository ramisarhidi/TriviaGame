package com.example.quizapp1;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.quizapp1.QuizContract.*;
import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TheTriviaGame.db";
    private static final int DATABASE_VERSION = 1;
    private static QuizDbHelper instance;

    private SQLiteDatabase db;

   QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
    private void fillCategoriesTable() {
        Category c1 = new Category("History");
        addCategory(c1);
        Category c2 = new Category("Geography");
        addCategory(c2);
        Category c3 = new Category("Science");
        addCategory(c3);
    }
    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }


    private void fillQuestionsTable() {
        Question q1 = new Question("Who was the first President of the United States?",
                "Barack Obama", "George Washington", "Abraham Lincoln", 2,
                Question.DIFFICULTY_EASY, Category.HISTORY);
        addQuestion(q1);
        Question q2 = new Question("How many stars does the American Flag have?",
                "50", "13", "25", 1,
                Question.DIFFICULTY_EASY, Category.HISTORY);
        addQuestion(q2);
        Question q3 = new Question("Who was a leader during the Civil Rights Movement during the 1960s?",
                "Harriet Tubman", "Fredrick Douglas", "Martin Luther King", 3,
                Question.DIFFICULTY_EASY, Category.HISTORY);
        addQuestion(q3);
        Question q4 = new Question("What is Thomas Edison famous for inventing?",
                "Steam Engine", "Telegram", "The Light Bulb", 3,
                Question.DIFFICULTY_MEDIUM, Category.HISTORY);
        addQuestion(q4);
        Question q5 = new Question("Who built the first car in America?",
                "Henry Ford", "Elon Musk", "Enzo Ferrari", 1,
                Question.DIFFICULTY_MEDIUM, Category.HISTORY);
        addQuestion(q5);
        Question q6 = new Question("Which team won the first Super Bowl?",
                "Patriots", "GreenBay Packers", "Yankees", 2,
                Question.DIFFICULTY_MEDIUM, Category.HISTORY);
        addQuestion(q6);
        Question q7 = new Question("In which year did WWI begin?",
                "1900", "1914", "1941", 2,
                Question.DIFFICULTY_HARD, Category.HISTORY);
        addQuestion(q7);
        Question q8 = new Question("What is the name of the disease that ravaged and killed a third of Europe’s population in the 14th century?",
                "Polio", "The Spanish Flu", "Bubonic Plague", 3,
                Question.DIFFICULTY_HARD, Category.HISTORY);
        addQuestion(q8);
        Question q9 = new Question("In which city and state was John F. Kennedy assassinated?",
                "Dallas, Texas", "San Francisco, California", "Chicago, Illinois", 1,
                Question.DIFFICULTY_HARD, Category.HISTORY);
        addQuestion(q9);



        Question q10 = new Question("What is Earth's largest continent?",
                "Africa", "Asia", "Europe", 2,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        addQuestion(q10);
        Question q11 = new Question("What is the Capital of the United States?",
                "Albany, NY", "Washington, DC", "Austin, Texas", 2,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        addQuestion(q11);
        Question q12 = new Question("Where is the Great Pyramid of Giza located?",
                "Mesopotamia", "Afghanistan", "Egypt", 3,
                Question.DIFFICULTY_EASY, Category.GEOGRAPHY);
        addQuestion(q12);
        Question q13 = new Question("What country is home to the tallest mountain in the world, Mount Everest?",
                "Nepal", "China", "India", 1,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        addQuestion(q13);
        Question q14 = new Question("What is the smallest country in the world?",
                "Monaco", "Vatican City", "Puerto Rico", 2,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        addQuestion(q14);
        Question q15 = new Question("How many countries are inside the United Kingdom?",
                "1", "4", "3", 2,
                Question.DIFFICULTY_MEDIUM, Category.GEOGRAPHY);
        addQuestion(q15);
        Question q16 = new Question("What African country has Portuguese as it  official language?",
                "South Africa", "Mozambique", "Morocco", 2,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        addQuestion(q16);
        Question q17 = new Question("Which country has the abbreviation 'CH'?",
                "China", "Chile", "Switzerland", 3,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        addQuestion(q17);
        Question q18 = new Question("How many time zones does Russia have?",
                "11", "3", "7", 1,
                Question.DIFFICULTY_HARD, Category.GEOGRAPHY);
        addQuestion(q18);


        Question q19 = new Question("What is the largest star in the Solar System?",
                "Big Dipper", "The Sun", "Vega", 2,
                Question.DIFFICULTY_EASY, Category.SCIENCE);
        addQuestion(q19);
        Question q20 = new Question("What is the largest planet?",
                "Mercury", "Jupiter", "Saturn", 2,
                Question.DIFFICULTY_EASY, Category.SCIENCE);
        addQuestion(q20);
        Question q21 = new Question("What is the largest land animal alive today?",
                "Giraffe", "Grizzly Bear", "African Elephant", 3,
                Question.DIFFICULTY_EASY, Category.SCIENCE);
        addQuestion(q21);
        Question q22 = new Question("What is the smallest unit of matter?",
                "An atom", "Molecule", "Cells", 1,
                Question.DIFFICULTY_MEDIUM, Category.SCIENCE);
        addQuestion(q22);
        Question q23 = new Question("How many elements are on the periodic table?",
                "128", "118", "108", 2,
                Question.DIFFICULTY_MEDIUM, Category.SCIENCE);
        addQuestion(q23);
        Question q24 = new Question("What is the nearest planet to the sun?",
                "Earth", "Mercury", "Mars", 2,
                Question.DIFFICULTY_MEDIUM, Category.SCIENCE);
        addQuestion(q24);
        Question q25 = new Question("What is the rarest bloodtype?",
                "O Negative", "AB Negative", "O Positive", 2,
                Question.DIFFICULTY_HARD, Category.SCIENCE);
        addQuestion(q25);
        Question q26 = new Question("Which scientist proposed the 3 laws of motion?",
                "Steven Hawking", "Albert Einstein", "Isaac Newton", 3,
                Question.DIFFICULTY_HARD, Category.SCIENCE);
        addQuestion(q26);
        Question q27 = new Question("How many bones do sharks have in total?",
                "0", "348", "1076", 1,
                Question.DIFFICULTY_HARD, Category.SCIENCE);
        addQuestion(q27);



    }
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }


    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);


        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};
        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        //QUESTION RETRIEVAL
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
