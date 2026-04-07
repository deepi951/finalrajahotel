package com.example.rajahotel;

/**
 * Admin Configuration - Define admin email addresses here
 */
public class AdminConfig {

    // List of admin email addresses
    public static final String[] ADMIN_EMAILS = {
            "keerthuganeshkumar2204@gmail.com",
            // Add more admin emails if needed
    };

    /**
     * Check if an email is an admin email
     */
    public static boolean isAdminEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        for (String adminEmail : ADMIN_EMAILS) {
            if (email.equalsIgnoreCase(adminEmail)) {
                return true;
            }
        }
        return false;
    }
}

