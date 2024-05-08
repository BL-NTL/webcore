/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */
var icon = "<i class='fa fa-times-circle'></i>  ";
$.extend($.validator.messages, {
    required: icon + "Required",
    remote: icon + "Please fix this field",
    email: icon + "Please enter a valid email address",
    url: icon + "Please enter a valid URL",
    date: icon + "Please enter a valid date",
    dateISO: icon + "Please enter a valid date (YYYY-MM-DD)",
    number: icon + "Please enter a valid number",
    digits: icon + "Please enter only digits",
    creditcard: icon + "Please enter a valid credit card number",
    equalTo: icon + "Your input does not match",
    extension: icon + "Please enter a valid extension",
    maxlength: $.validator.format(icon + "Maximum {0} characters allowed"),
    minlength: $.validator.format(icon + "Minimum {0} characters required"),
    rangelength: $.validator.format(icon + "Please enter a value between {0} and {1} characters long"),
    range: $.validator.format(icon + "Please enter a value between {0} and {1}"),
    step: $.validator.format(icon + "Please enter a multiple of {0}"),
    max: $.validator.format(icon + "Please enter a value less than or equal to {0}"),
    min: $.validator.format(icon + "Please enter a value greater than or equal to {0}")
});
