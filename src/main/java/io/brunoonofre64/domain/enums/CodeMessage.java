package io.brunoonofre64.domain.enums;

public enum CodeMessage {
    DTO_NULL_OR_IS_EMPTY("DTO_NULL_OR_IS_EMPTY"),
    UUID_NOT_FOUND_OR_NULL("UUID_NOT_FOUND_OR_NULL"),
    LIST_IS_EMPTY("LIST_IS_EMPTY"),
    ENTITY_ISNULL("ENTITY_ISNULL");
    private String value;
    CodeMessage(String value) {
        this.value = value;
    }
}
