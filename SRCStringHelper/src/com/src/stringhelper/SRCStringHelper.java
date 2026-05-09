package com.src.stringhelper;

import com.google.appinventor.components.annotations.SimpleFunction;

import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;

public class SRCStringHelper extends AndroidNonvisibleComponent {

    public SRCStringHelper(ComponentContainer container) {
        super(container.$form());
    }

    @SimpleFunction(description = "Count total words")
    public int CountWords(String text) {

        if (text == null || text.trim().isEmpty()) {
            return 0;
        }

        return text.trim().split("\\s+").length;
    }

    @SimpleFunction(description = "Reverse text")
    public String ReverseText(String text) {

        if (text == null) {
            return "";
        }

        return new StringBuilder(text).reverse().toString();
    }

    @SimpleFunction(description = "Check palindrome")
    public boolean IsPalindrome(String text) {

        if (text == null || text.isEmpty()) {
            return false;
        }

        String cleaned = text.toLowerCase().replaceAll("\\s+", "");

        String reversed = new StringBuilder(cleaned)
                .reverse()
                .toString();

        return cleaned.equals(reversed);
    }
}
