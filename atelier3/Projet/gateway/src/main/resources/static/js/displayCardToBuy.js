let cardId;

(function() {
    fetch('http://localhost:8080/card/sellList')
        .then(response => response.json())
        .then(data => {
            if (data && Array.isArray(data)) {
                const tableBody = document.getElementById('list-of-card');
                tableBody.innerHTML = ''; // Clear any existing rows

                data.forEach(card => {
                    const row = document.createElement('tr');
                    row.className = 'clickable-row';
                    row.setAttribute('data-id', card.id || 'unset');

                    const nameCell = document.createElement('th');
                    nameCell.scope = 'row';
                    nameCell.innerText = card.name || 'unset';
                    row.appendChild(nameCell);

                    const descriptionCell = document.createElement('td');
                    descriptionCell.innerText = card.description || 'unset';
                    row.appendChild(descriptionCell);

                    const famillyCell = document.createElement('td');
                    famillyCell.innerText = card.familly || 'unset';
                    row.appendChild(famillyCell);

                    const affinityCell = document.createElement('td');
                    affinityCell.innerText = card.affinity || 'unset';
                    row.appendChild(affinityCell);

                    const energyCell = document.createElement('td');
                    energyCell.innerText = card.energy || 'unset';
                    row.appendChild(energyCell);

                    const hpCell = document.createElement('td');
                    hpCell.innerText = card.hp || 'unset';
                    row.appendChild(hpCell);

                    const priceCell = document.createElement('td');
                    priceCell.innerText = card.price || 'unset';
                    row.appendChild(priceCell);

                    tableBody.appendChild(row);
                });
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

                $('tr.clickable-row').on('click', function() {
                    const id = $(this).data('id');
                    if(pokemonContainer != null){
                        pokemonContainer.style.display = "block";
                    }
                    cardId = id;
                    $.ajax({
                        url: '/card/' + id,
                        method: 'GET',
                        success: function(response) {                            
                            // Vérifiez et insérez les données dans les éléments correspondants
                            $('#pokemonId').text(response.id || 'unset');
                            $('#pokemonName').text(response.name || 'unset');
                            $('#pokemonDefense').text(response.defense || 'unset');
                            $('#image').attr('src', response.image || 'https://www.pokepedia.fr/images/thumb/7/76/Pikachu-DEPS.png/800px-Pikachu-DEPS.png');
                            $('#pokemonDescription').text(response.description || 'unset');
                            let buyButton = document.getElementById("Buy-button");
                            if(response.userBoId == localStorage.getItem('id')){
                                buyButton.style.display = "none";
                            } else {
                                buyButton.style.display = "block";
                            }
                        },
                        error: function(error) {
                            console.log('Error:', error);
                        }
                    });
                });
                let pokemonContainer = document.getElementById("detailsPokemon");
                if(pokemonContainer != null){
                    pokemonContainer.style.display = "none";
                }

                $('#Buy-button').on('click', function() {
                    if(localStorage.getItem('balance') >= 1000){

                        const formData = new FormData();
                        formData.append('id', cardId);
                        formData.append('amount', 1000);
                        $.ajax({
                            url: '/wf/card/buy/'+cardId, // URL de l'endpoint de vente des cartes
                            method: 'POST',
                            processData: false, // Ne pas traiter les données automatiquement
                            contentType: false, // Ne pas définir automatiquement le type de contenu
                            data: formData, // Envoyer les données FormData
                            success: function(response) {
                                console.log('achetée avec succès:', response);
                                window.location.href = '/cardGame/welcomePage.html';
                            },
                            error: function(error) {
                                console.log('Erreur lors de l\'achat:', error);
                            }
                        });

                    } else {
                        alert("Pas assez d'argent")
                    }
                });
            } else {
                console.error('Invalid data format:', data);
            }
        })
        .catch(error => {
            console.error('Erreur:', error);
            alert('Erreur lors de la récupération des cartes.');
        });
})();