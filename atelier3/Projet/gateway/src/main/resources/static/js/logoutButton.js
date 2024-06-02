(function() {

    logoutButton = document.getElementById("Logout-button");
    // Ajouter un événement onclick au bouton
    logoutButton.onclick = function() {
        // Appeler l'API localhost:8080/logout
        fetch('http://localhost:8080/account/logout', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
                // Ajoutez d'autres en-têtes si nécessaire
            }
        })
        .then(response => {
            // Rediriger vers index.html après avoir appelé l'API
            window.location.href = '/index.html';
        })
        .catch(error => {
            console.error('Erreur lors de la déconnexion :', error);
            // Gérer les erreurs si nécessaire
        });
    };
})();
