package com.src.stringhelper;

import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;

public class SRCStringHelper extends AndroidNonvisibleComponent {

    public SRCStringHelper(ComponentContainer container) {
        super(container.$form());
    }

    @SimpleFunction(description = "Text mein total words count karke return karta hai.")
    public int CountWords(String text) {
        if (text == null || text.trim().isEmpty()) return 0;
        return text.trim().split("\\s+").length;
    }

    @SimpleFunction(description = "Text ko ulta (reverse) karke return karta hai.")
    public String ReverseText(String text) {
        if (text == null) return "";
        return new StringBuilder(text).reverse().toString();
    }

    @SimpleFunction(description = "Check karta hai ki text palindrome hai ya nahi. " +
            "Palindrome = ulta-seedha same hota hai. Jaise: MADAM, RACECAR. " +
            "Returns true/false.")
    public boolean IsPalindrome(String text) {
        if (text == null || text.isEmpty()) return false;
        String cleaned = text.toLowerCase().replaceAll("\\s+", "");
        String reversed = new StringBuilder(cleaned).reverse().toString();
        return cleaned.equals(reversed);
    }
}
