// Client-side checks for the signup form (server-side validation is still enforced).
document.addEventListener('DOMContentLoaded', function () {
    var form = document.getElementById('registerForm');
    if (form) {
        var password = document.getElementById('password');
        var confirmField = document.getElementById('confirmPassword');
        var msg = document.getElementById('confirmMsg');

        function check() {
            if (confirmField.value && password.value !== confirmField.value) {
                msg.textContent = 'Passwords do not match';
                return false;
            }
            msg.textContent = '';
            return true;
        }
        password.addEventListener('input', check);
        confirmField.addEventListener('input', check);

        form.addEventListener('submit', function (e) {
            if (password.value.length < 6) {
                e.preventDefault();
                alert('Password must be at least 6 characters long.');
                return;
            }
            if (!check()) {
                e.preventDefault();
            }
        });
    }

    // Ask for confirmation before deleting anything
    document.querySelectorAll('a[href*="/remove-"]').forEach(function (link) {
        link.addEventListener('click', function (e) {
            if (!confirm('Are you sure you want to delete this record?')) {
                e.preventDefault();
            }
        });
    });
});
