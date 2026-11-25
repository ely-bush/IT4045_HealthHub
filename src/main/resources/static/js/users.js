async function loadUsers() {

    try {

        const response = await fetch('/users');

        const users = await response.json();

        const table =
            document.getElementById('userTable');

        table.innerHTML = '';

        users.forEach(user => {

            table.innerHTML += `
                <tr>

                    <td>${user.name}</td>

                    <td>${user.email}</td>

                    <td>

                        <button
                            onclick="openEditUser(
                                '${user.id}',
                                '${user.name}',
                                '${user.email}'
                            )">

                            Edit

                        </button>

                        <button
                            onclick="deleteUser('${user.id}')">

                            Delete

                        </button>

                    </td>

                </tr>
            `;
        });

    } catch (error) {

        console.error(error);

    }
}

async function createUser() {

    const name =
        document.getElementById('userName').value.trim();

    const email =
        document.getElementById('userEmail').value.trim();

    if (!name) {

        alert('Name is required.');

        return;
    }

    if (!isValidEmail(email)) {

        alert('Please enter a valid email address.');

        return;
    }

    const user = {
        name,
        email
    };

    try {

        const response = await fetch('/users', {

            method: 'POST',

            headers: {
                'Content-Type': 'application/json'
            },

            body: JSON.stringify(user)

        });

        if (!response.ok) {
            throw new Error();
        }

        document.getElementById('userName').value = '';
        document.getElementById('userEmail').value = '';

        loadUsers();

    } catch (error) {

        console.error(error);

        alert('Unable to create user.');

    }
}

function openEditUser(id, name, email) {

    document.getElementById('editUserPanel')
        .style.display = 'block';

    document.getElementById('editUserId')
        .value = id;

    document.getElementById('editUserName')
        .value = name;

    document.getElementById('editUserEmail')
        .value = email;
}

function closeEditUser() {

    document.getElementById('editUserPanel')
        .style.display = 'none';
}

async function saveUserChanges() {

    const id =
        document.getElementById('editUserId').value;

    const name =
        document.getElementById('editUserName').value;

    const email =
        document.getElementById('editUserEmail').value;

    try {

        const response =
            await fetch(`/users/${id}`, {

                method: 'PUT',

                headers: {
                    'Content-Type': 'application/json'
                },

                body: JSON.stringify({
                    name,
                    email
                })

            });

        if (!response.ok) {

            throw new Error();

        }

        closeEditUser();

        loadUsers();

    } catch (error) {

        console.error(error);

    }
}

async function deleteUser(id) {

    const confirmed =
        confirm('Delete this user?');

    if (!confirmed) {
        return;
    }

    try {

        const response =
            await fetch(`/users/${id}`, {

                method: 'DELETE'

            });

        if (!response.ok) {

            throw new Error();

        }

        loadUsers();

    } catch (error) {

        console.error(error);

    }
}

function isValidEmail(email) {

    const emailPattern =
        /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    return emailPattern.test(email);
}


document.addEventListener('DOMContentLoaded', () => {

    loadUsers();

    closeEditUser();

});