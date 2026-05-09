package com.src.stringhelper;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;

import com.google.appinventor.components.common.ComponentCategory;

import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;

@DesignerComponent(
        version = 1,
        description = "String Helper Extension by SRC",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "assets/icon.png"
)

@SimpleObject(external = true)

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

        String cleaned = text
                .toLowerCase()
                .replaceAll("\\s+", "");

        String reversed = new StringBuilder(cleaned)
                .reverse()
                .toString();

        return cleaned.equals(reversed);
    }
}
