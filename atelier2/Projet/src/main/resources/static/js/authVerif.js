(function() {
    // Effectue une requête vers l'endpoint pour obtenir les informations de l'utilisateur courant
    fetch('http://localhost:8080/currentUser')
        .then(response => response.json())
        .then(data => {
            // Vérifiez ici si la réponse est valide
            if (!data || data.id == null) {
                window.location.href = '/index.html';
            } else {
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
})();