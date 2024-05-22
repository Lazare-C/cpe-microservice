window.addEventListener('load', (event) => {
    const table = document.getElementById('tableau');
    const rows = table.getElementsByTagName('tr');

    for (let i = 1; i < rows.length; i++) { // Commencer à 1 pour ignorer l'en-tête
        rows[i].addEventListener('mouseover', function () {
            this.style.backgroundColor = '#0C84FF';
            this.style.cursor = 'pointer';
            this.style.color = 'white'; // Optionnel : change également la couleur du texte pour une meilleure lisibilité
        });
        rows[i].addEventListener('mouseout', function () {
            this.style.backgroundColor = '';
            this.style.color = ''; // Optionnel : réinitialise la couleur du texte
        });
    }
});

$(document).ready(function() {
    $('tr.clickable-row').on('click', function() {
        const id = $(this).data('id');
        console.log(id);
        $.ajax({
            url: '/list/' + id,
            method: 'GET',
            success: function(response) {
                console.log(response);
                $('#detailsPokemon').html(response);
            },
            error: function(error) {
                console.log('Error:', error);
            }
        });
    });
});
