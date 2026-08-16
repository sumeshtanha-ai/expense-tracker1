package com.sumesh.budgettracker.notifications

data class ParsedTransaction(val amount: Double, val isDebit: Boolean)

object TransactionParser {

    private val ippbDebitRegex = Regex(
        """Debit\s+Rs\.?\s?([0-9]+(?:\.[0-9]{1,2})?)\s+for\s+UPI""",
        RegexOption.IGNORE_CASE
    )
    private val ippbCreditRegex = Regex(
        """Credit\s+Rs\.?\s?([0-9]+(?:\.[0-9]{1,2})?)\s+for\s+UPI""",
        RegexOption.IGNORE_CASE
    )

    private val genericDebitRegex = Regex(
        """(?:debited|debit|spent|withdrawn|paid)\D{0,15}(?:rs\.?|inr|₹)\s?([0-9]+(?:,[0-9]{2,3})*(?:\.[0-9]{1,2})?)""",
        RegexOption.IGNORE_CASE
    )
    private val genericDebitRegexAmountFirst = Regex(
        """(?:rs\.?|inr|₹)\s?([0-9]+(?:,[0-9]{2,3})*(?:\.[0-9]{1,2})?)\D{0,15}(?:debited|debit|spent|withdrawn|paid)""",
        RegexOption.IGNORE_CASE
    )
    private val genericCreditRegex = Regex(
        """(?:credited|credit|received)\D{0,15}(?:rs\.?|inr|₹)\s?([0-9]+(?:,[0-9]{2,3})*(?:\.[0-9]{1,2})?)""",
        RegexOption.IGNORE_CASE
    )
    private val genericCreditRegexAmountFirst = Regex(
        """(?:rs\.?|inr|₹)\s?([0-9]+(?:,[0-9]{2,3})*(?:\.[0-9]{1,2})?)\D{0,15}(?:credited|credit|received)""",
        RegexOption.IGNORE_CASE
    )

    fun parse(text: String): ParsedTransaction? {
        ippbDebitRegex.find(text)?.let {
            return ParsedTransaction(amount = parseAmount(it.groupValues[1]), isDebit = true)
        }
        ippbCreditRegex.find(text)?.let {
            return ParsedTransaction(amount = parseAmount(it.groupValues[1]), isDebit = false)
        }
        genericDebitRegex.find(text)?.let {
            return ParsedTransaction(amount = parseAmount(it.groupValues[1]), isDebit = true)
        }
        genericDebitRegexAmountFirst.find(text)?.let {
            return ParsedTransaction(amount = parseAmount(it.groupValues[1]), isDebit = true)
        }
        genericCreditRegex.find(text)?.let {
            return ParsedTransaction(amount = parseAmount(it.groupValues[1]), isDebit = false)
        }
        genericCreditRegexAmountFirst.find(text)?.let {
            return ParsedTransaction(amount = parseAmount(it.groupValues[1]), isDebit = false)
        }
        return null
    }

    private fun parseAmount(raw: String): Double {
        return raw.replace(",", "").toDoubleOrNull() ?: 0.0
    }
}
