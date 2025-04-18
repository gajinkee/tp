package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.PREFIXES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEVICEINFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ORGID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.Prefix;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class AttributeContainsKeywordsPredicate implements Predicate<Person> {
    private final ArgumentMultimap attributeKeywordMap;

    public AttributeContainsKeywordsPredicate(ArgumentMultimap attributeKeywordMap) {
        this.attributeKeywordMap = attributeKeywordMap;
    }

    @Override
    public boolean test(Person person) {
        for (Prefix prefix : PREFIXES) {
            if (attributeKeywordMap.getAllValues(prefix).stream().anyMatch(keyword -> {
                if (prefix == PREFIX_NAME) {
                    return StringUtil.containsSubstringIgnoreCase(person.getName().fullName, keyword);
                } else if (prefix == PREFIX_EMAIL) {
                    return StringUtil.containsSubstringIgnoreCase(person.getEmail().value, keyword);
                } else if (prefix == PREFIX_PHONE) {
                    return StringUtil.containsSubstringIgnoreCase(person.getPhone().value, keyword);
                } else if (prefix == PREFIX_ADDRESS) {
                    return StringUtil.containsSubstringIgnoreCase(person.getAddress().value, keyword);
                } else if (prefix == PREFIX_STATUS) {
                    return StringUtil.containsSubstringIgnoreCase(person.getStatus().toString(), keyword);
                } else if (prefix == PREFIX_ORGID) {
                    return StringUtil.containsSubstringIgnoreCase(person.getOrgID().value, keyword);
                } else if (prefix == PREFIX_DEVICEINFO) {
                    return StringUtil.containsSubstringIgnoreCase(person.getDeviceInfo().deviceInfo, keyword);
                } else if (prefix == PREFIX_TAG) {
                    return person.getTags().stream()
                            .anyMatch(tag -> StringUtil.containsSubstringIgnoreCase(tag.tagName, keyword));
                }

                assert false : String.format("The prefix %s is not handled in the predicate", prefix);
                return false;
            })) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttributeContainsKeywordsPredicate)) {
            return false;
        }

        AttributeContainsKeywordsPredicate otherAttributeContainsKeywordsPredicate =
                (AttributeContainsKeywordsPredicate) other;
        return attributeKeywordMap.equals(otherAttributeContainsKeywordsPredicate.attributeKeywordMap);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("attributeKeywordMap", attributeKeywordMap).toString();
    }
}
