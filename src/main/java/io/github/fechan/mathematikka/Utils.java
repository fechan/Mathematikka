package io.github.fechan.mathematikka;

/**
 * Class with utility functions
 */
public final class Utils {
    /**
     * Sanitizes a query for WolframAlpha to prevent Mathematica code injection
     * @param query query to sanitize
     * @return sanitized query
     */
    public static String sanitizeQuery(String query) {
        return query.replaceAll("([\\\\\"])", "\\\\$1"); //replaces \ with \\ and " with \"
    }
}