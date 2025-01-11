$(document).ready(function () {
    // Fetch data from the API
    $.ajax({
        url: 'http://localhost:8081/wines', // Adjust the URL if necessary
        method: 'GET',
        success: function (data) {
            let rows = '';
            data.forEach(wine => {
                rows += `<tr>
                            <td>${wine.name}</td>
                            <td>${wine.year}</td>
                            <td>${wine.grapes}</td>
                            <td>${wine.country}</td>
                            <td>${wine.region}</td>
                        </tr>`;
            });
            $('#wineTable tbody').html(rows);
        },
        error: function (err) {
            console.error('Error fetching data:', err);
        }
    });
});

