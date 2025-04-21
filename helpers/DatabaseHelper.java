package com.peerlink.peerlinkapp.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import com.peerlink.peerlinkapp.models.Message;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "peerlink.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "email TEXT," +
                "password TEXT," +
                "role TEXT," +
                "department TEXT," +
                "credentials TEXT," +
                "isApproved INTEGER)");

        db.execSQL("CREATE TABLE sessions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "student_id INTEGER," +
                "tutor_id INTEGER," +
                "subject TEXT," +
                "date_time TEXT," +
                "status TEXT)");

        db.execSQL("CREATE TABLE quizzes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tutor_id INTEGER," +
                "title TEXT)");

        db.execSQL("CREATE TABLE questions (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "quiz_id INTEGER," +
                "question_text TEXT," +
                "correct_answer TEXT)");

        db.execSQL("CREATE TABLE results (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "student_id INTEGER," +
                "quiz_id INTEGER," +
                "score INTEGER," +
                "timestamp TEXT)");

        db.execSQL("CREATE TABLE messages (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sender_id INTEGER," +
                "receiver_id INTEGER," +
                "content TEXT," +
                "timestamp TEXT)");

        db.execSQL("CREATE TABLE documents (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "sender_id INTEGER," +
                "receiver_id INTEGER," +
                "file_path TEXT," +
                "file_name TEXT," +
                "timestamp TEXT)");

        db.execSQL("INSERT INTO users (username, email, password, role, department, credentials, isApproved) " +
                "VALUES ('Admin', 'admin@peerlink.com', '" + hashPassword("admin123") + "', 'admin', 'Admin Office', '', 1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS sessions");
        db.execSQL("DROP TABLE IF EXISTS quizzes");
        db.execSQL("DROP TABLE IF EXISTS questions");
        db.execSQL("DROP TABLE IF EXISTS results");
        db.execSQL("DROP TABLE IF EXISTS messages");
        db.execSQL("DROP TABLE IF EXISTS documents");
        onCreate(db);
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return password;
        }
    }

    public boolean registerUser(String username, String email, String password, String role, String department) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (isEmailExists(email)) return false;

        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("email", email);
        values.put("password", hashPassword(password));
        values.put("role", role);
        values.put("department", department);
        values.put("credentials", "");
        values.put("isApproved", role.equals("tutor") ? 0 : 1);

        return db.insert("users", null, values) != -1;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM users WHERE email = ?", new String[]{email});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    public String validateLogin(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String hashedPassword = hashPassword(password);
        Cursor cursor = db.rawQuery("SELECT role FROM users WHERE email = ? AND password = ? AND isApproved = 1",
                new String[]{email, hashedPassword});
        if (cursor.moveToFirst()) {
            String role = cursor.getString(0);
            cursor.close();
            return role;
        }
        cursor.close();
        return null;
    }

    public String[] getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT username, email, role, department FROM users WHERE email=?", new String[]{email});
        if (cursor.moveToFirst()) {
            String[] data = {
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3)
            };
            cursor.close();
            return data;
        }
        cursor.close();
        return null;
    }

    public int getUserIdByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM users WHERE email = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            cursor.close();
            return id;
        }
        cursor.close();
        return -1;
    }
    public boolean sendMessage(int senderId, int receiverId, String content, String timestamp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sender_id", senderId);
        values.put("receiver_id", receiverId);
        values.put("content", content);
        values.put("timestamp", timestamp);

        long result = db.insert("messages", null, values);
        return result != -1;
    }

    public int getLastInsertedMessageId() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT last_insert_rowid()", null);
        int id = -1;
        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
        }
        cursor.close();
        return id;
    }
    public ArrayList<String> getPendingTutors() {
        ArrayList<String> tutorList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT email FROM users WHERE role = 'tutor' AND isApproved = 0", null);
        if (cursor.moveToFirst()) {
            do {
                String email = cursor.getString(0);
                tutorList.add(email);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return tutorList;
    }
    public ArrayList<Message> getMessagesForTutorDetailed(String tutorEmail) {
        ArrayList<Message> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Ensure email is valid
        if (tutorEmail == null || tutorEmail.isEmpty()) return list;

        // Fetch messages where the receiver is the tutor with this email
        Cursor cursor = db.rawQuery(
                "SELECT m.id, m.sender_id, m.receiver_id, m.content, m.timestamp " +
                        "FROM messages m " +
                        "JOIN users t ON m.receiver_id = t.id " +
                        "WHERE t.email = ?", new String[]{tutorEmail});

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int senderId = cursor.getInt(1);
            int receiverId = cursor.getInt(2);
            String content = cursor.getString(3);
            String timestamp = cursor.getString(4);

            list.add(new Message(id, senderId, receiverId, content, timestamp));
        }

        cursor.close();
        return list;
    }

    public void updateTutorApproval(String email, boolean approve) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("isApproved", approve ? 1 : 0);
        db.update("users", values, "email=?", new String[]{email});
    }

    public void deleteUserByEmail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("users", "email=?", new String[]{email});
    }

    public ArrayList<String> getTutorsByDepartment(String department) {
        ArrayList<String> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT username FROM users WHERE role='tutor' AND department=? AND isApproved=1", new String[]{department});
        while (cursor.moveToNext()) {
            list.add(cursor.getString(0));
        }
        cursor.close();
        return list;
    }

    public boolean insertQuestion(int quizId, String question, String answer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("quiz_id", quizId);
        values.put("question_text", question);
        values.put("correct_answer", answer);
        return db.insert("questions", null, values) != -1;
    }

    public int insertQuizAndGetId(int tutorId, String title) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tutor_id", tutorId);
        values.put("title", title);
        long id = db.insert("quizzes", null, values);
        return (int) id;
    }
    public ArrayList<Message> getMessagesBetweenUsers(int userId1, int userId2) {
        ArrayList<Message> messages = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT id, sender_id, receiver_id, content, timestamp FROM messages " +
                "WHERE (sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?) " +
                "ORDER BY timestamp ASC";

        Cursor cursor = db.rawQuery(query, new String[]{
                String.valueOf(userId1),
                String.valueOf(userId2),
                String.valueOf(userId2),
                String.valueOf(userId1)
        });

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                int senderId = cursor.getInt(1);
                int receiverId = cursor.getInt(2);
                String content = cursor.getString(3);
                String timestamp = cursor.getString(4);

                messages.add(new Message(id, senderId, receiverId, content, timestamp));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return messages;
    }

    public void approveSessionRequest(int messageId) {
        deleteSessionRequest(messageId);
    }

    public void declineSessionRequest(int messageId) {
        deleteSessionRequest(messageId);
    }

    private void deleteSessionRequest(int messageId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("messages", "id=?", new String[]{String.valueOf(messageId)});
    }

    public boolean saveMessageRequest(String studentEmail, String tutorEmail, String messageContent) {
        SQLiteDatabase db = this.getWritableDatabase();

        int senderId = getUserIdByEmail(studentEmail);
        int receiverId = getUserIdByEmail(tutorEmail);

        if (senderId == -1 || receiverId == -1) return false;

        ContentValues values = new ContentValues();
        values.put("sender_id", senderId);
        values.put("receiver_id", receiverId);
        values.put("content", messageContent);
        values.put("timestamp", String.valueOf(System.currentTimeMillis()));

        return db.insert("messages", null, values) != -1;
    }
}
