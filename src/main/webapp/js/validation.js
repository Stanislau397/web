$(document).ready(function () {
    $('#email').keyup(function () {
        validateEmail();
    });
});

$(document).ready(function () {
    $('#password').keyup(function () {
        validatePassword();
    });
});

$(document).ready(function () {
    $('#confirm_password').keyup(function () {
        validateConfirmPassword();
    });
});

function validateEmail() {
    var email = $('#email').val();
    const re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if (!re.test(email)) {
        $("#email_error").html("Invalid email data!").css("color", "red");
        $("#submit").attr('disabled', true);
    } else {
        $("#email_error").html(" ").hide();
        $("#submit").attr('disabled', false);
    }
}

function validateUserName() {
    var isValid = true;
    var userName = $('#user_name').val();
    var re = /^(?=[a-z_\d]*[a-z])[a-z_\d]{6,}$/
    if (userName.length < 5 || userName.length > 25) {
        $("#name_error").html("Should be at least 5 characters").css("color", "red");
        $("#submit").attr('disabled', true);
    }  else {
        $("#name_error").html(" ").hide();
        $("#submit").attr('disabled', false);
    }
}

function validatePassword() {
    var password = $("#password").val();
    var re = /(?=.*[A-Z])/
    if (password.length < 6) {
        $("#password_error").html("Should be at least 6 characters").css("color", "red");
        $("#submit").attr('disabled', true);
    } else if (!re.test(password)) {
        $("#password_error").html("Should contain one capital letter").css("color", "red");
        $("#submit").attr('disabled', true);
    } else {
        $("#password_error").html(" ").hide();
        $("#submit").attr('disabled', false);
    }
}

function validateConfirmPassword() {
    var password = $("#password").val();
    var confirmPassword = $("#confirm_password").val();

    if (password !== confirmPassword) {
        $("#confirm_error").html("Confirm password doesnt match password").css("color", 'red');
        $("#submit").attr('disabled', true);
    } else {
        $("#confirm_error").html(" ").hide();
        $("#submit").attr('disabled', false);
    }
}

