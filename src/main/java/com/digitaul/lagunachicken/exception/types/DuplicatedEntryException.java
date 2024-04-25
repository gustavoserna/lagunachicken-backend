package com.digitaul.lagunachicken.exception.types;

public class DuplicatedEntryException extends RuntimeException {

    public DuplicatedEntryException (String entry) {
        super("Duplicated entry: " + entry);
    }

}
