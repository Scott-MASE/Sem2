/*$(document).ready(function () {
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
            // Initialize DataTable
            $('#wineTable').DataTable();
        },
        error: function (err) {
            console.error('Error fetching data:', err);
        }
    });
});*/

$(document).ready(function () {
    let wineTable;
    let apiUrl = 'http://localhost:8081/wines';

    // Initialize DataTable
    function initializeTable() {
        wineTable = $('#wineTable').DataTable({
            ajax: {
                url: apiUrl,
                dataSrc: '',
            },
            columns: [
                { data: 'name' },
                { data: 'year' },
                { data: 'grapes' },
                { data: 'country' },
                { data: 'region' },
                {
                    data: null,
                    render: function (data) {
                        return `
                            <button class="btn btn-warning btn-sm edit-btn" data-id="${data.id}">Edit</button>
                            <button class="btn btn-danger btn-sm delete-btn" data-id="${data.id}">Delete</button>
                        `;
                    },
                },
            ],
        });
    }

    initializeTable();
     // Open the Delete Confirmation Modal
    $('#wineTable').on('click', '.delete-btn', function () {
        wineIdToDelete = $(this).data('id'); // Get the ID of the wine to delete
        $('#deleteWineModal').modal('show'); // Show the delete confirmation modal
    });
    
     // Confirm Deletion
    $('#confirmDeleteBtn').click(function () {
        if (wineIdToDelete) {
            $.ajax({
                url: `${apiUrl}/${wineIdToDelete}`,
                method: 'DELETE',
                success: function () {
                    $('#deleteWineModal').modal('hide'); // Hide the modal after deletion
                    wineTable.ajax.reload(); // Reload the table data
                    wineIdToDelete = null; // Reset the deletion ID
                },
                error: function () {
                    alert('An error occurred while deleting the wine.');
                },
            });
        }
    });
//});
    
     // Open modal for adding new wine
    $('#addWineBtn').click(function () {
        $('#wineForm')[0].reset();
        $('#wineId').val('');
        $('#wineModalLabel').text('Add Wine');
        $('#wineModal').modal('show');
    });

    // Open modal for editing wine
    $('#wineTable').on('click', '.edit-btn', function () {
        const id = $(this).data('id');
        $.get(`${apiUrl}/${id}`, function (wine) {
            $('#wineId').val(wine.id);
            $('#wineName').val(wine.name);
            $('#wineYear').val(wine.year);
            $('#wineGrapes').val(wine.grapes);
            $('#wineCountry').val(wine.country);
            $('#wineRegion').val(wine.region);
            $('#wineModalLabel').text('Edit Wine');
            $('#wineModal').modal('show');
        });
    });

    // Save (Create/Update) wine
    $('#saveWineBtn').click(function () {
        const wine = {
            id: $('#wineId').val(),
            name: $('#wineName').val(),
            year: $('#wineYear').val(),
            grapes: $('#wineGrapes').val(),
            country: $('#wineCountry').val(),
            region: $('#wineRegion').val(),
        };

        if (wine.id) {
            // Update
            $.ajax({
                url: `${apiUrl}/${wine.id}`,
                method: 'PUT',
                contentType: 'application/json',
                data: JSON.stringify(wine),
                success: function () {
                    $('#wineModal').modal('hide');
                    wineTable.ajax.reload();
                },
            });
        } else {
            // Create
            $.ajax({
                url: apiUrl,
                method: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(wine),
                success: function () {
                    $('#wineModal').modal('hide');
                    wineTable.ajax.reload();
                },
            });
        }
    });
    
    });