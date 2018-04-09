package com.haha.OM;

/**
 * Created by arun.chauhan on 29/08/16.
 */
public enum DataType {

    STRING("character"),
    INT("integer"),
    DOUBLE("double"),
    FACTOR("factor"),
    ARRAY(""),  // List of Objects (Integer/String etc. )
    BYTEARRAY("bytearray"),
    DATAFRAME(""),
    BOOLEAN("logical"),
    VAR_NAME(""), // for assigning one variable to another. e.g fulldata <- train_df
    MODEL("");

    private final String rType;

    DataType(String rType) {
        this.rType = rType;
    }

    public String getRType() {
        return this.rType;
    }

    public static DataType getDataTypeFromRType(String rType) {
        if (rType.equalsIgnoreCase(STRING.getRType())) {
            return DataType.STRING;
        } else if (rType.equalsIgnoreCase(INT.getRType())) {
            return DataType.INT;
        } else if (rType.equalsIgnoreCase(DOUBLE.getRType())) {
            return DataType.DOUBLE;
        } else if (rType.equalsIgnoreCase(FACTOR.getRType())) {
            return DataType.FACTOR;
        } else if (rType.equalsIgnoreCase(BOOLEAN.getRType())) {
            return DataType.BOOLEAN;
        } else {
            throw new RuntimeException("Invalid Rtype: " + rType);
        }
    }
}
