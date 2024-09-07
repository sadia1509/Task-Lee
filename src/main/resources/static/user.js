$(document).ready(function () {
    fetchUsers();

    // Fetch Users
    function fetchUsers() {
        $.ajax({
            url: '/users',
            type: 'GET',
            success: function (data) {
                $('#userTableBody').empty();
                data.forEach(function (user) {
                    $('#userTableBody').append(
                        `<tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.email}</td>
                            <td>
                                <button class="btn btn-warning btn-sm me-1" onclick="editUser(${user.id}, '${user.name}', '${user.email}')">Edit</button>
                                <button class="btn btn-danger btn-sm" onclick="deleteUser(${user.id})">Delete</button>
                            </td>
                         </tr>`
                    );
                });
            }
        });
    }

    // Save or Update User
    $('#userForm').submit(function (e) {
        e.preventDefault();  // // Prevents the form from submitting and refreshing the page.
        const id = $('#userId').val();
        const user = {
            name: $('#name').val(),
            email: $('#email').val()
        };

        if (id) {
            // Update User
            $.ajax({
                url: `/users/${id}`,
                type: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(user),
                success: function () {
                    fetchUsers();
                    $('#userForm')[0].reset();
                }
            });
        } else {
            // Create User
            $.ajax({
                url: '/users',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(user),
                success: function () {
                    fetchUsers();
                    $('#userForm')[0].reset();
                }
            });
        }
    });

    // Edit User  (function defined globally on the window object)
    window.editUser = function (id, name, email) {
        $('#userId').val(id);
        $('#name').val(name);
        $('#email').val(email);
    };

    // Delete User  (function defined globally on the window object)
    window.deleteUser = function (id) {
        $.ajax({
            url: `/users/${id}`,
            type: 'DELETE',
            success: function () {
                fetchUsers();
            }
        });
    };
});