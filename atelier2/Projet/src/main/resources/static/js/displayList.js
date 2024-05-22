(function() {
    fetch('http://localhost:8080/card/my')
        .then(response => response.json())
        .then(data => {
            if (data && Array.isArray(data)) {
                const tableBody = document.getElementById('list-of-card');
                tableBody.innerHTML = ''; // Clear any existing rows

                data.forEach(card => {
                    const row = document.createElement('tr');
                    row.className = 'clickable-row';
                    row.setAttribute('data-id', card.id);

                    const nameCell = document.createElement('th');
                    nameCell.scope = 'row';
                    nameCell.innerText = card.name;
                    row.appendChild(nameCell);

                    const descriptionCell = document.createElement('td');
                    descriptionCell.innerText = card.description;
                    row.appendChild(descriptionCell);

                    const famillyCell = document.createElement('td');
                    famillyCell.innerText = card.familly;
                    row.appendChild(famillyCell);

                    const affinityCell = document.createElement('td');
                    affinityCell.innerText = card.affinity;
                    row.appendChild(affinityCell);

                    const energyCell = document.createElement('td');
                    energyCell.innerText = card.energy;
                    row.appendChild(energyCell);

                    const hpCell = document.createElement('td');
                    hpCell.innerText = card.hp;
                    row.appendChild(hpCell);

                    const priceCell = document.createElement('td');
                    priceCell.innerText = card.price;
                    row.appendChild(priceCell);

                    tableBody.appendChild(row);
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