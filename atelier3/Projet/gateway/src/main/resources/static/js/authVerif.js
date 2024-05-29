(function() {
    // Effectue une requête vers l'endpoint pour obtenir les informations de l'utilisateur courant
    fetch('http://localhost:8080/account/currentUser')
        .then(response => response.json())
        .then(data => {
            // Vérifiez ici si la réponse est valide
            if (!data || data.id == null) {
                window.location.href = '/index.html';
            } else {
                localStorage.setItem('id', data.id);
                localStorage.setItem('balance', data.balance);
                document.getElementById("header-name").textContent = data.username;
                if(data.balance == null){
                    document.getElementById("header-user-balance").textContent = 0 + ' $';
                }else {
                    document.getElementById("header-user-balance").textContent = data.balance + ' $';
                }
            }
        })
        .catch(error => {
            // En cas d'erreur, redirige vers la page de login
            console.error('Erreur:', error);
            window.location.href = '/index.html';
    });
    let pokemonContainer = document.getElementById("detailsPokemon");
    if(pokemonContainer != null){
        pokemonContainer.style.display = "none";
    }
})();
