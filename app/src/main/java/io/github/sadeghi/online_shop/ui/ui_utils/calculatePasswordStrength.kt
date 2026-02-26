package io.github.sadeghi.online_shop.ui.ui_utils


fun calculatePasswordStrength(password: String): PasswordStrength {

    if (password.isEmpty()) {
        return PasswordStrength.NONE
    }

    if (password.length < 8) {
        return PasswordStrength.TOO_SHORT
    }

    val hasDigit = password.any { it.isDigit() }
    val hasLetter = password.any { it.isLetter() }
    val hasSpecial = password.any { !it.isLetterOrDigit() }

    return when {
        // فقط عدد یا فقط حروف
        (hasDigit && !hasLetter && !hasSpecial) ||
                (!hasDigit && hasLetter && !hasSpecial) ->
            PasswordStrength.WEAK

        // حروف + عدد (بدون کاراکتر خاص)
        hasDigit && hasLetter && !hasSpecial ->
            PasswordStrength.MEDIUM

        // وجود کاراکتر خاص
        hasSpecial ->
            PasswordStrength.STRONG

        else ->
            PasswordStrength.WEAK
    }
}