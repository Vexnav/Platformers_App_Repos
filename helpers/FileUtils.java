package com.peerlink.peerlinkapp.helpers;


public class FileUtils {
    public static String getFileNameFromPath(String path) {
        return path.substring(path.lastIndexOf('/') + 1);
    }
}