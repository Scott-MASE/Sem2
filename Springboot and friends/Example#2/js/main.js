$(document).ready(function () {
    const users = [
        { name: "Alice", age: 25 },
        { name: "Bob", age: 30 },
        { name: "Charlie", age: 22 }
    ];

    const products = [
        { name: "Laptop", price: 999 },
        { name: "Phone", price: 499 },
        { name: "Tablet", price: 299 }
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

    function renderProducts(products) {
        let output = "";
        products.forEach(product => {
            output += templates.productRow
                .replace("{{name}}", product.name)
                .replace("{{price}}", product.price);
        });

        $("#product-container").html(output);
    }

    renderUsers(users);
    renderProducts(products);
});

