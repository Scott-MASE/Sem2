$(document).ready(function () {
    const users = [
        { name: "Alice", age: 25 },
        { name: "Bob", age: 30 },
        { name: "Charlie", age: 22 }
    ];

    function renderUsers(users) {
        let output = "";
        users.forEach(user => {
            output += templates.userCard
                .replace("{{name}}", user.name)
                .replace("{{age}}", user.age);
        });

        $("#user-container").html(output);
    }

    renderUsers(users);
});
